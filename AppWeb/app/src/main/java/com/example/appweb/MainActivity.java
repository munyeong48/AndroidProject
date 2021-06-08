package com.example.appweb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {
    public static FirebaseAnalytics mFirebaseAnalytics;
    public static String[]user_pseudo_id= new String[1];


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        /*
        Bundle bundle = new Bundle(); //Bundle 객체 생성
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "main"); //보고서-페이지 제목 및 화면 이름 or 이벤트-firebase_screen
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "main"); //보고서-이벤트-screen_view-firebase_screen_class
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle); //logEvent(이벤트 명, bundle)
*/


        FirebaseAnalytics.getInstance(this).getAppInstanceId().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {
                    user_pseudo_id[0] = task.getResult();
                    Log.v("Firebase Instance ID = " , user_pseudo_id[0]);
                    mFirebaseAnalytics.setUserProperty("user_property2",user_pseudo_id[0]);
                    mFirebaseAnalytics.setUserProperty("test",user_pseudo_id[0]);
                    mFirebaseAnalytics.setUserId(user_pseudo_id[0]);
                }
            }
        });
        //mFirebaseAnalytics.resetAnalyticsData(); // Firebase Analytics 를 reset하여 모든 analytics data와 appinstanceid를 초기화합니다.

//        mFirebaseAnalytics.setCurrentScreen(this,"myshinmain", "myshinmainactivity");

        //캠페인
//        Bundle campaignevent = new Bundle();
//        campaignevent.putString(FirebaseAnalytics.Param.CAMPAIGN,"myshin_Campaign");
//        campaignevent.putString(FirebaseAnalytics.Param.MEDIUM,"myshin_Medium");
//        campaignevent.putString(FirebaseAnalytics.Param.SOURCE,"myshin_Source");
//        campaignevent.putString(FirebaseAnalytics.Param.TERM,"myshin_Term");
//        campaignevent.putString(FirebaseAnalytics.Param.CONTENT,"myshin_Content");
//
//        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.CAMPAIGN_DETAILS, campaignevent);


        Button bt=(Button)findViewById(R.id.btn);

        bt.setOnClickListener(new View.OnClickListener() {
            @JavascriptInterface
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);


            }
        });
    }
}