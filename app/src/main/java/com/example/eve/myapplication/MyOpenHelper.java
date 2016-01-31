package com.example.eve.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by eve on 9/23/2015.
 */
public class MyOpenHelper extends SQLiteOpenHelper{
   //Explicit
    public static final String DATABASE_NAME ="my_time.db";
    private static final int DATABASE_VERSION=1;
    private static final String CREATE_TABLE = "create table timeTABLE (_id integer primary key,"+"" +
            "name text,date text,StartHr text,StartMin text,count text,data text,Results text,Count1 text, Count2 text, Count3 text, Count4 text," +
            " Count5 text, Count6 text, Count7 text, Count8 text, Count9 text, Count10 text, Count11 text," +
            " Count12 text, Count13 text, Count14 text, Count15 text, Count16 text, Count17 text, Count18 text," +
            " Count19 text, Count20 text,Time1 text,Time2 text,Time3 text,Time4 text,Time5 text);";



    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }//constructor




    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(CREATE_TABLE);
    }//onCreate





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+CREATE_TABLE);
        onCreate(db);

    }
} //main class
