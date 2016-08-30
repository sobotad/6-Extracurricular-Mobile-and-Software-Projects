package com.example.android.matrixapp;

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

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AppKeyPair;

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

    public void go_consent(View view) {

        Intent intent = new Intent(this, ConsentFormActivity.class);
        startActivity(intent);
        finish();


    }

    public void playVideo(View view) {
        Intent vidIntent = new Intent(this, VideoPlayActivity.class);
        startActivity(vidIntent);

    }


}

