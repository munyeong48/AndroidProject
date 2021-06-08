package com.goldenplanet.www.gp_android;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.goldenplanet.www.gp_android.Fragment.FirebaseCloudMessagingFragment;
import com.goldenplanet.www.gp_android.Fragment.Hybrid_Fragment;
import com.goldenplanet.www.gp_android.Fragment.Native_Fragment;
import com.goldenplanet.www.gp_android.GoogleAnalytics.AnalyticsApplication;
import com.goldenplanet.www.gp_android.GoogleAnalytics.GoogleAnalyticsApp;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.BuildConfig;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import static com.goldenplanet.www.gp_android.GoogleAnalytics.GoogleAnalytics.user_pseudo_id;
import static com.goldenplanet.www.gp_android.MainActivity.ClientID;

public class SplashActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    private static Fragment fragment = null;
    private static final String TAG = "MainActivity";
    public static String title = "메인 화면";
    public static TextView tv_url;
    private static Button logTokenButton;
    private static Button subscribeButton;
    private static Button subscribeButton_GPTEST;
    private Map<String,String> map = new HashMap<String,String>();
    String advertisingId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fabric.with(this, new Crashlytics());


        map.put("cd1", GoogleAnalyticsApp.user_pseudo_id[0]);
        map.put("cd2","userId");
        map.put("cd3",GoogleAnalyticsApp.adid);
        map.put("cd22","APP");

        map.put("t", "screenview");

        String an = getApplicationContext().getApplicationInfo().loadLabel(getPackageManager()).toString();
        String aid = getApplicationInfo().packageName;
        String av = BuildConfig.VERSION_NAME;

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);

        map.put("cd", "화면 이름");

        Thread t = new Thread(new GoogleAnalyticsApp.gaThread(map));
        t.start();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        Date currentTime = new Date ();
        String dTime = formatter.format ( currentTime );

        tv_url = (TextView) findViewById(R.id.tv_url);
        Button btn_remoteconfig = findViewById(R.id.btn_remoteconfig);
        Button btn_move_sub = findViewById(R.id.btn_move_sub);

        btnSetOnClickListener onClickListener = new btnSetOnClickListener() ;

        btn_remoteconfig.setOnClickListener(onClickListener);
        btn_move_sub.setOnClickListener(onClickListener);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AnalyticsApplication application = (AnalyticsApplication) (this).getApplication();
        Tracker mTracker = application.getDefaultTracker();

        String FragmentName = null;

        FirebaseMessaging.getInstance().subscribeToTopic("weather");
        FirebaseMessaging.getInstance().unsubscribeFromTopic("weather");
        String str = getIntent().getStringExtra("NotificationKey");
        if(str != null) {
            FragmentName = str;
            fragment = new Hybrid_Fragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_fragment_layout, fragment);
            ft.commit();
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
            }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }

        Uri data = this.getIntent().getData();
//        String url = "";
//        if (data != null && data.isHierarchical()) {
//            try {
//                splitQuery(data, "deeplink");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }

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

        String UL = mTracker.get("&ul");
        String gaid = AnalyticsApplication.GAID;
        Intent intent = getIntent(); /*데이터 수신*/
        String GAID = "";
        if(intent.getExtras() != null){
            GAID = intent.getExtras().getString("GAID");
        }

        tv_url.setText(ClientID);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }

        final String ga_trackingId = getString(R.string.ua_code);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Tracking UA Code: " + ga_trackingId, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(SplashActivity.this);
        navigationView.bringToFront();
    }


    @Override
    protected void onResume() {
        super.onResume();
        AnalyticsApplication.mFirebaseAnalytics.setCurrentScreen(this, "메인화면", null);
    }

    public class btnSetOnClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_move_sub:
                    if(user_pseudo_id[0] != null && user_pseudo_id[0].length() > 0) {
                        map.put("t", "event");
                        map.put("ec", "메인");
                        map.put("ea", "서브페이지로이동");
                        map.put("el", "클릭");

                        Thread t = new Thread(new GoogleAnalyticsApp.gaThread(map));
                        t.start();
                    }
//                    Crashlytics.getInstance().crash();
                    Intent i = new Intent(SplashActivity.this, SubActivity.class);
                    startActivity(i);
                    break;
                case R.id.btn_remoteconfig:
                    Intent z = new Intent(SplashActivity.this, RemoteConfigActivity.class);
                    startActivity(z);
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        tv_url.setVisibility(View.GONE);

//        GoogleAnalyticsBuilder gab = new GoogleAnalyticsBuilder(this);
//        Map<String, String> kMap = new HashMap<String, String>();
//        kMap.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory, "메인");
//        kMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1, ClientID);
//        kMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2, UniqueID);
//        kMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension4, ADID);
//        kMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension5, "1");
//        kMap.put(GoogleAnalyticsBuilder.GAHitKey.UserID, UniqueID);
        if (id == R.id.nav_native_screenview) {
            title = "GA Native&Ecommerce";
//            AnalyticsApplication.mFirebaseAnalytics.setCurrentScreen(this, title + "_onNavigationItemSelected", null);
//            kMap.put(GoogleAnalyticsBuilder.GAHitKey.EventAction, "GA 네이티브영역 진입");
//            kMap.put(GoogleAnalyticsBuilder.GAHitKey.Title, title);
//            gab.GADataSend_Event(kMap);
            fragment = new Native_Fragment();
        }  else if (id == R.id.nav_hybrid_screenview) {
            title = "GA Hybrid";
//            AnalyticsApplication.mFirebaseAnalytics.setCurrentScreen(this, title + "_onNavigationItemSelected", null);
//            kMap.put(GoogleAnalyticsBuilder.GAHitKey.EventAction, "GA 하이브리드영역 진입");
//            kMap.put(GoogleAnalyticsBuilder.GAHitKey.Title, title);
//            gab.GADataSend_Event(kMap);
            fragment = new Hybrid_Fragment();
        } else if (id == R.id.nav_firebase_analytics) {
            title = "Firebase Analytics";
//            AnalyticsApplication.mFirebaseAnalytics.setCurrentScreen(this, title + "_onNavigationItemSelected", null);
//            kMap.put(GoogleAnalyticsBuilder.GAHitKey.EventAction, "Firebase Analytics 진입");
//            kMap.put(GoogleAnalyticsBuilder.GAHitKey.Title, title);
            fragment = new com.goldenplanet.www.gp_android.Fragment.FirebaseAnalytics();
        } else if (id == R.id.nav_firebase_cloud_messaging) {
            title = "FCM";
//            AnalyticsApplication.mFirebaseAnalytics.setCurrentScreen(this, title + "_onNavigationItemSelected", null);
//            kMap.put(GoogleAnalyticsBuilder.GAHitKey.EventAction, "Firebase Cloud Messagind 진입");
//            kMap.put(GoogleAnalyticsBuilder.GAHitKey.Title, title);
            fragment = new FirebaseCloudMessagingFragment();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_fragment_layout, fragment);
            ft.commit();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
