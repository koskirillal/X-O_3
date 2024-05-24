package com.example.krestnull3.db;

public class MyConstans {
    public static final String TABLE_NAME = "new_table";//назване таблицы
    public static final String _ID = "_id";//1колнна
    public static final String FIRSTNAME = "firstname";//имя1
    public static final String SECONDNANE = "seconname";//имя2
    public static final String RESULT = "result";//результат
    public static final String DB_NAME = "my_db.db";//название бд
    public static final int DB_VERSION = 1;//версия
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS" +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY ," +
            FIRSTNAME + " TEXT,"+
            SECONDNANE + " TEXT,"+
            RESULT + " TEXT)";//структура таблицы
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;//дроп


}
