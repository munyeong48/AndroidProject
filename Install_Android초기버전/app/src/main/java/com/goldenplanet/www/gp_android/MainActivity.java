package com.goldenplanet.www.gp_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.bumptech.glide.Glide;
import com.goldenplanet.www.gp_android.GoogleAnalytics.AnalyticsApplication;
import com.goldenplanet.www.gp_android.GoogleAnalytics.GoogleAnalytics;
import com.goldenplanet.www.gp_android.GoogleAnalytics.GoogleAnalyticsApp;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.BuildConfig;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import static com.goldenplanet.www.gp_android.GoogleAnalytics.GoogleAnalytics.user_pseudo_id;
import static com.goldenplanet.www.gp_android.RemoteConfigActivity.initialize;

public class MainActivity extends AppCompatActivity implements InstallReferrerStateListener{

    private InstallReferrerClient mReferrerClient;
    public static String ADID = null;
    public static String ClientID = null;
    public static String UniqueID = null;
    public static String title = "스플래쉬 화면";
    private static String TAG = "SplashActivity";
    public static String FragmentName = null;
    String CampaignData = null;
    public static Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        AnalyticsApplication.mFirebaseAnalytics.setCurrentScreen(this, "스플래쉬화면1", null);

        final FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Thread t = new Thread(new GoogleAnalyticsApp.getAdid(MainActivity.this));
        t.start();

        FirebaseAnalytics.getInstance(this).getAppInstanceId().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {
                    user_pseudo_id[0] = task.getResult();
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

                    mReferrerClient = InstallReferrerClient.newBuilder(MainActivity.this).build();
                    mReferrerClient.startConnection(MainActivity.this);

                    Map<String, String> pageMap = new LinkedHashMap<String, String>();
/*
                    pageMap.put("dt", "골든플래닛 - Data Driven Marketing");
                    pageMap.put("dl", "http://www.goldenplanet.co.kr/main1&123&123??");

                    pageMap.put("uid", "userid");
                    pageMap.put("t", "pageview");
*/
                    Thread pageThread = new Thread(new GoogleAnalytics.gaThread(pageMap));
                    pageThread.start();

                    if (null != MainActivity.this.getIntent().getData()) {
                        Uri data = MainActivity.this.getIntent().getData();
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


        ImageView imageView = (ImageView)findViewById(R.id.image_logo);
        Glide.with(this).load(R.drawable.ohno).into(imageView);

        initialize();

        String str = getIntent().getStringExtra("notificationKey");
        if(str != null && str.equals("notificationValue")) {
            FragmentName = str;
        }

        FirebaseMessaging.getInstance().subscribeToTopic("suroro");


        String an = getApplicationContext().getApplicationInfo().loadLabel(getPackageManager()).toString();
        String aid = getApplicationInfo().packageName;
        String av = BuildConfig.VERSION_NAME;


        String Hello = AnalyticsApplication.GAID;
        UniqueID = UUID.randomUUID().toString();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(MainActivity.this, SplashActivity.class);
                if(FragmentName != null) {
                    i.putExtra("NotificationKey", FragmentName);
                }
                i.putExtra("GAID",ADID);
                startActivity(i);
                finish();
            }
        }, 1400);

    }

    @Override
    public void onInstallReferrerSetupFinished(int responseCode) {
        switch (responseCode) {
            case InstallReferrerClient.InstallReferrerResponse.OK:
                // Connection established
                if(CheckAppFirstExecute()) {
                    //CampaignData = getReferralUser();
                }
                break;
            case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                // API not available on the current Play Store app
                break;
            case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                // Connection could not be established
                break;
            case InstallReferrerClient.InstallReferrerResponse.DEVELOPER_ERROR:
                break;
            case InstallReferrerClient.InstallReferrerResponse.SERVICE_DISCONNECTED:
                break;
        }
    }

    private String getReferralUser() throws RemoteException {
        ReferrerDetails response = mReferrerClient.getInstallReferrer();
        long hello = response.getInstallBeginTimestampSeconds();
        String referrerData = response.getInstallReferrer();
        // referrer 데이터에 이미 utm_source=google-play&utm_medium=organic 이 삽입되어있음
        Log.e("TAG", "Install referrer:" + response.getInstallReferrer());

        try {
            splitQuery(Uri.parse(referrerData), "install");
//            splitQuery(Uri.parse(referrerData));
            Log.e("GADATA", "Install referrer:" + response.getInstallReferrer());
//            splitQuery(Uri.parse(referrerData), "install");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mReferrerClient.endConnection();
        return referrerData;
    }

    public boolean CheckAppFirstExecute() {
        SharedPreferences pref = getSharedPreferences("isFirst", Activity.MODE_PRIVATE);
        boolean isFirst = pref.getBoolean("isFirst", false);
        if (!isFirst) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirst", true);
            editor.commit();
        }
        return !isFirst;
    }

    @Override
    public void onInstallReferrerServiceDisconnected() {
    }

    public static String SHA256(String str) {
        String SHA = "";
        try{
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(str.getBytes());
            byte byteData[] = sh.digest();
            StringBuffer sb = new StringBuffer();
            for(int i = 0 ; i < byteData.length ; i++) sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            SHA = sb.toString();
        }catch(NoSuchAlgorithmException e) { e.printStackTrace(); SHA = null; }
        return SHA;
    }

    public void getReferral() throws RemoteException {
        ReferrerDetails response = mReferrerClient.getInstallReferrer();
        String referrerData = response.getInstallReferrer();
        Log.e("GADATA", "Install referrer:" + response.getInstallReferrer());
        try {
            splitQuery(Uri.parse(referrerData), "install");
//            splitQuery(Uri.parse(referrerData));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mReferrerClient.endConnection();
    }

//    public void splitQuery(Uri uri) throws UnsupportedEncodingException {
//        String query = uri.toString();
//        int i = 1;
//        String url = "gadata://?";
//        if (query != null) {
//            String[] pairs = query.split("&");
//            for (String pair : pairs) {
//                int idx = pair.indexOf("=");
//                if (pair.contains("utm_source")) url = url + "utm_source=" +  URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
//                else if (pair.contains("utm_medium")) url = url + "utm_medium=" +  URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
//                else if (pair.contains("utm_term")) url = url + "utm_term=" +  URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
//                else if (pair.contains("utm_content")) url = url + "utm_content=" +  URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
//                else if (pair.contains("utm_campaign")) url = url + "utm_campaign=" +  URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
//                if(i != pairs.length) {
//                    url += "&";
//                    i++;
//                }
//            }
//        }
//        Map<String, String> DeepLink_screen_Map = new HashMap<String, String>();
//        DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1, "맞춤 측정 기준2 값");
//        DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2, "맞춤 측정 기준2 값");
//        DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.UserID, "사용자 ID" );
//        DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.Title, "화면 명" );
//        DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.CampaignUrl, url );
//
//        GoogleAnalyticsBuilder gb = new GoogleAnalyticsBuilder (MainActivity.this);
//
//        gb.GADataSend_Screen(DeepLink_screen_Map);
//    }

    public static void splitQuery(Uri uri, String data) throws UnsupportedEncodingException {
        Map<String, String> campaignMap = new HashMap<String, String>();
        String query = uri.toString();

        if (data.equals("install")) campaignMap.put("dt", "캠페인>앱설치");
        if (data.equals("deeplink")) campaignMap.put("dt", "캠페인>딥링크");
        campaignMap.put("t", "pageview");
        campaignMap.put("dl", "http://www.goldenplanet.co.kr/campaign.do");

        if (uri.toString().contains("://")) query =  uri.getQuery();
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
        t.start();
    }


    class GAID extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... voids) {
            String advertisingId = "";
            try{
                AdvertisingIdClient.Info info = AdvertisingIdClient.getAdvertisingIdInfo(MainActivity.this);
                advertisingId = info.getId();
            }catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException e){
                e.printStackTrace();
            }
            return advertisingId;
        }
        @Override
        protected void onPostExecute(String id) {
            super.onPostExecute(id);
            MainActivity.ADID = id;
        }
    }
}
