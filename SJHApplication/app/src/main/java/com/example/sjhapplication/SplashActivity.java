package com.example.sjhapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

//implements InstallReferrerStateListener
public class SplashActivity extends AppCompatActivity {
    private InstallReferrerClient mReferrerClient;
    public static FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spla);
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        firebaseAnalytics.getInstance(this).getAppInstanceId().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {
                    GoogleAnalytics.user_pseudo_id[0] = task.getResult();
                    if (null != SplashActivity.this.getIntent().getData()) {
                        Uri data = SplashActivity.this.getIntent().getData();
                        if (data != null && data.isHierarchical()) {
                            try {
                                splitQuery(data, "deeplink");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        // fbcampaign
//        mReferrerClient = InstallReferrerClient.newBuilder(SplashActivity.this).build();
//        mReferrerClient.startConnection(SplashActivity.this);

        if (null != SplashActivity.this.getIntent().getData()) {
            Uri data = SplashActivity.this.getIntent().getData();
            if (data != null && data.isHierarchical()) {
                try {
                    splitQuery(data, "deeplink");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }


        // 화면전환
        Handler hd = new Handler();
        hd.postDelayed(new SplashActivity.splashhandler(), 2000); // 1초 후에 hd handler 실행  3000ms = 3초
}



        //2019
//        Uri data = this.getIntent().getData();
//        String url = "";
//
//        try {
//            if (data != null && data.isHierarchical()) {
//                url = this.getIntent().getDataString();
//            }
//
//            if (url != "" && url.contains("utm_source")) {
//                url = URLDecoder.decode(url, "UTF-8" );
//                Map<String, String> DeepLink_screen_Map = new HashMap<String, String>();
//
//                if (url != "" && url.contains("PushParam")) {
//                    String [] PushParam = url.split("&");
//                    for (String pair : PushParam) {
//                        int idx = pair.indexOf("=");
//                        if (pair.contains("PushParam")) DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3, pair.substring(idx +1));
//                        }
//                }
//
//                DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.UserID, "사용자 ID" );
//                DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.Title, "화면 명" );
//                DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.CampaignUrl, url );
//
//                GoogleAnalyticsBuilder gb = new GoogleAnalyticsBuilder (this);
//                gb.GADataSend_Screen(DeepLink_screen_Map);
//            }
//        } catch(Exception e){
//            Log.v("Error" , e.getMessage());
//        }


//        Uri data = this.getIntent().getData();
//        String url = "";
//        try{
//        if (data != null && data.isHierarchical()){
//            url = this.getIntent().getDataString();
//        }
//        if (url != "" && url.contains("utm_source")){
//            url = URLDecoder.decode(url, "UTF-8");
//            //url = URLDecoder.decode(url, "UTF-8");
//            Map<String, String> DeepLink_screen_Map = new HashMap<String, String>();
//
//            if(url != "" && url.contains("PushParam")){
//                String [] PushParam = url.split("&");
//                for (String pair : PushParam) {
//                    int idx = pair.indexOf("=");
//                    if (pair.contains("PushParam")) DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3, pair.substring(idx + 1));
//                }
//            }
//
//            DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.UserID, "jhshim");
//            DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.Title, "화면 명");
//            DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.CampaignUrl, url);
//
//            GoogleAnalyticsBuilder gb = new GoogleAnalyticsBuilder (this);
//            gb.GADataSend_Screen(DeepLink_screen_Map);
//        }
//        }catch (Exception e){
//            Log.v("Error",e.getMessage());
//        }


//    }

//    @Override
//    public void onInstallReferrerServiceDisconnected(){
//    }
//
//
//    @Override
//    public void onInstallReferrerSetupFinished(int responseCode){
//        if (responseCode == InstallReferrerClient.InstallReferrerResponse.OK) {
//            try {
//                // CheckAppFirstExecute -> 앱 최초 실행 확인
//                if (CheckAppFirstExecute()) {
//                    getReferral();
//                }
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        } else {
//            mReferrerClient.endConnection();
//        }
//    }
//
//    private void getReferral() throws RemoteException {
//        ReferrerDetails response = mReferrerClient.getInstallReferrer();
//        String referrerData = response.getInstallReferrer();
//
//        try {
//            splitQuery(Uri.parse(referrerData), "install");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        mReferrerClient.endConnection();
//    }
//
//
//    public boolean CheckAppFirstExecute(){
//        SharedPreferences pref = getSharedPreferences("isFirst", Activity.MODE_PRIVATE);
//        boolean isFirst = pref.getBoolean("isFirst", false);
//        if (!isFirst){
//            SharedPreferences.Editor editor = pref.edit();
//            editor.putBoolean("isFirst", true);
//            editor.commit();
//        }
//        return !isFirst;
//    }
//
//
//
        public static void splitQuery (Uri uri, String data) throws UnsupportedEncodingException {
            String query = uri.toString();
            Bundle bundle = new Bundle();


            if (data.equals("install")) bundle.putString("install", "캠페인>앱설치");
            if (data.equals("deeplink")) bundle.putString("deeplink", "캠페인>딥링크");

            if (uri.toString().contains("://")) {
                query = uri.getQuery();
            }
            if (query != null) {
                String[] pairs = query.split("&");
                for (String pair : pairs) {
                    int idx = pair.indexOf("=");
                    if (pair.contains("utm_source"))
                        bundle.putString(FirebaseAnalytics.Param.SOURCE, URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                    else if (pair.contains("utm_medium"))
                        bundle.putString(FirebaseAnalytics.Param.MEDIUM, URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                    else if (pair.contains("utm_term"))
                        bundle.putString(FirebaseAnalytics.Param.TERM, URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                    else if (pair.contains("utm_content"))
                        bundle.putString(FirebaseAnalytics.Param.CONTENT, URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                    else if (pair.contains("utm_campaign"))
                        bundle.putString(FirebaseAnalytics.Param.CAMPAIGN, URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                }
            }

            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


            Map<String, String> campaignMap = new HashMap<String, String>();
//            String query = uri.toString();
            if (data.equals("deeplink")) campaignMap.put("dt", "캠페인>딥링크");
            if (data.equals("install")) campaignMap.put("dt", "캠페인>앱설치");
            campaignMap.put("t", "pageview");
            campaignMap.put("ni", "1");
            campaignMap.put("dl", "http://www.goldenplanet.co.kr/campaign.do");

            if (uri.toString().contains("://")) query = uri.getQuery();
            if (query != null) {
                String[] pairs = query.split("&");
                for (String pair : pairs) {
                    int idx = pair.indexOf("=");
                    if (pair.contains("utm_source"))
                        campaignMap.put("cs", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                    else if (pair.contains("utm_medium"))
                        campaignMap.put("cm", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                    else if (pair.contains("utm_campaign"))
                        campaignMap.put("cn", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                    else if (pair.contains("utm_term"))
                        campaignMap.put("ck", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                    else if (pair.contains("utm_content"))
                        campaignMap.put("cc", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                }
            }
            Thread t = new Thread(new GoogleAnalytics.gaThread(campaignMap));
            t.start();


        }


        private class splashhandler implements Runnable {
            public void run() {
                startActivity(new Intent(getApplication(), MainActivity.class)); //로딩이 끝난 후, ChoiceFunction 이동
                SplashActivity.this.finish(); // 로딩페이지 Activity stack에서 제거
            }
        }


}