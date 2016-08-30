//Description: Handles the user inputting their info into a form

package com.example.android.sentencesapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class IntroForm extends AppCompatActivity {


    //volume defaults to half
    double vol = 0.5;
    @Override

    //sets up the layout of the file, and generates time data for the file
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_form);

        //generates the current minutes and seconds at this moment in time
        Calendar c = Calendar.getInstance();
        int minutes = c.get(Calendar.MINUTE);
        Variables.setMin(minutes);

        int seconds = c.get(Calendar.SECOND);
        Variables.setSec(seconds);
    }

    //When the user hits the next/submit button
    public void go_experiment(View view) {

        //Pulls the name and ID from the form, looking it up by ID (make sure these fields have an ID set in the layout field). Puts these fields into variables
        EditText id = (EditText) findViewById(R.id.enteredID);
        if(id.getText().toString().matches("")) {
            Toast.makeText(IntroForm.this, "Please enter your id", Toast.LENGTH_SHORT).show();
            return;
        }

        //Sets the global variables for the user's name and ID

        Variables.setUserID(Integer.parseInt(id.getText().toString()));

        //If nothing was entered for user ID, nothing happens. They cannot progress until they enter a number
        if(Variables.getUserID() != 0) {




            //Go to the sentence material
            Intent intent = new Intent(this, Instruction.class);

            startActivity(intent);
            finish();



        }

    }
}
