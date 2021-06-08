package com.goldenplanet.www.gp_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.goldenplanet.www.gp_android.GoogleAnalytics.AnalyticsApplication;
import com.goldenplanet.www.gp_android.GoogleAnalytics.GoogleAnalytics;
import com.goldenplanet.www.gp_android.GoogleAnalytics.GoogleAnalyticsApp;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.BuildConfig;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import static com.goldenplanet.www.gp_android.GoogleAnalytics.GoogleAnalytics.user_pseudo_id;

public class SubActivity extends AppCompatActivity implements InstallReferrerStateListener {

    Map<String, String> campaignPairs = null;
    public static InstallReferrerClient mReferrerClient;
    public static Tracker sTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Button button = (Button)findViewById(R.id.btn_main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SubActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        final FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        /*
        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse("https://kotlinbuilder.page.link/mVYD")) //https://javamyshin.page.link/6Suk
                .setDomainUriPrefix("https://kotlinbuilder.page.link")
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder()
                        .build()) //set minimum 넣을시 앱 무한로딩 발생
                .setGoogleAnalyticsParameters(
                        new DynamicLink.GoogleAnalyticsParameters.Builder()
                                .setSource("20210105")
                                .setMedium("20210105")
                                .setCampaign("20210105")
                                .build())
                .setItunesConnectAnalyticsParameters(
                        new DynamicLink.ItunesConnectAnalyticsParameters.Builder()
                                .setProviderToken("20210105")
                                .setCampaignToken("20210105")
                                .build())
                .setSocialMetaTagParameters(
                        new DynamicLink.SocialMetaTagParameters.Builder()
                                .setTitle("20210105")
                                .setDescription("20210105")
                                .build())


                .buildDynamicLink();
        Uri dynamicLinkUri = dynamicLink.getUri();
        System.out.println("dynamiclink " +dynamicLinkUri.toString());
*/
        FirebaseAnalytics.getInstance(this).getAppInstanceId().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {
                    GoogleAnalytics.user_pseudo_id[0] = task.getResult();
                    GoogleAnalyticsApp.user_pseudo_id[0] = task.getResult();
                    mFirebaseAnalytics.setUserProperty("userNo", user_pseudo_id[0]);
                    DisplayMetrics dm = new DisplayMetrics();
                    WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    windowManager.getDefaultDisplay().getMetrics(dm);

                    GoogleAnalyticsApp.aid = getApplicationContext().getApplicationInfo().packageName;
                    GoogleAnalyticsApp.an = getApplicationContext().getApplicationInfo().loadLabel(getPackageManager()).toString();
                    GoogleAnalyticsApp.av = BuildConfig.VERSION_NAME;
                    GoogleAnalyticsApp.sr = String.valueOf(dm.widthPixels) + "x" + String.valueOf(dm.heightPixels);
                    GoogleAnalyticsApp.ul = String.valueOf(Locale.getDefault().toLanguageTag().toLowerCase());

                    mReferrerClient = InstallReferrerClient.newBuilder(SubActivity.this).build();
                    mReferrerClient.startConnection(SubActivity.this);

                    Map<String, String> pageMap = new LinkedHashMap<String, String>();
/*
                    pageMap.put("dt", "골든플래닛 - Data Driven Marketing");
                    pageMap.put("dl", "http://www.goldenplanet.co.kr/main1&123&123??");

                    pageMap.put("uid", "userid");
                    pageMap.put("t", "pageview");

                    Thread pageThread = new Thread(new GoogleAnalytics.gaThread(pageMap));
                    pageThread.start();
*/
                    if (null != SubActivity.this.getIntent().getData()) {
                        Uri data = SubActivity.this.getIntent().getData();
                        if (data != null && data.isHierarchical()) {

                            try {
                                splitQuery(data, "deeplink");
//                                splitQuery(data);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                            SharedPreferences pref = getSharedPreferences("isFirst" , Activity.MODE_PRIVATE);
                            boolean isFirst = pref.getBoolean("isFirst", false);
                            AnalyticsApplication application = (AnalyticsApplication)getApplication();
                            sTracker = application.getDefaultTracker();
                            //sTracker.set("&uid","");

                            if(CheckAppFirstExecute()){
                                ReferrerDetails response = null;
                                try {
                                    response = mReferrerClient.getInstallReferrer();
                                    String referrerData = response.getInstallReferrer();

                                    sTracker.setScreenName("appinstall");
                                    HitBuilders.EventBuilder event_builder = new HitBuilders.EventBuilder();
                                    event_builder.setCategory(String.valueOf(deepLink));
                                    event_builder.setAction("appinstall_"+isFirst);
                                    event_builder.setLabel("label");
                                    sTracker.send(event_builder.build());
                                    sTracker.send((new HitBuilders.ScreenViewBuilder()
                                            .setCampaignParamsFromUrl(String.valueOf(deepLink))
                                            .build()));
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                            else{
                                sTracker.setScreenName("deeplink");
                                HitBuilders.EventBuilder event_builder = new HitBuilders.EventBuilder();
                                event_builder.setCategory(String.valueOf(deepLink));
                                event_builder.setAction("deeplink_"+isFirst);
                                event_builder.setLabel("label");
                                //sTracker.send(event_builder.build());
                                sTracker.send((new HitBuilders.ScreenViewBuilder()
                                        .setCampaignParamsFromUrl(String.valueOf(deepLink))
                                        .build()));
                            }

                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });
    }


    public boolean CheckAppFirstExecute(){
        SharedPreferences pref = getSharedPreferences("isFirst" , Activity.MODE_PRIVATE);
        boolean isFirst = pref.getBoolean("isFirst", false); //false 가 맨처음 진입시 값
        if(!isFirst){
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirst", true);
            editor.commit();
        }
        return !isFirst;
    }

    @Override
    public void onInstallReferrerServiceDisconnected() {
    }

    @Override
    public void onInstallReferrerSetupFinished(int responseCode) {
        if (responseCode == InstallReferrerClient.InstallReferrerResponse.OK) {
            String a;

            try {
                //ReferrerDetails response = mReferrerClient.getInstallReferrer();
                //String referrerUrl = response.getInstallReferrer();
                //long referrerClickTime = response.getReferrerClickTimestampSeconds();
                //long appInstallTime = response.getInstallBeginTimestampSeconds();
                //boolean instantExperienceLaunched = response.getGooglePlayInstantParam();
                // CheckAppFirstExecute -> 앱 최초 실행 확인
                if(CheckAppFirstExecute()) getReferral();

            } catch (RemoteException e) {
                e.printStackTrace();
            }



        } else {
            mReferrerClient.endConnection();
        }
    }


    public void getReferral() throws RemoteException {
        ReferrerDetails response = mReferrerClient.getInstallReferrer();
        String referrerData = response.getInstallReferrer();
        Log.e("GADATA_getRefferal", "Install referrer:" + response.getInstallReferrer());

        try {
            splitQuery(Uri.parse(referrerData), "install");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mReferrerClient.endConnection();
    }

    public void splitQuery(Uri uri, String data) throws UnsupportedEncodingException {
        Map<String, String> campaignMap = new HashMap<String, String>();
        String query = uri.toString();

        if (data.equals("install")) campaignMap.put("dt", "캠페인>앱설치");
        if (data.equals("deeplink")) campaignMap.put("dt", "캠페인>딥링크");
        campaignMap.put("t", "pageview");
        campaignMap.put("dl", "http://www.goldenplanet.co.kr/campaign.do");

        AnalyticsApplication application = (AnalyticsApplication)getApplication();
        sTracker = application.getDefaultTracker();
        sTracker.setScreenName("splitquery");
        HitBuilders.EventBuilder event_builder = new HitBuilders.EventBuilder();
        event_builder.setCategory(String.valueOf(query));
        event_builder.setAction("splitquery");
        event_builder.setLabel("label");
        //sTracker.send(event_builder.build());

        if (uri.toString().contains("://")) query =  uri.getQuery();
        Log.e("GADATA_splitquery", "Install referrer:" + query);
        if (query != null) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                if (pair.contains("utm_source")) campaignMap.put("cs", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                else if (pair.contains("utm_medium")) campaignMap.put("cm", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                else if (pair.contains("utm_term")) campaignMap.put("ck", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                else if (pair.contains("utm_content")) campaignMap.put("cc", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                else if (pair.contains("utm_campaign")) campaignMap.put("cn", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
        }
        Thread t = new Thread(new GoogleAnalytics.gaThread(campaignMap));
        //t.start();
    }



    public static void campaignSettings(CampaignInfo campaignInfo, Map<String, String> campaignPairs) {
        if (campaignPairs != null) {
            String name = campaignPairs.get("utm_campaign");
            String source = campaignPairs.get("utm_source");
            String medium = campaignPairs.get("utm_medium");
            String keyword = campaignPairs.get("utm_term");
            String content = campaignPairs.get("utm_content");

            if (name != null) {
                campaignInfo.setCn(name);
            }
            if (source != null) {
                campaignInfo.setCs(source);
            }
            if (medium != null) {
                campaignInfo.setCm(medium);
            }
            if (keyword != null) {
                campaignInfo.setCk(keyword);
            }
            if (content != null) {
                campaignInfo.setCc(content);
            }
        }
    }




    public static class CampaignInfo {
        private String cn;      // campaign name
        private String cs;      // campaign source
        private String cm;      // campaign medium
        private String ck;      // campaign keyword
        private String cc;      // campaign content

        public String getCn() {
            return cn;
        }

        public void setCn(String cn) {
            this.cn = cn;
        }

        public String getCs() {
            return cs;
        }

        public void setCs(String cs) {
            this.cs = cs;
        }

        public String getCm() {
            return cm;
        }

        public void setCm(String cm) {
            this.cm = cm;
        }

        public String getCk() {
            return ck;
        }

        public void setCk(String ck) {
            this.ck = ck;
        }

        public String getCc() {
            return cc;
        }

        public void setCc(String cc) {
            this.cc = cc;
        }
    }

}
