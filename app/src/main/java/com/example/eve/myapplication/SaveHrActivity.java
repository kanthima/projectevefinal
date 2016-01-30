package com.example.eve.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SaveHrActivity extends AppCompatActivity {


    //Explicit
    private TextView nameTextView, countTextView;
    private EditText hrEditText;
    private String nameString,countString,hrString;
    private int timesAnInt = 1,intCount, intID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_hr);

        receiveCount();

        bindWidget();

        showView();


    } //main method

    private void receiveCount() {
        String strCount = getIntent().getStringExtra("Count");
        intCount = Integer.parseInt(strCount);
    }

    private void showView() {


        nameString = getIntent().getStringExtra("Name");
        nameTextView.setText( nameString);

        countTextView.setText("ครั้งที่ = "+ Integer.toString(timesAnInt));


    }

    private void bindWidget() {

        nameTextView = (TextView) findViewById(R.id.txtSaveName);
        countTextView = (TextView) findViewById(R.id.txtSaveTime);
        hrEditText = (EditText) findViewById(R.id.edtSaveHr);

    }

    public void clickSaveButton(View view) {

        if (timesAnInt <=intCount) {



            //Get Data From Hr Edittext
            hrString = hrEditText.getText().toString().trim();

            //Update NewHr to SQLite

            intID = getIntent().getIntExtra("ID", 1);

            updateNewHr(hrString,timesAnInt,intID);
            timesAnInt += 1;
            showView();
        } else {

            Intent objIntent = new Intent(SaveHrActivity.this, ResultActivity.class);


            objIntent.putExtra("Count", intCount);
            objIntent.putExtra("ID", intID);
            startActivity(objIntent);

        }

    }//clicksavebutton

    private void updateNewHr(String strHr,int intColum ,int intID) {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("my_time.db",MODE_PRIVATE,null);

        //objSqLiteDatabase.execSQL("UPDATE timeTABLE SET Count"+ Integer.toString(intColum) +"='"+strHr+"' WHERE _id=1");
        objSqLiteDatabase.execSQL("UPDATE timeTABLE SET Count"+ Integer.toString(intColum) +"='"+strHr+
                                 "' WHERE _id="+ Integer.toString(intID) +"");

        timeTABEL objTimeTABEL = new timeTABEL(this);
        String strID = objTimeTABEL.searchID(nameString);
        Log.d("eve", "ID ==>" + strID);




    }//update new hr


}//mainclass
