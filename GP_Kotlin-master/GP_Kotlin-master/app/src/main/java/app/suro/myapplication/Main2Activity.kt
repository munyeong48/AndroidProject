package app.suro.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.lang.StringBuilder

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val pageMap = HashMap<String, String>()

        pageMap.put("cd1", "맞춤 측정 기준 1 값")
        pageMap.put("cd2", "맞춤 측정 기준 2 값")
        pageMap.put("cd3", "맞춤 측정 기준 3 값")
        pageMap.put("cd4", "맞춤 측정 기준 4 값")

        pageMap.put("dt", "페이지 명")
        pageMap.put("dl", "http://www.goldenplanet.co.kr/login.do")

        pageMap.put("uid", "사용자 Id")
        pageMap.put("t", "pageview")

        val pageThread = Thread(GoogleAnalytics.gaThread(pageMap))
//        pageThread.start()

        var webview = wv_main2 as WebView

        var cookie = StringBuilder("_ga_cid")
        cookie.append("=").append(GoogleAnalytics.userId.user_pseudo_id[0])
        cookie.append("; path=/")

        var CookieManager = CookieManager.getInstance()
        CookieManager.setAcceptCookie(true)
        CookieManager.setCookie("211.49.99.88",cookie.toString())

        var userAgent = webview.getSettings().getUserAgentString()
        userAgent += "/GA_Android"
        webview.getSettings().setUserAgentString(userAgent)
        webview.addJavascriptInterface(GoogleAnalyticsBuilder.WebAppInterface(this), "gascriptAndroid")

        webview.setWebChromeClient(WebChromeClient())
        webview.getSettings().setJavaScriptEnabled(true)

        webview.loadUrl("http://211.49.99.88:8011/alphalab/djkim/default")
        val webView_url = webview.getUrl().toString()

        if (intent.hasExtra("GAID")) {
            var GAID = intent.getStringExtra("GAID")

            var gab : GoogleAnalyticsBuilder = GoogleAnalyticsBuilder(this)

            val screen_Map : HashMap<String, String> = hashMapOf("NaN" to "NaN")

            screen_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1, "두번쨰 맞춤 측정 기준 1 값")
            screen_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2, GAID)

            screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.Title, "두번째 타이틀")
            screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.UserID, "두번쨰 사용자 ID")
//            gab.GADataSend_Screen(screen_Map)
        }
    }
}
