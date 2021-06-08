package com.example.gibon_library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        Tracker global_Tracker = application.getTracker(AnalyticsApplication.TrackerName.GLOBAL_TRACKER);	// 기본 속성
        Tracker popcorn_Tracker = application.getTracker(AnalyticsApplication.TrackerName.POPCORN_TRACKER);	// 팝콘스토어 속성

        HitBuilders.ScreenViewBuilder screenview_builder = new HitBuilders.ScreenViewBuilder();

        String screenName = "screenName";
        global_Tracker.setScreenName(screenName);
        popcorn_Tracker.setScreenName(screenName);

        screenview_builder.setCustomDimension(1, "dfb4419512ef9bf943a289888e164202");// User_ID
        screenview_builder.setCustomDimension(2, "M");			// User_gender
        screenview_builder.setCustomDimension(3, "25");			// User_age
        screenview_builder.setCustomDimension(4, "일반");			// User_rating
        screenview_builder.setCustomDimension(6, "Mozilla/5.0 Linux; Android 6.0");	// parma_agt
        screenview_builder.setCustomDimension(7, "Member");			// 로그인 여부
        screenview_builder.setCustomDimension(8, "android / ko");

        global_Tracker.send(screenview_builder.build());
        popcorn_Tracker.send(screenview_builder.build());

        HitBuilders.EventBuilder event_builder = new HitBuilders.EventBuilder();

        global_Tracker.setScreenName("screenName");
        popcorn_Tracker.setScreenName("screenName");

        event_builder.setCategory("category");
        event_builder.setAction("action");
        event_builder.setLabel("label");

        event_builder.setCustomDimension(1, "dfb4419512ef9bf943a289888e164202");	// User_ID
        event_builder.setCustomDimension(2, "M");			// User_gender
        event_builder.setCustomDimension(3, "25");			// User_age
        event_builder.setCustomDimension(4, "일반");			// User_rating
        event_builder.setCustomDimension(6, "Mozilla/5.0 Linux; Android 6.0");		// parma_agt
        event_builder.setCustomDimension(7, "Member");			// 로그인 여부
        event_builder.setCustomDimension(8, "android / ko");			// param_dvc

        global_Tracker.send(event_builder.build());
        popcorn_Tracker.send(event_builder.build());
    }
}