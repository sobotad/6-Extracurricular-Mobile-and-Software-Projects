//Global variables to be used throughout the app

package com.example.android.matrixapp;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by DavidSobota on 2/23/16.
 */
public class Variables extends AppCompatActivity{


    //Variables for noise volume, for independent left and right channels, not used in its current state, but left in to implement in the future
    public static double noiseVolL;
    public static void setNoiseVolL(double vol) {noiseVolL = vol;}
    public static double getNoiseVolL() {return noiseVolL;}

    public static double noiseVolR;
    public static void setNoiseVolR(double vol) {noiseVolR = vol;}
    public static double getNoiseVolR() {return noiseVolR;}

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

    //Volume for the left ear
    public static double leftEar;
    public static void setLeft(double vol) {leftEar = vol;}
    public static double getLeft() {return leftEar;}

    //Volume for the right ear
    public static double rightEar;
    public static void setRight(double vol) {rightEar = vol;}
    public static double getRight() {return rightEar;}

    //The alt values are just flipped from the previous ones, because the play audio button and the matrix use the same code, but we want to get it to different ears, so we just swap the values instead
    public static double altleftEar;
    public static void setaltLeft(double vol) {altleftEar = vol;}
    public static double getaltLeft() {return altleftEar;}

    public static double altrightEar;
    public static void setaltRight(double vol) {altrightEar = vol;}
    public static double getaltRight() {return altrightEar;}

    //The number button they pressed
    public static int number;
    public static int getNum() {return number;}
    public static void setNum(int num) { number = num;}

    //Date variables, used for naming the files
    public static int minutes;
    public static void setMin(int curMin) {minutes = curMin;}
    public static int getMin() {return minutes;}

    public static int seconds;
    public static void setSec(int curSec) {seconds = curSec;}
    public static int getSec() {return seconds;}

    //How many trials have been completed
    public static int trialNum = 0;
    public static void incrementTrial() {trialNum++;}
    public static int getTrial() {return trialNum;}

    //The rating the user selected
    public static int rating;
    public static void setRating(int rate) {rating = rate;}
    public static int getRating() {return rating;}

    public static void release(){
        System.exit(0);
    }




}

