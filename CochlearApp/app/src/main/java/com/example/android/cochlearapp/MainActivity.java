//Main homepage of the app

package com.example.android.cochlearapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private SurfaceHolder holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Variables.setUserID(0);

    }

    //Finish is used after every activity is done, to clear that activity from memory.
    //Then, at the end, when the user hits quit, all activities are already closed, and the app closes to the android homepage
    //(There is no easy method to just completely exit an app, so that's why this method is used

    public void go_consent(View view) {

        Intent intent = new Intent(this, consentFormActivity.class);
        startActivity(intent);
        finish();


    }

    public void playVideo(View view) {
       Intent vidIntent = new Intent(this, VideoPlayActivity.class);
        startActivity(vidIntent);
        finish();

    }


}
