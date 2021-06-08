package com.example.myapplication;
// splash activity
// main2 실행 후 main 실행 후 main3 실행순
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

        /*// firebaseinstance id 값 가져오기 전 웹뷰로드시 id값을 가져가지 못해 _ga_cid=null 로 찍히게 된다.

        final WebView mWebView1 = (WebView) findViewById(R.id.webview1);
        mWebView1.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        mWebView1.setWebChromeClient(new WebChromeClient());
        mWebView1.getSettings().setJavaScriptEnabled(true);

        mWebView1.loadUrl("http://210.114.9.22/GA_part/myshin/"); // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
        StringBuilder cookie = new StringBuilder("_ga_cid"); //cookie라는 string 생성
        cookie.append("=").append(GoogleAnalytics.user_pseudo_id[0]); //cookie에 userid append
        cookie.append("; path=/");

        final CookieManager cookieManager = CookieManager.getInstance(); //singletone 쿠키매니저 인스턴스 get
        cookieManager.setAcceptCookie(true); // 쿠키를 보내거나 받는거를 true
        cookieManager.setAcceptThirdPartyCookies(mWebView1, true); //웹뷰에서 타사 쿠키를 설정할 수 있는지 여부설정
        cookieManager.setCookie("http://210.114.9.22/GA_part/myshin/", cookie.toString()); //  주어진 url에 대한 쿠키 설정
        */

        // Param 관련 설명 링크 : https://firebase.google.com/docs/dynamic-links/android/create?authuser=0
        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse("https://kotlinbuilder.page.link/1srt")) //https://javamyshin.page.link/6Suk
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



        //기본적으로 짧은 동적 링크는 17자(영문 기준) 링크 서픽스와 함께 생성되어 누군가가 유효한 동적 링크를 추측하는 것이 거의 불가능합니다.
        // 누군가가 짧은 링크를 추측해도 괜찮다면 고유성을 위해 꼭 필요한 만큼의 길이로 서픽스를 만드는 것이 좋을 수 있으며,
        // ShortDynamicLink.Suffix.SHORT를 buildShortDynamicLink 메서드에 전달하면 됩니다.

        /*
        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse("https://kotlinbuilder.page.link/6BG2"))
                .setDomainUriPrefix("https://javamyshin.page.link/")
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
                .setIosParameters(new DynamicLink.IosParameters.Builder("com.example.ios").build())
                // Set parameters
                // ...
                .buildShortDynamicLink() //.buildShortDynamicLink(ShortDynamicLink.Suffix.SHORT);
                .addOnCompleteListener(this, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if (task.isSuccessful()) {
                            // Short link created
                            Uri shortLink = task.getResult().getShortLink();
                            Uri flowchartLink = task.getResult().getPreviewLink();
                            String s;
                        } else {
                            // Error
                            // ...
                        }
                    }
                });


         */

        //긴 동적 링크 축약
        /*
        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLongLink(Uri.parse("https://kotlinbuilder.page.link/EwdR"))
                .buildShortDynamicLink()
                .addOnCompleteListener(this, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if (task.isSuccessful()) {
                            // Short link created
                            Uri shortLink = task.getResult().getShortLink();
                            Uri flowchartLink = task.getResult().getPreviewLink();
                        } else {
                            // Error
                            // ...
                        }
                    }
                });
        */


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
/*
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                            String a;
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });

 */
        //다이나믹링크 받는 코드 넣고 4번실행, 빼고 2번실행
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

