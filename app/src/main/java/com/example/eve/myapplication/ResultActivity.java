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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class ResultActivity extends Activity {
    //explicit
    private int countAnInt,IDAnInt, HrAnInt, MinAnInt;
    private EditText startTimeEditText;
    private ListView resultListView;
    private Calendar objCalendar;
    private TimePicker setTimePicker;
    private TimePickerDialog objTimePickerDialog;
    private int RQs = 1;
    private TextView showTimeTextView, showTimeMinus;
    private int timeAdd = 0;
    public String strTodo;


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


            if (setCalendar.compareTo(nowCalendar) <= 0 ) {
                setCalendar.add(Calendar.DATE, 1);
            }

            showTimeTextView.setText(Integer.toString(hourOfDay));
            showTimeMinus.setText(Integer.toString(minute));

            //setAlarm(setCalendar);

        }   // event
    };

    private void setAlarm(Calendar setCalendar, int myRQS) {

        Log.d("30Jan", "Alarm ==> " + setCalendar.getTime());

        strTodo = findnameNotification();

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);

        intent.putExtra("note", strTodo);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), myRQS, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, setCalendar.getTimeInMillis(), pendingIntent);

    }   // setAlarm

    private String findnameNotification() {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM " + timeTABEL.TABLE_TIME, null);
        objCursor.moveToFirst();
        String strTodo = null;

        objCursor.moveToLast();
        strTodo = objCursor.getString(objCursor.getColumnIndex(timeTABEL.COLUMN_NAME));
        Log.d("eve30", "strTodo ==> " + strTodo);

        objCursor.close();

        return strTodo;
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


        String strHr = showTimeTextView.getText().toString().trim();    // Get Hr
        String strMin = showTimeMinus.getText().toString().trim();      // Get Min

        int intStartTime = Integer.parseInt(strHr);

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("my_time.db", MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM timeTABLE", null);
        objCursor.moveToFirst();
        objCursor.moveToPosition(IDAnInt - 1);
        String[] strAdder = new String[countAnInt];



        String strColumn = null;

        //About Alarm
        int[] intTimeAlarmHr = new int[countAnInt];

        String[] resultStrings = new String[countAnInt];
        for (int i=0;i<resultStrings.length;i++) {

            strColumn = "Count" + Integer.toString(i + 1);
            strAdder[i] = objCursor.getString(objCursor.getColumnIndex(strColumn));


            intTimeAlarmHr[i] = intStartTime + Integer.parseInt(strAdder[i]);
            Log.d("30Jan", "intTimeAlarmHr[" + i + "] = " + intTimeAlarmHr[i]);

            timeAdd = timeAdd + Integer.parseInt(strAdder[i]);

            calulateTimeAlarm(timeAdd, Integer.parseInt(strMin));


            resultStrings[i] = "ครั้งที่ " + Integer.toString(i + 1) + " ==> " +
                    Integer.toString(intStartTime + Integer.parseInt(strAdder[i])) + ":" + strMin;
            intStartTime = intStartTime + Integer.parseInt(strAdder[i]);


        }   // for



        objCursor.close();

        //Create ListView
        ResultAdapter objResultAdapter = new ResultAdapter(ResultActivity.this, resultStrings);
        resultListView.setAdapter(objResultAdapter);




    }   // clickCalculate

    private void calulateTimeAlarm(int intAdd, int intMinus) {

        Calendar objCalendar = Calendar.getInstance();
        Calendar setCalendar = (Calendar) objCalendar.clone();

        String strMinnute = showTimeMinus.getText().toString();
        int intMinnute = Integer.parseInt(strMinnute);

        int intCurrentHr = objCalendar.get(Calendar.HOUR_OF_DAY);


        //timeAdd = timeAdd + intAdd;


        setCalendar.set(Calendar.HOUR_OF_DAY, intCurrentHr + intAdd);
        setCalendar.set(Calendar.MINUTE, intMinnute );
        setCalendar.set(Calendar.SECOND, 0);

        Log.d("30Jan", "Time Test = " + setCalendar.getTime());

        RQs += 1;

        setAlarm(setCalendar, RQs);



    }   // calulateTimeAlarm


    private void receiveFormIntent() {
        countAnInt = getIntent().getIntExtra("Count", 0);
        IDAnInt = getIntent().getIntExtra("ID", 1);


        Log.d("eve", "Count=" + Integer.toString(countAnInt));
        Log.d("eve", "ID=" + Integer.toString(IDAnInt));
    }

    public void clickComplete(View view) {

        String strHr = showTimeTextView.getText().toString();
        String strMin = showTimeMinus.getText().toString();

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);
        objSqLiteDatabase.execSQL("UPDATE timeTABLE SET StartHr" + "='" + strHr + "' WHERE _id=" + IDAnInt);
        objSqLiteDatabase.execSQL("UPDATE timeTABLE SET StartMin" + "='" + strMin + "' WHERE _id=" + IDAnInt);

       Intent objIntent = new Intent(ResultActivity.this, MainActivity.class);
       startActivity(objIntent);
    }
}//main class