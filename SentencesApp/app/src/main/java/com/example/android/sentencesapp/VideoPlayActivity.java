//Description: Plays the instruction video to the participant

package com.example.android.sentencesapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoPlayActivity extends Activity {
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Generates the layout for the page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audio.setStreamVolume(AudioManager.STREAM_MUSIC, (int) (.5 * maxVolume), 0);

        //Creates a VideoView, that locates the video player by it's id
        VideoView videoView = (VideoView)findViewById(R.id.video_play);

        //MediaController is to give a play button, stop, etc. to control the video
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        //Select where the video should be played from, make sure the video is located in the raw folder (Will have to be created if it doesn't exist)
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.introduction);
        videoView.start();
    }

    //Go back to the main page
    public void goMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}