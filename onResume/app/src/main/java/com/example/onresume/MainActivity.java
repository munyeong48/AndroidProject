package com.example.onresume;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("onresume","onresume");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("onstart","onstart");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("onrestart","onrestart");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("onPause","onPause");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("oncreate","oncreate");
    }
}