package app.suro.myapplication

import android.net.Uri
import android.util.Log
import app.suro.myapplication.GoogleAnalytics.userId.user_pseudo_id
import app.suro.myapplication.MainActivity.GoogleAnalytics.mReferrerClient
import java.io.DataOutputStream
import java.io.UnsupportedEncodingException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.collections.HashMap

class GoogleAnalytics {


    object userId {
        var user_pseudo_id = arrayOfNulls<String>(1)
    }
    class gaThread(private val _gaData: Map<String, String>?) : Runnable {

        internal var gaData : Map<String, String> = hashMapOf("NaN" to "NaN")

        init {
            gaData = _gaData as Map<String, String>;
        }
        override fun run() {
            try {
                if (gaData != null && user_pseudo_id[0] != null) {
                    val parameters = LinkedHashMap<String, String>()
                    parameters.put("v", "1")
                    parameters.put("tid", "UA-115948787-1")
                    parameters.put("cid", user_pseudo_id[0]!!)
                    parameters.put("ds", "web")
                    parameters.put("aip","1")
                    val sIterator = gaData.keys.iterator()
                    while (sIterator.hasNext()) {
                        val key = sIterator.next()
                        if (key.contains("cd")) parameters.put(key, gaData.get(key)!!) // Custom Dimension
                        if (key.contains("cm")) parameters.put(key, gaData.get(key)!!) // Custom Metric
                        if (key == "t")
                            parameters.put(key, gaData.get(key)!!) // Hit Type
                        if (key == "dl")
                            parameters.put(key, gaData.get(key)!!) // Document Location URL
                        if (key == "dt")
                            parameters.put(key, gaData.get(key)!!) // Document Title
                        if (key == "ec")
                            parameters.put(key, gaData.get(key)!!) // Event Category
                        if (key == "ea")
                            parameters.put(key, gaData.get(key)!!) // Event Action
                        if (key == "el")
                            parameters.put(key, gaData.get(key)!!) // Event Label
                        if (key == "ev")
                            parameters.put(key, gaData.get(key)!!) // Event Value
                        if (key == "cn")
                            parameters.put(key, gaData.get(key)!!) // Campaign Name
                        if (key == "cs")
                            parameters.put(key, gaData.get(key)!!) // Campaign Source
                        if (key == "cm")
                            parameters.put(key, gaData.get(key)!!) // Campaign Medium
                        if (key == "ck")
                            parameters.put(key, gaData.get(key)!!) // Campaign Keyword
                        if (key == "cc")
                            parameters.put(key, gaData.get(key)!!) // Campaign Content
                        if (key == "ci")
                            parameters.put(key, gaData.get(key)!!) // Campaign ID
                        if (key == "sr")
                            parameters.put(key, gaData.get(key)!!) // Screen Resolution
                        if (key == "ul")
                            parameters.put(key, gaData.get(key)!!) // User Language
                        if (key == "an")
                            parameters.put(key, gaData.get(key)!!) // Application Name
                        if (key == "aid")
                            parameters.put(key, gaData.get(key)!!) // Application ID
                        if (key == "av")
                            parameters.put(key, gaData.get(key)!!) // Application Version
                        if (key == "cu")
                            parameters.put(key, gaData.get(key)!!) // Currency Code
                        if (key == "ni")
                            parameters.put(key, gaData.get(key)!!) // NonInteraction

                        if (key == "pa")
                            parameters.put(key, gaData.get(key)!!) // Product Action
                        if (key == "ti")
                            parameters.put(key, gaData.get(key)!!) // Transaction ID
                        if (key == "ta")
                            parameters.put(key, gaData.get(key)!!) // Transaction Affiliation
                        if (key == "tr")
                            parameters.put(key, gaData.get(key)!!) // Transaction Revenue
                        if (key == "ts")
                            parameters.put(key, gaData.get(key)!!) // Transaction Shipping
                        if (key == "tt")
                            parameters.put(key, gaData.get(key)!!) // Transaction Tax
                        if (key == "tcc")
                            parameters.put(key, gaData.get(key)!!) // Transaction Coupon Code
                        if (key == "pal")
                            parameters.put(key, gaData.get(key)!!) // Product Action List
                        if (key == "cos")
                            parameters.put(key, gaData.get(key)!!) // Checkout Step
                        if (key == "col")
                            parameters.put(key, gaData.get(key)!!) // Checkout Step Option

                        if (key.contains("il"))
                            parameters.put(key, gaData.get(key)!!)  // Impression Data
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
                        if (key.contains("pr"))
                            parameters.put(key, gaData.get(key)!!)  // Product Data
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

                    hitSend(parameters)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    companion object sendHit {
        fun hitSend(it: LinkedHashMap<String, String>) {
            val builder = StringBuilder()

            for((key, value) in it) {
                builder.append(URLEncoder.encode(key.toString(), "UTF-8"))
                builder.append("=")
                builder.append(URLEncoder.encode(value.toString(), "UTF-8"))
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

        fun getReferral() {
            val response = mReferrerClient!!.getInstallReferrer()
            val referrerData = response.getInstallReferrer()
            try {
                splitQuery(Uri.parse(referrerData), "install")
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }


            mReferrerClient!!.endConnection()
        }

        fun splitQuery(uri: Uri, data: String) {
            val campaignMap = HashMap<String, String>()
            var query: String? = uri.toString()

            if (data == "deeplink") campaignMap.put("dt", "캠페인>딥링크")
            if (data == "install") campaignMap.put("dt", "캠페인>앱설치")
            campaignMap.put("t", "pageview")
            campaignMap.put("dl", "http://211.49.99.88:8011/default/campaign")
            campaignMap.put("ni", "1")
            if (uri.toString().contains("://")) query = uri.query
            if (query != null) {
                var pairs = query.split("&")
                for (pair in pairs) {
                    val idx = pair.indexOf("=")
                    if (pair.contains("utm_source")) campaignMap.put("cs", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"))
                    else if (pair.contains("utm_medium")) campaignMap.put("cm", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"))
                    else if (pair.contains("utm_term")) campaignMap.put("ck", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"))
                    else if (pair.contains("utm_content")) campaignMap.put("cc", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"))
                    else if (pair.contains("utm_campaign")) campaignMap.put("cn", URLDecoder.decode(pair.substring(idx + 1), "UTF-8"))
                }
            }
            val t = Thread(gaThread(campaignMap))
            t.start()
        }
    }
}
