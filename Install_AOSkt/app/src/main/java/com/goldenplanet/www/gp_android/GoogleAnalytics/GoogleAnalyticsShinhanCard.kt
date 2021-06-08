package com.goldenplanet.Install_AOSkt

import android.app.Activity
import android.util.Log
import android.webkit.JavascriptInterface
import com.google.android.gms.analytics.HitBuilders.*
import com.google.android.gms.analytics.Tracker
import org.json.JSONObject
import java.net.URLDecoder

class GoogleAnalyticsShinhanCard(activity: Activity) {
    var mTracker: Tracker?
    var ActivityName: String

    enum class GACustomKey(val value: String) {
        Dimension1("dimension1"), Dimension2("dimension2"), Dimension3("dimension3"), Dimension4("dimension4"), Dimension5("dimension5"), Dimension6("dimension6"), Dimension7("dimension7"), Dimension8("dimension8"), Dimension9("dimension9"), Dimension10("dimension10"), Dimension11("dimension11"), Dimension12("dimension12"), Dimension13("dimension13"), Dimension14("dimension14"), Dimension15("dimension15"), Dimension16("dimension16"), Dimension17("dimension17"), Dimension18("dimension18"), Dimension19("dimension19"), Dimension20("dimension20"), Dimension21("dimension21"), Dimension22("dimension22"), Dimension23("dimension23"), Dimension24("dimension24"), Dimension25("dimension25"), Dimension26("dimension26"), Dimension27("dimension27"), Dimension28("dimension28"), Dimension29("dimension29"), Dimension30("dimension30"), Dimension31("dimension31"), Dimension32("dimension32"), Dimension33("dimension33"), Dimension34("dimension34"), Dimension35("dimension35"), Dimension36("dimension36"), Dimension37("dimension37"), Dimension38("dimension38"), Dimension39("dimension39"), Dimension40("dimension40"), Dimension41("dimension41"), Dimension42("dimension42"), Dimension43("dimension43"), Dimension44("dimension44"), Dimension45("dimension45"), Dimension46("dimension46"), Dimension47("dimension47"), Dimension48("dimension48"), Dimension49("dimension49"), Dimension50("dimension50"), Metric1("metric1"), Metric2("metric2"), Metric3("metric3"), Metric4("metric4"), Metric5("metric5"), Metric6("metric6"), Metric7("metric7"), Metric8("metric8"), Metric9("metric9"), Metric10("metric10"), Metric11("metric11"), Metric12("metric16"), Metric13("metric13"), Metric14("metric14"), Metric15("metric15"), Metric16("metric16"), Metric17("metric16"), Metric18("metric18"), Metric19("Metric19"), Metric20("metric20");

        override fun toString(): String {
            return value
        }
    }

    enum class GAEventKey(val value: String) {
        UserID("uid"), CampaignUrl("camp_url"), Title("title"), EventCategory("category"), EventAction("action"), EventLabel("label"), EventValue("value"), NonInteraction("ni");

        override fun toString(): String {
            return value
        }
    }

    enum class GAScreenKey(val value: String) {
        UserID("uid"), CampaignUrl("camp_url"), Title("title"), NonInteraction("ni");

        override fun toString(): String {
            return value
        }
    }

    enum class GASubKey(val value: String) {
        UserID("uid"), Title("title"), TimingCategory("timingcategory"), TimingVariable("timingvariable"), TimingLabel("timinglabel"), TimingValue("timingvalue"), Fatal("fatal"), ErrorDescription("errordescription");

        override fun toString(): String {
            return value
        }
    }

    fun GADataSend_Event(GAInfo: Map<String, String?>) {
        try {
            val eventBuilder = EventBuilder()
            var Category: String
            var Action: String
            var Label: String
            val sIterator = GAInfo.keys.iterator()
            while (sIterator.hasNext()) {
                val key = sIterator.next()
                if (GAInfo[key] != null && GAInfo[key]!!.length > 0) {
                    if (key.toLowerCase() == "dimension") {
                        val Number = key.replace("[^0-9]".toRegex(), "").toInt()
                        eventBuilder.setCustomDimension(Number, GAInfo[key])
                    }
                    if (key.toLowerCase() == "metric") {
                        val Number = key.replace("[^0-9]".toRegex(), "").toInt()
                        eventBuilder.setCustomMetric(Number, GAInfo[key]!!.toFloat())
                    }
                    if (key.toLowerCase() == "uid") mTracker!!["&uid"] = GAInfo[key]
                    if (key.toLowerCase() == "camp_url") {
                        var URL = GAInfo[key]
                        URL = URLDecoder.decode(URL, "UTF-8")
                        URL = URLDecoder.decode(URL, "UTF-8")
                        eventBuilder.setCampaignParamsFromUrl(URL)
                    }
                    if (key.toLowerCase() == "category") eventBuilder.setCategory(GAInfo[key])
                    if (key.toLowerCase() == "action") eventBuilder.setAction(GAInfo[key])
                    if (key.toLowerCase() == "label") eventBuilder.setLabel(GAInfo[key])
                    if (key.toLowerCase() == "value") eventBuilder.setValue(GAInfo[key]!!.toLong())
                    if (key.toLowerCase() == "title") {
                        if (GAInfo["title"] != null && GAInfo["title"]!!.length > 0) mTracker!!.setScreenName(GAInfo["title"])
                    }
                    if (key.toLowerCase() == "ni" && GAInfo[key] == "1") eventBuilder.setNonInteraction(true)
                }
            }
            if (GAInfo["title"] == null || GAInfo["title"] == "") mTracker!!.setScreenName(ActivityName)
            mTracker!!.send(eventBuilder.build())
        } catch (e: Exception) {
            Log.e("GAv4_Event", e.message!!)
        }
    }

    fun GADataSend_Screen(GAInfo: Map<String, String?>) {
        try {
            val screenViewBuilder = ScreenViewBuilder()
            var Category: String
            var Action: String
            var Label: String
            val sIterator = GAInfo.keys.iterator()
            while (sIterator.hasNext()) {
                val key = sIterator.next()
                if (GAInfo[key] != null && GAInfo[key]!!.length > 0) {
                    if (key.toLowerCase().contains("dimension")) {
                        val Number = key.replace("[^0-9]".toRegex(), "").toInt()
                        screenViewBuilder.setCustomDimension(Number, GAInfo[key])
                    }
                    if (key.toLowerCase().contains("metric")) {
                        val Number = key.replace("[^0-9]".toRegex(), "").toInt()
                        screenViewBuilder.setCustomMetric(Number, GAInfo[key]!!.toFloat())
                    }
                    if (key.toLowerCase() == "camp_url") {
                        var URL = GAInfo[key]
                        URL = URLDecoder.decode(URL, "UTF-8")
                        URL = URLDecoder.decode(URL, "UTF-8")
                        screenViewBuilder.setCampaignParamsFromUrl(URL)
                    }
                    if (key.toLowerCase() == "uid") mTracker!!["&uid"] = GAInfo[key]
                    if (key.toLowerCase() == "title") {
                        if (GAInfo["title"] != null && GAInfo["title"]!!.length > 0) mTracker!!.setScreenName(GAInfo["title"])
                    }
                    if (key.toLowerCase() == "ni" && GAInfo[key] == "1") screenViewBuilder.setNonInteraction(true)
                }
            }
            if (GAInfo["title"] == null || GAInfo["title"] == "") mTracker!!.setScreenName(ActivityName)
            mTracker!!.send(screenViewBuilder.build())
        } catch (e: Exception) {
            Log.e("GAv4_Screen", e.message!!)
        }
    }

    fun GADataSend_Timing(GAInfo: Map<String, String?>) {
        try {
            val timingBuilder = TimingBuilder()
            val sIterator = GAInfo.keys.iterator()
            while (sIterator.hasNext()) {
                val key = sIterator.next()
                if (GAInfo[key] != null && GAInfo[key]!!.length > 0) {
                    if (key.toLowerCase().contains("dimension")) {
                        val Number = key.replace("[^0-9]".toRegex(), "").toInt()
                        timingBuilder.setCustomDimension(Number, GAInfo[key])
                    }
                    if (key.toLowerCase().contains("metric")) {
                        val Number = key.replace("[^0-9]".toRegex(), "").toInt()
                        timingBuilder.setCustomMetric(Number, GAInfo[key]!!.toFloat())
                    }
                    if (key.toLowerCase() == "uid") mTracker!!["&uid"] = GAInfo[key]
                    if (key.toLowerCase() == "title") {
                        if (GAInfo["title"] != null && GAInfo["title"]!!.length > 0) mTracker!!.setScreenName(GAInfo["title"])
                    }
                    if (key.toLowerCase() == "timingcategory") timingBuilder.setCategory(GAInfo[key])
                    if (key.toLowerCase() == "timingvalue") {
                        if (NumericChecker(GAInfo[key])) timingBuilder.setValue(java.lang.Long.valueOf(GAInfo[key]!!))
                    }
                    if (key.toLowerCase() == "timingvariable") timingBuilder.setVariable(GAInfo[key])
                    if (key.toLowerCase() == "timinglabel") timingBuilder.setLabel(GAInfo[key])
                }
            }
            if (GAInfo["title"] == null || GAInfo["title"]!!.length == 0) mTracker!!.setScreenName(ActivityName)
            if (NumericChecker(GAInfo["timingvalue"])) mTracker!!.send(timingBuilder.build())
            mTracker = NullSet(mTracker)
        } catch (e: Exception) {
            Log.e("GAv4_Timing", e.message!!)
        }
    }

    fun GADataSend_Exception(GAInfo: Map<String, String?>) {
        try {
            val exceptionBuilder = ExceptionBuilder()
            val sIterator = GAInfo.keys.iterator()
            while (sIterator.hasNext()) {
                val key = sIterator.next()
                if (GAInfo[key] != null && GAInfo[key]!!.length > 0) {
                    if (key.toLowerCase().contains("dimension")) {
                        val Number = key.replace("[^0-9]".toRegex(), "").toInt()
                        exceptionBuilder.setCustomDimension(Number, GAInfo[key])
                    }
                    if (key.toLowerCase().contains("metric")) {
                        val Number = key.replace("[^0-9]".toRegex(), "").toInt()
                        exceptionBuilder.setCustomMetric(Number, GAInfo[key]!!.toFloat())
                    }
                    if (key.toLowerCase() == "uid") mTracker!!["&uid"] = GAInfo[key]
                    if (key.toLowerCase() == "title") {
                        if (GAInfo["title"] != null && GAInfo["title"]!!.length > 0) mTracker!!.setScreenName(GAInfo["title"])
                    }
                    if (key.toLowerCase() == "fatal") {
                        if (GAInfo["fatal"] == "true" || GAInfo["fatal"] == "Y") exceptionBuilder.setFatal(true) else if (GAInfo["fatal"] == "false" || GAInfo["fatal"] == "N") exceptionBuilder.setFatal(false)
                    }
                    if (key.toLowerCase() == "errordescription") exceptionBuilder.setDescription(GAInfo[key])
                }
            }
            if (GAInfo["title"] == null || GAInfo["title"]!!.length == 0) mTracker!!.setScreenName(ActivityName)
            mTracker!!.send(exceptionBuilder.build())
            mTracker = NullSet(mTracker)
        } catch (e: Exception) {
            Log.e("GAv4_Exception", e.message!!)
        }
    }

    class WebAppInterface(activity: Activity) {
        var mTracker: Tracker?
        @JavascriptInterface
        fun GA_DATA(JsonData: String?) {
            try {
                val event_builder = EventBuilder()
                val Screenview_Builder = ScreenViewBuilder()
                val data = JSONObject(JsonData)

//                Firebase Code
//                FirebaseAnalytics mFirebaseAnalytics;
//                mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
//                Bundle bundle = new Bundle();
                var sType = ""
                val Impression_List = ""
                if (data.has("type")) sType = data.getString("type")
                if (data.has("userId")) mTracker!!["&uid"] = data.getString("userId")
                if (data.has("dimension")) {
                    val obj_dimension = data.getJSONObject("dimension")
                    val sIterator = obj_dimension.keys()
                    while (sIterator.hasNext()) {
                        val key = sIterator.next()
                        val Number = key.replace("[^0-9]".toRegex(), "").toInt()
                        if (sType == "P") {
                            Screenview_Builder.setCustomDimension(Number, obj_dimension.getString(key))
                            //                            Firebase Code
//                            if (Number > 40 && Number < 46) bundle.putString("depth" + String.valueOf(Number).substring(1), obj_dimension.getString(key));
                        } else event_builder.setCustomDimension(Number, obj_dimension.getString(key))
                    }
                }
                if (data.has("metric")) {
                    val obj_metric = data.getJSONObject("metric")
                    val sIterator = obj_metric.keys()
                    while (sIterator.hasNext()) {
                        // get key
                        val key = sIterator.next()
                        val Number = key.replace("[^0-9]".toRegex(), "").toInt()
                        if (sType == "P") Screenview_Builder.setCustomMetric(Number, java.lang.Double.toString(obj_metric.getDouble(key)).toFloat()) else event_builder.setCustomMetric(Number, java.lang.Double.toString(obj_metric.getDouble(key)).toFloat())
                    }
                }
                if (data.has("title")) {
                    mTracker!!.setScreenName(data.getString("title"))
                    //                    Firebase Code
//                    bundle.putString("screen_name",data.getString("title"));
                }
                if (sType == "P") {
                    mTracker!!.send(Screenview_Builder.build())
                    //                    Firebase Code
//                    mFirebaseAnalytics.logEvent("screenview", bundle);
                } else {
                    if (data.has("category")) event_builder.setCategory(data.getString("category"))
                    if (data.has("action")) event_builder.setAction(data.getString("action"))
                    if (data.has("label")) event_builder.setLabel(data.getString("label"))
                    if (data.has("value")) event_builder.setValue(data.getString("value").toLong())
                    mTracker!!.send(event_builder.build())
                }
            } catch (ex: Exception) {
                Log.i("GAv4_Bridge_Data", ex.message!!)
            }
        }

        init {
            val application = activity.application as AnalyticsApplication
            mTracker = application.defaultTracker
        }
    }

    companion object {
        fun NullSet(mTracker: Tracker?): Tracker {
            mTracker!!.setScreenName(null)
            mTracker["&uid"] = null
            return mTracker
        }

        private fun NumericChecker(s: String?): Boolean {
            return try {
                s!!.toDouble()
                true
            } catch (e: NumberFormatException) {
                false
            }
        }
    }

    init {
        val application = activity.application as AnalyticsApplication
        ActivityName = activity.componentName.className
        mTracker = application.defaultTracker
    }
}