//The main experiment. This activity loops itself multiple times until all 12 words are presented and recorded, then the app ends

package com.example.android.cochlearapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;

public class experiment extends AppCompatActivity {


    //invokes the list class to get a random word to be used for that trial
    String name = list.get();

    //checks to see if something was selected, and whether the selection was correct
    String correct= "0";
    String prev_checked = "";
    int check = 0;
    int audioPlayed = 0;


    //radio group variables
    RadioGroup rg1;
    RadioGroup rg2;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experiment);

        TextView text = (TextView)findViewById(R.id.trial);
        text.setText("Trial " + (Variables.getTrial()) + "/12:");

        

        //Checks used to clear radio groups. Basically, there are two radiogroups, so before, you could have a selection in each group
        //with the following code, whenever a button is selected, the other radio group is cleared, to prevent having multiple selections
        audioPlayed=0;
        rg1 = (RadioGroup)findViewById(R.id.radioGroup1);
        rg2 = (RadioGroup)findViewById(R.id.radioGroup2);
        rg1.clearCheck();
        rg2.clearCheck();
        rg1.setOnCheckedChangeListener(listener1);
        rg2.setOnCheckedChangeListener(listener2);
    }

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg2.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                rg2.clearCheck(); // clear the second RadioGroup!
                rg2.setOnCheckedChangeListener(listener2); //reset the listener
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
            }
        }
    };

    //Button that plays the target word. Once the word plays, the button is greyed out and not usable
    public void playAudio(View view) {
        Button btn = (Button)findViewById(R.id.playAudio);
        btn.setEnabled(false);
        btn.setClickable(false);
        audioPlayed=1;

        //passes in the word generated above
        MediaPlayer mediaPlayer = MediaPlayer.create(this, this.getResources().getIdentifier(name.toLowerCase(),"raw", getPackageName()));
        mediaPlayer.setVolume(1, 1);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you

        if(check==1) {

            Button submitbtn = (Button) findViewById(R.id.Submit);
            submitbtn.setClickable(true);
            submitbtn.setVisibility(View.VISIBLE);
        }
    }

    //Submit button
    //The submit button will do nothing until an option is selected, updating the 'selected' check variable
    public void recordAnswer(View view) {

        if(audioPlayed==1) {

            Button submitbtn = (Button) findViewById(R.id.Submit);
            submitbtn.setClickable(true);
            submitbtn.setVisibility(View.VISIBLE);
        }
        boolean checked = ((RadioButton) view).isChecked();

        //Go through all cases. Each button is tied with the appropriate string. That button is compared to the string generated at the beginning (The word that was played), and sees if it matches up. If they match, selected is updated to 1
        switch(view.getId()){
            case R.id.bait:
                if (checked) {
                    Variables.setSelected("BAIT");
                    check = 1;
                    break;
                }

            case R.id.bart:
                if (checked) {
                    Variables.setSelected("BART");
                    check = 1;
                    break;
                }

            case R.id.bat:
                if (checked) {
                    Variables.setSelected("BAT");
                    check = 1;
                    break;
                }

            case R.id.beet:
                if (checked) {
                    Variables.setSelected("BEET");
                    check = 1;
                    break;
                }

            case R.id.burt:
                if (checked) {
                    Variables.setSelected("BURT");
                    check = 1;
                    break;
                }

            case R.id.bet:
                if (checked) {
                    Variables.setSelected("BET");
                    check = 1;
                    break;
                }

            case R.id.bit:
                if (checked) {
                    Variables.setSelected("BIT");
                    check = 1;
                    break;
                }

            case R.id.bite:
                if (checked) {
                    Variables.setSelected("BITE");
                    check = 1;
                    break;
                }

            case R.id.boot:
                if (checked) {
                    Variables.setSelected("BOOT");
                    check = 1;
                    break;
                }

            case R.id.bought:
                if (checked) {
                    Variables.setSelected("BOUGHT");
                    check = 1;
                    break;
                }

            case R.id.bout:
                if (checked) {
                    Variables.setSelected("BOUT");
                    check = 1;
                    break;
                }

            case R.id.but:
                if (checked) {
                    Variables.setSelected("BUT");
                    check = 1;
                    break;
                }
        }
    }

    public void submit(View view) {

        //if an option was selected, this triggers
        if(check == 1) {
            //Check to see if the answered word is correct
            if (name == Variables.getSelected())
                correct="1";
            //Toast.makeText(experiment.this, name + " " + Variables.getSelected() + " " + correct, Toast.LENGTH_SHORT).show();

            //Check to see if all words were presented, if so, end the app
            if (Variables.getTrial() == 12)
            {
                Intent intent = new Intent(this, End.class);
                startActivity(intent);
                finish();
            }

            //Otherwise, loop through the activity. The checks are located in the list code
            else {
                Intent intent = new Intent(this, experiment.class);
                startActivity(intent);
                finish();
            }

            //Code to create a text file and save relevant info. This code will append each time, so this activity can safely loop and keep adding to the text file
            File path = Environment.getExternalStorageDirectory();
            File myFile = new File(path + "/", Variables.getUserID() + "results-" + Variables.getMin() + "-" + Variables.getSec() + ".txt");
            try {

                FileWriter writer = new FileWriter(myFile, true);
                writer.write(name + " " + Variables.getSelected() + " " + correct + "\r\n");
                writer.flush();
                writer.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
