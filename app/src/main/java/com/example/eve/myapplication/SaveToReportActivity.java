package com.example.eve.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SaveToReportActivity extends AppCompatActivity {
    private TextView nameTextView, countTextView;
    private EditText ResultEditText;
    private String nameString,countString,resultString;
    private int intCount, intID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_to_report);


        receiveCount();

        bindWidget();

        showView();
    }

    private void showView() {

        nameString = getIntent().getStringExtra("Name");
        nameTextView.setText(nameString);
    }

    private void bindWidget() {

        nameTextView = (TextView) findViewById(R.id.txtSaveReName);
        //ountTextView = (TextView) findViewById(R.id.txtSaveCount);
        ResultEditText = (EditText) findViewById(R.id.edtReport);
    }

    private void receiveCount() {

        String strCount = getIntent().getStringExtra("Count");
        intCount = Integer.parseInt(strCount);
    }

    public void clickSaveResult(View view) {
        resultString = ResultEditText.getText().toString().trim();
        intID = getIntent().getIntExtra("ID", 1);


        updateNewHr(resultString, intID);
        showView();

        confirmData();

        //Intent objIntent = new Intent(SaveToReportActivity.this, ReportActivity.class);
       // startActivity(objIntent);


    }

    private void confirmData() {

        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setTitle("Are You Sure?");
        objAlert.setMessage("Name =" + nameString + "\n" +"Results=" + resultString);
        objAlert.setCancelable(false);
        objAlert.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                //Intent to CalendaActivity
                startActivity(new Intent(SaveToReportActivity.this, History2Activity.class));
            }
        });
        objAlert.show();
    }

    private void updateNewHr(String resultString, int intID) {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("my_time.db",MODE_PRIVATE,null);
        objSqLiteDatabase.execSQL("UPDATE timeTABLE SET Results='" + resultString +
                "' WHERE _id=" + Integer.toString(intID) + "");

        timeTABEL objTimeTABEL = new timeTABEL(this);
        String strID = objTimeTABEL.searchID(nameString);
        Log.d("eve", "ID ==>" + strID);

        Toast.makeText(SaveToReportActivity.this, "Add Data Finish", Toast.LENGTH_SHORT).show();


    }

    public void clickback(View view) {
        Intent objIntent = new Intent(this, ReportActivity.class);
        startActivity(objIntent);
    }
}//mainclss
