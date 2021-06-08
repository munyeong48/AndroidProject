package com.goldenplanet.www.gp_android.GoogleAnalytics;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.goldenplanet.www.gp_android.MainActivity;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;


public class GoogleAnalyticsApp {

    public static String[] user_pseudo_id = new String[1];
    public static String an;
    public static String av;
    public static String aid;
    public static String ul;
    public static String sr;
    public static String adid;

    public static class getAdid implements Runnable {

        private final Context _context;

        public getAdid(Context context) {
            _context = context;
        }

        public void run() {
            if (_context != null) {
                    try {
                        AdvertisingIdClient.Info info = AdvertisingIdClient.getAdvertisingIdInfo(_context);
                        GoogleAnalyticsApp.adid = info.getId();
                    } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException e) {
                        e.printStackTrace();
                    }
            }

        }
    }

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
                    parameters.put("tid", "UA-115948787-1");
                    parameters.put("cid", user_pseudo_id[0]);
                    parameters.put("ds", "app");
                    parameters.put("aip", "1");
                    parameters.put("ate", "1");
                    if(aid != null) parameters.put("aid", aid);
                    if(an != null) parameters.put("an", an);
                    if(av != null) parameters.put("av", av);
                    if(sr != null) parameters.put("sr", sr);
                    if(ul != null) parameters.put("ul", ul);
                    if(adid != null) parameters.put("adid", adid);

                    Iterator<String> sIterator = gaData.keySet().iterator();
                    while (sIterator.hasNext()) {
                        String key = sIterator.next();
                        if(gaData.get(key) != null) {
                            if (key.contains("cd")) parameters.put(key, gaData.get(key));   // Custom Dimension
                            if (key.contains("cm")) parameters.put(key, gaData.get(key));   // Custom Metric
                            if (key.equals("cd")) parameters.put(key, gaData.get(key));     // Screen Name
                            if (key.equals("t")) parameters.put(key, gaData.get(key));      // Hit Type
                            if (key.equals("dl")) parameters.put(key, gaData.get(key));     // Document Location URL
                            if (key.equals("dt")) parameters.put(key, gaData.get(key));     // Document Title
                            if (key.equals("ec")) parameters.put(key, gaData.get(key));     // Event Category
                            if (key.equals("ea")) parameters.put(key, gaData.get(key));     // Event Action
                            if (key.equals("el")) parameters.put(key, gaData.get(key));     // Event Label
                            if (key.equals("ev")) parameters.put(key, gaData.get(key));     // Event Value
                            if (key.equals("cn")) parameters.put(key, gaData.get(key));     // Campaign Name
                            if (key.equals("cs")) parameters.put(key, gaData.get(key));     // Campaign Source
                            if (key.equals("cm")) parameters.put(key, gaData.get(key));     // Campaign Medium
                            if (key.equals("ck")) parameters.put(key, gaData.get(key));     // Campaign Keyword
                            if (key.equals("cc")) parameters.put(key, gaData.get(key));     // Campaign Content
                            if (key.equals("ci")) parameters.put(key, gaData.get(key));     // Campaign ID
                            if (key.equals("sr")) parameters.put(key, gaData.get(key));     // Screen Resolution
                            if (key.equals("sc")) parameters.put(key, gaData.get(key));     // Screen Resolution
                            if (key.equals("ul")) parameters.put(key, gaData.get(key));     // User Language
                            if (key.equals("an")) parameters.put(key, gaData.get(key));     // Application Name
                            if (key.equals("aid")) parameters.put(key, gaData.get(key));    // Application ID
                            if (key.equals("av")) parameters.put(key, gaData.get(key));     // Application Version
                            if (key.equals("cu")) parameters.put(key, gaData.get(key));     // Currency Code
                            if (key.equals("ni")) parameters.put(key, gaData.get(key));     // NonInteraction
                            if (key.equals("adid")) parameters.put(key, gaData.get(key));   // Google Advertising Id
                            if (key.equals("pa")) parameters.put(key, gaData.get(key));     // Product Action
                            if (key.equals("ti"))  parameters.put(key, gaData.get(key));    // Transaction ID
                            if (key.equals("ta")) parameters.put(key, gaData.get(key));     // Transaction Affiliation
                            if (key.equals("tr")) parameters.put(key, gaData.get(key));     // Transaction Revenue
                            if (key.equals("ts")) parameters.put(key, gaData.get(key));     // Transaction Shipping
                            if (key.equals("tt")) parameters.put(key, gaData.get(key));     // Transaction Tax
                            if (key.equals("tcc")) parameters.put(key, gaData.get(key));    // Transaction Coupon Code
                            if (key.equals("pal")) parameters.put(key, gaData.get(key));    // Product Action List
                            if (key.equals("cos"))  parameters.put(key, gaData.get(key));   // Checkout Step
                            if (key.equals("col")) parameters.put(key, gaData.get(key));    // Checkout Step Option
                            if (key.contains("il")) parameters.put(key, gaData.get(key));   // Impression Data
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
                            if (key.contains("pr")) parameters.put(key, gaData.get(key));   // Product Data
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
                    }
                    Set set = parameters.entrySet();
                    Iterator it = set.iterator();
                    hitSend(it);
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.v("GADATA", e.getMessage());
            }
        }
    }

    public static void hitSend(Iterator it) throws Exception {
        StringBuilder builder = new StringBuilder();

        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            builder.append(URLEncoder.encode(e.getKey().toString(), "UTF-8"));
            builder.append("=");
            builder.append(URLEncoder.encode(e.getValue().toString(), "UTF-8"));
            builder.append("&");
        }
        builder.setLength(builder.length() - 1);
        String request = "http://www.google-analytics.com/collect";
        URL url = new URL(request);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Length", Integer.toString(builder.toString().getBytes().length));
        conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(builder.toString());
        wr.close();
        int status = conn.getResponseCode();
        try {
            if (status != 200) {
                throw new Exception("Google Analytics tracking did not return OK 200");
            } else {
                Log.v("GADATA", URLDecoder.decode(builder.toString(), "UTF-8"));
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
