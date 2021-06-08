package com.goldenplanet.www.gp_android.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.goldenplanet.www.gp_android.R;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;


public class FirebaseAnalytics extends Fragment {
    private static Button TestA;
    private static Button TestB;
    private static Button APPCALL;
    private static String TAG = "FirebaseAnalytics";
    com.google.firebase.analytics.FirebaseAnalytics mFirebaseAnalytics;

    public FirebaseAnalytics() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_firebase_analytics, container, false);

        mFirebaseAnalytics = com.google.firebase.analytics.FirebaseAnalytics.getInstance(getContext());


        Bundle params = new Bundle();
        params.putString("ScreenDepth1", "screen_depth1_" + TAG);
        params.putString("ScreenDepth2", "screen_depth2_" + TAG);
        params.putString("ScreenDepth3", "screen_depth3_" + TAG);
        params.putString("ScreenDepth4", "screen_depth4_" + TAG);
        params.putString("ScreenDepth5", "screen_depth5_" + TAG);
        params.putFloat("FloatData_" + TAG, 1);
        params.putDouble("DoubleData_" + TAG, 1);
        params.putInt("IntData_" + TAG, 1);
        mFirebaseAnalytics.logEvent("screenview", params);
        btnSetOnClickListener onClickListener = new btnSetOnClickListener() ;

        TestA = v.findViewById(R.id.btn_firebase_analytics_a);
        TestA.setOnClickListener(onClickListener);

        TestB = v.findViewById(R.id.btn_firebase_analytics_b);
        TestB.setOnClickListener(onClickListener);

        APPCALL = v.findViewById(R.id.btn_call_bapp);
        APPCALL.setOnClickListener(onClickListener);

        return v;
    }

    public class btnSetOnClickListener implements Button.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_firebase_analytics_a:
                    mFirebaseAnalytics.setUserProperty("PRO1", "TestA");
                    break;
                case R.id.btn_firebase_analytics_b:
                    mFirebaseAnalytics.setUserProperty("PRO1", "TestB");
                    break;
            }
        }
    }
}
