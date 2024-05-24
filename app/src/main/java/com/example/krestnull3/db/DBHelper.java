package com.example.krestnull3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String MY_TABLE = "my_table";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String YEAR = "year";

    public DBHelper(@Nullable Context context) {
        super(context, "example.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + MY_TABLE + "(" + NAME + " TEXT , " + SURNAME + " TEXT , " + YEAR + " INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    public  void DeleALL(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MY_TABLE , null , null);
        db.close();
    }
    /*public void AddOne(Data data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME , data.name);
        cv.put(SURNAME , data.surname);
        cv.put(YEAR , data.year);
        db.insert(MY_TABLE , null , cv);
        db.close();
    }*/
    public List<String> GetAll(){
        List<String> datalist = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(MY_TABLE , null , null , null , null , null , null , null);
        while (cursor.moveToNext()){
            int id = cursor.getColumnIndex(NAME);
            String name = cursor.getString(id);
            datalist.add(name);

        }




        db.close();
        return datalist;
    }
}
