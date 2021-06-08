package com.example.sjhapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
//import com.google.android.gms.analytics.HitBuilders;
//import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    private Retrofit mRetrofit;
    private RetrofitAPI mRetrofitAPI;
    private Call<String> mCallMoviewList;


    private final String BASE_URL = "http://www.google-analytics.com";
    //private final String BASE_URL = "http://34.64.134.105:4000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRetrofitInit();
    }


    private void setRetrofitInit() {
//        Gson gson = new Gson();
//        JsonReader reader = new JsonReader(new StringReader(result1));
//        reader.setLenient(true);
//        Userinfo userinfo1 = gson.fromJson(reader, Userinfo.class);


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mRetrofitAPI = mRetrofit.create(RetrofitAPI.class);

        callRequest();
    }

    private void callRequest() {
        final HashMap<String,String> pageMap = new HashMap<String,String>();

        pageMap.put("dl", "http://cjmallapp.cjmall.com/m/homeTab/main?hmtabMenuId=000002");
        pageMap.put("cd89", "000002");
        pageMap.put("tid", "UA-115948787-1");
        pageMap.put("ds", "web");
        pageMap.put("cd123", "000002_unique");
        pageMap.put("dt", "홈탭>홈");

        pageMap.put("cd96", "CAMC201209158249_1002");
        pageMap.put("uid", "200710004037");
        pageMap.put("cd94", "I0878");
        pageMap.put("cd95", "APP_PUSH_ANDROID");
        pageMap.put("cd93", "G0001");
        pageMap.put("ul", "ko");
        pageMap.put("sr", "2058x1080");
        pageMap.put("cd38", "SM-G955N"); // s8+
        pageMap.put("cd39", "Mozilla/5.0 (Linux; Android 9; SM-G955N Build/PPR1.180610.011; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/86.0.4240.198 Mobile Safari/537.36 OSHOPPING(os=android;osVersion=9;deviceModel=SM-G955N;serviceName=cjmall;serviceVersion=6.9.4;serviceBuildVersion=710;serverStatus=prd;ab=1;) GA(_ga_cid=6b0af77b0d65781b81ec04d9f9afcad2;)");
        pageMap.put("cd36", "Mobile App");
        pageMap.put("cd56", "홈탭_000002");

        pageMap.put("an", "CJmall");
        pageMap.put("cd13", "N");
        pageMap.put("cd35", "Y");
        pageMap.put("cd10", "Y");
        pageMap.put("cd11", "Y");
        pageMap.put("cd41", "710");
        pageMap.put("cd1", "6b0af77b0d65781b81ec04d9f9afcad2");
        pageMap.put("t", "pageview");
        pageMap.put("cd6", "200710004037");
        pageMap.put("av", "6.9.4");

        pageMap.put("v", "1");
        pageMap.put("cd8", "65");
        pageMap.put("cd7", "F");
        pageMap.put("cd9", "A0106010");
        pageMap.put("aid", "com.cjoshppingphone");
        pageMap.put("cid", "6b0af77b0d65781b81ec04d9f9afcad2");



        mCallMoviewList = mRetrofitAPI.postFirst(pageMap);

        mCallMoviewList.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    String result = response.body();
                    Log.d(TAG, result);
                    int v = 1;
                    //response.body(); // have your all data
                }else   {
                    int a = 0;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE
                t.printStackTrace();
                String a = "p";
            }
        });
    }

//    private Callback<Result> mRetrofitCallback = new Callback<String>() {
//        @Override
//        public void onResponse(Call<Result> call, Response<Result> response) {
//            if (response.isSuccessful())
//            {
//                String result = response.body();
//                //Log.d(TAG, result);
//            }
//            else
//            {
//
//            }
//
//        }
//
//        @Override
//        public void onFailure(Call<String> call, Throwable t) {
//            t.printStackTrace();
//        }
//    };


//    private Retrofit mRetrofit;
//    private RetrofitAPI mRetrofitAPI;
//    private Call<String> mCallMovieList;
//    private Gson mGson;
//
//    private void setRetrofitInit(){
//        mRetrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com/repos/square/retrofit/contributors")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        mRetrofitAPI = mRetrofit.create(RetrofitAPI.class);
//    }
//    private void callMovieList() {
//        mCallMovieList = mRetrofitAPI.getMovieList();
//        mCallMovieList.enqueue(mRetrofitCallback);
//    }
//
//    private Callback<String> mRetrofitCallback = new Callback<String>() {
//        @Override
//        public void onResponse(Call<String> call, Response<String> response) {
//            String result = response.body();
//            MovieListVO mMovieListVO = (MovieListVO) mGson.fromJson(result, MovieListVO.class);
//        }
//
//        @Override
//        public void onFailure(Call<String> call, Throwable t) {
//            t.printStackTrace();
//        }
//    };












//    private Retrofit retrofit;
//    private TextView textView;
//
//    private final String BASE_URL = "http://www.google-analytics.com";
//
//
//    public void init() {
////        textView = findViewById(R.id.textView);
//
//        // GSON 컨버터를 사용하는 REST 어댑터 생성
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        int a = 8;
//    }
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        init();
//
//        // 10번 HashMap
//        HashMap<String, String> pageMap = new HashMap<String,String>();
//
//        pageMap.put("dl", "http://cjmallapp.cjmall.com/m/homeTab/main?hmtabMenuId=000002");
//        pageMap.put("cd89", "000002");
//        pageMap.put("tid", "UA-115948787-1");
//        pageMap.put("ds", "web");
//        pageMap.put("cd123", "000002_unique");
//        pageMap.put("dt", "홈탭>홈");
//
//        pageMap.put("cd96", "CAMC201209158249_1002");
//        pageMap.put("uid", "200710004037");
//        pageMap.put("cd94", "I0878");
//        pageMap.put("cd95", "APP_PUSH_ANDROID");
//        pageMap.put("cd93", "G0001");
//        pageMap.put("ul", "ko");
//        pageMap.put("sr", "2058x1080");
//        pageMap.put("cd38", "SM-G955N"); // s8+
//        pageMap.put("cd39", "Mozilla/5.0 (Linux; Android 9; SM-G955N Build/PPR1.180610.011; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/86.0.4240.198 Mobile Safari/537.36 OSHOPPING(os=android;osVersion=9;deviceModel=SM-G955N;serviceName=cjmall;serviceVersion=6.9.4;serviceBuildVersion=710;serverStatus=prd;ab=1;) GA(_ga_cid=6b0af77b0d65781b81ec04d9f9afcad2;)");
//        pageMap.put("cd36", "Mobile App");
//        pageMap.put("cd56", "홈탭_000002");
//
//        pageMap.put("an", "CJmall");
//        pageMap.put("cd13", "N");
//        pageMap.put("cd35", "Y");
//        pageMap.put("cd10", "Y");
//        pageMap.put("cd11", "Y");
//        pageMap.put("cd41", "710");
//        pageMap.put("cd1", "6b0af77b0d65781b81ec04d9f9afcad2");
//        pageMap.put("t", "pageview");
//        pageMap.put("cd6", "200710004037");
//        pageMap.put("av", "6.9.4");
//
//        pageMap.put("v", "1");
//        pageMap.put("cd8", "65");
//        pageMap.put("cd7", "F");
//        pageMap.put("cd9", "A0106010");
//        pageMap.put("aid", "com.cjoshppingphone");
//        pageMap.put("cid", "6b0af77b0d65781b81ec04d9f9afcad2");
//
//
//        // GitHub API 인터페이스 생성
//        GACol gacol = retrofit.create(GACol.class);
//        // 인터페이스에 구현한 메소드인 contributors에 param 값을 넘기는 요청 만든다
////        Call<List<Contributor>> call = gitHub.contributors("square", "retrofit");
//        Call<HashMap<String,String>> call = gacol.getPost(pageMap);
//        int asdlfkjsadlkfsaldkjf = 0;
////        // 앞서만든 요청을 수행
////        call.enqueue(new Callback<HashMap<String, String>>() {
////            @Override     // 성공 시
////            public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
////                HashMap<String, String> contributors = response.body();
////                // 받아온 리스트를 순회하면서
////                // 1. status 값이 있는가?
////                // 2. is success true false
////
////                for (Contributor contributor : contributors) {
////                    // 텍스트 뷰에 login 정보를 붙임
////                    textView.append(contributor.login);
////                }
////            }
////            @Override     // 서버 실패 시
////            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
////                Toast.makeText(MainActivity.this, "정보받아오기 실패", Toast.LENGTH_LONG)
////                        .show();
////            }
////        });
//
//
//        call.enqueue(new Callback<HashMap<String, String>>() {
//            @Override
//            public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
//                // onResponse 결과 true false 반환 매개변수 ->
//                // status code != 200
//                int statusCode = response.code();
//
//                if(response.isSuccessful()){
//                    HashMap<String, String> pageMap = response.body();
//                    Log.d(TAG, "onResponse: 성공, 결과 ");
//                }else {
//                    Log.d(TAG, "onResponse: 실패 ");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<HashMap<String, String>> call,  Throwable t) {
//                // Log error here since request failed
//                Log.e(TAG, "onFailure");
//
//            }
//        });



//        setRetrofitInit();
//        callMovieList();

        //GAID
//        GAID gaid = new GAID();
//        gaid.execute();

        // ADID
//        ADID adid = new ADID();
//        adid.execute();


        // pageview: 페이지 전송
//        Map<String,String> pageMap = new HashMap<String,String>();
//
//        pageMap.put("cd1", GoogleAnalytics.user_pseudo_id[0]);
//        pageMap.put("cd2", "맞춤 측정 기준 2 값");
//        pageMap.put("cd3", "맞춤 측정 기준 3 값");
//        pageMap.put("cd4", "맞춤 측정 기준 4 값");
//
//
//        Thread pageThread = new Thread(new GoogleAnalytics.gaThread(pageMap));
//        pageThread.start();

//        Thread thread = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                try  {
//                    //Your code goes here
//                    GoogleAnalytics.hitSe();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        thread.start();

//        Map<String, String> pageMap = new LinkedHashMap<String, String>();
//
//
//        Thread pageThread = new Thread(new GoogleAnalytics.gaThread(pageMap));
//        pageThread.start();

    // onCreate 끝
//    }

    public void btnEvent(View v){
    }
    private WebView webView;
    public void btnWebView(View v){
        //SubActivity로 가는 인텐트를 생성
        Intent intent = new Intent(this, WebViewActivity.class);
        //액티비티 시작!
        startActivity(intent);
    }
    // 공통 전자상거래
    Map<String, String> action_Map = new HashMap<String, String>();
    Map<String, String> ecommerce_Map = new HashMap<String, String>();
    Map<String, Map<String, String>> product_Map = new HashMap<String, Map<String, String>>();
    public void proimp(View v){
    }
    public void proclick(View v){
    }
    public void prodetail(View v){
    }
    public void proadd(View v){
    }
    public void prodel(View v){
    }
    public void procheck(View v){
    }
    public void propur(View v){
    }
    public void proref(View v){
    }
    public void promoimpre(View v){
    }
    public void promoclick(View v){
    }
}