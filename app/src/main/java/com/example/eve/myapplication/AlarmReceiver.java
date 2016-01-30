package com.example.eve.myapplication;

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



    }   // onReceive
}   // Main Class
