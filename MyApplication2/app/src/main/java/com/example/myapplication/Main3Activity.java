package com.example.myapplication;
// 웹뷰
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.text.CollationElementIterator;
import java.util.Locale;

public class Main3Activity extends AppCompatActivity {
    private String p;
    private String version;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button bt4=(Button)findViewById(R.id.w2);
        Button bt5=(Button)findViewById(R.id.w3);
        final WebView mWebView1 = (WebView) findViewById(R.id.webview1);

        mWebView1.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        final WebView mWebView2 = (WebView) findViewById(R.id.webview2);

        mWebView2.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        mWebView2.getSettings().setJavaScriptEnabled(true);
        mWebView2.setWebChromeClient(new WebChromeClient());

        mWebView1.setWebChromeClient(new WebChromeClient());
        mWebView1.getSettings().setJavaScriptEnabled(true);


        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            @JavascriptInterface
            public void onClick(View v) {
                mWebView2.loadUrl("http://www.naver.com"); // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @JavascriptInterface
            @Override
            public void onClick(View v) {
                p = getPackageName();
                PackageInfo packageInfo = null;
                try {
                    packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                version = packageInfo.versionName;
                String n = null;
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

                String userAgent = mWebView1.getSettings().getUserAgentString() + "/_ga_cid=" + GoogleAnalytics.user_pseudo_id[0] +
                        "&PackageName=" + p + "&Appversion=" + v + "&AppNam" + "e=" + n;
                mWebView1.getSettings().setUserAgentString(userAgent);
                mWebView1.loadUrl("http://211.49.99.88:8011/alphalab/myshin/"); // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
                //StringBuilder cookie = new StringBuilder("_ga_cid"); //cookie라는 string 생성
                //cookie.append("=").append(GoogleAnalytics.user_pseudo_id[0]); //cookie에 userid append
                //cookie.append("; path=/");


                final CookieManager cookieManager = CookieManager.getInstance(); //singletone 쿠키매니저 인스턴스 get
                cookieManager.setAcceptCookie(true); // 쿠키를 보내거나 받는거를 true
                cookieManager.setAcceptThirdPartyCookies(mWebView1, true); //웹뷰에서 타사 쿠키를 설정할 수 있는지 여부설정

                //cookieManager.setCookie("http://211.49.99.88:8011/alphalab/myshin/", cookie.toString()); //  주어진 url에 대한 쿠키 설정
                //cookieManager.setCookie("http://211.49.99.88:8011/alphalab/myshin/", "_ga_cid" + "=" + GoogleAnalytics.user_pseudo_id[0] + "; path=/");
                //cookieManager.setCookie("http://211.49.99.88:8011/alphalab/myshin/", "_ga_adid" + "=" + GoogleAnalytics.adid + "; path=/");
            }
        });
    }
}