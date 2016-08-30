//Experiment time! Same as practice, but now with a play audio button that plays a normal sentence to the other, implanted ear

package com.example.android.matrixapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

public class experiment extends AppCompatActivity {

    //Variables needed
    int check = 0;
    double left = Variables.getLeft();
    double right = Variables.getRight();
    double cLeft = Variables.getaltLeft();
    double cRight = Variables.getaltRight();

    //RadioGroups
    RadioGroup rg1;
    RadioGroup rg2;
    RadioGroup rg3;
    RadioGroup rg4;
    RadioGroup rg5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);
        TextView text = (TextView)findViewById(R.id.trial);
        text.setText("Trial "+ (Variables.getTrial()+1) +"/5:");


        //Following code creates and checks the radiogroups, clearing the others if a button is pressed
        rg1 = (RadioGroup)findViewById(R.id.radioGroup1);
        rg2 = (RadioGroup)findViewById(R.id.radioGroup2);
        rg3 = (RadioGroup)findViewById(R.id.radioGroup3);
        rg4 = (RadioGroup)findViewById(R.id.radioGroup4);
        rg5 = (RadioGroup)findViewById(R.id.radioGroup5);

        rg1.clearCheck();
        rg2.clearCheck();
        rg3.clearCheck();
        rg4.clearCheck();
        rg5.clearCheck();
        rg1.setOnCheckedChangeListener(listener1);
        rg2.setOnCheckedChangeListener(listener2);
        rg3.setOnCheckedChangeListener(listener3);
        rg4.setOnCheckedChangeListener(listener4);
        rg5.setOnCheckedChangeListener(listener5);
    }

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg2.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                rg2.clearCheck(); // clear the second RadioGroup!
                rg2.setOnCheckedChangeListener(listener2);

                rg3.setOnCheckedChangeListener(null);
                rg3.clearCheck();
                rg3.setOnCheckedChangeListener(listener3);

                rg4.setOnCheckedChangeListener(null);
                rg4.clearCheck();
                rg4.setOnCheckedChangeListener(listener4);

                rg5.setOnCheckedChangeListener(null);
                rg5.clearCheck();
                rg5.setOnCheckedChangeListener(listener5);
            }
        }
    };

    private  RadioGroup.OnCheckedChangeListener listener2 = new  RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg1.setOnCheckedChangeListener(null);
                rg1.clearCheck();
                rg1.setOnCheckedChangeListener(listener1);

                rg3.setOnCheckedChangeListener(null);
                rg3.clearCheck();
                rg3.setOnCheckedChangeListener(listener3);

                rg4.setOnCheckedChangeListener(null);
                rg4.clearCheck();
                rg4.setOnCheckedChangeListener(listener4);

                rg5.setOnCheckedChangeListener(null);
                rg5.clearCheck();
                rg5.setOnCheckedChangeListener(listener5);
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener3 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg1.setOnCheckedChangeListener(null);
                rg1.clearCheck();
                rg1.setOnCheckedChangeListener(listener1);

                rg2.setOnCheckedChangeListener(null);
                rg2.clearCheck();
                rg2.setOnCheckedChangeListener(listener2);

                rg4.setOnCheckedChangeListener(null);
                rg4.clearCheck();
                rg4.setOnCheckedChangeListener(listener4);

                rg5.setOnCheckedChangeListener(null);
                rg5.clearCheck();
                rg5.setOnCheckedChangeListener(listener5);
            }
        }
    };

    private  RadioGroup.OnCheckedChangeListener listener4 = new  RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg1.setOnCheckedChangeListener(null);
                rg1.clearCheck();
                rg1.setOnCheckedChangeListener(listener1);

                rg2.setOnCheckedChangeListener(null);
                rg2.clearCheck();
                rg2.setOnCheckedChangeListener(listener2);

                rg3.setOnCheckedChangeListener(null);
                rg3.clearCheck();
                rg3.setOnCheckedChangeListener(listener3);

                rg5.setOnCheckedChangeListener(null);
                rg5.clearCheck();
                rg5.setOnCheckedChangeListener(listener5);
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener5 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg1.setOnCheckedChangeListener(null);
                rg1.clearCheck();
                rg1.setOnCheckedChangeListener(listener1);

                rg2.setOnCheckedChangeListener(null);
                rg2.clearCheck();
                rg2.setOnCheckedChangeListener(listener2);

                rg4.setOnCheckedChangeListener(null);
                rg4.clearCheck();
                rg4.setOnCheckedChangeListener(listener4);

                rg3.setOnCheckedChangeListener(null);
                rg3.clearCheck();
                rg3.setOnCheckedChangeListener(listener3);
            }
        }
    };

    //Play audio, plays normal material to the implanted ear. The rest plays altered versions to the good ear
    public void playAudio(View view) {

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift0_pitchshift0);

        //I swapped these values, here's the reasoning: I set the volume for the normal ear to 1, so it all the matrix will play to the normal ear. All that leaves is for the target to be played
        //to the implant ear, so by swapping the values, it will now play the target to the cochlear ear.
        mediaPlayer.setVolume((float)cLeft, (float)cRight);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
    }

    public void recordAnswer(View view) {
        //Makes the submit button appear
        boolean checked = ((RadioButton) view).isChecked();
        Button submitbtn = (Button)findViewById(R.id.Submit);
        submitbtn.setClickable(true);
        submitbtn.setVisibility(View.VISIBLE);

        //Go through all cases. Each button is tied with the appropriate string. That button is compared to the string generated at the beginning (The word that was played), and sees if it matches up. If they match, selected is updated to 1
        //Saves which values were selected, writes which ones were pressed, and records the final submission separately
        switch(view.getId()){
            case R.id.one:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift0_pitchshift0);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift0_pitchshift0");
                    Variables.setNum(1);
                    write();
                    check = 1;
                    break;
                }

            case R.id.two:
                if (checked) {

                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift100_pitchshift0);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift100_pitchshift0");
                    Variables.setNum(2);
                    check = 1;
                    write();
                    break;
                }

            case R.id.three:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift200_pitchshift0);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift200_pitchshift0");
                    Variables.setNum(3);
                    check = 1;
                    write();
                    break;
                }

            case R.id.four:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift300_pitchshift0);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift300_pitchshift0");
                    Variables.setNum(4);
                    check = 1;
                    write();
                    break;
                }

            case R.id.five:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift400_pitchshift0);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift400_pitchshift0");
                    Variables.setNum(5);
                    check = 1;
                    write();
                    break;
                }

            case R.id.six:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift0_pitchshift25);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift0_pitchshift25");
                    Variables.setNum(6);
                    check = 1;
                    write();
                    break;
                }

            case R.id.seven:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift100_pitchshift25);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift100_pitchshift25");
                    Variables.setNum(7);
                    check = 1;
                    write();
                    break;
                }

            case R.id.eight:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift200_pitchshift25);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift200_pitchshift25");
                    Variables.setNum(8);
                    check = 1;
                    write();
                    break;
                }

            case R.id.nine:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift300_pitchshift25);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift300_pitchshift25");
                    Variables.setNum(9);
                    check = 1;
                    write();
                    break;
                }

            case R.id.ten:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift400_pitchshift25);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift400_pitchshift25");
                    Variables.setNum(10);
                    check = 1;
                    write();
                    break;
                }

            case R.id.eleven:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift0_pitchshift50);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift0_pitchshift50");
                    Variables.setNum(11);
                    check = 1;
                    write();
                    break;
                }

            case R.id.twelve:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift100_pitchshift50);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift100_pitchshift50");
                    Variables.setNum(12);
                    check = 1;
                    write();
                    break;
                }

            case R.id.thirteen:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift200_pitchshift50);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift200_pitchshift50");
                    Variables.setNum(13);
                    check = 1;
                    write();
                    break;
                }

            case R.id.fourteen:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift300_pitchshift50);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift300_pitchshift50");
                    Variables.setNum(14);
                    check = 1;
                    write();
                    break;
                }

            case R.id.fifteen:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift400_pitchshift50);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift400_pitchshift50");
                    Variables.setNum(15);
                    check = 1;
                    write();
                    break;
                }

            case R.id.sixteen:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift0_pitchshift75);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift0_pitchshift75");
                    Variables.setNum(16);
                    check = 1;
                    write();
                    break;
                }

            case R.id.seventeen:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift100_pitchshift75);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift100_pitchshift75");
                    Variables.setNum(17);
                    check = 1;
                    write();
                    break;
                }

            case R.id.eighteen:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift200_pitchshift75);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift200_pitchshift75");
                    Variables.setNum(18);
                    check = 1;
                    write();
                    break;
                }

            case R.id.nineteen:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift300_pitchshift75);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift300_pitchshift75");
                    Variables.setNum(19);
                    check = 1;
                    write();
                    break;
                }

            case R.id.twenty:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift400_pitchshift75);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift400_pitchshift75");
                    Variables.setNum(20);
                    check = 1;
                    write();
                    break;
                }

            case R.id.twentyone:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift0_pitchshift100);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift0_pitchshift100");
                    Variables.setNum(21);
                    check = 1;
                    write();
                    break;
                }

            case R.id.twentytwo:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift100_pitchshift100);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift100_pitchshift100");
                    Variables.setNum(22);
                    check = 1;
                    write();
                    break;
                }

            case R.id.twentythree:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift200_pitchshift100);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift200_pitchshift100");
                    Variables.setNum(23);
                    check = 1;
                    write();
                    break;
                }

            case R.id.twentyfour:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift300_pitchshift100);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift300_pitchshift100");
                    Variables.setNum(24);
                    check = 1;
                    write();
                    break;
                }

            case R.id.twentyfive:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift400_pitchshift100);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    Variables.setSelected("formatshift400_pitchshift100");
                    Variables.setNum(25);
                    check = 1;
                    write();
                    break;
                }
        }
    }

    //Function that stores the info to the text file
    public void write(){

        //Every button pressed is recorded, making sure to specify that these presses are NOT the final answer
        File path = Environment.getExternalStorageDirectory();
        File myFile = new File(path + "/" + Variables.getUserID() + "results-" + Variables.getMin() + "-" + Variables.getSec() + ".txt");
        try {

            FileWriter writer = new FileWriter(myFile, true);
            writer.write("User pressed: " + Variables.getSelected() + " . Number button pressed: " + Variables.getNum() + "\r\n");
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Final submission
    public void submit(View view) {

        //if an option was selected, this triggers
        if(check == 1) {



            //Confirm the decision
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure of your decision?");
            builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    goEnd();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });

            // Create the AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();

        }

    }

    public void goEnd(){

        //If the user submits their decision, the "final press" gets recorded to the text file as well
        File path = Environment.getExternalStorageDirectory();
        File myFile = new File(path + "/" + Variables.getUserID() + "results-" + Variables.getMin() + "-" + Variables.getSec() + ".txt");
        try {

            FileWriter writer = new FileWriter(myFile, true);
            writer.write("\r\n" + "Final Selection: " + Variables.getSelected() + " . Final number pressed: " + Variables.getNum() + "\r\n");
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Increment trial count, and go get a rating
        Variables.incrementTrial();

        Intent intent = new Intent(this, Rating.class);
        startActivity(intent);
        finish();


    }
}
