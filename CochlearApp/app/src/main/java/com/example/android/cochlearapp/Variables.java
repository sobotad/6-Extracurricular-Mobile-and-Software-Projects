//Gather variables and store them all in this app, to use as global variables across the experiment

package com.example.android.cochlearapp;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by DavidSobota on 2/23/16.
 */
public class Variables extends AppCompatActivity{


    //User ID
    public static int userID;
    public  static int getUserID() {
        return userID;
    }
    public static void setUserID(int id) {
        userID = id;
    }

    //The choice they selected
    public static String selected;
    public static void setSelected(String select){
        selected = select;
    }
    public static String getSelected(){return selected;}

    //Date variables, used for naming the files
    public static int minutes;
    public static void setMin(int curMin) {minutes = curMin;}
    public static int getMin() {return minutes;}

    public static int seconds;
    public static void setSec(int curSec) {seconds = curSec;}
    public static int getSec() {return seconds;}


    //Variables for noise volume, for independent left and right channels, not used in its current state, but left in to implement in the future
    public static double noiseVolL;
    public static void setNoiseVolL(double vol) {noiseVolL = vol;}
    public static double getNoiseVolL() {return noiseVolL;}

    public static double noiseVolR;
    public static void setNoiseVolR(double vol) {noiseVolR = vol;}
    public static double getNoiseVolR() {return noiseVolR;}

    //Which trial they are on
    public static int trialNum;
    public static void setTrial(int num) {trialNum = num;}
    public static int getTrial() { return trialNum;}

    //Background mediaplayer
    public static MediaPlayer background;

    //pass in values for the left and right channeels of stereo, creates a mediaplayer that pulls the background sound from the multi file in the raw folder
    //Sets a loop so the background plays throughout, and then starts
    public static void startBackground( Context context, double left, double right){
        background = MediaPlayer.create(context, R.raw.multi);
        background.setVolume((float)left, (float)right);
        background.setLooping(true);
        background.start();
    }

    //stop the background noise when the experiment finishes
    public static void stopBackground() {
        background.stop();
        background.release();

    }

    //Counter used for keeping track of practice
    public static int practiceCounter = 0;
    public static void setCounter() {practiceCounter++;}
    public static int getCounter() {return practiceCounter;}

    //Call this to end this activity at the end, to make sure everything is exited and reset for the next time the app is open (might not be neccesary here)
    public static void release(){
        System.exit(0);
    }
}
