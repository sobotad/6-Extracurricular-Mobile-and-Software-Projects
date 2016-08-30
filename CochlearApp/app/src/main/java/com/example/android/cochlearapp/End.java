//Description: Debrief and conclude experiment

package com.example.android.cochlearapp;

import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AppKeyPair;

import java.io.File;

public class End extends AppCompatActivity {

    //Plug in information from your DropBox app, key name, secret, and generate an access token
    final static private String APP_KEY = "26rdwxwrcuhy5r7";
    final static private String APP_SECRET = "fl5kvhi25oqvqkh";
    private DropboxAPI<AndroidAuthSession> mDBApi;

    //By hard-coding an access token, files can be placed directly in the dropbox without users having to sign in, etc.
    final static private String ACCESS_TOKEN = "w49p96QBdgsAAAAAAAAn2knODVPo0y262nnn_JUUpH4JglGbyFuDYoz2-hM-5QHy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        //Stops the background noise
        Variables.stopBackground();


        // Create the appropriate key pair using the variables you designated above
        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        AndroidAuthSession session = new AndroidAuthSession(appKeys);

        //Create a session to dropbox and use the access token
        mDBApi = new DropboxAPI<AndroidAuthSession>(session);
        mDBApi.getSession().setOAuth2AccessToken(ACCESS_TOKEN);

        //Find the file you created, and call the upload function to upload/delete the file
        File path = Environment.getExternalStorageDirectory();
        File myFile = new File(path + "/", Variables.getUserID() + "results-" + Variables.getMin() + "-" + Variables.getSec() + ".txt");
        UploadFile upload = new UploadFile(this, mDBApi, "/Matching/" + Variables.getUserID() +"/", myFile);
        upload.execute();

    }
     public void quit(View view){

         //Release the activities, and finish this one, effectively closing the app
         list.release();
         Variables.release();
         finish();

     }



}
