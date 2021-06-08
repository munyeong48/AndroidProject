package com.goldenplanet.www.gp_android.GoogleAnalytics;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.json.JSONObject;

import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;

public class GoogleAnalyticsShinhanCard {

    Tracker mTracker;
    String ActivityName;

    public GoogleAnalyticsShinhanCard(Activity activity) {
        AnalyticsApplication application = (AnalyticsApplication) ((Activity) activity).getApplication();
        ActivityName = activity.getComponentName().getClassName();
        mTracker = application.getDefaultTracker();
    }

    public enum GACustomKey {
        Dimension1("dimension1"), Dimension2("dimension2"), Dimension3("dimension3"), Dimension4("dimension4"), Dimension5("dimension5"),
        Dimension6("dimension6"), Dimension7("dimension7"), Dimension8("dimension8"), Dimension9("dimension9"), Dimension10("dimension10"),
        Dimension11("dimension11"), Dimension12("dimension12"), Dimension13("dimension13"), Dimension14("dimension14"), Dimension15("dimension15"),
        Dimension16("dimension16"), Dimension17("dimension17"), Dimension18("dimension18"), Dimension19("dimension19"), Dimension20("dimension20"),
        Dimension21("dimension21"), Dimension22("dimension22"), Dimension23("dimension23"), Dimension24("dimension24"), Dimension25("dimension25"),
        Dimension26("dimension26"), Dimension27("dimension27"), Dimension28("dimension28"), Dimension29("dimension29"), Dimension30("dimension30"),
        Dimension31("dimension31"), Dimension32("dimension32"), Dimension33("dimension33"), Dimension34("dimension34"), Dimension35("dimension35"),
        Dimension36("dimension36"), Dimension37("dimension37"), Dimension38("dimension38"), Dimension39("dimension39"), Dimension40("dimension40"),
        Dimension41("dimension41"), Dimension42("dimension42"), Dimension43("dimension43"), Dimension44("dimension44"), Dimension45("dimension45"),
        Dimension46("dimension46"), Dimension47("dimension47"), Dimension48("dimension48"), Dimension49("dimension49"), Dimension50("dimension50"),

        Metric1("metric1"), Metric2("metric2"), Metric3("metric3"), Metric4("metric4"), Metric5("metric5"),
        Metric6("metric6"), Metric7("metric7"), Metric8("metric8"), Metric9("metric9"), Metric10("metric10"),
        Metric11("metric11"), Metric12("metric16"), Metric13("metric13"), Metric14("metric14"), Metric15("metric15"),
        Metric16("metric16"), Metric17("metric16"), Metric18("metric18"), Metric19("Metric19"), Metric20("metric20");

        private String value;

        GACustomKey(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }

    public enum GAEventKey {
        UserID("uid"), CampaignUrl("camp_url"), Title("title"), EventCategory("category"), EventAction("action"),
        EventLabel("label"), EventValue("value"), NonInteraction("ni");

        private String value;

        GAEventKey(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }

    public enum GAScreenKey {
        UserID("uid"), CampaignUrl("camp_url"), Title("title"), NonInteraction("ni");

        private String value;

        GAScreenKey(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }

    public enum GASubKey {
        UserID("uid"), Title("title"), TimingCategory("timingcategory"), TimingVariable("timingvariable"),
        TimingLabel("timinglabel"), TimingValue("timingvalue"), Fatal("fatal"), ErrorDescription("errordescription");

        private String value;

        GASubKey(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }

    public void GADataSend_Event(Map<String, String> GAInfo) {
        try {
            HitBuilders.EventBuilder eventBuilder = new HitBuilders.EventBuilder();
            String Category, Action, Label;
            Iterator<String> sIterator = GAInfo.keySet().iterator();
            while (sIterator.hasNext()) {
                String key = sIterator.next();
                if (GAInfo.get(key) != null && GAInfo.get(key).length() > 0) {
                    if (key.toLowerCase().equals("dimension")) {
                        int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                        eventBuilder.setCustomDimension(Number, GAInfo.get(key));
                    }

                    if (key.toLowerCase().equals("metric")) {
                        int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                        eventBuilder.setCustomMetric(Number, Float.parseFloat(GAInfo.get(key)));
                    }

                    if (key.toLowerCase().equals("uid"))
                        mTracker.set("&uid", GAInfo.get(key));

                    if (key.toLowerCase().equals("camp_url")) {
                        String URL = GAInfo.get(key);
                        URL = URLDecoder.decode(URL,"UTF-8");
                        URL = URLDecoder.decode(URL,"UTF-8");
                        eventBuilder.setCampaignParamsFromUrl(URL);
                    }
                    if (key.toLowerCase().equals("category"))
                        eventBuilder.setCategory(GAInfo.get(key));

                    if (key.toLowerCase().equals("action"))
                        eventBuilder.setAction(GAInfo.get(key));

                    if (key.toLowerCase().equals("label"))
                        eventBuilder.setLabel(GAInfo.get(key));

                    if (key.toLowerCase().equals("value"))
                        eventBuilder.setValue(Long.parseLong(GAInfo.get(key)));

                    if (key.toLowerCase().equals("title")){
                        if(GAInfo.get("title") != null && GAInfo.get("title").length() > 0)
                            mTracker.setScreenName(GAInfo.get("title"));
                    }

                    if (key.toLowerCase().equals("ni") && GAInfo.get(key).equals("1"))
                        eventBuilder.setNonInteraction(true);
                }
            }
            if(GAInfo.get("title") == null || GAInfo.get("title").equals(""))
                mTracker.setScreenName(ActivityName);

            mTracker.send(eventBuilder.build());
        } catch (Exception e) {
            Log.e("GAv4_Event", e.getMessage());
        }
    }

    public void GADataSend_Screen(Map<String, String> GAInfo) {
        try {
            HitBuilders.ScreenViewBuilder screenViewBuilder = new HitBuilders.ScreenViewBuilder();
            String Category, Action, Label;
            Iterator<String> sIterator = GAInfo.keySet().iterator();
            while (sIterator.hasNext()) {
                String key = sIterator.next();
                if (GAInfo.get(key) != null && GAInfo.get(key).length() > 0) {
                    if (key.toLowerCase().contains("dimension")) {
                        int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                        screenViewBuilder.setCustomDimension(Number, GAInfo.get(key));
                    }
                    if (key.toLowerCase().contains("metric")) {
                        int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                        screenViewBuilder.setCustomMetric(Number, Float.parseFloat(GAInfo.get(key)));
                    }
                    if (key.toLowerCase().equals("camp_url")) {
                        String URL = GAInfo.get(key);
                        URL = URLDecoder.decode(URL,"UTF-8");
                        URL = URLDecoder.decode(URL,"UTF-8");
                        screenViewBuilder.setCampaignParamsFromUrl(URL);
                    }
                    if (key.toLowerCase().equals("uid"))
                        mTracker.set("&uid", GAInfo.get(key));

                    if (key.toLowerCase().equals("title")){
                        if(GAInfo.get("title") != null && GAInfo.get("title").length() > 0)
                            mTracker.setScreenName(GAInfo.get("title"));
                    }

                    if (key.toLowerCase().equals("ni") && GAInfo.get(key).equals("1"))
                        screenViewBuilder.setNonInteraction(true);
                }
            }
            if(GAInfo.get("title") == null || GAInfo.get("title").equals(""))
                mTracker.setScreenName(ActivityName);

            mTracker.send(screenViewBuilder.build());
        } catch (Exception e) {
            Log.e("GAv4_Screen", e.getMessage());
        }
    }

    public void GADataSend_Timing(Map<String, String> GAInfo) {
        try {
            HitBuilders.TimingBuilder timingBuilder = new HitBuilders.TimingBuilder();
            Iterator<String> sIterator = GAInfo.keySet().iterator();
            while (sIterator.hasNext()) {
                String key = sIterator.next();
                if (GAInfo.get(key) != null && GAInfo.get(key).length() > 0) {
                    if (key.toLowerCase().contains("dimension")) {
                        int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                        timingBuilder.setCustomDimension(Number, GAInfo.get(key));
                    }
                    if (key.toLowerCase().contains("metric")) {
                        int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                        timingBuilder.setCustomMetric(Number, Float.parseFloat(GAInfo.get(key)));
                    }

                    if (key.toLowerCase().equals("uid"))
                        mTracker.set("&uid", GAInfo.get(key));

                    if (key.toLowerCase().equals("title")) {
                        if (GAInfo.get("title") != null && GAInfo.get("title").length() > 0)
                            mTracker.setScreenName(GAInfo.get("title"));
                    }

                    if (key.toLowerCase().equals("timingcategory"))
                        timingBuilder.setCategory(GAInfo.get(key));

                    if (key.toLowerCase().equals("timingvalue")){
                        if (NumericChecker(GAInfo.get(key))) timingBuilder.setValue(Long.valueOf(GAInfo.get(key)));
                    }

                    if (key.toLowerCase().equals("timingvariable"))
                        timingBuilder.setVariable(GAInfo.get(key));

                    if (key.toLowerCase().equals("timinglabel"))
                        timingBuilder.setLabel(GAInfo.get(key));
                }
            }

            if (GAInfo.get("title") == null || GAInfo.get("title").length() == 0)
                mTracker.setScreenName(ActivityName);
            if (NumericChecker(GAInfo.get("timingvalue"))) mTracker.send(timingBuilder.build());
            mTracker = NullSet(mTracker);
        } catch (Exception e) {
            Log.e("GAv4_Timing", e.getMessage());
        }
    }

    public void GADataSend_Exception(Map<String, String> GAInfo){
        try {
            HitBuilders.ExceptionBuilder exceptionBuilder = new HitBuilders.ExceptionBuilder();
            Iterator<String> sIterator = GAInfo.keySet().iterator();
            while (sIterator.hasNext()) {
                String key = sIterator.next();
                if (GAInfo.get(key) != null && GAInfo.get(key).length() > 0) {
                    if (key.toLowerCase().contains("dimension")) {
                        int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                        exceptionBuilder.setCustomDimension(Number, GAInfo.get(key));
                    }
                    if (key.toLowerCase().contains("metric")) {
                        int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                        exceptionBuilder.setCustomMetric(Number, Float.parseFloat(GAInfo.get(key)));
                    }

                    if (key.toLowerCase().equals("uid"))
                        mTracker.set("&uid", GAInfo.get(key));

                    if (key.toLowerCase().equals("title")) {
                        if (GAInfo.get("title") != null && GAInfo.get("title").length() > 0)
                            mTracker.setScreenName(GAInfo.get("title"));
                    }

                    if (key.toLowerCase().equals("fatal")) {
                        if(GAInfo.get("fatal").equals("true") || GAInfo.get("fatal").equals("Y")) exceptionBuilder.setFatal(true);
                        else if(GAInfo.get("fatal").equals("false") || GAInfo.get("fatal").equals("N")) exceptionBuilder.setFatal(false);
                    }

                    if (key.toLowerCase().equals("errordescription"))
                        exceptionBuilder.setDescription(GAInfo.get(key));
                }
            }

            if (GAInfo.get("title") == null || GAInfo.get("title").length() == 0)
                mTracker.setScreenName(ActivityName);
            mTracker.send(exceptionBuilder.build());
            mTracker = NullSet(mTracker);
        } catch (Exception e) {
            Log.e("GAv4_Exception", e.getMessage());
        }
    }

    public static Tracker NullSet(Tracker mTracker){
        mTracker.setScreenName(null);

        mTracker.set("&uid", null);
        return mTracker;
    }

    private static boolean NumericChecker(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static class WebAppInterface {
        Tracker mTracker;

        public WebAppInterface(Activity activity) {
            AnalyticsApplication application = (AnalyticsApplication) ((Activity) activity).getApplication();
            mTracker = application.getDefaultTracker();
        }

        @JavascriptInterface
        public void GA_DATA(String JsonData) {
            try {
                HitBuilders.EventBuilder event_builder = new HitBuilders.EventBuilder();
                HitBuilders.ScreenViewBuilder Screenview_Builder = new HitBuilders.ScreenViewBuilder();
                JSONObject data = new JSONObject(JsonData);

//                Firebase Code
//                FirebaseAnalytics mFirebaseAnalytics;
//                mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
//                Bundle bundle = new Bundle();


                String sType = "";
                String Impression_List = "";
                if (data.has("type")) sType = data.getString("type");

                if (data.has("userId"))  mTracker.set("&uid", data.getString("userId"));

                if (data.has("dimension")) {
                    JSONObject obj_dimension = data.getJSONObject("dimension");
                    Iterator<String> sIterator = obj_dimension.keys();
                    while (sIterator.hasNext()) {
                        String key = sIterator.next();
                        int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                        if (sType.equals("P")) {
                            Screenview_Builder.setCustomDimension(Number, obj_dimension.getString(key));
//                            Firebase Code
//                            if (Number > 40 && Number < 46) bundle.putString("depth" + String.valueOf(Number).substring(1), obj_dimension.getString(key));
                        }
                        else event_builder.setCustomDimension(Number, obj_dimension.getString(key));
                    }
                }

                if (data.has("metric")) {
                    JSONObject obj_metric = data.getJSONObject("metric");
                    Iterator<String> sIterator = obj_metric.keys();
                    while (sIterator.hasNext()) {
                        // get key
                        String key = sIterator.next();
                        int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                        if (sType.equals("P"))
                            Screenview_Builder.setCustomMetric(Number, Float.parseFloat(Double.toString(obj_metric.getDouble(key))));
                        else
                            event_builder.setCustomMetric(Number, Float.parseFloat(Double.toString(obj_metric.getDouble(key))));
                    }
                }

                if (data.has("title")) {
                    mTracker.setScreenName(data.getString("title"));
//                    Firebase Code
//                    bundle.putString("screen_name",data.getString("title"));
                }

                if (sType.equals("P")) {
                    mTracker.send(Screenview_Builder.build());
//                    Firebase Code
//                    mFirebaseAnalytics.logEvent("screenview", bundle);
                } else {
                    if (data.has("category")) event_builder.setCategory(data.getString("category"));
                    if (data.has("action")) event_builder.setAction(data.getString("action"));
                    if (data.has("label")) event_builder.setLabel(data.getString("label"));
                    if (data.has("value")) event_builder.setValue(Long.parseLong(data.getString("value")));
                    mTracker.send(event_builder.build());
                }
            } catch (Exception ex) {
                Log.i("GAv4_Bridge_Data", ex.getMessage());
            }
        }
    }
}
