package com.goldenplanet.www.gp_android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.goldenplanet.www.gp_android.GoogleAnalytics.GoogleAnalytics.getReferral;
import static com.goldenplanet.www.gp_android.GoogleAnalytics.GoogleAnalytics.splitQuery;

public class SubActivity extends AppCompatActivity implements InstallReferrerStateListener {

    Map<String, String> campaignPairs = null;
    public static InstallReferrerClient mReferrerClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Button button = (Button)findViewById(R.id.btn_main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SubActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        mReferrerClient =  InstallReferrerClient.newBuilder(this).build();
        mReferrerClient.startConnection(this);

        if (null != this.getIntent().getData()) {
            Uri data = this.getIntent().getData();
            if (data != null && data.isHierarchical()) {
                try {
                    splitQuery(data,"deeplink");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
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

    @Override
    public void onInstallReferrerServiceDisconnected() {
    }

    @Override
    public void onInstallReferrerSetupFinished(int responseCode) {
        if (responseCode == InstallReferrerClient.InstallReferrerResponse.OK) {
            try {
                // CheckAppFirstExecute -> 앱 최초 실행 확인
                if(CheckAppFirstExecute()) getReferral();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            mReferrerClient.endConnection();
        }
    }



    public static void campaignSettings(CampaignInfo campaignInfo, Map<String, String> campaignPairs) {
        if (campaignPairs != null) {
            String name = campaignPairs.get("utm_campaign");
            String source = campaignPairs.get("utm_source");
            String medium = campaignPairs.get("utm_medium");
            String keyword = campaignPairs.get("utm_term");
            String content = campaignPairs.get("utm_content");

            if (name != null) {
                campaignInfo.setCn(name);
            }
            if (source != null) {
                campaignInfo.setCs(source);
            }
            if (medium != null) {
                campaignInfo.setCm(medium);
            }
            if (keyword != null) {
                campaignInfo.setCk(keyword);
            }
            if (content != null) {
                campaignInfo.setCc(content);
            }
        }
    }




    public static class CampaignInfo {
        private String cn;      // campaign name
        private String cs;      // campaign source
        private String cm;      // campaign medium
        private String ck;      // campaign keyword
        private String cc;      // campaign content

        public String getCn() {
            return cn;
        }

        public void setCn(String cn) {
            this.cn = cn;
        }

        public String getCs() {
            return cs;
        }

        public void setCs(String cs) {
            this.cs = cs;
        }

        public String getCm() {
            return cm;
        }

        public void setCm(String cm) {
            this.cm = cm;
        }

        public String getCk() {
            return ck;
        }

        public void setCk(String ck) {
            this.ck = ck;
        }

        public String getCc() {
            return cc;
        }

        public void setCc(String cc) {
            this.cc = cc;
        }
    }

}
