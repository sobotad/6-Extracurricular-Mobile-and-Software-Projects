//Practice, so the user can go through and get familiar with the buttons

package com.example.android.cochlearapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.media.MediaPlayer;
import android.widget.RadioGroup;

import java.io.File;
import java.io.FileWriter;

public class practice extends AppCompatActivity {

    //Creates radiogroups to keep track of button presses, etc. Not really necessary for this, but this code was lifted from the experiment activity
    //Check the experiment activity for a more detailed breakdown
    RadioGroup rg1;
    RadioGroup rg2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        //Sets the device global volume to max
        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audio.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);

        rg1 = (RadioGroup)findViewById(R.id.radioGroup1);
        rg2 = (RadioGroup)findViewById(R.id.radioGroup2);
        rg1.clearCheck();
        rg2.clearCheck();
        rg1.setOnCheckedChangeListener(listener1);
        rg2.setOnCheckedChangeListener(listener2);
    }

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg2.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                rg2.clearCheck(); // clear the second RadioGroup!
                rg2.setOnCheckedChangeListener(listener2); //reset the listener
            }
        }
    };

    private  RadioGroup.OnCheckedChangeListener listener2 = new  RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg1.setOnCheckedChangeListener(null);
                rg1.clearCheck();
                rg1.setOnCheckedChangeListener(listener1);
            }
        }
    };

    public void recordAnswer(View view) {

        //Once the user presses the buttons enough, submit button becomes visible and clickable
        if(Variables.getCounter() == 11) {

            Button submitbtn = (Button) findViewById(R.id.Submit);
            submitbtn.setClickable(true);
            submitbtn.setVisibility(View.VISIBLE);
        }




        boolean checked = ((RadioButton) view).isChecked();

        //Checks each button, and plays the appropriate audio file on press
        switch(view.getId()){
            //If bait pressed, generate a mediaplayer to play bait, so on and so forth...
            case R.id.bait:
                if (checked) {
                    MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.bait);
                    mediaplayer.setVolume(1,1);
                    mediaplayer.start();
                    Variables.setCounter();
                    break;
                }

            case R.id.bart:
                if (checked) {
                    MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.bart);
                    mediaplayer.setVolume(1,1);
                    mediaplayer.start();
                    Variables.setCounter();
                    break;
                }

            case R.id.bat:
                if (checked) {
                    MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.bat);
                    mediaplayer.setVolume(1,1);
                    mediaplayer.start();
                    Variables.setCounter();
                    break;
                }

            case R.id.beet:
                if (checked) {
                    MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.beet);
                    mediaplayer.setVolume(1,1);
                    mediaplayer.start();
                    Variables.setCounter();
                    break;
                }

            case R.id.burt:
                if (checked) {
                    MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.burt);
                    mediaplayer.setVolume(1,1);
                    mediaplayer.start();
                    Variables.setCounter();
                    break;
                }

            case R.id.bet:
                if (checked) {
                    MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.bet);
                    mediaplayer.setVolume(1,1);
                    mediaplayer.start();
                    Variables.setCounter();
                    break;
                }

            case R.id.bit:
                if (checked) {
                    MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.bit);
                    mediaplayer.setVolume(1,1);
                    mediaplayer.start();
                    Variables.setCounter();
                    break;
                }

            case R.id.bite:
                if (checked) {
                    MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.bite);
                    mediaplayer.setVolume(1,1);
                    mediaplayer.start();
                    Variables.setCounter();
                    break;
                }

            case R.id.boot:
                if (checked) {
                    MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.boot);
                    mediaplayer.setVolume(1,1);
                    mediaplayer.start();
                    Variables.setCounter();
                    break;
                }

            case R.id.bought:
                if (checked) {
                    MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.bought);
                    mediaplayer.setVolume(1,1);
                    mediaplayer.start();
                    Variables.setCounter();
                    break;
                }

            case R.id.bout:
                if (checked) {
                    MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.bout);
                    mediaplayer.setVolume(1,1);
                    mediaplayer.start();
                    Variables.setCounter();
                    break;
                }

            case R.id.but:
                if (checked) {
                    MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.but);
                    mediaplayer.setVolume(1,1);
                    mediaplayer.start();
                    Variables.setCounter();
                    break;
                }
        }
    }

    public void submit(View view) {

        Intent intent = new Intent(this, experiment.class);
        startActivity(intent);

        double snr = -10;

        double db = Math.pow(10, (snr/20));
        double actualdb = db *.316;





        //Calls the functions to start the background noise, using the variables calculated above
        //The variables above were found by measuring output from headphones in the lab, with a target of 60db for sentences presented, and a signal to noise ratio of 10, (meaning the background noise is 10db quieter than the target material)
        Variables.startBackground(this, actualdb, actualdb);
        finish();


    }
}
