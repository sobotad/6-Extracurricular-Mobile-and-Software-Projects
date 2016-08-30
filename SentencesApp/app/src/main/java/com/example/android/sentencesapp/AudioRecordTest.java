//Description: Sentences app calls upon this whenever the user is ready to record their response
//This code was found as a Google example code online, with some changes made throughout

package com.example.android.sentencesapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AppKeyPair;

import java.io.File;
import java.io.IOException;

//Most of this code was lifted wholesale from the Android MediaRecorder example you can find online
public class AudioRecordTest extends Activity
{
    private static final String LOG_TAG = "AudioRecordTest";
    public static String mFileName = null;

    private RecordButton mRecordButton = null;
    private MediaRecorder mRecorder = null;


    //calls functions when onRecord
    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }


    //sets the microphone source, sets the output format, the name, etc.
    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    //When the button is pressed again, recording will stop, and the button will become unclickable, until the next trial
    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        mRecordButton.setClickable(false);
        mRecordButton.setVisibility(View.INVISIBLE);


        File myFile = new File(mFileName);
        UploadFile upload = new UploadFile(this, mDBApi, "/Sentences/" + Variables.getUserID() + "/", myFile);
        upload.execute();



        //if two lists have been completed, jump to the end of the experiment
        if (Variables.getTrials() == 2) {
            Intent intent = new Intent(this, End.class);
            startActivity(intent);
            finish();
        }
        //if the two lists aren't completed yet, jump back to the sentences activity for the next sentence
        else
        {
            Intent intent = new Intent(this, Sentence1.class);
            startActivity(intent);
            finish();
        }
    }


    //Creates the record button
    class RecordButton extends Button {
        boolean mStartRecording = true;

        OnClickListener clicker = new OnClickListener() {
            public void onClick(View v) {
                onRecord(mStartRecording);
                if (mStartRecording) {
                    setText("Stop recording");
                } else {
                    setText("Start recording");
                }
                mStartRecording = !mStartRecording;
            }
        };

        public RecordButton(Context ctx) {
            super(ctx);
            setText("Start recording");
            setOnClickListener(clicker);
        }
    }

    //This code is what sets the filename
    public AudioRecordTest() {
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/" + Variables.getUserID() + "results-" + "time-" + Variables.getMin() + "-List-" + Variables.getList() + "-Sent-" + Variables.getSent() +".3gp";
    }

    //Variables to connect to dropbox project
    final static private String APP_KEY = "26rdwxwrcuhy5r7";
    final static private String APP_SECRET = "fl5kvhi25oqvqkh";
    private DropboxAPI<AndroidAuthSession> mDBApi;
    final static private String ACCESS_TOKEN = "w49p96QBdgsAAAAAAAAn2knODVPo0y262nnn_JUUpH4JglGbyFuDYoz2-hM-5QHy";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);


        //Establish session with dropbox
        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        AndroidAuthSession session = new AndroidAuthSession(appKeys);
        mDBApi = new DropboxAPI<AndroidAuthSession>(session);

        mDBApi.getSession().setOAuth2AccessToken(ACCESS_TOKEN);

        //This code creates the layout of the page, making a separate layout file wasn't working for this code
        LinearLayout ll = new LinearLayout(this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        //Text instructions for the recording
        layoutParams.setMargins(8, 50, 8, 0);
        ll.setGravity(Gravity.CENTER_HORIZONTAL);
        ll.setOrientation(LinearLayout.VERTICAL);
        TextView text = new TextView(this);
        text.append("Please record your response. Make sure you hit start recording, and hit stop when you're done.\n\n\n");
        text.setGravity(Gravity.CENTER);
        text.setTextSize(24);

        //puts the record button on the screen
        mRecordButton = new RecordButton(this);
        ll.addView(text, layoutParams);
        ll.addView(mRecordButton, layoutParams);


        //sets the layout
        setContentView(ll);

    }

    @Override
    //pause and release the recorder when you go back to the sentences activity
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }
    }
}
