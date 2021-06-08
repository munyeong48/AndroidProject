package com.example.cgvtest;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

public class AnalyticsApplication extends Application {
    private static final String FASTORDER = "UA-115948787-1";
    public enum TrackerName {
        INTEGRATED_PAYMENT,
        FASTORDER
    }

    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    public AnalyticsApplication() {
        super();
    }

    synchronized Tracker getTracker(TrackerName trackerId) {
        if (!mTrackers.containsKey(trackerId)) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            Tracker t = (trackerId == TrackerName.FASTORDER) ?
            analytics.newTracker(FASTORDER) : analytics.newTracker(R.xml.global_tracker);
            t.enableAdvertisingIdCollection(true);
            t.enableExceptionReporting(true);
            mTrackers.put(trackerId, t);
        }
        return mTrackers.get(trackerId);
    }

}


