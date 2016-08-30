//Description: Home page for the app

package com.example.android.sentencesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;

import java.util.List;

//Every activity needs to have a class. You can have more than one class in an Activity though, but I've generally stuck with one per activity. Within each class,
//you can have multiple functions, and they can be called upon only in certain circumstances (Check out the list java file to see how it's setup/ how you can call on those functions)
public class MainActivity extends AppCompatActivity {
    private SurfaceHolder holder;



    @Override
    //Calls the layout file to be generated
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //function to go to the next activity
    public void go_consent(View view) {

        //Always have to create an intent, passing in the activity name you want to go to, then startActivity
        Intent intent = new Intent(this, ConsentForm.class);
        startActivity(intent);
        finish();


    }

    //Calls upon the video play activity, to display the instruction video for participants
    public void playVideo(View view) {
        Intent vidIntent = new Intent(this, VideoPlayActivity.class);
        startActivity(vidIntent);
    }
}
