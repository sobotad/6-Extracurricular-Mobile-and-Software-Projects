//This activity gets the user's name and ID number, so we can name and identify files properly
//Note*- User name will probably be ommitted, and just ID will be left, because only the lab will be able to identify participants through the ID number
package com.example.android.cochlearapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;

/**
 * Created by DavidSobota on 2/25/16.
 */
public class IntroForm extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        //Gets date information for saving files
        Calendar c = Calendar.getInstance();
        int minutes = c.get(Calendar.MINUTE);
        Variables.setMin(minutes);

        int seconds = c.get(Calendar.SECOND);
        Variables.setSec(seconds);

    }


    public void go_experiment(View view) {


        EditText id = (EditText) findViewById(R.id.enteredID);

        //Error message if nothing was put in the ID field
        if(id.getText().toString().matches("")) {
            Toast.makeText(IntroForm.this, "Please enter your id", Toast.LENGTH_SHORT).show();
            return;
        }

        //Puts the ID to a string and saves it for use
        Variables.setUserID(Integer.parseInt(id.getText().toString()));

        //If the ID is valid, go on to the next step
        if(Variables.getUserID() != 0) {

            Intent intent = new Intent(this, Instruction.class);

            startActivity(intent);
            finish();
        }

    }
}
