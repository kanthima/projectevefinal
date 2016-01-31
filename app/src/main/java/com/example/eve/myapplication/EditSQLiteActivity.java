package com.example.eve.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditSQLiteActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText,dateEditText,countEditText,detailEditText,resultsEditText;
    private  String nameString,dateString,countString,detailString,resultsString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sqlite);


        //Bind Widget

        bindWidget();

        //Show View
        showView();
    }// onCreate

    private void showView() {

        nameString = getIntent().getStringExtra("name");
        nameEditText.setText(nameString);

        dateString = getIntent().getStringExtra("date");
        dateEditText.setText(dateString);

        countString= getIntent().getStringExtra("count");
        countEditText.setText(countString);

        detailString = getIntent().getStringExtra("data");
        detailEditText.setText(detailString);


        resultsString = getIntent().getStringExtra("Results");
        resultsEditText.setText(resultsString);


    }

    public void clickUpdateSQLite(View view){
        deleteOnData();

        getNewValue();

        updateSQLite();

        Intent objIntent = new Intent(EditSQLiteActivity.this,MainActivity.class);
        startActivity(objIntent);


    }

    private void updateSQLite() {

        timeTABEL objTimeTABEL = new timeTABEL(this);
        objTimeTABEL.addNewValueToSQLite(nameString, dateString, Integer.parseInt(countString), detailString,resultsString);

        //My Dialog

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);


    }

    private void getNewValue() {

        nameString = nameEditText.getText().toString().trim();
        dateString = dateEditText.getText().toString().trim();
        countString = countEditText.getText().toString().trim();
        detailString = detailEditText.getText().toString().trim();
        resultsString = resultsEditText.getText().toString().trim();


    }

    private void deleteOnData() {

        int intID = getIntent().getIntExtra("id",0);
        if (intID != 0) {

            SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("my_time.db",MODE_PRIVATE,null);
            Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM timeTABLE",null);
            objSqLiteDatabase.delete("timeTABLE","_id"+"="+intID,null);
            Log.d("eve","Delete id = "+Integer.toString(intID));

        }
    }

    private void bindWidget() {

        nameEditText = (EditText) findViewById(R.id.edtNameED);
        dateEditText = (EditText) findViewById(R.id.edtDateED);
        countEditText = (EditText) findViewById(R.id.edtCountED);
        detailEditText = (EditText) findViewById(R.id.edtDataEDA);
        resultsEditText = (EditText) findViewById(R.id.edtResultsED);
    }

    public void clickBackEdit(View view) {
        finish();
    }
}//Main class
