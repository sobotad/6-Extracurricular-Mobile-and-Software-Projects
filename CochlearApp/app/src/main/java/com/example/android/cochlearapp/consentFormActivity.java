//Description: Displays consent form to users

package com.example.android.cochlearapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class consentFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consent_form);

        //Sets the textview to be scrollable, allowing us to fit a lot of info there
        TextView text = (TextView) findViewById(R.id.body);
        text.setMovementMethod(new ScrollingMovementMethod());
    }
    public void goInstruction(View view) {
        Intent intent = new Intent(this, IntroForm.class);
        startActivity(intent);
        finish();
    }


}
