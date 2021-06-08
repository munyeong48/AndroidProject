package com.goldenplanet.Install_AOSkt

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.firebase.analytics.FirebaseAnalytics

class Hybrid_Fragment : Fragment() {
    var mWebView: WebView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.activity_hybrid, container, false)
        val mFirebaseAnalytics: FirebaseAnalytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context!!)
        val params = Bundle()
        params.putString("ScreenDepth1", "screen_depth1_" + TAG)
        params.putString("ScreenDepth2", "screen_depth2_" + TAG)
        params.putString("ScreenDepth3", "screen_depth3_" + TAG)
        params.putString("ScreenDepth4", "screen_depth4_" + TAG)
        params.putString("ScreenDepth5", "screen_depth5_" + TAG)
        params.putFloat("FloatData_" + TAG, 1f)
        params.putDouble("DoubleData_" + TAG, 1.0)
        params.putInt("IntData_" + TAG, 1)
        mFirebaseAnalytics.logEvent("screenview", params)
        mWebView = v.findViewById<View>(R.id.wv_hybrid) as WebView
        val userAgent = mWebView!!.settings.userAgentString
        //
//        mWebView.setWebViewClient(new WebViewClient());
        mWebView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                val url2 = "http://211.49.99.88:8011/alphalab/magnet/"
                return if (url.length!=0 && url.startsWith(url2)) {
                    view.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    true
                } else {
                    false
                }
            }
        }
        mWebView!!.settings.domStorageEnabled = true // Add this
        mWebView!!.settings.javaScriptCanOpenWindowsAutomatically = true
        mWebView!!.settings.javaScriptEnabled = true
        WebView.setWebContentsDebuggingEnabled(true)
        //        StringBuilder cookie = new StringBuilder("_ga_cid");
//        cookie.append("=").append(user_pseudo_id[0]).append("; path=/");
        val cookieManager = CookieManager.getInstance()
        cookieManager.removeAllCookies(null)
        CookieManager.getInstance().setAcceptCookie(true)
        cookieManager.setAcceptThirdPartyCookies(mWebView, true)
        cookieManager.setCookie("211.49.99.88", "_ga_cid" + "=" + GoogleAnalytics.user_pseudo_id[0] + "; path=/")
        //        cookieManager.setCookie("211.49.99.88", "_ga" + "=" + user_pseudo_id[0] + "; path=/");
//        cookieManager.setCookie("211.49.99.88", "_ga_aid" + "=" + GoogleAnalyticsApp.aid + "; path=/");
//        cookieManager.setCookie("211.49.99.88", "_ga_av" + "=" + GoogleAnalyticsApp.av + "; path=/");
//        cookieManager.setCookie("211.49.99.88", "_ga_an" + "=" + GoogleAnalyticsApp.an + "; path=/");
//        cookieManager.setCookie("211.49.99.88", "_ga_sr" + "=" + GoogleAnalyticsApp.sr + "; path=/");
//        cookieManager.setCookie("211.49.99.88", "_ga_ul" + "=" + GoogleAnalyticsApp.ul + "; path=/");

//        cookieManager.setCookie("211.49.99.88", cookie.toString());

//        userAgent += "/HmallApp_Play_190_Android";
        mWebView!!.settings.userAgentString = userAgent
        //        mWebView.addJavascriptInterface(new hmall.WebAppInterface(getActivity()),"AndroidJS");
//        mWebView.loadUrl("http://211.49.99.88:8011/alphalab/djkim/default");
        mWebView!!.loadUrl("http://211.49.99.88:8011/default")
        val url = mWebView!!.url.toString()
        return v
    }

    companion object {
        private const val TAG = "HybridFragment"
    }
}