package com.ite.schoolapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;


public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);

        //let it wait for 3 seconds and then go to login activity
        new android.os.Handler().postDelayed(
                () -> {
                    Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                },
                3000);
    }


}
