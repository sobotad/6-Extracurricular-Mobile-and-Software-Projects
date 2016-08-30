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

    //Information has been removed for the sake of privacy for the lab
    final static private String APP_KEY = "";
    final static private String APP_SECRET = "";
    private DropboxAPI<AndroidAuthSession> mDBApi;

    //By hard-coding an access token, files can be placed directly in the dropbox without users having to sign in, etc.
    final static private String ACCESS_TOKEN = "";

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

