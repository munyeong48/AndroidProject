package com.goldenplanet.www.gp_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.goldenplanet.www.gp_android.GoogleAnalytics.AnalyticsApplication;
import com.goldenplanet.www.gp_android.GoogleAnalytics.GoogleAnalytics;
import com.goldenplanet.www.gp_android.GoogleAnalytics.GoogleAnalyticsBuilder;
import com.google.android.gms.analytics.HitBuilders;

import java.net.URLDecoder;
import java.sql.Ref;
import java.util.HashMap;
import java.util.Map;

import static com.goldenplanet.www.gp_android.SubActivity.sTracker;

public abstract class Receiver extends BroadcastReceiver {

//    Map<String, String> campaignPairs;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String Referrer = extras.getString("referrer");
        if (Referrer != "" && Referrer.contains("utm_source")) {
            try {
                Log.e("GADATA_onReceive", "Install referrer:" + Referrer);

                Map<String, String> campaignMap = new HashMap<String, String>();
                campaignMap.put("dt", "캠페인>앱설치");
                campaignMap.put("t", "pageview");
                campaignMap.put("dl", "http://www.goldenplanet.co.kr/campaign.do");
                    String[] pairs = Referrer.split("&");
                    for (String pair : pairs) {
                        int idx = pair.indexOf("=");
                        if (pair.contains("utm_source")) campaignMap.put("cs", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                        else if (pair.contains("utm_medium")) campaignMap.put("cm", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                        else if (pair.contains("utm_term")) campaignMap.put("ck", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                        else if (pair.contains("utm_content")) campaignMap.put("cc", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                        else if (pair.contains("utm_campaign")) campaignMap.put("cn", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                    }
                Thread t = new Thread(new GoogleAnalytics.gaThread(campaignMap));
                t.start();
                /*
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
                */

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

