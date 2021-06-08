package com.goldenplanet.www.gp_android.GoogleAnalytics;

import android.app.Activity;
import android.app.Application;
import android.os.AsyncTask;

import com.goldenplanet.www.gp_android.R;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.IOException;

public class AnalyticsApplication extends Application {

    private static GoogleAnalytics sAnalytics;
    public static Tracker sTracker;
    public static String GAID = "";
    public static String APPCheck = "Dev";
    public static FirebaseAnalytics mFirebaseAnalytics;
    public static Activity activity;
    @Override
    public void onCreate() {
        super.onCreate();

        sAnalytics = GoogleAnalytics.getInstance(this);
        sAnalytics.setLocalDispatchPeriod(1);
        AsyncTask<Void, Void, String> task = new GAID();
        task.execute();
        activity = new Activity();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

    }

    class GAID extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... voids) {
            String advertisingId = "";
            try{
                AdvertisingIdClient.Info info = AdvertisingIdClient.getAdvertisingIdInfo(AnalyticsApplication.this);
                advertisingId = info.getId();
            }catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException e){
                e.printStackTrace();
            }
            return advertisingId;
        }
        @Override
        protected void onPostExecute(String id) {
            super.onPostExecute(id);
            GAID = id;
        }
    }


    public Tracker getDefaultTracker() {
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
            sTracker.enableAdvertisingIdCollection(true);
            sTracker.enableExceptionReporting(true);
            sTracker.setAnonymizeIp(true);
//            sTracker.enableAutoActivityTracking(true);
        }
        return sTracker;
    }
}