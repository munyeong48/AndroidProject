package com.example.gtmandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    public static String[]user_pseudo_id= new String[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.setUserId("qab");

        FirebaseAnalytics.getInstance(this).getAppInstanceId().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {

                    user_pseudo_id[0] = task.getResult();
                    Log.v("Firebase Instance ID = " , user_pseudo_id[0]);
                    //UserProperty
                    mFirebaseAnalytics.setUserProperty("user_property2","abc");
                    //UserId
                    mFirebaseAnalytics.setUserId(user_pseudo_id[0]);

                    // 여기 안으로 들어오면 onResume 단계가 되는듯
                    Bundle params = new Bundle();
                    params.putString("myshin", "myshin");
                    mFirebaseAnalytics.logEvent("myshin0414", params);

                    //mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.CAMPAIGN_DETAILS,bundle);

                }
            }//onComplete
        });
    }
}