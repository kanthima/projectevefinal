package com.example.eve.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class DetaildayActivity extends AppCompatActivity {

    //Explicit
    private TextView dateTextView;
    private ListView dateListView;
    private String strDate, TAG = "eve";
    private String[] strName = null;
    private String[] strCount = null;
    private EditText objEditText;
    private EditText[] countEditTexts;
    private boolean statusABoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailday);

        statusABoolean = getIntent().getBooleanExtra("Status", false);//false == from mainActivity

        //BindWidget
        bindWidter();

        showDate();

        //Create List Dailyplan

        createListDailyPlan();
    }//onCreate

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

            NameAdapter objNameAdapter = new NameAdapter(strCount, DetaildayActivity.this, strName);
            dateListView.setAdapter(objNameAdapter);

            //**************************************************
            //Get Active When Click Listview

            //**************************************************


            dateListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                    if (statusABoolean) {

                        //showMyDialog(strName[i],strCount[i]);

                        intentToSaveHr(strName[i], strCount[i], i + 1);
                    } else {

                        //show detail
                    }


                }
            });//event


        } catch (Exception e) {
            Toast.makeText(DetaildayActivity.this, "วันนี้ไม่มีงานอะไร", Toast.LENGTH_LONG).show();
        }

    }//Create List Dailyplan

    private void intentToSaveHr(String strName, String strCount, int intID) {

        Intent objIntent = new Intent(DetaildayActivity.this, SaveHrActivity.class);
        objIntent.putExtra("Name", strName);
        objIntent.putExtra("Count", strCount);
        objIntent.putExtra("ID", intID);
        startActivity(objIntent);
    }


    private void showMyDialog(String strName, String strCount) {

        int intCount = Integer.parseInt(strCount);
        Log.d(TAG, "intCount ==>" + Integer.toString(intCount));
        countEditTexts = new EditText[intCount];


        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setTitle(strName);
        objBuilder.setMessage("โปรดกรอกข้อมูล");
        objBuilder.setCancelable(false);

        for (int i = 0; i < intCount; i++) {
            countEditTexts[i] = new EditText(DetaildayActivity.this);
            objBuilder.setView(countEditTexts[i]);
        }


        objEditText = new EditText(DetaildayActivity.this);
        objBuilder.setView(objEditText);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();


            }//event
        });

        objBuilder.show();

    }//showmydailog

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
        Intent objIntent = new Intent(DetaildayActivity.this, MainActivity.class);
        startActivity(objIntent);
    }

}//MainClass
