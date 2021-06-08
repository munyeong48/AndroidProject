package com.goldenplanet.Install_AOSkt

import android.content.Context
import android.util.Log
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import java.io.DataOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.*

object GoogleAnalyticsApp {
    var user_pseudo_id = arrayOfNulls<String>(1)
    var an: String? = null
    var av: String? = null
    var aid: String? = null
    var ul: String? = null
    var sr: String? = null
    var adid: String? = null
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

    class getAdid(private val _context: Context?) : Runnable {
        override fun run() {
            if (_context != null) {
                try {
                    val info = AdvertisingIdClient.getAdvertisingIdInfo(_context)
                    adid = info.id
                } catch (e: GooglePlayServicesNotAvailableException) {
                    e.printStackTrace()
                } catch (e: GooglePlayServicesRepairableException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    class gaThread(private val gaData: Map<String, String?>?) : Runnable {
        override fun run() {
            try {
                if (gaData != null && user_pseudo_id[0] != null) {
                    val parameters: MutableMap<String, String?> = LinkedHashMap()
                    parameters["v"] = "1"
                    parameters["tid"] = "UA-115948787-1"
                    parameters["cid"] = user_pseudo_id[0]
                    parameters["ds"] = "app"
                    parameters["aip"] = "1"
                    parameters["ate"] = "1"
                    if (aid != null) parameters["aid"] = aid
                    if (an != null) parameters["an"] = an
                    if (av != null) parameters["av"] = av
                    if (sr != null) parameters["sr"] = sr
                    if (ul != null) parameters["ul"] = ul
                    if (adid != null) parameters["adid"] = adid
                    val sIterator = gaData.keys.iterator()
                    while (sIterator.hasNext()) {
                        val key = sIterator.next()
                        if (gaData[key] != null) {
                            if (key.contains("cd")) parameters[key] = gaData[key] // Custom Dimension
                            if (key.contains("cm")) parameters[key] = gaData[key] // Custom Metric
                            if (key == "cd") parameters[key] = gaData[key] // Screen Name
                            if (key == "t") parameters[key] = gaData[key] // Hit Type
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
                            if (key == "sc") parameters[key] = gaData[key] // Screen Resolution
                            if (key == "ul") parameters[key] = gaData[key] // User Language
                            if (key == "an") parameters[key] = gaData[key] // Application Name
                            if (key == "aid") parameters[key] = gaData[key] // Application ID
                            if (key == "av") parameters[key] = gaData[key] // Application Version
                            if (key == "cu") parameters[key] = gaData[key] // Currency Code
                            if (key == "ni") parameters[key] = gaData[key] // NonInteraction
                            if (key == "adid") parameters[key] = gaData[key] // Google Advertising Id
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
                    }
                    val set: Set<*> = parameters.entries
                    val it = set.iterator()
                    hitSend(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.v("GADATA", e.message!!)
            }
        }
    }
}