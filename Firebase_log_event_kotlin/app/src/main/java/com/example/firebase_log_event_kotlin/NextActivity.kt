package com.example.firebase_log_event_kotlin

import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase_log_event_kotlin.MainActivity.WebAppInterface
import com.google.firebase.analytics.FirebaseAnalytics

class NextActivity : AppCompatActivity() {
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)


        //FirebaseAnalytics 인스턴스 생성
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        //이벤트 로깅 (이벤트 이름 : NEvent)
        val mWebView1 = findViewById<View>(R.id.webview) as WebView
        mWebView1.webViewClient = WebViewClient() // 클릭시 새창 안뜨게
        mWebView1.webChromeClient = WebChromeClient()
        mWebView1.settings.javaScriptEnabled = true
        mWebView1.loadUrl("http://211.49.99.88:8011/alphalab/jylee/") // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
        val userAgent = mWebView1.settings.userAgentString // 기존 useragentstring을 받아와
        mWebView1.settings.userAgentString = "$userAgent/GA_Android"
        mWebView1.addJavascriptInterface(WebAppInterface(), "gascriptAndroid")
    }

    override fun onResume() {
        super.onResume()
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        //수동화면추적
        //SCREEN_VIEW 이벤트를 수동으로 로깅할 수 있습니다.
        val bundle = Bundle() //Bundle 객체 생성
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "firebasek_main2") //보고서-페이지 제목 및 화면 이름 or 이벤트-firebase_screen
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "firebasek_main2") //보고서-이벤트-screen_view-firebase_screen_class
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle) //logEvent(이벤트 명, bundle)
        //더 이상 지원 안 되는 서비스3
        //mFirebaseAnalytics.setCurrentScreen(this, "FinalActivity", null ); //보고서-페이지 제목 및 화면 이름
    }
}