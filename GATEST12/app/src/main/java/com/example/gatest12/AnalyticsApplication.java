package com.example.gatest12;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public class AnalyticsApplication extends Application {

    private static Tracker sTracker;
    private static GoogleAnalytics sAnalytics;
    @Override
    public void onCreate() {
        super.onCreate();
        sAnalytics = GoogleAnalytics.getInstance(this);
    }

    synchronized public Tracker getDefaultTracker(){
        if(sTracker == null){
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
            sTracker.enableAdvertisingIdCollection(true);
            sTracker.enableExceptionReporting(true);
            sTracker.setAnonymizeIp(true);

        }
        return sTracker;
    }
}
