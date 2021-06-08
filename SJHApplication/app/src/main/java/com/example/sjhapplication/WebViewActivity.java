package com.example.sjhapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        String url = "http://211.49.99.88:8011/alphalab/jhshin/";

        WebView mWebView = (WebView)findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);

        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게

        //webview생성 시 _ga_cid라는 값으로 string cookie 생성
        StringBuilder cookie = new StringBuilder("_ga_cid");
        //cookie에 userid append
        cookie.append("=").append(GoogleAnalytics.user_pseudo_id[0]);
        cookie.append("; path=/");

        //singletone 쿠키매니저 인스턴스 get
        CookieManager cookieManager = CookieManager.getInstance();
        // 쿠키를 보내거나 받는거를 true
        cookieManager.setAcceptCookie(true);
//        cookieManager.setAcceptThirdPartyCookies(mWebView, true); //웹뷰에서 타사 쿠키를 설정할 수 있는지 여부설정
        //  주어진 url에 대한 쿠키 설정
        cookieManager.setCookie("211.49.99.88:8011", cookie.toString());






        // 추가적 UserAgent를 붙어주는 설정, webview 실행여부선언
        String userAgent = mWebView.getSettings().getUserAgentString();
        mWebView.getSettings().setUserAgentString(userAgent+"/GA_Android");

        // JavaScriptInterface 추가
//        mWebView.addJavascriptInterface( new GoogleAnalyticsBuilder.WebAppInterface(this), "gascriptAndroid" );
    }
}