package com.goldenplanet.www.gp_android.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.goldenplanet.www.gp_android.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;


public class FirebaseCloudMessagingFragment extends Fragment {
    private static Button logTokenButton;
    private static Button subscribeButton;
    private static Button subscribeButton_GPTEST;
    private static String TAG = "CloudMessagingFragment";
    com.google.firebase.analytics.FirebaseAnalytics mFirebaseAnalytics;

    public FirebaseCloudMessagingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

        View v = inflater.inflate(R.layout.fragment_firebase_cloud_messaging, container, false);

        com.google.firebase.analytics.FirebaseAnalytics mFirebaseAnalytics;
        mFirebaseAnalytics = com.google.firebase.analytics.FirebaseAnalytics.getInstance(getContext());
        mFirebaseAnalytics.setUserProperty("PRO1", "FCM");


        btnSetOnClickListener onClickListener = new btnSetOnClickListener() ;

        subscribeButton = v.findViewById(R.id.subscribeButton);
        subscribeButton.setOnClickListener(onClickListener);

        subscribeButton_GPTEST = v.findViewById(R.id.subscribeButton_GPTEST);
        subscribeButton_GPTEST.setOnClickListener(onClickListener);

        logTokenButton = v.findViewById(R.id.logTokenButton);
        logTokenButton.setOnClickListener(onClickListener);

        return v;
    }

    public class btnSetOnClickListener implements Button.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.logTokenButton:
                    FirebaseInstanceId.getInstance().getInstanceId()
                            .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                @Override
                                public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                    Log.e(TAG,task.getResult().getToken());
                                }
                            });

                    FirebaseInstanceId.getInstance().getInstanceId()
                            .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                @Override
                                public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                    if (!task.isSuccessful()) {
                                        return;
                                    }
                                    String token = task.getResult().getToken();
                                    String msg = getString(R.string.msg_token_fmt, token);
                                    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                                    Log.e(TAG+"토큰 가짜",token);
                                }
                            });
                    break;
                case R.id.subscribeButton_GPTEST:
                    FirebaseMessaging.getInstance().subscribeToTopic("GPTEST")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String msg = "GPTEST 토픽 구독 좋아요!";
                                    if (!task.isSuccessful()) {
                                        msg = getString(R.string.msg_subscribe_failed);
                                    }
                                    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                                }
                            });
                    break;
                case R.id.subscribeButton:
                    FirebaseMessaging.getInstance().subscribeToTopic("Cosmetic")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String msg = "화장품 토픽 구독 좋아요!";
                                    if (!task.isSuccessful()) {
                                        msg = getString(R.string.msg_subscribe_failed);
                                    }
                                    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                                }
                            });
                    break;
            }
        }
    }
}
