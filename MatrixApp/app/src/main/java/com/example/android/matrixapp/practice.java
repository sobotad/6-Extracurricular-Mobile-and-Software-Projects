//practice, so the user gets accustomed to the matrix interface

package com.example.android.matrixapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class practice extends AppCompatActivity {

    //Variables
    int counter = 0;
    double left = Variables.getLeft();
    double right = Variables.getRight();

    //Radiogroups, similar to the Cochlear Implant setup. Now, we have 5 different radio groups to deal with
    RadioGroup rg1;
    RadioGroup rg2;
    RadioGroup rg3;
    RadioGroup rg4;
    RadioGroup rg5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);


        //Setting up the radiogroups and clearing them
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

    //For the following code, when a button in one radio group is selected, all the others are cleared, to prevent having multiple selections at once

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

    //When a user hits the button
    public void recordAnswer(View view) {
        boolean checked = ((RadioButton) view).isChecked();


        //For the practice, users have to hit certain buttons in a certain order, this is handled by a series of checks, and updating a counter when the appropriate button is pressed,
        //updating the text, and the button that the user then needs to hit
        switch(view.getId()){
            case R.id.one:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift0_pitchshift0);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();

                    if(counter == 3){
                        TextView text = (TextView)findViewById(R.id.descrip);
                        text.setText("Last one! Hit the bottom right!");
                        counter++;
                    }

                    break;
                }

            case R.id.two:
                if (checked) {

                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift100_pitchshift0);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();

                    break;
                }

            case R.id.three:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift200_pitchshift0);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.four:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift300_pitchshift0);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.five:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift400_pitchshift0);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();

                    if(counter == 4){
                        TextView text = (TextView)findViewById(R.id.descrip);
                        text.setText("All done! Click Submit to start the experiment!");
                        counter++;

                        Button btn = (Button)findViewById(R.id.Submit);
                        btn.setClickable(true);
                        btn.setVisibility(View.VISIBLE);

                    }
                    break;
                }

            case R.id.six:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift0_pitchshift25);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.seven:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift100_pitchshift25);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.eight:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift200_pitchshift25);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.nine:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift300_pitchshift25);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.ten:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift400_pitchshift25);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.eleven:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift0_pitchshift50);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.twelve:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift100_pitchshift50);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.thirteen:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift200_pitchshift50);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();

                    if(counter == 2){
                        TextView text = (TextView)findViewById(R.id.descrip);
                        text.setText("Awesome! Now the bottom left!");
                        counter++;
                    }
                    break;
                }

            case R.id.fourteen:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift300_pitchshift50);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.fifteen:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift400_pitchshift50);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.sixteen:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift0_pitchshift75);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.seventeen:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift100_pitchshift75);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.eighteen:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift200_pitchshift75);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.nineteen:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift300_pitchshift75);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.twenty:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift400_pitchshift75);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.twentyone:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift0_pitchshift100);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();

                    if(counter == 0){
                        TextView text = (TextView)findViewById(R.id.descrip);
                        text.setText("Great! Now select the upper right corner.");
                        counter++;
                    }
                    break;
                }

            case R.id.twentytwo:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift100_pitchshift100);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.twentythree:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift200_pitchshift100);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.twentyfour:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift300_pitchshift100);
                    mediaPlayer.setVolume((float)left, (float)right);
                    mediaPlayer.start();
                    break;
                }

            case R.id.twentyfive:
                if (checked) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.az_19_20_formatshift400_pitchshift100);
                    mediaPlayer.setVolume((float) left, (float) right);
                    mediaPlayer.start();

                    if(counter == 1){
                        TextView text = (TextView)findViewById(R.id.descrip);
                        text.setText("Great! Now select the middle!");
                        counter++;
                    }
                    break;
                }
        }
    }

    public void submit(View view) {

        Intent intent = new Intent(this, experiment.class);
        startActivity(intent);
        finish();

    }
}
