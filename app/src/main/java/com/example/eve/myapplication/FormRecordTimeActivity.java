

package com.example.eve.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormRecordTimeActivity extends Activity {
    //Explicit
    private TextView txtShowDate;
    private EditText edtCount, edtName, edtData;
    private String strShowDate, strCount, strName, strData,strResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_record_time);
        //Bind Widget
        bindWidget();
        //get Time from Device
        getTimeFromDevice();


    }//onCreate


    public void clickSave(View view) {

        strCount = edtCount.getText().toString().trim();
        if (strCount.equals("")) {
            showAlert();
        }
        strName = edtName.getText().toString().trim();
        if (strName.equals("")) {
            showAlert();
        }

        strData = edtData.getText().toString();
        if (strData.equals(""))
            showLog();
        confirmData();

    }//clickSave

    private void confirmData() {

        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setTitle("Are You Sure?");
        objAlert.setMessage("Date =" + strShowDate + "\n" + "Name =" + strName + "\n" +
                "Count =" + strCount + "\n" + "Data =" + strData);
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
                upDatatoSOLite();


                //Intent to CalendaActivity
                startActivity(new Intent(FormRecordTimeActivity.this, CalandaActivity.class));
            }
        });
        objAlert.show();
    }//confirmData


    private void upDatatoSOLite() {
        timeTABEL objtimeTABLE = new timeTABEL(this);
        long inSertData = objtimeTABLE.addNewValueToSQLite(strName, strShowDate, Integer.parseInt(strCount), strData,strResults);
        edtName.setText("");
        edtCount.setText("");
        edtData.setText("");
        Toast.makeText(FormRecordTimeActivity.this, "Add Data Finish", Toast.LENGTH_SHORT).show();

    }//upDateSQLite

    private void showLog() {
        Log.d("count", "Time = " + strShowDate);
        Log.d("count", "count = " + strCount);
        Log.d("name", "name =" + strName);
        Log.d("data", "data =" + strData);
    }// show Log

    private void showAlert() {

        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setIcon(R.drawable.bg10);
        objAlert.setTitle("Have Space");
        objAlert.setMessage("Please Fill in the Blank");
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objAlert.show();
    }//show Alert


    private void getTimeFromDevice() {
        // DateFormat objDateFormat = new SimpleDateFormat("dd/MM/yy 'at' HH:mm:ss a zzz" );


        DateFormat objDateFormat = new SimpleDateFormat("d-MMMM-yyyy");

        Date objDate = new Date();

        strShowDate = objDateFormat.format(objDate);

        txtShowDate.setText(strShowDate);

    }//getTimeFromDevice

    private void bindWidget() {
        txtShowDate = (TextView) findViewById(R.id.txtShowDate);
        edtCount = (EditText) findViewById(R.id.edtCount);
        edtName = (EditText) findViewById(R.id.edtName);
        edtData = (EditText) findViewById(R.id.edtData);
    }//bindWidget

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form_record_time, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clickHome(View view) {
        Intent objIntent = new Intent(FormRecordTimeActivity.this, MainActivity.class);
        startActivity(objIntent);
    }
}// MainClass
