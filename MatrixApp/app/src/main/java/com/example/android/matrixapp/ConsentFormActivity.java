//Displays consent form to the user
package com.example.android.matrixapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class ConsentFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consent_form);

        //Scrollable textfield
        TextView text = (TextView) findViewById(R.id.body);
        text.setMovementMethod(new ScrollingMovementMethod());
    }
    public void goInstruction(View view) {
        Intent intent = new Intent(this, IntroForm.class);
        startActivity(intent);
        finish();
    }


}
