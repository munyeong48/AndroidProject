package com.example.gatest;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    private String p;
    private String version;
    private String n;

    public static String[]user_pseudo_id= new String[1];
    public static String aid;
    public static String an;
    public static String av;
    public static String sr;
    public static String ul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString("ScreenDepth1","화면 깊이_1");
        params.putString("ScreenDepth2","화면 깊이_2");
        params.putString("ScreenDepth3","화면 깊이_3");
        params.putString("ScreenName","화면 명");
        mFirebaseAnalytics.logEvent("screen_event1",params);

        Bundle event = new Bundle();
        event.putString(FirebaseAnalytics.Param.ITEM_ID,"id");
        event.putString(FirebaseAnalytics.Param.ITEM_NAME,"name");
        event.putString(FirebaseAnalytics.Param.CONTENT_TYPE,"image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,event);

        Bundle customevent = new Bundle();
        customevent.putString("Category","카테고리");
        customevent.putString("Action","액션");
        customevent.putString("Lable","라벨");
        mFirebaseAnalytics.logEvent("Event1",customevent);

        mFirebaseAnalytics.setUserProperty("사용자 속성 1","User_Property1");

        FirebaseAnalytics.getInstance(this).getAppInstanceId().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {
                    user_pseudo_id[0] = task.getResult();
                }
            }
        });

        Button bt=(Button)findViewById(R.id.webviewbtn);
        final WebView mWebView1 = (WebView) findViewById(R.id.webviewid);

        mWebView1.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        mWebView1.setWebChromeClient(new WebChromeClient());
        mWebView1.getSettings().setJavaScriptEnabled(true);

        bt.setOnClickListener(new View.OnClickListener() {
            @JavascriptInterface
            @Override
            public void onClick(View v) {
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

                String userAgent = mWebView1.getSettings().getUserAgentString() + "/_ga_cid=" + user_pseudo_id[0] +
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
                cookieManager.setCookie("http://211.49.99.88:8011/alphalab/myshin/", "_ga_cid" + "=" + user_pseudo_id[0] + "; path=/");

                //cookieManager.setCookie("http://211.49.99.88:8011/alphalab/myshin/", "_ga_adid" + "=" + GoogleAnalytics.adid + "; path=/");
            }
        });
    }
}
