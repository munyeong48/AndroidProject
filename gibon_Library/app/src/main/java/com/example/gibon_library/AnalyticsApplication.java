package com.example.gibon_library;

import android.app.Application;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

public class AnalyticsApplication extends Application {
    private static final String POPCORN_UA_ID = "UA-115948787-1";
    private static Tracker sTracker;
    private static GoogleAnalytics sAnalytics;

    public enum TrackerName {
        POPCORN_TRACKER,    // POPCORN 속성에 전송하는 tracker
        GLOBAL_TRACKER    // ROLLUP 속성에 전송하는 tracker
    }

    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    public AnalyticsApplication() {
        super();
    }

    synchronized Tracker getTracker(TrackerName trackerId) {
        if (!mTrackers.containsKey(trackerId)) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            Tracker t = (trackerId == TrackerName.POPCORN_TRACKER) ?
            analytics.newTracker(POPCORN_UA_ID) : analytics.newTracker(R.xml.global_tracker);
            t.enableAdvertisingIdCollection(true);
            t.enableExceptionReporting(true);
            mTrackers.put(trackerId, t);
        }
        return mTrackers.get(trackerId);
    }


}