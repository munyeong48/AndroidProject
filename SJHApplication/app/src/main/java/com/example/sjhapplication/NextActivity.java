package com.example.sjhapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class NextActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        //GAID
        Intent intent = getIntent();
        String GAID = intent.getStringExtra("GAID");
        Log.d("gaid: ",GAID);

    }
}