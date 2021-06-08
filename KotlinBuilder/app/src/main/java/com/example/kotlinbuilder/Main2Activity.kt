package com.example.kotlinbuilder

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinbuilder.GoogleAnalyticsBuilder.WebAppInterface

class Main2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val wv = findViewById<View>(R.id.webview) as WebView
        wv.loadUrl("http://211.49.99.88:8011/alphalab/myshin/") // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
        val userAgent =
            wv.settings.userAgentString // 기존 useragentstring을 받아와
        wv.settings
            .setUserAgentString("$userAgent/GA_Android") // GA_Android 값을 추가 여기서 GA_Android로 추가하였으면 모바일 웹페이지 내에서 받는
        // 코드에서도 browser value 값이 ga_android와 같아야 한다.
        wv.settings.javaScriptEnabled = true
        wv.addJavascriptInterface(WebAppInterface(this), "Android")
    }
}