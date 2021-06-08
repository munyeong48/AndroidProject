package com.example.asyncaos;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.example.asyncaos.producer.abc;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final RadioGroup r1;
        final RadioButton r_1,r_2,r_3,r_4,r_5,r_6,r_7,r_8,r_9,r_10;
        final WebView mWebView;

        Button bt1=(Button)findViewById(R.id.bt1);
        Button bt2=(Button)findViewById(R.id.c1);
        Button bt3=(Button)findViewById(R.id.w1);

        mWebView = (WebView) findViewById(R.id.webview1);


        r1=(RadioGroup) findViewById(R.id.commerce);
        r_1=(RadioButton) findViewById(R.id.im);
        r_2=(RadioButton) findViewById(R.id.cl);
        r_3=(RadioButton) findViewById(R.id.de);
        r_4=(RadioButton) findViewById(R.id.caa);
        r_5=(RadioButton) findViewById(R.id.cad);
        r_6=(RadioButton) findViewById(R.id.o1);
        r_7=(RadioButton) findViewById(R.id.o2);
        r_8=(RadioButton) findViewById(R.id.o3);
        r_9=(RadioButton) findViewById(R.id.co);
        r_10=(RadioButton) findViewById(R.id.re);

        r1.setVisibility(View.INVISIBLE);
        /*
        Map<String, String> pageMap = new LinkedHashMap<String, String>();

        pageMap.put("cd1", GoogleAnalytics.user_pseudo_id[0]);
        pageMap.put("cd3", "cd3_Android");

        pageMap.put("dt","dt_Android");
        pageMap.put("dl","android_location");
        pageMap.put("uid", "userid");
        pageMap.put("t", "pageview");
        Log.v("pagemap",pageMap.toString());
        GoogleAnalytics.GoogleAsyncTask pageAsync = new GoogleAnalytics.GoogleAsyncTask(pageMap);
        pageAsync.execute();

        */

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<5000 ; i++){
                Map<String,String> eventMap = new LinkedHashMap<String,String>();
                eventMap.put("cd1", GoogleAnalytics.user_pseudo_id[0]);
                eventMap.put("cd3", "cd3_Android");

                eventMap.put("ec","main2_category");
                eventMap.put("ea","main2_action");
                eventMap.put("el","main2_label");
                eventMap.put("t","event");

                GoogleAnalytics.GoogleAsyncTask eventAsync = new GoogleAnalytics.GoogleAsyncTask(eventMap);
                eventAsync.execute();
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r1.setVisibility(View.VISIBLE);
                r_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 1; i < 3; i++) {
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<>();
                            mapmap.put("ea", "impression");
                            GoogleAnalytics.GoogleAsyncTask commerceAsync = new GoogleAnalytics.GoogleAsyncTask(abc(i, mapmap));
                            commerceAsync.execute();
                        }
                    }
                });
                r_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 1; i < 3; i++) {
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<String, String>();
                            mapmap.put("ea", "click");
                            GoogleAnalytics.GoogleAsyncTask commerceAsync = new GoogleAnalytics.GoogleAsyncTask(abc(i, mapmap));
                            commerceAsync.execute();
                        }
                    }
                });
                r_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 1; i < 11; i++) {
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<String, String>();
                            mapmap.put("ea", "detail");
                            GoogleAnalytics.GoogleAsyncTask commerceAsync = new GoogleAnalytics.GoogleAsyncTask(abc(i, mapmap));
                            commerceAsync.execute();
                        }
                    }
                });
                r_4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 1; i < 11; i++) {
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<String, String>();
                            mapmap.put("ea", "cartadd");
                            GoogleAnalytics.GoogleAsyncTask commerceAsync = new GoogleAnalytics.GoogleAsyncTask(abc(i, mapmap));
                            commerceAsync.execute();
                        }
                    }
                });
                r_5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 1; i < 11; i++) {
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<String, String>();
                            mapmap.put("ea", "cartdelete");
                            GoogleAnalytics.GoogleAsyncTask commerceAsync = new GoogleAnalytics.GoogleAsyncTask(abc(i, mapmap));
                            commerceAsync.execute();
                        }
                    }
                });
                r_6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 1; i < 11; i++) {
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<String, String>();
                            mapmap.put("ea", "order1");
                            GoogleAnalytics.GoogleAsyncTask commerceAsync = new GoogleAnalytics.GoogleAsyncTask(abc(i, mapmap));
                            commerceAsync.execute();
                        }
                    }
                });
                r_7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < 10; i++) {
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<String, String>();
                            mapmap.put("ea", "order2");
                            GoogleAnalytics.GoogleAsyncTask commerceAsync = new GoogleAnalytics.GoogleAsyncTask(abc(i, mapmap));
                            commerceAsync.execute();
                        }
                    }
                });
                r_8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 1; i < 11; i++) {
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<String, String>();
                            mapmap.put("ea", "order3");
                            GoogleAnalytics.GoogleAsyncTask commerceAsync = new GoogleAnalytics.GoogleAsyncTask(abc(i, mapmap));
                            commerceAsync.execute();
                        }
                    }
                });
                r_9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 1; i < 11; i++) {
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<String, String>();
                            mapmap.put("ea", "complete");
                            GoogleAnalytics.GoogleAsyncTask commerceAsync = new GoogleAnalytics.GoogleAsyncTask(abc(i, mapmap));
                            commerceAsync.execute();
                        }
                    }
                });
                r_10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int a = 11; ////////// 20개와 30개 구매시 환불은 처음 10개의 상품 종류만 환불
                        if (a != 21 || a != 31) {
                            for (int i = 1; i < a; i++) {
                                Map<String, String> mapmap = null;
                                mapmap = new LinkedHashMap<String, String>();
                                mapmap.put("ea", "refund");
                                GoogleAnalytics.GoogleAsyncTask commerceAsync = new GoogleAnalytics.GoogleAsyncTask(abc(i, mapmap));
                                commerceAsync.execute();
                            }
                        } else {
                            for (int i = 1; i < 11; i++) {
                                Map<String, String> mapmap = null;
                                mapmap = new LinkedHashMap<String, String>();
                                mapmap.put("ea", "refund");
                                GoogleAnalytics.GoogleAsyncTask commerceAsync = new GoogleAnalytics.GoogleAsyncTask(abc(i, mapmap));
                                commerceAsync.execute();
                            }}
                    }});
            }
        });
    }
}
