package com.goldenplanet.Install_AOSkt

import android.content.Intent
import android.os.Bundle
import android.os.RemoteException
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import java.io.UnsupportedEncodingException

class SubActivity : AppCompatActivity(), InstallReferrerStateListener {
    var campaignPairs: Map<String, String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        val button = findViewById<View>(R.id.btn_main) as Button
        button.setOnClickListener {
            val i = Intent(this@SubActivity, MainActivity::class.java)
            startActivity(i)
        }
        mReferrerClient = InstallReferrerClient.newBuilder(this).build()
        mReferrerClient!!.startConnection(this)
        if (null != this.intent.data) {
            val data = this.intent.data
            if (data != null && data.isHierarchical) {
                try {
                    val a= "deeplink"
                    //GoogleAnalytics.splitQuery(data, "deeplink")
                } catch (e: UnsupportedEncodingException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun CheckAppFirstExecute(): Boolean {
        val pref = getSharedPreferences("isFirst", MODE_PRIVATE)
        val isFirst = pref.getBoolean("isFirst", false)
        if (!isFirst) {
            val editor = pref.edit()
            editor.putBoolean("isFirst", true)
            editor.commit()
        }
        return !isFirst
    }

    override fun onInstallReferrerServiceDisconnected() {}
    override fun onInstallReferrerSetupFinished(responseCode: Int) {
        if (responseCode == InstallReferrerClient.InstallReferrerResponse.OK) {
            try {
                // CheckAppFirstExecute -> 앱 최초 실행 확인
                val a= "deeplink"
//                if (CheckAppFirstExecute()) {GoogleAnalytics.getReferral()}
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        } else {
            mReferrerClient!!.endConnection()
        }
    }

    class CampaignInfo {
        var cn // campaign name
                : String? = null
        var cs // campaign source
                : String? = null
        var cm // campaign medium
                : String? = null
        var ck // campaign keyword
                : String? = null
        var cc // campaign content
                : String? = null
    }

    companion object {
        var mReferrerClient: InstallReferrerClient? = null
        fun campaignSettings(campaignInfo: CampaignInfo, campaignPairs: Map<String?, String?>?) {
            if (campaignPairs != null) {
                val name = campaignPairs["utm_campaign"]
                val source = campaignPairs["utm_source"]
                val medium = campaignPairs["utm_medium"]
                val keyword = campaignPairs["utm_term"]
                val content = campaignPairs["utm_content"]
                if (name != null) {
                    campaignInfo.cn = name
                }
                if (source != null) {
                    campaignInfo.cs = source
                }
                if (medium != null) {
                    campaignInfo.cm = medium
                }
                if (keyword != null) {
                    campaignInfo.ck = keyword
                }
                if (content != null) {
                    campaignInfo.cc = content
                }
            }
        }
    }
}