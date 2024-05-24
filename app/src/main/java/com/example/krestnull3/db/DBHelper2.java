package com.example.krestnull3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.LinkedList;

public class DBHelper2 extends SQLiteOpenHelper {
    private static final String FIRSTNAME = "firstname";
    private static final String SECONDNAME = "secondname";
    private static final String RESULT = "result";
    private static final String MY_DB = "my_db";

    public DBHelper2(@Nullable Context context) {
        super(context, MY_DB + ".db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + MY_DB + "(" + FIRSTNAME + " TEXT , " + SECONDNAME + " TEXT , " + RESULT + " TEXT);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public void onDEll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MY_DB, null, null);
        db.close();

    }
    public void AddOne(Data data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FIRSTNAME , data.firstname);
        cv.put(SECONDNAME , data.secondname);
        cv.put(RESULT, data.result);
        db.insert(MY_DB , null , cv);
        db.close();
    }
    public LinkedList<Data> GetAll(){
        LinkedList<Data> datalist = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(MY_DB, null , null , null , null , null , null , null);
        if(cursor.moveToFirst()){
            do{
                int id_1name = cursor.getColumnIndex(FIRSTNAME);
                int id_2name = cursor.getColumnIndex(SECONDNAME);
                int id_res = cursor.getColumnIndex(RESULT);
                Data data = new Data(cursor.getString(id_1name) , cursor.getString(id_2name) ,cursor.getString(id_res));
                datalist.add(data);


            }while(cursor.moveToNext());
        }


        db.close();
        return datalist;
    }
}
