//Activity to adjust noise in the user's left ear, to a comfortable listening level

package com.example.android.matrixapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileWriter;

public class adjustNoiseL extends AppCompatActivity {

    MediaPlayer background;
    double vol = 0.5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_noise_l);

        //Creates audio playing only to the left ear, getting a comfortable listening level

        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audio.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);


        //Starts the backgorund noise
        background = MediaPlayer.create(this, R.raw.multi);
        background.setVolume((float)vol, 0);
        background.start();



    }


    //Tied to buttons to make left or right channels louder or quieter, independent of each other
    public void louder (View view){

        vol += 0.1;
        background.setVolume((float)vol, 0);
    }

    public void quieter (View view){

        vol -= 0.1;
        background.setVolume((float)vol, 0);
    }

    //Next activity
    public void goExperiment (View view){
        Variables.setNoiseVolL(vol);
        background.stop();
        background.release();

        Intent intent = new Intent(this, adjustNoiseR.class);

        startActivity(intent);
        finish();


    }
}
