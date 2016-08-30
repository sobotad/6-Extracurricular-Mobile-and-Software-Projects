//Activity to adjust the noise in the user's right ear, for comfortable listening experience

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

public class adjustNoiseR extends AppCompatActivity {

    MediaPlayer background;
    double vol = 0.5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_noise_r);

        //Sets global volume to max, sets target background noise to .5, then plays to appropriate ear
        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audio.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);


        background = MediaPlayer.create(this, R.raw.multi);
        background.setVolume(0, (float)vol);
        background.start();



    }


    //Tied to buttons to make left or right channels louder or quieter, independent of each other
    public void louder (View view){

        vol += 0.1;
        background.setVolume(0, (float)vol);
    }

    public void quieter (View view){

        vol -= 0.1;
        background.setVolume(0, (float)vol);
    }

    //Next activity
    public void goExperiment (View view){
        Variables.setNoiseVolR(vol);
        background.stop();
        background.release();

        Intent intent = new Intent(this, SelectEar.class);

        startActivity(intent);
        finish();


    }
}
