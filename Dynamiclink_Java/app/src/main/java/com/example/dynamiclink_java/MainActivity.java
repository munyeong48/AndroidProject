package com.example.dynamiclink_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.ReferrerDetails;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

import static com.example.dynamiclink_java.AnalyticsApplication.TrackerName.FASTORDER;

public class MainActivity extends AppCompatActivity {

    public static Tracker sTracker;
    public static InstallReferrerClient mReferrerClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.setUserProperty("login_type", "direct");
        FirebaseAnalytics.getInstance(this).getAppInstanceId().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {
                    task.getResult();
                    sTracker.setScreenName();
                    mFirebaseAnalytics.setSampleRate(50.0d);
                    mFirebaseAnalytics.setUserProperty("user_property1", task.getResult());
                    Bundle abc= new Bundle();
                    abc.putLong("abc", Long.parseLong("abc"));
                    mFirebaseAnalytics.logEvent("screen",abc);
                    }
            }
        });


        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse("http://210.114.9.22/GA_part/myshin/"))
                .setDomainUriPrefix("https://kotlinbuilder.page.link")
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder("com.example.dynamiclink_java")
                        .build()) //set minimum 넣을시 앱 무한로딩 발생
                .setGoogleAnalyticsParameters(
                        new DynamicLink.GoogleAnalyticsParameters.Builder()
                                .setSource("20210105")
                                .setMedium("20210105")
                                .setCampaign("20210105")
                                .build())
                .setItunesConnectAnalyticsParameters(
                        new DynamicLink.ItunesConnectAnalyticsParameters.Builder()
                                .setProviderToken("20210105")
                                .setCampaignToken("20210105")
                                .build())
                .setSocialMetaTagParameters(
                        new DynamicLink.SocialMetaTagParameters.Builder()
                                .setTitle("20210105")
                                .setDescription("20210105")
                                .build())
                .buildDynamicLink();
        Uri dynamicLinkUri = dynamicLink.getUri();
        System.out.println("dynamiclink value " +dynamicLinkUri.toString());

        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @SuppressLint("InvalidAnalyticsName")
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        Uri deepLink = null;
                        Bundle bundle = new Bundle();

                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                            bundle.putString("dynamiclink value : ", String.valueOf(deepLink));
                            if(CheckAppFirstExecute()){
                                mFirebaseAnalytics.logEvent("appinstall",bundle);
                            }
                            else{
                                mFirebaseAnalytics.logEvent("deeplink",bundle);
                            }
                        }
                    }
                })
                        .addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }
    public boolean CheckAppFirstExecute(){
        SharedPreferences pref = getSharedPreferences("isFirst" , Activity.MODE_PRIVATE);
        boolean isFirst = pref.getBoolean("isFirst", false);
        if(!isFirst){
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirst", true);
            editor.commit();
        }
        return !isFirst;
    }
}