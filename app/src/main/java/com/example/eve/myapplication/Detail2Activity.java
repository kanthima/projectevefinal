package com.example.eve.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Detail2Activity extends AppCompatActivity {

    //Explicit
    private TextView dateTextView;
    private ListView dateListView;
    private String strDate, TAG = "eve";
    private String[] strName = null;
    private String[] strCount = null;
    private EditText objEditText;
    private EditText[] countEditTexts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailday);

        bindWidter();

        showDate();

        createListDailyPlan();
    }

    private void createListDailyPlan() {

        try {
            SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("my_time.db", MODE_PRIVATE, null);
            Cursor objcursor = objSqLiteDatabase.rawQuery("SELECT * FROM timeTABLE WHERE date='"+strDate+"'", null);
            objcursor.moveToFirst();
            strName = new String[objcursor.getCount()];
            strCount = new String[objcursor.getCount()];
            Log.d(TAG, "จำนวน Cursor ==> " + Integer.toString(objcursor.getCount()));

            for (int i = 0; i < objcursor.getCount(); i++) {

                strName[i] = objcursor.getString(objcursor.getColumnIndex("name"));
                strCount[i] = objcursor.getString(objcursor.getColumnIndex("count"));
                objcursor.moveToNext();
            }//for

            objcursor.close();

            NameAdapter objNameAdapter = new NameAdapter(strCount, Detail2Activity.this, strName);
            dateListView.setAdapter(objNameAdapter);
            dateListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {


                    //showMyDialog(strName[i],strCount[i]);
                    Intent objIntent = new Intent(Detail2Activity.this,ResultActivity.class);
                    startActivity(objIntent);

                }
            });


        } catch (Exception e) {
            Toast.makeText(Detail2Activity.this, "วันนี้ไม่มีงานอะไร", Toast.LENGTH_LONG).show();
        }

    }

    private void showDate() {

        strDate = getIntent().getStringExtra("Date");
        dateTextView.setText(strDate);
    }

    private void bindWidter() {

        dateTextView = (TextView) findViewById(R.id.txtShowDateDetail);
        dateListView = (ListView) findViewById(R.id.listView);
    }

    public void clickBack(View view) {
        finish();
    }

    public void clickHome(View view) {
        Intent objIntent = new Intent(Detail2Activity.this, MainActivity.class);
        startActivity(objIntent);
    }
}//main class
