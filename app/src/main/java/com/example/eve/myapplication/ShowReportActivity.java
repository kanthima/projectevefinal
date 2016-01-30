package com.example.eve.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowReportActivity extends AppCompatActivity {

    private TextView nameTextView, countTextView,DataTextView,ResultTextView;
    private String nameString ,countString,dataString,resultString,ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_report);





        bindWidget();

        showView();


    }//oncreate







    private void showView() {
        nameString = getIntent().getStringExtra("Name");
        nameTextView.setText( nameString);

        countString= getIntent().getStringExtra("Count");
        countTextView.setText("จำนวนครั้งที่เลี้ยง " + countString + " ครั้ง");

        dataString = getIntent().getStringExtra("Data");
        DataTextView.setText(dataString);


        resultString = getIntent().getStringExtra("Result");
        ResultTextView.setText(resultString);





    }

    private void bindWidget() {
        nameTextView = (TextView) findViewById(R.id.textView13);
        countTextView = (TextView) findViewById(R.id.textView14);
        DataTextView = (TextView) findViewById(R.id.textView15);
        ResultTextView = (TextView) findViewById(R.id.textView16);


    }

    public void clickComplete(View view) {
        Intent objIntent = new Intent(ShowReportActivity.this, History2Activity.class);
        startActivity(objIntent);
    }
}
