package com.example.android.sentencesapp;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class End extends AppCompatActivity {

    @Override
    //Put the stop background noise in both on create and on pause, just to be on the safe side

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Variables.stopBackground();

    }

    public void quit(View view){

        Variables.release();
        finish();

    }



}
