package com.example.asyncaos;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static com.example.asyncaos.MainActivity.firebaseAnalytics;

public class GoogleAnalytics {

    public static String[] user_pseudo_id = new String[1];

    //AsyncTask 상속한 클래스
    static class GoogleAsyncTask extends AsyncTask<Void, String, Void> {

        private final Map<String, String> gaData;

        public GoogleAsyncTask(Map<String, String> _gaData) {
            gaData = _gaData;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Log.e("__LOG__","onPreExecute");
        }

        //doInBackground : 실제 작업은 이곳에서 구현함, 필수 메소드임
        @Override
        protected Void doInBackground(Void ... params) {
            if (gaData != null && user_pseudo_id[0] != null) {
                Map<String, String> parameters = new LinkedHashMap<String, String>();
                parameters.put("v","1");
                parameters.put("tid", "UA-115948787-1");
                parameters.put("cid", user_pseudo_id[0]);
                parameters.put("ds", "web");
                parameters.put("aip", "1");

                Iterator<String> sIterator = gaData.keySet().iterator();
                while (sIterator.hasNext()) {
                    String key = sIterator.next();
                    if (key.contains("cd")) parameters.put(key, gaData.get(key));   // Custom Dimension
                    if (key.contains("cm")) parameters.put(key, gaData.get(key));   // Custom Metric
                    if (key.equals("t")) parameters.put(key, gaData.get(key));   // Hit Type
                    if (key.equals("dl")) parameters.put(key, gaData.get(key));   // Document Location URL
                    if (key.equals("dt")) parameters.put(key, gaData.get(key));   // Document Title
                    if (key.equals("ec")) parameters.put(key, gaData.get(key));   // Event Category
                    if (key.equals("ea")) parameters.put(key, gaData.get(key));   // Event Action
                    if (key.equals("el")) parameters.put(key, gaData.get(key));   // Event Label
                    if (key.equals("ev")) parameters.put(key, gaData.get(key));   // Event Value
                    if (key.equals("cn")) parameters.put(key, gaData.get(key));   // Campaign Name
                    if (key.equals("cs")) parameters.put(key, gaData.get(key));   // Campaign Source
                    if (key.equals("cm")) parameters.put(key, gaData.get(key));   // Campaign Medium
                    if (key.equals("ck")) parameters.put(key, gaData.get(key));   // Campaign Keyword
                    if (key.equals("cc")) parameters.put(key, gaData.get(key));   // Campaign Content
                    if (key.equals("ci")) parameters.put(key, gaData.get(key));   // Campaign ID
                    if (key.equals("sr")) parameters.put(key, gaData.get(key));   // Screen Resolution
                    if (key.equals("ul")) parameters.put(key, gaData.get(key));   // User Language
                    if (key.equals("an")) parameters.put(key, gaData.get(key));   // Application Name
                    if (key.equals("av")) parameters.put(key, gaData.get(key));   // Application Version
                    if (key.equals("cu")) parameters.put(key, gaData.get(key));   // Currency Code
                    if (key.equals("ni")) parameters.put(key, gaData.get(key));   // NonInteraction
                    if (key.equals("pa")) parameters.put(key, gaData.get(key));   // Product Action
                    //if (key.equals("adid")) parameters.put(key, gaData.get(key));   // Google Advertising Id
                    if (key.equals("ti")) parameters.put(key, gaData.get(key));   // Transaction ID
                    if (key.equals("ta")) parameters.put(key, gaData.get(key));   // Transaction Affiliation
                    if (key.equals("tr")) parameters.put(key, gaData.get(key));   // Transaction Revenue
                    if (key.equals("ts")) parameters.put(key, gaData.get(key));   // Transaction Shipping
                    if (key.equals("tt")) parameters.put(key, gaData.get(key));   // Transaction Tax
                    if (key.equals("tcc")) parameters.put(key, gaData.get(key));  // Transaction Coupon Code
                    if (key.equals("pal")) parameters.put(key, gaData.get(key));  // Product Action List
                    if (key.equals("cos")) parameters.put(key, gaData.get(key));  // Checkout Step
                    if (key.equals("col")) parameters.put(key, gaData.get(key));  // Checkout Step Option
                    if (key.equals("aid")) parameters.put(key, gaData.get(key));  // Application ID
                    if (key.contains("il")) parameters.put(key, gaData.get(key)); // Impression Data
                    // Product Impression List Name -> il<listIndex>nm
                    // Product Impression SKU -> il<listIndex>pi<productIndex>id
                    // Product Impression Name -> il<listIndex>pi<productIndex>nm
                    // Product Impression Brand -> il<listIndex>pi<productIndex>br
                    // Product Impression Category -> il<listIndex>pi<productIndex>ca
                    // Product Impression Variant -> il<listIndex>pi<productIndex>va
                    // Product Impression Position -> il<listIndex>pi<productIndex>ps
                    // Product Impression Price -> il<listIndex>pi<productIndex>pr
                    // Product Impression Custom Dimension -> il<listIndex>pi<productIndex>cd<dimensionIndex>
                    // Product Impression Custom Metric -> il<listIndex>pi<productIndex>cm<metricIndex>
                    if (key.contains("pr")) parameters.put(key, gaData.get(key)); // Product Data
                    // Product SKU -> pr<productIndex>id
                    // Product Name -> pr<productIndex>nm
                    // Product Brand -> pr<productIndex>br,
                    // Product Category -> pr<productIndex>ca
                    // Product Variant -> pr<productIndex>va
                    // Product Price -> pr<productIndex>pr
                    // Product Quantity -> pr<productIndex>qt
                    // Product Coupon Code -> pr<productIndex>cc
                    // Product Position -> pr<productIndex>ps
                    // Product Custom Dimension -> pr<productIndex>cd<dimensionIndex>
                    // Product Custom Metric -> pr<productIndex>cm<metricIndex>
                }
                Set set = parameters.entrySet();
                Iterator it = set.iterator();
                try {
                    hitSend(it);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Log.e("__LOG__","doInBackground End");


            //cancel 메소드 : 중간에 작업을 취소할 때 호출함
            //doInBackground가 끝나기 전에 AsyncTask의 cancel메서드를 호출하면 onPostExecute를 호출하지 않음
            //대신 성공 완료와 취소 완료를 위한 동작을 각각 구현할 수 있도록 대안적인 onCancelled 콜백 메서드를 호출함
            //cancel(true)를 호출하면 doInBackground가 실행 중인 스레드를 무조건 중단할 것이다.
            //cancel(true)는 과격하게 AsynkTask를 중지하는 방법으로 가능한 한 이 방법은 피하는 것이 좋음
            //cancel(false)를 호출하면 isCancelled()의 결과가 true가 됨
            //따라서 AsynkTask의 doInBackground 내부에서 이를 확인하여 스레드의 실행을 종료할 수 있다.
            //https://m.blog.naver.com/PostView.nhn?blogId=horajjan&logNo=220733723966&proxyReferer=https:%2F%2Fwww.google.com%2F
            //doInBackground()함수 내에서 어떤 쓰레드 관련 함수를 실행시키는 상태였을 때 InterruptedException을 발생시키고 싶으면
            //true를 매개변수로 전달하고 발생시키고 싶지 않으면 false를 전달하면 됨
            //cancel을 호출하자마자 쓰레드 내부에서 뭐가 실행되고 있던 간에 바로 종료시키고 싶으면 true를 매개변수로 전달하면 되고,
            //현재 실행되고 있는 것은 마무리된 다음에 종료시키고 싶으면 false를 전달하면 됨
            //https://simsimjae.tistory.com/246
            //cancel(false);

            //작업 완료 후 결과 반환
            return null;

        }//doInBackground
        //onPostExecute : doInBackground 작업 완료 후 호출됨 cacel 메소드가 호출되지않으면 onpost 가 마지막 메소드
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Log.e("__LOG__","onPostExecute");
        }


        //onCancelled : cancel메소드가 호출되었을 때 실행되며 마지막 메소드가 됨
        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.e("__LOG__","onCancelled");
        }
    }//GoogleAsyncTask


    @SuppressLint("InvalidAnalyticsName")
    public static void hitSend(Iterator it) throws Exception {
        StringBuilder builder = new StringBuilder();
        while(it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            builder.append(URLEncoder.encode(e.getKey().toString(), "UTF-8"));
            builder.append("=");
            builder.append(URLEncoder.encode(e.getValue().toString(), "UTF-8"));
            builder.append("&");
        }
        builder.setLength(builder.length()-1);
        String request = "http://www.google-analytics.com/collect";
        URL url = new URL(request);

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Length", Integer.toString(builder.toString().getBytes().length));
        conn.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(builder.toString());
        wr.close();
        int status = conn.getResponseCode();
        try {
            if(status != 200) {
                Bundle bundle = new Bundle(); //Bundle 객체 생성
                bundle.putString("firebase_logevent", "firebase_logevent"); //보고서-페이지 제목 및 화면 이름 or 이벤트-firebase_screen
                firebaseAnalytics.logEvent("error", bundle); //logEvent(이벤트 명, bundle)
                throw new Exception("Google Analytics tracking did not return OK 200");
            } else {
                Bundle bundle = new Bundle(); //Bundle 객체 생성
                bundle.putString("firebase_logevent", "firebase_logevent"); //보고서-페이지 제목 및 화면 이름 or 이벤트-firebase_screen
                firebaseAnalytics.logEvent("1209_async", bundle); //logEvent(이벤트 명, bundle)
                Log.v("GADATA", URLDecoder.decode(builder.toString(), "UTF-8"));
            }
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
