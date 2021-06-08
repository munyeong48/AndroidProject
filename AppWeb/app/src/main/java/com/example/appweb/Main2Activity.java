package com.example.appweb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.appweb.MainActivity.mFirebaseAnalytics;

public class Main2Activity extends AppCompatActivity {
    private String p;
    private String version;
    private String n;

    public static String[]user_pseudo_id= new String[1];
    public static String aid;
    public static String an;
    public static String av;
    public static String sr;
    public static String ul;
    @SuppressLint("JavascriptInterface")
    @Override
        protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        final WebView mWebView1 = (WebView) findViewById(R.id.webview);

        mWebView1.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        mWebView1.setWebChromeClient(new WebChromeClient());
        mWebView1.getSettings().setJavaScriptEnabled(true);
        mWebView1.loadUrl("http://211.49.99.88:8011/alphalab/jylee/"); // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작

        p = getPackageName();
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        version= packageInfo.versionName;
        try {
            n = (String) getPackageManager()
                    .getApplicationLabel(getPackageManager()
                            .getApplicationInfo(p, PackageManager.GET_UNINSTALLED_PACKAGES));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        String userAgent = mWebView1.getSettings().getUserAgentString(); // 기존 useragentstring을 받아와
        mWebView1.getSettings().setUserAgentString(userAgent+"/GA_Android");
        mWebView1.addJavascriptInterface(new WebAppInterface(),"gascriptAndroid");

    }
    public static class WebAppInterface {

        @JavascriptInterface
        public void GA_DATA(String JsonData) throws JSONException { //Webview 내에서 자바스크립트 코드내에서 불러올 클래

            JSONObject data = new JSONObject(JsonData);

            String sType = "";
            String title = "Hybrid_AOSmyshin3_";
            String en="FirebaseAnalytics.Event.";
            if (data.has("event_name")) en += data.getString("event_name").toUpperCase();

            if (data.has("type")) sType = data.getString("type");
            if (data.has("title")) title += data.getString("title");
            if (sType.contains("P")) { //스크린뷰 일때

                Bundle bundle = new Bundle(); //Bundle 객체 생성
                bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "hybrid"); //보고서-페이지 제목 및 화면 이름 or 이벤트-firebase_screen
                bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "hybrid"); //보고서-이벤트-screen_view-firebase_screen_class
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle); //logEvent(이벤트 명, bundle)

            }
        }
    }


}
