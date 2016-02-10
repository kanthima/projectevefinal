package com.example.eve.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class ShowDetailActivity extends AppCompatActivity {

    private ListView showTimeListView;
    private int IDAnInt;
    private String tag = "masterEVE1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        //Bind Widget
        showTimeListView = (ListView) findViewById(R.id.listView5);

        IDAnInt = getIntent().getIntExtra("ID", 1);
        Log.d(tag, "ID ที่รับได้ ==> " + IDAnInt); // อันนี่ได้ ID ละ

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM timeTABLE WHERE _id='" + Integer.toString(IDAnInt) + "'", null);
        objCursor.moveToFirst();

        int intCount = Integer.parseInt(objCursor.getString(objCursor.getColumnIndex(timeTABEL.COLUMN_COUNT)));
        String[] showTime = new String[intCount];
        int[] addHr = new int[intCount];

        int intStartTime = 0;
        int[] showMin = new int[intCount];
        int[] intAnswerHr = new int[intCount];
        String[] showTimeStrings = new String[intCount];

        //แสดงเวลาที่เพิ่ม ชั่วโมง บวก เวลาเพิ่ม
        intStartTime = Integer.parseInt(objCursor.getString(3));

        for (int i=0;i<intCount;i++) {

            //    if (i != 0) {
            //        addHr[i] = Integer.parseInt(objCursor.getString(7 + i));
            //    } else {
            //        addHr[i] = 0;
            //    }
            addHr[i] = Integer.parseInt(objCursor.getString(7 + i));
            intStartTime += addHr[i];

            intAnswerHr[i] = intStartTime;

            if (intAnswerHr[i]> 24) {
                intAnswerHr[i] = intAnswerHr[i] - 24;
                          }

            Log.d(tag, "showHr = " + intAnswerHr[i]);

            // Get นาที
            showMin[i] = Integer.parseInt(objCursor.getString(4));
            showTimeStrings[i] = "เวลาตรวจ ==> " + Integer.toString(intAnswerHr[i]) + " : " + Integer.toString(showMin[i]);
            Log.d(tag, "showTime " + showTimeStrings[i]);
        }   //for
        objCursor.close();

        //Create ListView
        ResultAdapter objResultAdapter = new ResultAdapter(this, showTimeStrings);
        showTimeListView.setAdapter(objResultAdapter);


    }   // Main Method

    public void clickBackShowData(View view) {
        finish();
    }

}   // Main Class


