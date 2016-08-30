//Rating activity. For every final submission, the user is asked to rate how good that match was, on a scale of 1-10

package com.example.android.matrixapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RatingBar;

import java.io.File;
import java.io.FileWriter;

public class Rating extends AppCompatActivity {

    //Variables
    double left = Variables.getLeft();
    double right = Variables.getRight();
    double cLeft = Variables.getaltLeft();
    double cRight = Variables.getaltRight();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
    }

    //Create buttons, one with the original material
    public void playOriginal(View view){

        MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift0_pitchshift0);

        mediaplayer.setVolume((float) cLeft, (float) cRight);
        mediaplayer.start();
    }

    //Other button uses the selection that they picked as their final answer
    public void playSelected(View view){

        String name = "az_19_20_" + Variables.getSelected();

        MediaPlayer mediaplayer = MediaPlayer.create(this, this.getResources().getIdentifier(name,"raw", getPackageName()));
        mediaplayer.setVolume((float) left, (float) right);
        mediaplayer.start();

    }


    public void submit(View view){

        //Grabs the rating from the page
        RatingBar rate = (RatingBar)findViewById(R.id.rating);
        Variables.setRating((int) rate.getRating());

        //Stores the value of the rating to the text file as well
        File path = Environment.getExternalStorageDirectory();
        File myFile = new File(path + "/" + Variables.getUserID() + "results-" + Variables.getMin() + "-" + Variables.getSec() + ".txt");
        try {

            FileWriter writer = new FileWriter(myFile, true);
            writer.write("Confidence Rating(0-10):   " + Variables.getRating() + "\r\n\r\n\r\n");
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //If five trials were completed, go to the end, else, just repeate the experiment until 5 trials done
        if(Variables.getTrial() == 5){
            Intent intent = new Intent(this, End.class);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(this, experiment.class);
            startActivity(intent);
            finish();
        }
    }
}
