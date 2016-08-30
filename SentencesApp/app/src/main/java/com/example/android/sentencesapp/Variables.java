//Description: A separate activity to house global variables, so you can easily glance and see everything all in one place

package com.example.android.sentencesapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class Variables extends AppCompatActivity{

    //All variables are in public static format, that's the only way these functions can be called outside of this activity.
    //Sometimes there are issues with dealing with public static variables, and incorporating them in other activities can be messy.
    //The best way I've found so far is to put all variables in their own acitivities, and set functions to set and get that variable

    //format: initialize the variable you want, then have two functions, one to set it by passing in something, and another function to retrieve the value of the variable


    //user id
    public static int userID;

    public  static int getUserID() {
        return userID;
    }

    public static void setUserID(int id) {
        userID = id;
    }


    //minutes at the time of starting the experiment
    public static int minutes;

    public static void setMin(int curMin) {minutes = curMin;}

    public static int getMin() {return minutes;}

    //seconds at the time of starting the experiment
    public static int seconds;

    public static void setSec(int curSec) {seconds = curSec;}

    public static int getSec() {return seconds;}


    //Variables that can be utilized to adjust left and right volume independently
    public static double noiseVolL;

    public static void setNoiseVolL(double vol) {noiseVolL = vol;}

    public static double getNoiseVolL() {return noiseVolL;}

    public static double noiseVolR;

    public static void setNoiseVolR(double vol) {noiseVolR = vol;}

    public static double getNoiseVolR() {return noiseVolR;}


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


    //Variable to keep track of what the first list was
    public static int List = 0;
    public static void setList(int num) { List = num;}
    public static int getList() {return List;}


    //This is used in list comparison logic in the sentence1 activity
    public static int listCheck = 0;
    public static void setListCheck(int num) { listCheck = num;}
    public static void incrementListCheck() {listCheck++;}
    public static int getListCheck() {return listCheck;}

    //Counter to see how many sentences have been completed
    public static int sentenceNum = 0;
    public static void incrementSent() { sentenceNum++;}
    public static int getSent() { return sentenceNum;}
    public static void resetSent() { sentenceNum = 0;}

    //Once a list is done, trialsdone is updated to reflect that
    public static int trialsDone = 0;
    public static void incrementList() {trialsDone++;}
    public static int getTrials() { return trialsDone;}

    public static void release(){
        System.exit(0);
    }
}
