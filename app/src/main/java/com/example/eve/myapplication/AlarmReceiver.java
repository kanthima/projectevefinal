package com.example.eve.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

/**
 * Created by masterUNG on 12/7/15 AD.
 */
public class AlarmReceiver extends BroadcastReceiver{
    @Override

    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Alarm OK", Toast.LENGTH_SHORT).show();

        MediaPlayer objMediaPlayer = MediaPlayer.create(context, R.raw.intro_start_horse);
        objMediaPlayer.start();


        Intent objIntent = new Intent(context, MainActivity.class);
        PendingIntent objPendingIntent = PendingIntent.getActivity(context, 0, objIntent, 0);

        Notification objNotification = new Notification.Builder(context)
                .setSmallIcon(R.drawable.icon_myaccount)
                .setContentTitle("ข้อความเตือน")
                .setContentText("มีภาระกิจที่ต้องทำ")
                .setAutoCancel(true)
                .setContentIntent(objPendingIntent)
                .build();

        NotificationManager objNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        objNotificationManager.notify(1000, objNotification);



    }   // onReceive
}   // Main Class
