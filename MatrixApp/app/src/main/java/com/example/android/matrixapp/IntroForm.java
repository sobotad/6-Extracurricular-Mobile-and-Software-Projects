//Users log in using their subject ID

package com.example.android.matrixapp;

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


    MediaPlayer background;
    double vol = 0.5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_form);

        //Gather date info for the filename
        Calendar c = Calendar.getInstance();
        int minutes = c.get(Calendar.MINUTE);
        Variables.setMin(minutes);

        int seconds = c.get(Calendar.SECOND);
        Variables.setSec(seconds);
    }


    public void go_experiment(View view) {

        //Error checking to make sure an ID was provided
        EditText id = (EditText) findViewById(R.id.enteredID);
        if(id.getText().toString().matches("")) {
            Toast.makeText(IntroForm.this, "Please enter your id", Toast.LENGTH_SHORT).show();
            return;
        }

        //Stores the ID
        Variables.setUserID(Integer.parseInt(id.getText().toString()));

        //If valid ID is given, move on
        if(Variables.getUserID() != 0) {
            Intent intent = new Intent(this, Instruction.class);

            startActivity(intent);
            finish();



        }

    }
}
