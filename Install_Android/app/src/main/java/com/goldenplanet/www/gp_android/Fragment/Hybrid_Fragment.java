package com.goldenplanet.www.gp_android.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.goldenplanet.www.gp_android.R;
import com.google.firebase.analytics.FirebaseAnalytics;

import static com.goldenplanet.www.gp_android.GoogleAnalytics.GoogleAnalytics.user_pseudo_id;

public class Hybrid_Fragment extends Fragment {

    public WebView mWebView;
    private static String TAG = "HybridFragment";

    public Hybrid_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_hybrid, container, false);

        com.google.firebase.analytics.FirebaseAnalytics mFirebaseAnalytics;
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());

        Bundle params = new Bundle();
        params.putString("ScreenDepth1", "screen_depth1_" + TAG);
        params.putString("ScreenDepth2", "screen_depth2_" + TAG);
        params.putString("ScreenDepth3", "screen_depth3_" + TAG);
        params.putString("ScreenDepth4", "screen_depth4_" + TAG);
        params.putString("ScreenDepth5", "screen_depth5_" + TAG);
        params.putFloat("FloatData_" + TAG, 1);
        params.putDouble("DoubleData_" + TAG, 1);
        params.putInt("IntData_" + TAG, 1);
        mFirebaseAnalytics.logEvent("screenview", params);
        mWebView = (WebView) v.findViewById(R.id.wv_hybrid);
        String userAgent = mWebView.getSettings().getUserAgentString();
//
//        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String url2="http://211.49.99.88:8011/alphalab/magnet/";
                if (url != null && url.startsWith(url2)) {
                    view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else {
                    return false;
                }
            }
        });

        mWebView.getSettings().setDomStorageEnabled(true); // Add this
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebContentsDebuggingEnabled(true);
//        StringBuilder cookie = new StringBuilder("_ga_cid");
//        cookie.append("=").append(user_pseudo_id[0]).append("; path=/");

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookies(null);
        CookieManager.getInstance().setAcceptCookie(true);
        cookieManager.setAcceptThirdPartyCookies(mWebView, true);
        cookieManager.setCookie("211.49.99.88", "_ga_cid" + "=" + user_pseudo_id[0] + "; path=/");
//        cookieManager.setCookie("211.49.99.88", "_ga" + "=" + user_pseudo_id[0] + "; path=/");
//        cookieManager.setCookie("211.49.99.88", "_ga_aid" + "=" + GoogleAnalyticsApp.aid + "; path=/");
//        cookieManager.setCookie("211.49.99.88", "_ga_av" + "=" + GoogleAnalyticsApp.av + "; path=/");
//        cookieManager.setCookie("211.49.99.88", "_ga_an" + "=" + GoogleAnalyticsApp.an + "; path=/");
//        cookieManager.setCookie("211.49.99.88", "_ga_sr" + "=" + GoogleAnalyticsApp.sr + "; path=/");
//        cookieManager.setCookie("211.49.99.88", "_ga_ul" + "=" + GoogleAnalyticsApp.ul + "; path=/");

//        cookieManager.setCookie("211.49.99.88", cookie.toString());

//        userAgent += "/HmallApp_Play_190_Android";
        mWebView.getSettings().setUserAgentString(userAgent);
//        mWebView.addJavascriptInterface(new hmall.WebAppInterface(getActivity()),"AndroidJS");
//        mWebView.loadUrl("http://211.49.99.88:8011/alphalab/djkim/default");

        mWebView.loadUrl("http://211.49.99.88:8011/default");
        String url = mWebView.getUrl().toString();
        return v;
    }
}






