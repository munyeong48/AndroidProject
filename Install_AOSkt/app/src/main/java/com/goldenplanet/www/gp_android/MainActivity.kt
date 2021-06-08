package com.goldenplanet.Install_AOSkt

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.bumptech.glide.Glide
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.firebase.BuildConfig
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.messaging.FirebaseMessaging
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import kotlin.experimental.and

class MainActivity : AppCompatActivity(), InstallReferrerStateListener {
    private var mReferrerClient: InstallReferrerClient? = null
    var CampaignData: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        AnalyticsApplication.mFirebaseAnalytics!!.setCurrentScreen(this, "스플래쉬화면1", null)
        val mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val t = Thread(GoogleAnalyticsApp.getAdid(this@MainActivity))
        t.start()
        FirebaseAnalytics.getInstance(this).appInstanceId.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                GoogleAnalytics.user_pseudo_id[0] = task.result
                GoogleAnalyticsApp.user_pseudo_id[0] = task.result
                mFirebaseAnalytics.setUserProperty("userNo", GoogleAnalytics.user_pseudo_id[0])
                val dm = DisplayMetrics()
                val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
                windowManager.defaultDisplay.getMetrics(dm)
                GoogleAnalyticsApp.aid = applicationContext.applicationInfo.packageName
                GoogleAnalyticsApp.an = applicationContext.applicationInfo.loadLabel(packageManager).toString()
                GoogleAnalyticsApp.av = BuildConfig.VERSION_NAME
                GoogleAnalyticsApp.sr = dm.widthPixels.toString() + "x" + dm.heightPixels.toString()
                GoogleAnalyticsApp.ul = Locale.getDefault().toLanguageTag().toLowerCase()
                mReferrerClient = InstallReferrerClient.newBuilder(this@MainActivity).build()
                mReferrerClient!!.startConnection(this@MainActivity)
                val pageMap: Map<String, String> = LinkedHashMap()
                val pageThread = Thread(GoogleAnalytics.gaThread(pageMap))
                pageThread.start()
                if (null != this@MainActivity.intent.data) {
                    val data = this@MainActivity.intent.data
                    if (data != null && data.isHierarchical) {
                        try {
                            splitQuery(data, "deeplink")
                            //                                splitQuery(data);
                        } catch (e: UnsupportedEncodingException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
        val imageView = findViewById<View>(R.id.image_logo) as ImageView
        Glide.with(this).load(R.drawable.ohno).into(imageView)
        RemoteConfigActivity.Companion.initialize()
        val str = intent.getStringExtra("notificationKey")
        if (str != null && str == "notificationValue") {
            FragmentName = str
        }
        FirebaseMessaging.getInstance().subscribeToTopic("suroro")
        val an = applicationContext.applicationInfo.loadLabel(packageManager).toString()
        val aid = applicationInfo.packageName
        val av = BuildConfig.VERSION_NAME
        val Hello: String = AnalyticsApplication.Companion.GAID
        UniqueID = UUID.randomUUID().toString()
        Handler().postDelayed({ // This method will be executed once the timer is over
            val i = Intent(this@MainActivity, SplashActivity::class.java)
            if (FragmentName != null) {
                i.putExtra("NotificationKey", FragmentName)
            }
            i.putExtra("GAID", ADID)
            startActivity(i)
            finish()
        }, 1400)
    }

    override fun onInstallReferrerSetupFinished(responseCode: Int) {
        when (responseCode) {
            InstallReferrerClient.InstallReferrerResponse.OK ->                 // Connection established
                if (CheckAppFirstExecute()) {
                    CampaignData = referralUser;
                    Log.i("campaigndata : " , CampaignData)
                }
            InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED -> {
            }
            InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE -> {
            }
            InstallReferrerClient.InstallReferrerResponse.DEVELOPER_ERROR -> {
            }
            InstallReferrerClient.InstallReferrerResponse.SERVICE_DISCONNECTED -> {
            }
        }
    }

    //            splitQuery(Uri.parse(referrerData));
    //            splitQuery(Uri.parse(referrerData), "install");
    // referrer 데이터에 이미 utm_source=google-play&utm_medium=organic 이 삽입되어있음

    @get:Throws(RemoteException::class)
    private val referralUser: String
        get() {
            val response = mReferrerClient!!.installReferrer
            val hello = response.installBeginTimestampSeconds
            val referrerData = response.installReferrer
            // referrer 데이터에 이미 utm_source=google-play&utm_medium=organic 이 삽입되어있음
            Log.e("TAG", "Install referrer:" + response.installReferrer)
            try {
                splitQuery(Uri.parse(referrerData), "install")
                Log.e("GADATA", "Install referrer:" + response.installReferrer)
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
            mReferrerClient!!.endConnection()
            return referrerData
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

    //            splitQuery(Uri.parse(referrerData));
    /*
    @get:Throws(RemoteException::class)
    val referral: Unit
        get() {
            val response = mReferrerClient!!.installReferrer
            val referrerData = response.installReferrer
            Log.e("GADATA", "Install referrer:" + response.installReferrer)
            try {
                splitQuery(Uri.parse(referrerData), "install")
                //            splitQuery(Uri.parse(referrerData));
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
            mReferrerClient!!.endConnection()
        }

     */

    internal inner class GAID : AsyncTask<Void?, Void?, String>() {
        protected override fun doInBackground(vararg params: Void?): String? {
            var advertisingId = ""
            try {
                val info = AdvertisingIdClient.getAdvertisingIdInfo(this@MainActivity)
                advertisingId = info.id
            } catch (e: GooglePlayServicesNotAvailableException) {
                e.printStackTrace()
            } catch (e: GooglePlayServicesRepairableException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return advertisingId
        }

        override fun onPostExecute(id: String) {
            super.onPostExecute(id)
            ADID = id
        }
    }

    companion object {
        var ADID: String? = null
        var ClientID: String? = null
        var UniqueID: String? = null
        var title = "스플래쉬 화면"
        private const val TAG = "SplashActivity"
        var FragmentName: String? = null
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
        fun SHA256(str: String): String? {
            val SHA: Any?
            SHA = try {
                val sh = MessageDigest.getInstance("SHA-256")
                sh.update(str.toByteArray())
                val byteData = sh.digest()
                val sb = StringBuffer()
                for (i in byteData.indices) sb.append(Integer.toString((byteData[i] and 0xff.toByte()) + 0x100, 16).substring(1))
                sb.toString()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
                null
            }
            return SHA
        }

        @Throws(UnsupportedEncodingException::class)
        fun splitQuery(uri: Uri, data: String) {
            val campaignMap: MutableMap<String, String> = HashMap()
            var query: String? = uri.toString()
            if (data == "install") campaignMap["dt"] = "캠페인>앱설치"
            if (data == "deeplink") campaignMap["dt"] = "캠페인>딥링크"
            campaignMap["t"] = "pageview"
            campaignMap["dl"] = "http://www.goldenplanet.co.kr/campaign.do"
            if (uri.toString().contains("://")) query = uri.query
            if (query != null) {
                val pairs = query.split("&").toTypedArray()
                for (pair in pairs) {
                    val idx = pair.indexOf("=")
                    if (pair.contains("utm_source")) campaignMap["cs"] = URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                    else if (pair.contains("utm_medium")) campaignMap["cm"] = URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                    else if (pair.contains("utm_term")) campaignMap["ck"] = URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                    else if (pair.contains("utm_content")) campaignMap["cc"] = URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                    else if (pair.contains("utm_campaign")) campaignMap["cn"] = URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                }
            }
            val t = Thread(GoogleAnalytics.gaThread(campaignMap))
            t.start()
        }
    }
}