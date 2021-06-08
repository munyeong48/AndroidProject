package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.example.myapplication.producer.abc;
import static com.example.myapplication.screenname.getSn_previous;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        model id 배포 버전 등 hitsend 와 useragent 설정 시 발생되는 값 예시
        model = Android SDK built for x86;
        id = Build/QSR1.190920.001;
        release = Android 10;

        p = com.example.myapplication;
        n = My Application;
        v = androidx.appcompat.widget.AppCompatButton{9daa25d VFED..C.. ...P.... 0,0-231,126 #7f080102 app:id/w2};
        */

        int status = NetworkStatus.getConnectivityStatus(getApplicationContext());
        /*
        if(status == NetworkStatus.TYPE_MOBILE){
            alert("모바일로 연결됨");
        }else if (status == NetworkStatus.TYPE_WIFI){
            textView.setText("무선랜으로 연결됨");
        }else {
            textView.setText("연결 안됨.");
        }
        */

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

        //Intent intent = getIntent();
        //String t1 = intent.getStringExtra("GAID");

        Map<String, String> pageMap = new LinkedHashMap<String, String>();

        // pageMap.put("dt", screenname.sn_previous); // 이렇게해도 sn_previous 값을 가져올수있다.
        pageMap.put("dt", getSn_previous());
        pageMap.put("dl", "http://www.goldenplanet.co.kr/myshin");

        pageMap.put("uid", "userid");
        pageMap.put("t", "pageview");


        Thread pageThread = new Thread(new GoogleAnalytics.gaThread(pageMap));
        pageThread.start();


        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intent);
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String,String> eventMap = new LinkedHashMap<String,String>();

                eventMap.put("ec","qab");
                eventMap.put("ea","dds");
                eventMap.put("el"," ");
                eventMap.put("cd1", "");
                eventMap.put("cd2", " ");
                eventMap.put("cd3", "123");
                eventMap.put("uid","70DAE0eFWaVeEVJY1Wcggln6/MJftgPrS0WUy2qdMdRcYilVTve/EdHjYW8deg6B_Android");
                eventMap.put("t","event");

                Thread eventThread = new Thread(new GoogleAnalytics.gaThread(eventMap));
                eventThread.start();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r1.setVisibility(View.VISIBLE);
                r_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i =1; i<3;i++){
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<>();
                            mapmap.put("ea", "impression");
                            Thread commerceThread = new Thread(new GoogleAnalytics.gaThread(abc(i, mapmap)));
                            commerceThread.start();
                    }}
                });
                r_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i =1; i<3;i++){
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<String, String>();
                            mapmap.put("ea", "click");
                            Thread commerceThread = new Thread(new GoogleAnalytics.gaThread(abc(i, mapmap)));
                            commerceThread.start();
                    }}
                });
                r_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i =1; i<11;i++){
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<String, String>();
                            mapmap.put("ea", "detail");
                            Thread commerceThread = new Thread(new GoogleAnalytics.gaThread(abc(i, mapmap)));
                            commerceThread.start();
                        }}
                });
                r_4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i =1; i<11;i++){
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<String, String>();
                            mapmap.put("ea", "cartadd");
                            Thread commerceThread = new Thread(new GoogleAnalytics.gaThread(abc(i, mapmap)));
                            commerceThread.start();
                        }}
                });
                r_5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i =1; i<11;i++){
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<String, String>();
                            mapmap.put("ea", "cartdelete");
                            Thread commerceThread = new Thread(new GoogleAnalytics.gaThread(abc(i, mapmap)));
                            commerceThread.start();
                        }}
                });
                r_6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i =1; i<11;i++){
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<String, String>();
                            mapmap.put("ea", "order1");
                            Thread commerceThread = new Thread(new GoogleAnalytics.gaThread(abc(i, mapmap)));
                            commerceThread.start();
                        }}
                });
                r_7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i =0; i<10;i++){
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<String, String>();
                            mapmap.put("ea", "order2");
                            Thread commerceThread = new Thread(new GoogleAnalytics.gaThread(abc(i, mapmap)));
                            commerceThread.start();
                        }}
                });
                r_8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i =1; i<11;i++){
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<String, String>();
                            mapmap.put("ea", "order3");
                            Thread commerceThread = new Thread(new GoogleAnalytics.gaThread(abc(i, mapmap)));
                            commerceThread.start();
                        }}
                });
                r_9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i =1; i<11;i++){
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<String, String>();
                            mapmap.put("ea", "complete");
                            Thread commerceThread = new Thread(new GoogleAnalytics.gaThread(abc(i, mapmap)));
                            commerceThread.start();
                        }}
                });
                r_10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int a=11; ////////// 20개와 30개 구매시 환불은 처음 10개의 상품 종류만 환불
                        if(a!=21 || a!=31){
                        for(int i =1; i<a;i++){
                            Map<String, String> mapmap = null;
                            mapmap = new LinkedHashMap<String, String>();
                            mapmap.put("ea", "refund");
                            Thread commerceThread = new Thread(new GoogleAnalytics.gaThread(abc(i, mapmap)));
                            commerceThread.start();}}
                        else{
                                for(int i =1; i<11;i++){
                                Map<String, String> mapmap = null;
                                mapmap = new LinkedHashMap<String, String>();
                                mapmap.put("ea", "refund");
                                Thread commerceThread = new Thread(new GoogleAnalytics.gaThread(abc(i, mapmap)));
                                commerceThread.start();}}
                        }});
            }
        });
    }


    //    class GAID extends AsyncTask<Void,Void,String> {
//        @Override protected String doInBackground(Void... voids) {
//            String advertisingId = "";
//            try{
//                AdvertisingIdClient.Info info = AdvertisingIdClient.getAdvertisingIdInfo(MainActivity.this);
//                advertisingId = info.getId();
//            }catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException e){ e.printStackTrace(); }
//            return advertisingId;
//        }
//        @Override protected void onPostExecute(String id) {
//            super.onPostExecute(id);
//            Intent intent = new Intent(MainActivity.this, MainActivity.class);
//            intent.putExtra("GAID",id);
//            startActivity(intent);
//        }
//    }

}
