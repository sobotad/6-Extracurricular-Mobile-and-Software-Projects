//Description: Display instructions for the participant
package com.example.android.sentencesapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Instruction extends AppCompatActivity {

    @Override
    //generate layout
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
    }

    //Go to the intro form
    public void go_experiment(View view) {
        Intent intent = new Intent(this, Sentence1.class);

        //Sets the device global volume to max
        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audio.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);

        startActivity(intent);
        finish();
    }
}
