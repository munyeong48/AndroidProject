package com.goldenplanet.Install_AOSkt

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.*
import com.google.firebase.BuildConfig
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.messaging.FirebaseMessaging
import java.io.UnsupportedEncodingException
import java.text.SimpleDateFormat
import java.util.*

class SplashActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val map: MutableMap<String, String?> = HashMap()
    var advertisingId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Fabric.with(this, new Crashlytics());
        map["cd1"] = GoogleAnalyticsApp.user_pseudo_id[0]
        map["cd2"] = "userId"
        map["cd3"] = GoogleAnalyticsApp.adid
        map["cd22"] = "APP"
        map["t"] = "screenview"
        val an = applicationContext.applicationInfo.loadLabel(packageManager).toString()
        val aid = applicationInfo.packageName
        val av = BuildConfig.VERSION_NAME
        val dm = DisplayMetrics()
        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(dm)
        map["cd"] = "화면 이름"
        val t = Thread(GoogleAnalyticsApp.gaThread(map))
        //t.start()
        val date = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA)
        val currentTime = Date()
        val dTime = formatter.format(currentTime)
        tv_url = findViewById<View>(R.id.tv_url) as TextView
        val btn_remoteconfig = findViewById<Button>(R.id.btn_remoteconfig)
        val btn_move_sub = findViewById<Button>(R.id.btn_move_sub)
        val onClickListener: btnSetOnClickListener = btnSetOnClickListener()
        btn_remoteconfig.setOnClickListener(onClickListener)
        btn_move_sub.setOnClickListener(onClickListener)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val application = this.application as AnalyticsApplication
        val mTracker = application.defaultTracker
        var FragmentName: String? = null
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
        FirebaseMessaging.getInstance().unsubscribeFromTopic("weather")
        val str = intent.getStringExtra("NotificationKey")
        if (str != null) {
            FragmentName = str
            fragment = Hybrid_Fragment()
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.content_fragment_layout, fragment as Hybrid_Fragment)
            ft.commit()
            if (supportActionBar != null) {
                supportActionBar!!.setTitle(Companion.title)
            }
            val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
            drawer.closeDrawer(GravityCompat.START)
        }
        val data = this.intent.data
        val url = ""
        if (data != null && data.isHierarchical) {
            try {
                val a= "deeplink"
                //GoogleAnalytics.splitQuery(data, "deeplink")
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
        }

//        if (url != "" && url.contains("utm_source")) {
//            Map<String, String> DeepLinkMap = new HashMap<String, String>();
//            GoogleAnalyticsBuilder googleAnalyticsBuilder = new GoogleAnalyticsBuilder(this);
//            try {
//
//                HitBuilders.ScreenViewBuilder screenViewBuilder = new HitBuilders.ScreenViewBuilder();
//                screenViewBuilder.setCustomDimension(1,"맞춤 측정 기준 1 값");
//                screenViewBuilder.setCustomDimension(2,"맞춤 측정 기준 2 값");
//                screenViewBuilder.setCustomDimension(3,"맞춤 측정 기준 3 값");
//
//                url = URLDecoder.decode(url, "UTF-8");
//                url = URLDecoder.decode(url, "UTF-8");
//
//                screenViewBuilder.setCampaignParamsFromUrl(url);
//                mTracker.setScreenName("화면 명");
//                mTracker.set("&uid","사용자 ID");
//
//                mTracker.send(screenViewBuilder.build());
//
//                mTracker.setScreenName(null);
//                mTracker.set("&uid",null);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//            DeepLinkMap.put(GoogleAnalyticsBuilder.GAHitKey.Title, "골든플래닛 스플래쉬 딥링크_SplashActivity");
//            DeepLinkMap.put(GoogleAnalyticsBuilder.GAHitKey.CampaignUrl, url);
//            DeepLinkMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1, ClientID);
//            DeepLinkMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2, UniqueID);
//            DeepLinkMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension4, ADID);
//            DeepLinkMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension5, "U");
//            DeepLinkMap.put(GoogleAnalyticsBuilder.GAHitKey.UserID, UniqueID);
//            googleAnalyticsBuilder.GADataSend_Screen(DeepLinkMap);
//        }
        val UL = mTracker!!["&ul"]
        val gaid: String = AnalyticsApplication.Companion.GAID
        val intent = intent /*데이터 수신*/
        var GAID: String? = ""
        if (intent.extras != null) {
            GAID = intent.extras!!.getString("GAID")
        }
        tv_url!!.setText(MainActivity.Companion.ClientID)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = getString(R.string.default_notification_channel_id)
            val channelName = getString(R.string.default_notification_channel_name)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW))
        }
        if (getIntent().extras != null) {
            for (key in getIntent().extras!!.keySet()) {
                val value = getIntent().extras!![key]
                Log.d(TAG, "Key: $key Value: $value")
            }
        }
        val ga_trackingId = getString(R.string.ua_code)
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Tracking UA Code: $ga_trackingId", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this@SplashActivity)
        navigationView.bringToFront()
    }

    inner class btnSetOnClickListener : View.OnClickListener {
        override fun onClick(v: View) {
            when (v.id) {
                R.id.btn_move_sub -> {
                    if (GoogleAnalytics.user_pseudo_id[0] != null && GoogleAnalytics.user_pseudo_id[0]!!.length > 0) {
                        map["t"] = "event"
                        map["ec"] = "메인"
                        map["ea"] = "서브페이지로이동"
                        map["el"] = "클릭"
                        val t = Thread(GoogleAnalyticsApp.gaThread(map))
                        t.start()
                    }
                    //                    Crashlytics.getInstance().crash();
                    val i = Intent(this@SplashActivity, SubActivity::class.java)
                    startActivity(i)
                }
                R.id.btn_remoteconfig -> {
                    val z = Intent(this@SplashActivity, RemoteConfigActivity::class.java)
                    startActivity(z)
                }
                else -> {
                }
            }
        }
    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        tv_url!!.visibility = View.GONE

//        GoogleAnalyticsBuilder gab = new GoogleAnalyticsBuilder(this);
//        Map<String, String> kMap = new HashMap<String, String>();
//        kMap.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory, "메인");
//        kMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1, ClientID);
//        kMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2, UniqueID);
//        kMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension4, ADID);
//        kMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension5, "1");
//        kMap.put(GoogleAnalyticsBuilder.GAHitKey.UserID, UniqueID);
        if (id == R.id.nav_native_screenview) {
            Companion.title = "GA Native&Ecommerce"
            //            AnalyticsApplication.mFirebaseAnalytics.setCurrentScreen(this, title + "_onNavigationItemSelected", null);
//            kMap.put(GoogleAnalyticsBuilder.GAHitKey.EventAction, "GA 네이티브영역 진입");
//            kMap.put(GoogleAnalyticsBuilder.GAHitKey.Title, title);
//            gab.GADataSend_Event(kMap);
            fragment = Native_Fragment()
        } else if (id == R.id.nav_hybrid_screenview) {
            Companion.title = "GA Hybrid"
            //            AnalyticsApplication.mFirebaseAnalytics.setCurrentScreen(this, title + "_onNavigationItemSelected", null);
//            kMap.put(GoogleAnalyticsBuilder.GAHitKey.EventAction, "GA 하이브리드영역 진입");
//            kMap.put(GoogleAnalyticsBuilder.GAHitKey.Title, title);
//            gab.GADataSend_Event(kMap);
            fragment = Hybrid_Fragment()
        } else if (id == R.id.nav_firebase_analytics) {
            Companion.title = "Firebase Analytics"
            //            AnalyticsApplication.mFirebaseAnalytics.setCurrentScreen(this, title + "_onNavigationItemSelected", null);
//            kMap.put(GoogleAnalyticsBuilder.GAHitKey.EventAction, "Firebase Analytics 진입");
//            kMap.put(GoogleAnalyticsBuilder.GAHitKey.Title, title);
            //fragment = FirebaseAnalytics()?
        } else if (id == R.id.nav_firebase_cloud_messaging) {
            Companion.title = "FCM"
            //            AnalyticsApplication.mFirebaseAnalytics.setCurrentScreen(this, title + "_onNavigationItemSelected", null);
//            kMap.put(GoogleAnalyticsBuilder.GAHitKey.EventAction, "Firebase Cloud Messagind 진입");
//            kMap.put(GoogleAnalyticsBuilder.GAHitKey.Title, title);
            fragment = FirebaseCloudMessagingFragment()
        }
        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.content_fragment_layout, fragment!!)
            ft.commit()
        }
        if (supportActionBar != null) {
            supportActionBar!!.title = Companion.title
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {
        private var fragment: Fragment? = null
        private const val TAG = "MainActivity"
        var title = "메인 화면"
        var tv_url: TextView? = null
        private val logTokenButton: Button? = null
        private val subscribeButton: Button? = null
        private val subscribeButton_GPTEST: Button? = null
    }
}