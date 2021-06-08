package com.example.myapplication;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import static com.example.myapplication.chitsend.hitSend;

public class GoogleAnalytics {
    public static String[]user_pseudo_id= new String[1];
    public static String aid;
    public static String an;
    public static String av;
    public static String sr;
    public static String ul;
    public static String a;

    public static class gaThread implements Runnable{
        private final Map<String,String>gaData;

        public gaThread(Map<String,String>_gaData){
            gaData = _gaData;
        }
        public void run(){
            try{
                a="1";
                if(gaData!=null && user_pseudo_id[0]!=null) {
                    Map<String,String>parameters = new LinkedHashMap<String,String>();

                    parameters.put("v","1");
                    parameters.put("tid", "UA-115948787-1");

                    parameters.put("cid", user_pseudo_id[0]);
                    parameters.put("ds", "web");
                    parameters.put("aip", "1");

                    Iterator<String> sIterator= gaData.keySet().iterator();
                    while(sIterator.hasNext()) {
                        String key= sIterator.next();
                        if(key.contains("aip")) parameters.put(key, gaData.get(key));
                        if(key.contains("v")) parameters.put(key, gaData.get(key));
                        if(key.contains("tid")) parameters.put(key, gaData.get(key));
                        if(key.contains("cid")) parameters.put(key, gaData.get(key));
                        if(key.contains("t")) parameters.put(key, gaData.get(key));
                        if(key.contains("ds")) parameters.put(key, gaData.get(key));
                        if(key.contains("dl")) parameters.put(key, gaData.get(key));
                        if(key.contains("dt")) parameters.put(key, gaData.get(key));
                        if(key.contains("ec")) parameters.put(key, gaData.get(key));
                        if(key.contains("ea")) parameters.put(key, gaData.get(key));
                        if(key.contains("el")) parameters.put(key, gaData.get(key));
                        if(key.contains("ev")) parameters.put(key, gaData.get(key));
                        if(key.contains("ni")) parameters.put(key, gaData.get(key));
                        if(key.contains("cd")) parameters.put(key, gaData.get(key));
                        if(key.contains("cm")) parameters.put(key, gaData.get(key));
                        if(key.contains("uid")) parameters.put(key, gaData.get(key));
                        if(key.contains("cn")) parameters.put(key, gaData.get(key));
                        if(key.contains("cs")) parameters.put(key, gaData.get(key));
                        if(key.contains("ck")) parameters.put(key, gaData.get(key));
                        if(key.contains("cc")) parameters.put(key, gaData.get(key));
                        if(key.contains("ci")) parameters.put(key, gaData.get(key));
                        if(key.contains("sr")) parameters.put(key, gaData.get(key));
                        if(key.contains("ul")) parameters.put(key, gaData.get(key));
                        if(key.contains("an")) parameters.put(key, gaData.get(key));
                        if(key.contains("aid")) parameters.put(key, gaData.get(key));
                        if(key.contains("av")) parameters.put(key, gaData.get(key));
                        if(key.contains("pa")) parameters.put(key, gaData.get(key));
                        if(key.contains("ti")) parameters.put(key, gaData.get(key));
                        if(key.contains("ta")) parameters.put(key, gaData.get(key));
                        if(key.contains("tr")) parameters.put(key, gaData.get(key));
                        if(key.contains("ts")) parameters.put(key, gaData.get(key));
                        if(key.contains("tt")) parameters.put(key, gaData.get(key));
                        if(key.contains("tcc")) parameters.put(key, gaData.get(key));
                        if(key.contains("pal")) parameters.put(key, gaData.get(key));
                        if(key.contains("cos")) parameters.put(key, gaData.get(key));
                        if(key.contains("col")) parameters.put(key, gaData.get(key));
                        if(key.contains("cu")) parameters.put(key, gaData.get(key));
                        if(key.contains("pr")) parameters.put(key, gaData.get(key));
                        if(key.contains("il")) parameters.put(key, gaData.get(key));
                    }
                Set set= parameters.entrySet();
                Iterator it= set.iterator(); // Iterator 는 interface로서 hasNext,next(),remove()를 내포하고있다.
                hitSend(it); }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
