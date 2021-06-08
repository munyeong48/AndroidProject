package com.example.Kotlin

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main3.*

// 웹뷰
class Main3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val bt4 = findViewById<View>(R.id.w2) as Button
        val bt5 = findViewById<View>(R.id.w3) as Button
        val mWebView1 = webview1 as WebView

        val mWebView2 = findViewById<View>(R.id.webview2) as WebView
        mWebView2.webViewClient = WebViewClient() // 클릭시 새창 안뜨게
        mWebView1.webViewClient = WebViewClient() // 클릭시 새창 안뜨게
        mWebView2.webChromeClient = WebChromeClient()
        mWebView1.webChromeClient = WebChromeClient()

        mWebView2.settings.javaScriptEnabled = true
        mWebView1.settings.javaScriptEnabled = true

        bt5.setOnClickListener {
            mWebView2.loadUrl("http://www.naver.com") // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
        }
        bt4.setOnClickListener {
            val cookie = StringBuilder("_ga_cid") //cookie라는 string 생성
            cookie.append("=").append(GoogleAnalytics.userId.user_pseudo_id[0]) //cookie에 userid append
            cookie.append("; path=/")
            val cookieManager = CookieManager.getInstance() //singletone 쿠키매니저 인스턴스 get
            cookieManager.setAcceptCookie(true) // 쿠키를 보내거나 받는거를 true
            cookieManager.setAcceptThirdPartyCookies(mWebView1, true) //웹뷰에서 타사 쿠키를 설정할 수 있는지 여부설정
            cookieManager.setCookie("http://211.49.99.88:8011/alphalab/myshin", cookie.toString()) //  주어진 url에 대한 쿠키 설정
            mWebView1.loadUrl("http://211.49.99.88:8011/alphalab/myshin") // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작

        }
    }
}