package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import static android.net.ConnectivityManager.TYPE_WIFI;
import static android.provider.ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;
import static com.example.myapplication.Main2Activity.firebaseAnalytics;


public class chitsend {
    @SuppressLint("InvalidAnalyticsName")
    public static void hitSend(Iterator it) throws Exception {
        StringBuilder builder = new StringBuilder();
        String model = Build.MODEL;
        String id = " Build/" + Build.ID;
        String release = "Android " + Build.VERSION.RELEASE+"; ";

        while(it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            builder.append(URLEncoder.encode(e.getKey().toString(), "UTF-8"));
            builder.append("=");
            builder.append(URLEncoder.encode(e.getValue().toString(), "UTF-8"));
            builder.append("&");
        }

        builder.setLength(builder.length()-1);
        String request = "http://www.google-analytics.com/collect";

        URL url = new URL(request);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Length", Integer.toString(builder.toString().getBytes().length));
        conn.setRequestProperty("User-Agent", "/ (Linux; U; " + release +" "+ model +" "+ id+")");
        conn.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(builder.toString());
        wr.close();

        GoogleAnalytics.a="2";
        int status = conn.getResponseCode();

        try {
            if(status != 200) {
                Bundle bundle = new Bundle(); //Bundle 객체 생성
                bundle.putString("firebase_logevent", "firebase_logevent"); //보고서-페이지 제목 및 화면 이름 or 이벤트-firebase_screen
                firebaseAnalytics.logEvent("error", bundle); //logEvent(이벤트 명, bundle)
                throw new Exception("Google Analytics tracking did not return OK 200");
            } else {
                Bundle bundle = new Bundle(); //Bundle 객체 생성
                bundle.putString("firebase_logevent", "firebase_logevent"); //보고서-페이지 제목 및 화면 이름 or 이벤트-firebase_screen
                firebaseAnalytics.logEvent("status2020", bundle); //logEvent(이벤트 명, bundle)

                // Log.v("GADATA", URLDecoder.decode(builder.toString(), "UTF-8"));
            }
        } catch(Exception e) {
            Log.v("catch", "exception error");
            throw new Exception(e.getMessage());
        }
    }

}

