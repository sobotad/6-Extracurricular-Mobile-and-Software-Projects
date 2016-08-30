//Description: Displays the consent form to participants

package com.example.android.sentencesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class ConsentForm extends AppCompatActivity {

    @Override
    //generates the layout of the page
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consent_form);

        //Scrollable text field
        TextView text = (TextView) findViewById(R.id.body);
        text.setMovementMethod(new ScrollingMovementMethod());
    }

    //Go on to the next activity
    public void goInstruction(View view) {
        Intent intent = new Intent(this, IntroForm.class);
        startActivity(intent);
        finish();
    }

}
