//Description: Handles the presentation of sentences to the participant

package com.example.android.sentencesapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.os.Handler;
import android.util.Log;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class Sentence1 extends AppCompatActivity {

    //initialize variables
    int list;
    MediaPlayer target;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence1);

        //This triggers if it's the first list/sentence being played(Practice)
        if (Variables.getListCheck() == 0)
        {


            Variables.setList(0);

            //Create popup to let the user know what's going on
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Alright, first we're gonna go through a practice trial in quiet, then the experiment will start. Good luck!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

            //by setting list check to 3, it prevents any logic from happening until the list is finished, then you can go to the next one
            Variables.setListCheck(3);
        }


        //This code generates a random list to be played, now that practice is done
        if (Variables.getListCheck() == 1) {

            //makes a new random list number
            Variables.resetSent();

            Random r = new Random();
            int Low = 1;
            int High = 31;
            int Result = r.nextInt(High - Low) + Low;


            Variables.setList(Result);
            list = Result;

            //Create pop up
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Ok, time to take the actual experiment now! Good luck!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

            //Set list check to basically null for now, until it's time for the next list to be generated
            Variables.setListCheck(3);

            //Now that the expeirment is starting, generate and start the background noise


            //variables used for setting the snr
            double snr = -10;
            double db = Math.pow(10, (snr/20));

            //Equation used to convert to the correct db. This is currently set to around 60 db
            double actualdb = db *.316;

            //Sets up an audio stream, in order to play the background noise
            AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

            //sets device global volume to max
            audio.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);

            //after volume is max, call this function from the Variables activity to start playing the background sound
            Variables.startBackground(this, actualdb, actualdb);


        }

        //Code to generate the second trial list
        if (Variables.getListCheck() == 2) {

            //makes a new random list number

            Variables.resetSent();

            //Variable used as a check
            int found = 0;
            while (found == 0) {
                Random r = new Random();
                int Low = 1;
                int High = 31;
                int Result = r.nextInt(High - Low) + Low;

                //This code checks to make sure that the new list is not the same as the list that was just played
                if (Result != Variables.getList()) {
                    Variables.setList(Result);
                    list = Result;

                    //a unique list is found, and the loop can exit
                    found = 1;

                    //Create popup
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("You're halfway there! Take a break, then hit OK to do the last trial.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    Variables.setListCheck(3);
                }
            }
        }



        //increment the number of sentences that have been played
        Variables.incrementSent();


    }

    public void playAudio(View view) {

        //When the audio button is pressed, it becomes greyed out to prevent listening to the target more than once
        Button btn = (Button)findViewById(R.id.playAudio);
        btn.setEnabled(false);
        btn.setClickable(false);


        //create the name of the sentence to be played, using the global variables to pull the correct sentence
        String name = "azbio_" + Variables.getList() + "_" + Variables.getSent()+"_s60";

        //Once the practice is done, update the list check, for the next round
        if (Variables.getSent() == 5 && Variables.getTrials()==0)
        {
            Variables.incrementList();
            Variables.setListCheck(1);
        }

        //If a trial list is done, update the counter and trials completed
        if (Variables.getSent() == 20 && Variables.getTrials()==1)
        {
            //reset the sentence counter to 1, and update the setListCheck, to create the second trial list
            Variables.incrementList();
            Variables.setListCheck(2);
        }

        //prepare the sentence, using the variable "name" you created above, and play it
        target = MediaPlayer.create(this, this.getResources().getIdentifier(name,"raw", getPackageName()));
        target.setVolume(1,1);
        target.start();


        //wait 5 seconds, then call the go_next function
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                go_next();

            }
        }, 5000);
    }

    //This function takes you to the audio recorder class, in order for the participant to record their answer
    public void go_next() {

            //Finish and release the audioplayer
            Intent intent = new Intent(this, AudioRecordTest.class);
            startActivity(intent);
            target.stop();
            target.release();
            finish();
    }
}

