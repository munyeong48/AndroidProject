package com.goldenplanet.Install_AOSkt

import android.content.pm.ApplicationInfo
import android.net.Uri
import android.os.RemoteException
import android.util.Log
import com.android.installreferrer.api.ReferrerDetails
import java.io.DataOutputStream
import java.io.UnsupportedEncodingException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.*

object GoogleAnalytics {
    var user_pseudo_id = arrayOfNulls<String>(1)
    @Throws(Exception::class)
    fun hitSend(it: Iterator<*>) {
        val builder = StringBuilder()
        while (it.hasNext()) {
            val e = it.next() as Map.Entry<*, *>
            builder.append(URLEncoder.encode(e.key.toString(), "UTF-8"))
            builder.append("=")
            builder.append(URLEncoder.encode(e.value.toString(), "UTF-8"))
            builder.append("&")
        }
        builder.setLength(builder.length - 1)
        val request = "http://www.google-analytics.com/collect"
        val url = URL(request)
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "POST"
        conn.setRequestProperty("Content-Length", Integer.toString(builder.toString().toByteArray().size))
        conn.doOutput = true
        val wr = DataOutputStream(conn.outputStream)
        wr.writeBytes(builder.toString())
        wr.close()
        val status = conn.responseCode
        try {
            if (status != 200) {
                throw Exception("Google Analytics tracking did not return OK 200")
            } else {
                Log.v("GADATA", URLDecoder.decode(builder.toString(), "UTF-8"))
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
/*
    @Throws(RemoteException::class)
    fun getReferral() {
        val response: ReferrerDetails = SubActivity.mReferrerClient!!.installReferrer
        val referrerData: String = response.installReferrer
        Log.e("GADATA", "Install referrer:" + response.installReferrer)
        try {
            splitQuery(Uri.parse(referrerData), "install")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        SubActivity.mReferrerClient!!.endConnection()
    }

    @Throws(UnsupportedEncodingException::class)
    fun splitQuery(uri: Uri, data: String) {
        val campaignMap: MutableMap<String, String> = HashMap()
        var query: String? = uri.toString()
        var url = "gadata://?"
        var i = 1
        if (data == "deeplink") campaignMap["dt"] = "캠페인>딥링크"
        if (data == "install") campaignMap["dt"] = "캠페인>앱설치"
        campaignMap["t"] = "pageview"
        campaignMap["dl"] = "http://211.49.99.88:8011/default/campaign"
        campaignMap["ni"] = "1"
        if (uri.toString().contains("://")) query = uri.query
        if (query != null) {
            val pairs = query.split("&").toTypedArray()
            for (pair in pairs) {
                val idx = pair.indexOf("=")
                if (pair.contains("utm_source")) {
                    url = url + "utm_source=" + URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                    campaignMap["cs"] = URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                } else if (pair.contains("utm_medium")) {
                    url = url + "utm_medium=" + URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                    campaignMap["cm"] = URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                } else if (pair.contains("utm_term")) {
                    url = url + "utm_term=" + URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                } else if (pair.contains("utm_content")) {
                    url = url + "utm_content=" + URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                    campaignMap["cc"] = URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                } else if (pair.contains("utm_campaign")) {
                    url = url + "utm_campaign=" + URLDecoder.decode(pair.substring(idx + 1), "UTF-8") + "&"
                    campaignMap["cn"] = URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                }
                if (i != pairs.size) {
                    url += "&"
                    i++
                }
            }
        }
        val t = Thread(gaThread(campaignMap))
        t.start()
    }

 */

    class gaThread(private val gaData: Map<String, String>?) : Runnable {
        override fun run() {
            try {
                if (gaData != null && user_pseudo_id[0] != null) {
                    val parameters: MutableMap<String, String?> = LinkedHashMap()
                    parameters["v"] = "1"
                    parameters["tid"] = "UA-115948787-1"
                    val a = ApplicationInfo()
                    parameters["cid"] = user_pseudo_id[0]
                    parameters["ds"] = "web"
                    parameters["aip"] = "1"
                    val sIterator = gaData.keys.iterator()
                    while (sIterator.hasNext()) {
                        val key = sIterator.next()
                        if (key.contains("cd")) parameters[key] = gaData[key] // Custom Dimension
                        if (key.contains("cm")) parameters[key] = gaData[key] // Custom Metric
                        if (key == "t") parameters[key] = gaData[key] // Hit Type
                        if (key == "uid") parameters[key] = gaData[key] // User Id
                        if (key == "dl") parameters[key] = gaData[key] // Document Location URL
                        if (key == "dt") parameters[key] = gaData[key] // Document Title
                        if (key == "ec") parameters[key] = gaData[key] // Event Category
                        if (key == "ea") parameters[key] = gaData[key] // Event Action
                        if (key == "el") parameters[key] = gaData[key] // Event Label
                        if (key == "ev") parameters[key] = gaData[key] // Event Value
                        if (key == "cn") parameters[key] = gaData[key] // Campaign Name
                        if (key == "cs") parameters[key] = gaData[key] // Campaign Source
                        if (key == "cm") parameters[key] = gaData[key] // Campaign Medium
                        if (key == "ck") parameters[key] = gaData[key] // Campaign Keyword
                        if (key == "cc") parameters[key] = gaData[key] // Campaign Content
                        if (key == "ci") parameters[key] = gaData[key] // Campaign ID
                        if (key == "sr") parameters[key] = gaData[key] // Screen Resolution
                        if (key == "ul") parameters[key] = gaData[key] // User Language
                        if (key == "an") parameters[key] = gaData[key] // Application Name
                        if (key == "aid") parameters[key] = gaData[key] // Application ID
                        if (key == "av") parameters[key] = gaData[key] // Application Version
                        if (key == "cu") parameters[key] = gaData[key] // Currency Code
                        if (key == "ni") parameters[key] = gaData[key] // NonInteraction
                        if (key == "pa") parameters[key] = gaData[key] // Product Action
                        if (key == "ti") parameters[key] = gaData[key] // Transaction ID
                        if (key == "ta") parameters[key] = gaData[key] // Transaction Affiliation
                        if (key == "tr") parameters[key] = gaData[key] // Transaction Revenue
                        if (key == "ts") parameters[key] = gaData[key] // Transaction Shipping
                        if (key == "tt") parameters[key] = gaData[key] // Transaction Tax
                        if (key == "tcc") parameters[key] = gaData[key] // Transaction Coupon Code
                        if (key == "pal") parameters[key] = gaData[key] // Product Action List
                        if (key == "cos") parameters[key] = gaData[key] // Checkout Step
                        if (key == "col") parameters[key] = gaData[key] // Checkout Step Option
                        if (key.contains("il")) parameters[key] = gaData[key] // Impression Data
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
                        if (key.contains("pr")) parameters[key] = gaData[key] // Product Data
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
                    val set: Set<*> = parameters.entries
                    val it = set.iterator()
                    hitSend(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}