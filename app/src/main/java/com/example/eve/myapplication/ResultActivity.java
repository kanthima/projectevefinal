package com.example.eve.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.view.View.OnClickListener;


import java.util.Calendar;

public class ResultActivity extends Activity {
    //explicit
    private int countAnInt,IDAnInt, HrAnInt, MinAnInt;
    private EditText startTimeEditText;
    private ListView resultListView;
    private Calendar objCalendar;
    private TimePicker setTimePicker;
    private TimePickerDialog objTimePickerDialog;
    private static final int RQS_1 = 1;
    private TextView showTimeTextView, showTimeMinus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bindWidget();

        //Get Time
        getTime();


        //Receive from intent
        receiveFormIntent();

        //Create Listview
        //createListView();

    }//oncreate

    public void clickSetTime(View view) {

        openTimePickerDialog(false);

    }   // clickSetTime

    private void openTimePickerDialog(boolean bol24Hr) {

        Calendar objCalendar = Calendar.getInstance();
        objTimePickerDialog = new TimePickerDialog(ResultActivity.this,
                objOnTimeSetListener,
                objCalendar.get(Calendar.HOUR_OF_DAY),
                objCalendar.get(Calendar.MINUTE), bol24Hr);
        objTimePickerDialog.setTitle("กำหนดเวลาเริ่มต้น");
        objTimePickerDialog.show();

    }   // openTimePickerDialog

    TimePickerDialog.OnTimeSetListener objOnTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

            Calendar nowCalendar = Calendar.getInstance();
            Calendar setCalendar = (Calendar) nowCalendar.clone();

            setCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            setCalendar.set(Calendar.MINUTE, minute);
            setCalendar.set(Calendar.SECOND, 0);
            setCalendar.set(Calendar.MILLISECOND, 0);

            if (setCalendar.compareTo(nowCalendar) <= 0 ) {
                setCalendar.add(Calendar.DATE, 1);
            }

            showTimeTextView.setText(Integer.toString(hourOfDay));
            showTimeMinus.setText(Integer.toString(minute));

            setAlarm(setCalendar);

        }   // event
    };

    private void setAlarm(Calendar setCalendar) {

        Log.d("eve1", "Alame ==> " + setCalendar.getTime());

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, setCalendar.getTimeInMillis(), pendingIntent);

    }

    private void getTime() {
        objCalendar = Calendar.getInstance();
        HrAnInt = objCalendar.get(Calendar.HOUR_OF_DAY);
        MinAnInt = objCalendar.get(Calendar.MINUTE);

        showTimeTextView.setText(Integer.toString(HrAnInt));
        showTimeMinus.setText(Integer.toString(MinAnInt));

    }

    private void bindWidget() {

        showTimeTextView = (TextView) findViewById(R.id.textView4);
        showTimeMinus = (TextView) findViewById(R.id.textView9);
        resultListView = (ListView) findViewById(R.id.listView2);
    }

    public void clickCalculate(View view) {


        String strHr = showTimeTextView.getText().toString().trim();
        String strMin = showTimeMinus.getText().toString().trim();

        int intStartTime = Integer.parseInt(strHr);

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("my_time.db", MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM timeTABLE", null);
        objCursor.moveToFirst();
        objCursor.moveToPosition(IDAnInt - 1);
        String[] strAdder = new String[countAnInt];
        String strColumn = null;

        String[] resultStrings = new String[countAnInt];
        for (int i=0;i<resultStrings.length;i++) {

            strColumn = "Count" + Integer.toString(i + 1);
            strAdder[i] = objCursor.getString(objCursor.getColumnIndex(strColumn));

            resultStrings[i] = "ครั้งที่ " + Integer.toString(i + 1) + " ==> " +
                    Integer.toString(intStartTime + Integer.parseInt(strAdder[i])) + ":" + strMin;
            intStartTime = intStartTime + Integer.parseInt(strAdder[i]);



        }   // for

        //Create ListView
        ResultAdapter objResultAdapter = new ResultAdapter(ResultActivity.this, resultStrings);
        resultListView.setAdapter(objResultAdapter);




    }   // clickCalculate




    private void receiveFormIntent() {
        countAnInt = getIntent().getIntExtra("Count", 0);
        IDAnInt = getIntent().getIntExtra("ID", 1);


        Log.d("eve", "Count=" + Integer.toString(countAnInt));
        Log.d("eve", "ID=" + Integer.toString(IDAnInt));
    }

    public void clickComplete(View view) {





        Intent objIntent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(objIntent);
    }
}//main class
