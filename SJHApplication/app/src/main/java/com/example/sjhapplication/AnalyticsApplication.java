package com.example.sjhapplication;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.util.ArrayList;


public class AnalyticsApplication extends Application {

    private static Tracker sTracker;
    private static GoogleAnalytics sAnalytics;

    public void onCreate() {
        super.onCreate();

        sAnalytics = GoogleAnalytics.getInstance(this);
    }

    synchronized public Tracker getDefaultTracker() {
        if ( sTracker == null ) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
            sTracker.enableAdvertisingIdCollection(true);
            sTracker.enableExceptionReporting(true);
            sTracker.setAnonymizeIp(true);
//            sTracker.enableAutoActivityTracking(true); // 2018
        }
        return sTracker;
    }


    /* AsyncTask의 doInBackground에서 getAdvertisingIdInfo안에 존재하는 isLimitAdTrackingEnabled통해 사용자가 광고 맞춤 설정을 선택했는지 여부를 불린값으로 가져온다
     *  false : 제한을 하지 않는다 -> 수집에 동의한다
     *  AsyncTask 완료되면 onPostExecute에서 GAID 사용여부 확인 가능 --> 기본적 GAID 수집 허용
     *  해당 activity.java에 설정하고 있는 sTracker는 전역변수로 설정함*/
    class ADID extends AsyncTask<Void, Void, Boolean> {
        AdvertisingIdClient.Info idinfo = null;
        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                idinfo = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());
            } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException e) {
                e.printStackTrace();
            }
            Boolean TrackingEnable = null;
            try {
                TrackingEnable = idinfo.isLimitAdTrackingEnabled();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
            return TrackingEnable;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(!aBoolean) {
                sTracker.enableAdvertisingIdCollection(true);
            }
        }
    }



}
