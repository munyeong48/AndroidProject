package com.goldenplanet.Install_AOSkt

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

import java.util.*

class Receiver : BroadcastReceiver() {
    //    Map<String, String> campaignPairs;
    override fun onReceive(context: Context, intent: Intent) {
        val extras = intent.extras
        var Referrer = extras!!.getString("referrer")
        if (Referrer !== "" && Referrer!!.contains("utm_source")) {
            try {
                Referrer = "gadata://?$Referrer"
                val DeepLink_screen_Map: MutableMap<String?, String?> = HashMap()
                DeepLink_screen_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension1] = "맞춤 측정 기준2 값"
                DeepLink_screen_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = "맞춤 측정 기준2 값"
                DeepLink_screen_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = "맞춤 측정 기준3 값"
                DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.UserID] = "사용자 ID"
                DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.Title] = "화면 명"
                DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.CampaignUrl] = Referrer
                val googleAnalyticsBuilder = GoogleAnalyticsBuilder(context)
                googleAnalyticsBuilder.GADataSend_Screen(DeepLink_screen_Map)
            } catch (e: Exception) {
                Log.e("GAv4", e.message!!)
                e.printStackTrace()
            }
        }
    }
}