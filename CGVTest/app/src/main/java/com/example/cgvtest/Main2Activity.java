package com.example.cgvtest;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final WebView wv = (WebView) findViewById(R.id.webview1);

        wv.loadUrl("http://211.49.99.88:8011/alphalab/myshin/"); // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
        String userAgent = wv.getSettings().getUserAgentString(); // 기존 useragentstring을 받아와
        wv.getSettings().setUserAgentString(userAgent+"/CGV_Android"); // GA_Android 값을 추가 여기서 GA_Android로 추가하였으면 모바일 웹페이지 내에서 받는
        // 코드에서도 browser value 값이 ga_android와 같아야 한다.
        wv.getSettings().setJavaScriptEnabled(true);
        wv.addJavascriptInterface( new MainActivity.WebAppInterface(this),"Android");
    }
}

