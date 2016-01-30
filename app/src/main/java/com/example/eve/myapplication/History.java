package com.example.eve.myapplication;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class History extends ListActivity {

    private  timeTABEL objtimeTABLE;
    private  SimpleCursorAdapter  objSimpleCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_history);

          objtimeTABLE = new timeTABEL(this);

        //Create Listview
          createListView();

    } // OnCreate

    private void createListView() {
        Cursor ListDate = objtimeTABLE.readAllData();
        String[] from = new String[]{timeTABEL.COLUMN_NAME};
        int[] target = new int[]{R.id.txtListDate};
        objSimpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.activity_history,ListDate,from,target);
        setListAdapter(objSimpleCursorAdapter);
    }//createListView

    @Override
    protected void onListItemClick(ListView l, View v, final int position, long id) {
        super.onListItemClick(l, v, position, id);




          Cursor objCursor = (Cursor) l.getItemAtPosition(position);
          final String strName = objCursor.getString(objCursor.getColumnIndex(timeTABEL.COLUMN_NAME));
          final String strDate = objCursor.getString(objCursor.getColumnIndex(timeTABEL.COLUMN_DATE));
          final int intCount = objCursor.getInt(objCursor.getColumnIndex(timeTABEL.COLUMN_COUNT));
          final String strData = objCursor.getString(objCursor.getColumnIndex(timeTABEL.COLUMN_DATA));


        // shiw Alert Dialog

          AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
          objAlert.setIcon(R.drawable.history);
         objAlert.setTitle("You Name = " + strName);
         objAlert.setMessage("Date = " + strDate + "\n" + "Your Count = " + Integer.toString(intCount) +
                        "\n" + "Your Data =" + strData );
         objAlert.setCancelable(false);
          objAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
            objAlert.setNegativeButton("Edit SQLite", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Intent to EditSQLiteActivity
                Intent objIntent = new Intent(History.this,EditSQLiteActivity.class);
                objIntent.putExtra("id",position+1);
               objIntent.putExtra("name",strName);
                objIntent.putExtra("date",strDate);
                objIntent.putExtra("count",Integer.toString(intCount));
                objIntent.putExtra("data",strData);

                 startActivity(objIntent);
            }//event


        });

        objAlert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
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





}//Mainclass
