package com.goldenplanet.www.gp_android;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;


import com.goldenplanet.www.gp_android.Fragment.ProductPurchase;
import com.goldenplanet.www.gp_android.GoogleAnalytics.AnalyticsApplication;
import com.goldenplanet.www.gp_android.GoogleAnalytics.GoogleAnalyticsBuilder;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Ref;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.goldenplanet.www.gp_android.Fragment.ProductPurchase.googleAnalyticsBuilder;

public class Receiver extends BroadcastReceiver {

//    Map<String, String> campaignPairs;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String Referrer = extras.getString("referrer");
        if (Referrer != "" && Referrer.contains("utm_source")) {
            try {
                Referrer = "gadata://?" + Referrer;
                Map<String, String> DeepLink_screen_Map = new HashMap<String, String>();
                DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1, "맞춤 측정 기준2 값");
                DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2, "맞춤 측정 기준2 값");
                DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3, "맞춤 측정 기준3 값");
                DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.UserID, "사용자 ID");
                DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.Title, "화면 명");
                DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.CampaignUrl, Referrer);
                GoogleAnalyticsBuilder googleAnalyticsBuilder = new GoogleAnalyticsBuilder(context);
                googleAnalyticsBuilder.GADataSend_Screen(DeepLink_screen_Map);

            } catch (Exception e) {
                Log.e("GAv4", e.getMessage());
                e.printStackTrace();
            }
        }
    }
}












//
//    public static Map<String, String> splitQuery(Uri uri) throws UnsupportedEncodingException {
//        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
//        String query = "";
//        if (!uri.toString().contains("://")) {
//            query = URLDecoder.decode(uri.toString(), "UTF-8");
//        } else {
//            query = uri.getQuery();
//        }
//        if (query != null) {
//            String[] pairs = query.split("&");
//            for (String pair : pairs) {
//                int idx = pair.indexOf("=");
//                Log.e("splitQueryKey", URLDecoder.decode(pair.substring(0, idx), "UTF-8"));
//                Log.e("splitQueryValue", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
//                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
//            }
//        }
//        return query_pairs;
//    }
//}

