//This activity gets info as to which ear is the cochlear implant, and which is normal
package com.example.android.matrixapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class SelectEar extends AppCompatActivity {

    int check = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ear);
    }

    public void setVolume(View view) {

        //Offer a choice, radio buttons, either left or right, answer is stored
        boolean checked = ((RadioButton) view).isChecked();
        Button submitbtn = (Button)findViewById(R.id.Submit);
        submitbtn.setClickable(true);
        submitbtn.setVisibility(View.VISIBLE);

        switch(view.getId()){

            //NOTE: Implanted ear set to 0, normal ear set to 1, for future reference
            case R.id.Left:
                if (checked){
                    check =1;
                    //Left, right, altleft, altright stored (This is used for playing audio to implanted ear, normal ear, etc.
                    //The reason there are alternates is because the audio played switches depending on whether it's the target sentence,
                    //or the matrix, so we can just use the same code, but just change the variables used. So the alts basically
                    //just flip it around, because the audio played itself gets flipped around
                    Variables.setLeft(0);
                    Variables.setRight(Variables.getNoiseVolR());
                    Variables.setaltLeft(Variables.getNoiseVolL());
                    Variables.setaltRight(0);
                    break;
                }

            case R.id.Right:
                if (checked) {
                    check=1;
                    Variables.setLeft(Variables.getNoiseVolL());
                    Variables.setRight(0);
                    Variables.setaltLeft(0);
                    Variables.setaltRight(Variables.getNoiseVolR());
                    break;
                }
        }

    }

    public void submit(View view){
        if (check ==1 ){
            Intent intent = new Intent(this, practice.class);
            startActivity(intent);
            finish();
        }
    }
}
