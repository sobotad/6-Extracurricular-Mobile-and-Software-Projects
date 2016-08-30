//End activity. Debriefing + uploading final file to dropbox

package com.example.android.matrixapp;

import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AppKeyPair;

import java.io.File;

public class End extends AppCompatActivity {

    //Variables corresponding to dropbox app project
    final static private String APP_KEY = "26rdwxwrcuhy5r7";
    final static private String APP_SECRET = "fl5kvhi25oqvqkh";
    private DropboxAPI<AndroidAuthSession> mDBApi;
    final static private String ACCESS_TOKEN = "w49p96QBdgsAAAAAAAAn2knODVPo0y262nnn_JUUpH4JglGbyFuDYoz2-hM-5QHy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);


        // Initializes the dropbox session
        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        AndroidAuthSession session = new AndroidAuthSession(appKeys);
        mDBApi = new DropboxAPI<AndroidAuthSession>(session);

        mDBApi.getSession().setOAuth2AccessToken(ACCESS_TOKEN);


        //Uploads the file using the proper activity
        File path = Environment.getExternalStorageDirectory();
        File myFile = new File(path + "/" + Variables.getUserID() + "results-" + Variables.getMin() + "-" + Variables.getSec() + ".txt");
        UploadFile upload = new UploadFile(this, mDBApi, "/Matrix/" + Variables.getUserID() + "/", myFile);
        upload.execute();

    }
    public void quit(View view){

        Variables.release();
        finish();

    }




}

