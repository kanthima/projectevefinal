package com.example.eve.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SaveTimeActivity extends AppCompatActivity {
    private ListView resultListView;
    private String resultString,nameString;
    private int timesAnInt = 1,intCount, intID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        receiveCount();

        bindWidget();


    }


    private void bindWidget() {
        resultListView = (ListView) findViewById(R.id.listView2);

    }

    private void receiveCount() {

        String strCount = getIntent().getStringExtra("Time");
        intCount = Integer.parseInt(strCount);
    }

    public void clickSaveButton(View view){

        if (timesAnInt <=intCount) {

            resultString = resultListView.toString().trim();

            intID = getIntent().getIntExtra("ID", 1);

            updateNewHr(resultString,timesAnInt,intID);
            timesAnInt += 1;

        } else {

            Intent objIntent = new Intent(SaveTimeActivity.this, ResultActivity.class);


            objIntent.putExtra("Count", intCount);
            objIntent.putExtra("ID", intID);
            startActivity(objIntent);

        }

    }


    private void updateNewHr(String strResult, int intColum, int intID) {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("my_time.db",MODE_PRIVATE,null);

        //objSqLiteDatabase.execSQL("UPDATE timeTABLE SET Count"+ Integer.toString(intColum) +"='"+strHr+"' WHERE _id=1");
        objSqLiteDatabase.execSQL("UPDATE timeTABLE SET Count"+ Integer.toString(intColum) +"='"+strResult+
                "' WHERE _id="+ Integer.toString(intID) +"");

        timeTABEL objTimeTABEL = new timeTABEL(this);
        String strID = objTimeTABEL.searchID(nameString);
        Log.d("eve", "ID ==>" + strID);


    }
}//mainclass
