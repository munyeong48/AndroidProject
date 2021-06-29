package com.example.myapplication;
// splash activity
// main2 실행 후 main 실행 후 main3 실행순
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.BuildConfig;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import static com.example.myapplication.screenname.setSn_previous;


public class Main2Activity extends AppCompatActivity implements InstallReferrerStateListener {

    public static FirebaseAnalytics firebaseAnalytics;
    private InstallReferrerClient mReferrerClient;


    @Override
    public void onInstallReferrerServiceDisconnected() {
    }

    @Override
    public void onInstallReferrerSetupFinished(int responseCode) {
        if (responseCode == InstallReferrerClient.InstallReferrerResponse.OK) {
            try {
                if (CheckAppFirstExecute()) { //앱 최초실행 확인
                    getReferral();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            mReferrerClient.endConnection();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        FirebaseAnalytics.getInstance(this).getAppInstanceId().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {

                    GoogleAnalytics.user_pseudo_id[0] = task.getResult();

//                    new GAID().execute();
//
//                    Thread adid = new Thread(new GoogleAnalyticsApp.getAdid(Main2Activity.this));
//                    adid.start();

                    DisplayMetrics dm = new DisplayMetrics();
                    WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    windowManager.getDefaultDisplay().getMetrics(dm);

                    GoogleAnalytics.aid = getApplicationContext().getApplicationInfo().packageName;
                    GoogleAnalytics.an = getApplicationContext().getApplicationInfo().loadLabel(getPackageManager()).toString();
                    GoogleAnalytics.av = BuildConfig.VERSION_NAME;
                    GoogleAnalytics.sr = String.valueOf(dm.widthPixels) + "x" + String.valueOf(dm.heightPixels);
                    GoogleAnalytics.ul = String.valueOf(Locale.getDefault().toLanguageTag().toLowerCase());


                    mReferrerClient = InstallReferrerClient.newBuilder(Main2Activity.this).build(); //InstallReferrerClient 메소드 호출통한 생성
                    mReferrerClient.startConnection(Main2Activity.this); //mreferrerclient 는 applink 에서 사용한다.

                    //new screenname("Main2","Main2Activity"); // 이렇게 해도 생성자를 통해 setting 가능
                    setSn_previous("Main2","Main2Activity");

//                    Intent myIntent;
//                    String url = "http://www.naver.com";
//                    myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    startActivity(myIntent);

                    Intent intent = new Intent(Main2Activity.this, MainActivity.class); // firebase 인스턴스 실행전 webview로드코드 설정했다면 이후 이코드가 실행되어 다음 페이지로 넘어감
                    startActivity(intent);


                    if (null != Main2Activity.this.getIntent().getData()) {
                        // 이를 통해 웹에서 실행했을때만 참이되어 아래 코드가 실행됩니다.
                        Uri data = Main2Activity.this.getIntent().getData();
                        // 이때 gadata 만오는것이아닌 url형태로 gata://호스트?param=? 이런식으로 온다. 호스트는 명시안했으므로 빈공간으로해서나올듯
                        //Uri는 URL과 비슷하나 그 주소의 method를 불러온다는개념
                        if (data != null && data.isHierarchical()) { // isHierarchical = Returns true if this URI is hierarchical like "http://google.com".
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

    }

//    class GAID extends AsyncTask<Void,Void,String> {
//        @Override protected String doInBackground(Void... voids) {
//            String advertisingId = "";
//            try{
//                AdvertisingIdClient.Info info = AdvertisingIdClient.getAdvertisingIdInfo(Main2Activity.this);
//                advertisingId = info.getId();
//            }catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException e){ e.printStackTrace(); }
//            return advertisingId;
//        }
//        @Override protected void onPostExecute(String id) {
//            super.onPostExecute(id);
//            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
//            intent.putExtra("GAID",id);
//            startActivity(intent);
//        }
//    }
    //앱 설치하고 처음실행했는지 확인하는 함수
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
// 앱 설치하고 처음실행했을시 실행되는 함수
    private void getReferral() throws RemoteException {
        ReferrerDetails response = mReferrerClient.getInstallReferrer();
        String referrerData = response.getInstallReferrer();
        try {
            splitQuery(Uri.parse(referrerData), "install");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mReferrerClient.endConnection();
    }

    public static Map<String, String> splitQuery(Uri uri, String data) throws UnsupportedEncodingException { // throws un~ 이것은 if this isn't a hierarchical url 일때 throw를 반환
        Map<String, String> campaignMap = new HashMap<String, String>();
        String query = uri.toString(); // 위에서 받은 uri를 string 으로 받아옴
        Log.i("MyApp", "Deep link clicked " + query); // log 출력하는 영역
        if (data.equals("install")) campaignMap.put("dt", "캠페인>앱설치");
        if (data.equals("deeplink")) campaignMap.put("dt", "캠페인>딥링크");
        campaignMap.put("t", "pageview");
        campaignMap.put("dl", "http://www.goldenplanet.co.kr/campaign.do"); // 각 map에 필요한 값 put

        if (uri.toString().contains("://")) query = uri.getQuery(); // url 부분중 ? 로 된 부분에대한 query들을 가져온다.
        if (query != null) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                if (pair.contains("utm_source"))
                    campaignMap.put("cs", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                else if (pair.contains("utm_medium"))
                    campaignMap.put("cm", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                else if (pair.contains("utm_term"))
                    campaignMap.put("ck", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                else if (pair.contains("utm_content"))
                    campaignMap.put("cc", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                else if (pair.contains("utm_campaign"))
                    campaignMap.put("cn", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
        }
        Thread t = new Thread(new GoogleAnalytics.gaThread(campaignMap)); //  campaignmap에 put 시켜놓은 것들을 gathread로 보냄
        t.start();

        return  campaignMap;
    }
}

