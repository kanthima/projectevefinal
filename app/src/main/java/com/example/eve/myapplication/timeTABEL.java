package com.example.eve.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.method.DateTimeKeyListener;

/**
 * Created by eve on 9/23/2015.
 */
public class timeTABEL {
    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite,readSQLite;
    public static final String TABLE_TIME = "timeTABLE";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_HR = "StartHr";
    public static final String COLUMN_MIN = "StartMin";
    public static final String COLUMN_COUNT = "count";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_RESULTS = "Results";

    private String[] strlistName;


    public timeTABEL(Context context) {
      objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();


    }//constructor


    public String[] listName() {

         strlistName = null;
        Cursor objCursor = readSQLite.query(TABLE_TIME, new String [] {COLUMN_ID,COLUMN_NAME},null,null,null,null,null);
        objCursor.moveToFirst();
        strlistName = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strlistName[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_NAME));
            objCursor.moveToNext();
        }
        objCursor.close();
        return strlistName;
    }// List Name

    public String searchID(String strName) {

        String strID = null;
        Cursor objCursor = readSQLite.query("timeTABLE",
                new String[]{COLUMN_ID,COLUMN_NAME},
                COLUMN_NAME +"=?",
                new String[]{String.valueOf(strName)},
                null,null,null,null);
        if (objCursor != null) {
            if (objCursor.moveToFirst()) {

                strID = objCursor.getString(0);
            }
        }
        return strID;
    }

    


    public  Cursor readAllData(){

    Cursor objCursor = readSQLite.query(TABLE_TIME, new String[]{COLUMN_ID, COLUMN_NAME,
            COLUMN_DATE, COLUMN_COUNT, COLUMN_DATA,}, null, null, null, null, null);
    if (objCursor != null){
        objCursor.moveToFirst();
    }
    return objCursor;
}//readAllData



    //Add New Value to SQLite
    public long addNewValueToSQLite(String strname,String strdate,Integer intcount,String strdata,String strresults){

        ContentValues objContentValue = new ContentValues();
        objContentValue.put(COLUMN_NAME,strname);
        objContentValue.put(COLUMN_DATE,strdate);
        objContentValue.put(COLUMN_COUNT,intcount);
        objContentValue.put(COLUMN_DATA,strdata);
        objContentValue.put(COLUMN_RESULTS,strresults);


        return writeSQLite.insert(TABLE_TIME, null, objContentValue);
    }//addNewValueSQLite



}//Main Class
