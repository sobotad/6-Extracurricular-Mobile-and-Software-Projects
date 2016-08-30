//Displays the instructions for the user

package com.example.android.cochlearapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Instruction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

    }
    public void go_experiment(View view) {

        Intent intent = new Intent(this, practice.class);
        startActivity(intent);
        finish();
    }
}
