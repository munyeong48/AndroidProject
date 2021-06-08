package com.example.sjhapplication;

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

public class GoogleAnalytics {

    public static String[] user_pseudo_id = new String[1];

    public static class gaThread implements Runnable {

         private final Map<String, String> gaData;
         public gaThread(Map<String, String> _gaData) {
             gaData = _gaData;
         }
         public void run() {
             try {
                 if (gaData != null && user_pseudo_id[0] != null) {
                     Map<String, String> parameters = new LinkedHashMap<String, String>();
                     parameters.put("v", "1");
                     // 엑세스받은 ga계정 추적Id로 변경함
                     parameters.put("tid", "UA-115948787-1");
                     // 내 추적Id               parameters.put("tid", "UA-170166665-1");
                     parameters.put("cid", user_pseudo_id[0]);
                     parameters.put("ds", "web");
                     parameters.put("aip", "1");

                     Iterator<String> sIterator = gaData.keySet().iterator();

                     while (sIterator.hasNext()) {
                         String key = sIterator.next();
                         if (key.contains("cd")) parameters.put(key, gaData.get(key));
                         if (key.contains("cm")) parameters.put(key, gaData.get(key));

                         if (key.equals("dt")) parameters.put(key, gaData.get(key));
                         if (key.equals("dl")) parameters.put(key, gaData.get(key));
                         if (key.equals("uid")) parameters.put(key, gaData.get(key));
                         if (key.equals("t")) parameters.put(key, gaData.get(key));

                         if (key.equals("ec")) parameters.put(key, gaData.get(key));
                         if (key.equals("ea")) parameters.put(key, gaData.get(key));
                         if (key.equals("el")) parameters.put(key, gaData.get(key));

                         if (key.contains("pr")) parameters.put(key, gaData.get(key));
                         if (key.equals("pa")) parameters.put(key, gaData.get(key));
                         if (key.equals("cu")) parameters.put(key, gaData.get(key));
                         if (key.equals("ni")) parameters.put(key, gaData.get(key));
                         if (key.equals("tr")) parameters.put(key, gaData.get(key));
                         if (key.equals("ti")) parameters.put(key, gaData.get(key));
                         if (key.equals("ts")) parameters.put(key, gaData.get(key));

                         if (key.equals("cs")) parameters.put(key, gaData.get(key));
                         if (key.equals("cm")) parameters.put(key, gaData.get(key));
                         if (key.equals("cn")) parameters.put(key, gaData.get(key));
                         if (key.equals("cn")) parameters.put(key, gaData.get(key));
                         if (key.equals("ck")) parameters.put(key, gaData.get(key));
                         if (key.equals("cc")) parameters.put(key, gaData.get(key));
                     }
                     Set set = parameters.entrySet();
                     Iterator it = set.iterator();
                     hitSend(it);
                    }
                 }catch (Exception e) {
                 Log.d("Error",e.getMessage());
             }
         }
     }


     private static void hitSend(Iterator it) throws Exception {

         StringBuilder builder = new StringBuilder();

         while(it.hasNext()) {
             Map.Entry e = (Map.Entry)it.next();
             builder.append(URLEncoder.encode(e.getKey().toString(), "UTF-8"));
             builder.append("=");
             builder.append(URLEncoder.encode(e.getValue().toString(), "UTF-8"));
             builder.append("&");
         }

         builder.setLength(builder.length() - 1);
         String request = "http://www.google-analytics.com/collect";

         URL url = new URL(request);
         HttpURLConnection conn = (HttpURLConnection)url.openConnection();
         conn.setRequestMethod("POST");
         conn.setRequestProperty("Content-Length", Integer.toString(builder.toString().getBytes().length));
         conn.setDoOutput(true);

         //post형식으로 데이터 쌓은것 보냄 body단
         DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
         wr.writeBytes(builder.toString());
         wr.close();

         int status = conn.getResponseCode();
         try {
             if(status != 200) {
                 throw new Exception("Google Analytics tracking did not return OK 200");
             }else{
                 Log.v("GADATA", URLDecoder.decode(builder.toString()));
                 Log.v("status", String.valueOf(status));

             }
         } catch (Exception e) {
             throw new Exception(e.getMessage());
         }
     }


//115948787
    public static void hitSe() throws Exception {
        StringBuilder builder = new StringBuilder();

//1
//        builder.append("dl=http://cjmallapp.cjmall.com/m/homeTab/main?hmtabMenuId=000002&cd89=000002&tid=UA-115948787-1&ds=web&cd123=000002_unique&dt=%ED%99%88%ED%83%AD%3E%ED%99%88_1&cd96=lucky&uid=200203078187&cd94=I3676&cd95=KAKAO_PLUS_APP&cd93=G1012&ul=ko&sr=2094x1080&cd38=SM-G955N&cd39=Mozilla%2F5.0%20%28Linux%3B%20Android%209%3B%20SM-G955N%20Build%2FPPR1.180610.011%3B%20wv%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Version%2F4.0%20Chrome%2F87.0.4280.101%20Mobile%20Safari%2F537.36%20OSHOPPING%28os%3Dandroid%3BosVersion%3D9%3BdeviceModel%3DSM-G955N%3BserviceName%3Dcjmall%3BserviceVersion%3D6.9.4%3BserviceBuildVersion%3D710%3BserverStatus%3Dprd%3Bab%3D1%3B%29%20GA%28_ga_cid%3D6e34ee6dbd6317673f02026e1a4104f0%3B%29&cd36=Mobile%20App&cd56=%ED%99%88%ED%83%AD_000002&an=CJmall&cd13=N&cd35=Y&cd10=Y&cd11=Y&cd41=710&cd1=6e34ee6dbd6317673f02026e1a4104f0&t=pageview&cd6=200203078187&av=6.9.4&v=1&cd8=47&cd7=F&cd9=A0107010&aid=com.cjoshppingphone&cid=6e34ee6dbd6317673f02026e1a4104f0");
//2
//        builder.append("dl=http://cjmallapp.cjmall.com/m/homeTab/main?hmtabMenuId=000002&cd89=000002&tid=UA-115948787-1&ds=web&cd123=000002_on_resume&dt=%ED%99%88%ED%83%AD%3E%ED%99%88_2&uid=201703130929&cd94=I4052&cd95=%EB%B2%84%EC%A6%88%EB%B9%8C&cd93=G1010&ul=ko&sr=2020x1080&cd38=SM-G970N&cd39=Mozilla%2F5.0%20%28Linux%3B%20Android%2010%3B%20SM-G970N%20Build%2FQP1A.190711.020%3B%20wv%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Version%2F4.0%20Chrome%2F87.0.4280.101%20Mobile%20Safari%2F537.36%20OSHOPPING%28os%3Dandroid%3BosVersion%3D10%3BdeviceModel%3DSM-G970N%3BserviceName%3Dcjmall%3BserviceVersion%3D6.9.4%3BserviceBuildVersion%3D710%3BserverStatus%3Dprd%3Bab%3D1%3B%29%20GA%28_ga_cid%3D5a52c3879fe885a2ab653aff68f4414f%3B%29&cd36=Mobile%20App&cd56=%ED%99%88%ED%83%AD_000002&an=CJmall&cd13=N&cd35=Y&cd10=Y&cd11=Y&cd41=710&cd1=5a52c3879fe885a2ab653aff68f4414f&t=pageview&cd6=201703130929&av=6.9.4&v=1&cd8=54&cd7=M&cd9=A0106010&aid=com.cjoshppingphone&cid=5a52c3879fe885a2ab653aff68f4414f");
//3
//        builder.append("dl=http://cjmallapp.cjmall.com/m/homeTab/main?hmtabMenuId=000002&cd89=000002&tid=UA-115948787-1&ds=web&cd123=000002_on_resume&dt=%ED%99%88%ED%83%AD%3E%ED%99%88_3&cd96=CAMC201209158257_1002&uid=200108095158&cd94=I0878&cd95=APP_PUSH_ANDROID&cd93=G0001&ul=o&sr=1920x1080&cd38=SM-G930K&cd39=Mozilla%2F5.0%20%28Linux%3B%20Android%208.0.0%3B%20SM-G930K%20Build%2FR16NW%3B%20wv%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Version%2F4.0%20Chrome%2F87.0.4280.101%20Mobile%20Safari%2F537.36%20OSHOPPING%28os%3Dandroid%3BosVersion%3D8.0.0%3BdeviceModel%3DSM-G930K%3BserviceName%3Dcjmall%3BserviceVersion%3D6.9.4%3BserviceBuildVersion%3D710%3BserverStatus%3Dprd%3Bab%3D1%3B%29%20GA%28_ga_cid%3D0c951857d5372679a201404f93ed5dea%3B%29&cd36=Mobile%20App&cd56=%ED%99%88%ED%83%AD_000002&an=CJmall&cd13=N&cd35=Y&cd10=Y&cd11=Y&cd41=710&cd1=0c951857d5372679a201404f93ed5dea&t=pageview&cd6=200108095158&av=6.9.4&v=1&cd8=43&cd7=F&cd9=A0105010&aid=com.cjoshppingphone&cid=0c951857d5372679a201404f93ed5dea");
//4
//        builder.append("dl=http://cjmallapp.cjmall.com/m/homeTab/main?hmtabMenuId=000002&cd89=000002&tid=UA-115948787-1&ds=web&cd123=000002_on_resume&dt=%ED%99%88%ED%83%AD%3E%ED%99%88_4&cd96=CAMC201209158241_1002&uid=200305094548&cd94=I0878&cd95=APP_PUSH_ANDROID&cd93=G0001&ul=ko&sr=2050x1080&cd38=SM-G977N&cd39=Mozilla%2F5.0%20%28Linux%3B%20Android%2010%3B%20SM-G977N%20Build%2FQP1A.190711.020%3B%20wv%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Version%2F4.0%20Chrome%2F87.0.4280.101%20Mobile%20Safari%2F537.36%20OSHOPPING%28os%3Dandroid%3BosVersion%3D10%3BdeviceModel%3DSM-G977N%3BserviceName%3Dcjmall%3BserviceVersion%3D6.9.4%3BserviceBuildVersion%3D710%3BserverStatus%3Dprd%3Bab%3D1%3B%29%20GA%28_ga_cid%3Dd735a3dc506730866e9f966f1ab295fa%3B%29&cd36=Mobile%20App&cd56=%ED%99%88%ED%83%AD_000002&an=CJmall&cd13=N&cd35=Y&cd10=Y&cd11=Y&cd41=710&cd1=d735a3dc506730866e9f966f1ab295fa&t=pageview&cd6=200305094548&av=6.9.4&v=1&cd8=47&cd7=F&cd9=A0104010&aid=com.cjoshppingphone&cid=d735a3dc506730866e9f966f1ab295fa");
//5
//        builder.append("dl=http://cjmallapp.cjmall.com/m/homeTab/main?hmtabMenuId=000002&cd89=000002&tid=UA-115948787-1&ds=web&cd123=000002_unique&dt=%ED%99%88%ED%83%AD%3E%ED%99%88_5&uid=201801067868&ul=ko&sr=2094x1080&cd38=SM-A750N&cd39=Mozilla%2F5.0%20%28Linux%3B%20Android%209%3B%20SM-A750N%20Build%2FPPR1.180610.011%3B%20wv%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Version%2F4.0%20Chrome%2F87.0.4280.101%20Mobile%20Safari%2F537.36%20OSHOPPING%28os%3Dandroid%3BosVersion%3D9%3BdeviceModel%3DSM-A750N%3BserviceName%3Dcjmall%3BserviceVersion%3D6.9.4%3BserviceBuildVersion%3D710%3BserverStatus%3Dprd%3Bab%3D1%3B%29%20GA%28_ga_cid%3Df87915250454ba66c1757bc0c3ae698a%3B%29&cd36=Mobile%20App&cd56=%ED%99%88%ED%83%AD_000002&an=CJmall&cd13=N&cd35=Y&cd10=Y&cd11=N&cd41=710&cd1=f87915250454ba66c1757bc0c3ae698a&t=pageview&cd6=201801067868&av=6.9.4&v=1&cd8=59&cd9=A0107010&aid=com.cjoshppingphone&cid=f87915250454ba66c1757bc0c3ae698a");
//6
//        builder.append("dl=http://cjmallapp.cjmall.com/m/homeTab/main?hmtabMenuId=000002&cd89=000002&tid=UA-115948787-1&ds=web&cd123=000002_unique&dt=%ED%99%88%ED%83%AD%3E%ED%99%88_6&uid=201305071321&ul=ko&sr=2094x1080&cd38=SM-N950N&cd39=Mozilla%2F5.0%20%28Linux%3B%20Android%209%3B%20SM-N950N%20Build%2FPPR1.180610.011%3B%20wv%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Version%2F4.0%20Chrome%2F87.0.4280.86%20Mobile%20Safari%2F537.36%20OSHOPPING%28os%3Dandroid%3BosVersion%3D9%3BdeviceModel%3DSM-N950N%3BserviceName%3Dcjmall%3BserviceVersion%3D6.9.4%3BserviceBuildVersion%3D710%3BserverStatus%3Dprd%3Bab%3D1%3B%29%20GA%28_ga_cid%3Dab30d4ccb1ab926ac8b86f72d782df81%3B%29&cd36=Mobile%20App&cd56=%ED%99%88%ED%83%AD_000002&an=CJmall&cd13=Y&cd35=Y&cd10=Y&cd11=Y&cd41=710&cd1=ab30d4ccb1ab926ac8b86f72d782df81&t=pageview&cd6=201305071321&av=6.9.4&v=1&cd8=38&cd7=M&cd9=A0106010&aid=com.cjoshppingphone&cid=ab30d4ccb1ab926ac8b86f72d782df81");
//7
//        builder.append("dl=http://cjmallapp.cjmall.com/m/homeTab/main?hmtabMenuId=000002&cd89=000002&tid=UA-115948787-1&ds=web&cd123=000002_on_resume&dt=%ED%99%88%ED%83%AD%3E%ED%99%88_7&uid=200001040153&ul=ko&sr=2050x1080&cd38=SM-G977N&cd39=Mozilla%2F5.0%20%28Linux%3B%20Android%2010%3B%20SM-G977N%20Build%2FQP1A.190711.020%3B%20wv%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Version%2F4.0%20Chrome%2F87.0.4280.101%20Mobile%20Safari%2F537.36%20OSHOPPING%28os%3Dandroid%3BosVersion%3D10%3BdeviceModel%3DSM-G977N%3BserviceName%3Dcjmall%3BserviceVersion%3D6.9.4%3BserviceBuildVersion%3D710%3BserverStatus%3Dprd%3Bab%3D1%3B%29%20GA%28_ga_cid%3Dc2e0031c30aaeb803959f9487bb52423%3B%29&cd36=Mobile%20App&cd56=%ED%99%88%ED%83%AD_000002&an=CJmall&cd13=N&cd35=Y&cd10=Y&cd11=Y&cd41=710&cd1=c2e0031c30aaeb803959f9487bb52423&t=pageview&cd6=200001040153&av=6.9.4&v=1&cd8=45&cd7=F&cd9=A0105010&aid=com.cjoshppingphone&cid=c2e0031c30aaeb803959f9487bb52423");
//8
//        builder.append("dl=http://cjmallapp.cjmall.com/m/homeTab/main?hmtabMenuId=000002&cd89=000002&tid=UA-115948787-1&ds=web&cd123=000002_on_page_selected&dt=%ED%99%88%ED%83%AD%3E%ED%99%88_8&uid=200311017850&cd90=002349&ul=ko&sr=1920x1080&cd38=SM-A520S&cd39=Mozilla%2F5.0%20%28Linux%3B%20Android%208.0.0%3B%20SM-A520S%20Build%2FR16NW%3B%20wv%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Version%2F4.0%20Chrome%2F87.0.4280.86%20Mobile%20Safari%2F537.36%20OSHOPPING%28os%3Dandroid%3BosVersion%3D8.0.0%3BdeviceModel%3DSM-A520S%3BserviceName%3Dcjmall%3BserviceVersion%3D6.9.4%3BserviceBuildVersion%3D710%3BserverStatus%3Dprd%3Bab%3D1%3B%29%20GA%28_ga_cid%3D8ed540ed3ed097443aa47430f73401da%3B%29&cd36=Mobile%20App&cd56=%ED%99%88%ED%83%AD_000002&an=CJmall&cd13=N&cd35=Y&cd10=Y&cd11=Y&cd41=710&cd1=8ed540ed3ed097443aa47430f73401da&t=pageview&cd6=200311017850&av=6.9.4&v=1&cd8=39&cd7=F&cd9=A0105010&aid=com.cjoshppingphone&cid=8ed540ed3ed097443aa47430f73401da");
//9
//        builder.append("dl=http://cjmallapp.cjmall.com/m/homeTab/main?hmtabMenuId=000002&cd89=000002&tid=UA-115948787-1&ds=web&cd123=000002_unique&dt=%ED%99%88%ED%83%AD%3E%ED%99%88_9&uid=201707099865&ul=ko&sr=1920x1080&cd38=SM-G930S&cd39=Mozilla%2F5.0%20%28Linux%3B%20Android%208.0.0%3B%20SM-G930S%20Build%2FR16NW%3B%20wv%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Version%2F4.0%20Chrome%2F87.0.4280.101%20Mobile%20Safari%2F537.36%20OSHOPPING%28os%3Dandroid%3BosVersion%3D8.0.0%3BdeviceModel%3DSM-G930S%3BserviceName%3Dcjmall%3BserviceVersion%3D6.9.4%3BserviceBuildVersion%3D710%3BserverStatus%3Dprd%3Bab%3D1%3B%29%20GA%28_ga_cid%3Dc49a00859a4a3a951d2d1eb98f5c225d%3B%29&cd36=Mobile%20App&cd56=%ED%99%88%ED%83%AD_000002&an=CJmall&cd13=N&cd35=Y&cd10=Y&d11=Y&cd41=710&cd1=c49a00859a4a3a951d2d1eb98f5c225d&t=pageview&cd6=201707099865&av=6.9.4&v=1&cd8=36&cd7=F&cd9=A0106010&aid=com.cjoshppingphone&cid=c49a00859a4a3a951d2d1eb98f5c225d");
//10
//        builder.append("dl=http://cjmallapp.cjmall.com/m/homeTab/main?hmtabMenuId=000002&cd89=000002&tid=UA-115948787-1&ds=web&cd123=000002_unique&dt=%ED%99%88%ED%83%AD%3E%ED%99%88_10&cd96=CAMC201209158249_1002&uid=200710004037&cd94=I0878&cd95=APP_PUSH_ANDROID&cd93=G0001&ul=ko&sr=2058x1080&cd38=SM-G955N&cd39=Mozilla%2F5.0%20%28Linux%3B%20Android%209%3B%20SM-G955N%20Build%2FPPR1.180610.011%3B%20wv%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Version%2F4.0%20Chrome%2F86.0.4240.198%20Mobile%20Safari%2F537.36%20OSHOPPING%28os%3Dandroid%3BosVersion%3D9%3BdeviceModel%3DSM-G955N%3BserviceName%3Dcjmall%3BserviceVersion%3D6.9.4%3BserviceBuildVersion%3D710%3BserverStatus%3Dprd%3Bab%3D1%3B%29%20GA%28_ga_cid%3D6b0af77b0d65781b81ec04d9f9afcad2%3B%29&cd36=Mobile%20App&cd56=%ED%99%88%ED%83%AD_000002&an=CJmall&cd13=N&cd35=Y&cd10=Y&cd11=Y&cd41=710&cd1=6b0af77b0d65781b81ec04d9f9afcad2&t=pageview&cd6=200710004037&av=6.9.4&v=1&cd8=65&cd7=F&cd9=A0106010&aid=com.cjoshppingphone&cid=6b0af77b0d65781b81ec04d9f9afcad2");

        builder.setLength(builder.length() - 1);
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
                throw new Exception("Google Analytics tracking did not return OK 200");
            }else{
                Log.v("GADATA", URLDecoder.decode(builder.toString()));
                Log.d("status", String.valueOf(status));
            }
        } catch (Exception e) {  throw new Exception(e.getMessage()); }
    }
 }