package com.goldenplanet.Install_AOSkt

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.gms.analytics.Tracker
import com.google.firebase.analytics.FirebaseAnalytics
import java.util.*

class Native_Fragment : Fragment() {
    private var mTracker: Tracker? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.activity_native, container, false)

        // [START shared_tracker]
        // Obtain the shared Tracker instance.
        val application = activity!!.application as AnalyticsApplication
        mTracker = application.defaultTracker
        // [END shared_tracker]
        val googleAnalyticsBuilder = GoogleAnalyticsBuilder(activity)
        val mFirebaseAnalytics: FirebaseAnalytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context!!)
        val params = Bundle()
        params.putString("ScreenDepth1", "screen_depth1_" + TAG)
        params.putString("ScreenDepth2", "screen_depth2_" + TAG)
        params.putString("ScreenDepth3", "screen_depth3_" + TAG)
        params.putString("ScreenDepth4", "screen_depth4_" + TAG)
        params.putString("ScreenDepth5", "screen_depth5_" + TAG)
        params.putFloat("FloatData_" + TAG, 1f)
        params.putDouble("DoubleData_" + TAG, 1.0)
        params.putInt("IntData_" + TAG, 1)
        mFirebaseAnalytics.logEvent("screenview", params)
        val btn_customdimension_add = v.findViewById<View>(R.id.btn_customdimension_add) as Button
        val btn_customdimension_remove = v.findViewById<View>(R.id.btn_customdimension_remove) as Button
        btn_customdimension_add.setOnClickListener {
            if (customdimensionNumber != 20) {
                val linearLayout: LinearLayout? = null
                when (customdimensionNumber) {
                    1 -> (v.findViewById<View>(R.id.linearlayout_custom2) as LinearLayout).visibility = View.VISIBLE
                    2 -> (v.findViewById<View>(R.id.linearlayout_custom3) as LinearLayout).visibility = View.VISIBLE
                    3 -> (v.findViewById<View>(R.id.linearlayout_custom4) as LinearLayout).visibility = View.VISIBLE
                    4 -> (v.findViewById<View>(R.id.linearlayout_custom5) as LinearLayout).visibility = View.VISIBLE
                    5 -> (v.findViewById<View>(R.id.linearlayout_custom6) as LinearLayout).visibility = View.VISIBLE
                    6 -> (v.findViewById<View>(R.id.linearlayout_custom7) as LinearLayout).visibility = View.VISIBLE
                    7 -> (v.findViewById<View>(R.id.linearlayout_custom8) as LinearLayout).visibility = View.VISIBLE
                    8 -> (v.findViewById<View>(R.id.linearlayout_custom9) as LinearLayout).visibility = View.VISIBLE
                    9 -> (v.findViewById<View>(R.id.linearlayout_custom10) as LinearLayout).visibility = View.VISIBLE
                    10 -> (v.findViewById<View>(R.id.linearlayout_custom11) as LinearLayout).visibility = View.VISIBLE
                    11 -> (v.findViewById<View>(R.id.linearlayout_custom12) as LinearLayout).visibility = View.VISIBLE
                    12 -> (v.findViewById<View>(R.id.linearlayout_custom13) as LinearLayout).visibility = View.VISIBLE
                    13 -> (v.findViewById<View>(R.id.linearlayout_custom14) as LinearLayout).visibility = View.VISIBLE
                    14 -> (v.findViewById<View>(R.id.linearlayout_custom15) as LinearLayout).visibility = View.VISIBLE
                    15 -> (v.findViewById<View>(R.id.linearlayout_custom16) as LinearLayout).visibility = View.VISIBLE
                    16 -> (v.findViewById<View>(R.id.linearlayout_custom17) as LinearLayout).visibility = View.VISIBLE
                    17 -> (v.findViewById<View>(R.id.linearlayout_custom18) as LinearLayout).visibility = View.VISIBLE
                    18 -> (v.findViewById<View>(R.id.linearlayout_custom19) as LinearLayout).visibility = View.VISIBLE
                    19 -> (v.findViewById<View>(R.id.linearlayout_custom20) as LinearLayout).visibility = View.VISIBLE
                }
                customdimensionNumber++
            }
        }
        btn_customdimension_remove.setOnClickListener {
            if (customdimensionNumber != 1) {
                val linearLayout: LinearLayout? = null
                when (customdimensionNumber) {
                    2 -> (v.findViewById<View>(R.id.linearlayout_custom2) as LinearLayout).visibility = View.GONE
                    3 -> (v.findViewById<View>(R.id.linearlayout_custom3) as LinearLayout).visibility = View.GONE
                    4 -> (v.findViewById<View>(R.id.linearlayout_custom4) as LinearLayout).visibility = View.GONE
                    5 -> (v.findViewById<View>(R.id.linearlayout_custom5) as LinearLayout).visibility = View.GONE
                    6 -> (v.findViewById<View>(R.id.linearlayout_custom6) as LinearLayout).visibility = View.GONE
                    7 -> (v.findViewById<View>(R.id.linearlayout_custom7) as LinearLayout).visibility = View.GONE
                    8 -> (v.findViewById<View>(R.id.linearlayout_custom8) as LinearLayout).visibility = View.GONE
                    9 -> (v.findViewById<View>(R.id.linearlayout_custom9) as LinearLayout).visibility = View.GONE
                    10 -> (v.findViewById<View>(R.id.linearlayout_custom10) as LinearLayout).visibility = View.GONE
                    11 -> (v.findViewById<View>(R.id.linearlayout_custom11) as LinearLayout).visibility = View.GONE
                    12 -> (v.findViewById<View>(R.id.linearlayout_custom12) as LinearLayout).visibility = View.GONE
                    13 -> (v.findViewById<View>(R.id.linearlayout_custom13) as LinearLayout).visibility = View.GONE
                    14 -> (v.findViewById<View>(R.id.linearlayout_custom14) as LinearLayout).visibility = View.GONE
                    15 -> (v.findViewById<View>(R.id.linearlayout_custom15) as LinearLayout).visibility = View.GONE
                    16 -> (v.findViewById<View>(R.id.linearlayout_custom16) as LinearLayout).visibility = View.GONE
                    17 -> (v.findViewById<View>(R.id.linearlayout_custom17) as LinearLayout).visibility = View.GONE
                    18 -> (v.findViewById<View>(R.id.linearlayout_custom18) as LinearLayout).visibility = View.GONE
                    19 -> (v.findViewById<View>(R.id.linearlayout_custom19) as LinearLayout).visibility = View.GONE
                    20 -> (v.findViewById<View>(R.id.linearlayout_custom20) as LinearLayout).visibility = View.GONE
                }
                customdimensionNumber--
            }
        }
        btn_screenview = v.findViewById<View>(R.id.button2) as Button
        btn_event = v.findViewById<View>(R.id.button3) as Button
        btn_product_click = v.findViewById<View>(R.id.btn_product_click) as Button
        et_cd_1 = v.findViewById<View>(R.id.et_cd_1) as EditText
        et_cd_2 = v.findViewById<View>(R.id.et_cd_2) as EditText
        et_cd_3 = v.findViewById<View>(R.id.et_cd_3) as EditText
        et_cd_4 = v.findViewById<View>(R.id.et_cd_4) as EditText
        et_cd_5 = v.findViewById<View>(R.id.et_cd_5) as EditText
        et_cd_6 = v.findViewById<View>(R.id.et_cd_6) as EditText
        et_cd_7 = v.findViewById<View>(R.id.et_cd_7) as EditText
        et_cd_8 = v.findViewById<View>(R.id.et_cd_8) as EditText
        et_cd_9 = v.findViewById<View>(R.id.et_cd_9) as EditText
        et_cd_10 = v.findViewById<View>(R.id.et_cd_10) as EditText
        et_cd_11 = v.findViewById<View>(R.id.et_cd_11) as EditText
        et_cd_12 = v.findViewById<View>(R.id.et_cd_12) as EditText
        et_cd_13 = v.findViewById<View>(R.id.et_cd_13) as EditText
        et_cd_14 = v.findViewById<View>(R.id.et_cd_14) as EditText
        et_cd_15 = v.findViewById<View>(R.id.et_cd_15) as EditText
        et_cd_16 = v.findViewById<View>(R.id.et_cd_16) as EditText
        et_cd_17 = v.findViewById<View>(R.id.et_cd_17) as EditText
        et_cd_18 = v.findViewById<View>(R.id.et_cd_18) as EditText
        et_cd_19 = v.findViewById<View>(R.id.et_cd_19) as EditText
        et_cd_20 = v.findViewById<View>(R.id.et_cd_20) as EditText
        btn_screenview!!.setOnClickListener {
            val sendScreenMap: MutableMap<String?, String?> = HashMap()
            if (et_cd_1!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension1] = et_cd_1!!.text.toString()
            if (et_cd_2!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = et_cd_2!!.text.toString()
            if (et_cd_3!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = et_cd_3!!.text.toString()
            if (et_cd_4!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension4] = et_cd_4!!.text.toString()
            if (et_cd_5!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension5] = et_cd_5!!.text.toString()
            if (et_cd_6!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension6] = et_cd_6!!.text.toString()
            if (et_cd_7!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension7] = et_cd_7!!.text.toString()
            if (et_cd_8!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension8] = et_cd_8!!.text.toString()
            if (et_cd_9!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension9] = et_cd_9!!.text.toString()
            if (et_cd_10!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension10] = et_cd_10!!.text.toString()
            if (et_cd_11!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension11] = et_cd_11!!.text.toString()
            if (et_cd_12!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension12] = et_cd_12!!.text.toString()
            if (et_cd_13!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension13] = et_cd_13!!.text.toString()
            if (et_cd_14!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension14] = et_cd_14!!.text.toString()
            if (et_cd_15!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension15] = et_cd_15!!.text.toString()
            if (et_cd_16!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension16] = et_cd_16!!.text.toString()
            if (et_cd_17!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension17] = et_cd_17!!.text.toString()
            if (et_cd_18!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension18] = et_cd_18!!.text.toString()
            if (et_cd_19!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension19] = et_cd_19!!.text.toString()
            if (et_cd_20!!.text.toString().length > 0) sendScreenMap[GoogleAnalyticsBuilder.GACustomKey.Dimension20] = et_cd_20!!.text.toString()
            sendScreenMap[GoogleAnalyticsBuilder.GAHitKey.Title] = SplashActivity.Companion.title
            googleAnalyticsBuilder.GADataSend_Screen(sendScreenMap)
            Toast.makeText(activity, "스크린뷰 전송", Toast.LENGTH_LONG).show()
        }
        btn_event!!.setOnClickListener {
            val sendEventMap: MutableMap<String?, String?> = HashMap()
            if (et_cd_1!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension1] = et_cd_1!!.text.toString()
            if (et_cd_2!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = et_cd_2!!.text.toString()
            if (et_cd_3!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = et_cd_3!!.text.toString()
            if (et_cd_4!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension4] = et_cd_4!!.text.toString()
            if (et_cd_5!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension5] = et_cd_5!!.text.toString()
            if (et_cd_6!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension6] = et_cd_6!!.text.toString()
            if (et_cd_7!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension7] = et_cd_7!!.text.toString()
            if (et_cd_8!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension8] = et_cd_8!!.text.toString()
            if (et_cd_9!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension9] = et_cd_9!!.text.toString()
            if (et_cd_10!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension10] = et_cd_10!!.text.toString()
            if (et_cd_11!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension11] = et_cd_11!!.text.toString()
            if (et_cd_12!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension12] = et_cd_12!!.text.toString()
            if (et_cd_13!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension13] = et_cd_13!!.text.toString()
            if (et_cd_14!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension14] = et_cd_14!!.text.toString()
            if (et_cd_15!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension15] = et_cd_15!!.text.toString()
            if (et_cd_16!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension16] = et_cd_16!!.text.toString()
            if (et_cd_17!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension17] = et_cd_17!!.text.toString()
            if (et_cd_18!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension18] = et_cd_18!!.text.toString()
            if (et_cd_19!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension19] = et_cd_19!!.text.toString()
            if (et_cd_20!!.text.toString().length > 0) sendEventMap[GoogleAnalyticsBuilder.GACustomKey.Dimension20] = et_cd_20!!.text.toString()
            sendEventMap[GoogleAnalyticsBuilder.GAHitKey.EventCategory] = "Category_Android"
            sendEventMap[GoogleAnalyticsBuilder.GAHitKey.EventAction] = "Action_Android"
            sendEventMap[GoogleAnalyticsBuilder.GAHitKey.EventLabel] = "Label_Android"
            googleAnalyticsBuilder.GADataSend_Event(sendEventMap)
            Toast.makeText(activity, "이벤트 전송", Toast.LENGTH_LONG).show()
        }
        btn_product_click!!.setOnClickListener {
            val ProductMap: MutableMap<String?, String?> = HashMap()
            ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductID] = "GP_Product"
            ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductName] = "GP_Product"
            ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductCategory] = "GA Test"
            ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductBrand] = "GP"
            ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductVariant] = "Black"
            ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductPosition] = "1"
            val product_Map: MutableMap<String, Map<String?, String?>> = HashMap()
            product_Map["pr1"] = ProductMap
            val ProductActionMap: MutableMap<String?, String?> = HashMap()
            ProductActionMap[GoogleAnalyticsBuilder.GAActionFieldKey.ProductActionList] = "ProductActionList"
            val EcommerceMap: MutableMap<String?, String?> = HashMap()
            EcommerceMap[GoogleAnalyticsBuilder.GAHitKey.EventCategory] = "EV_ProductClick_Category"
            EcommerceMap[GoogleAnalyticsBuilder.GAHitKey.EventAction] = "EV_ProductClick_Action"
            EcommerceMap[GoogleAnalyticsBuilder.GAHitKey.EventLabel] = "EV_ProductClick_Label"
            if (et_cd_1!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension1] = et_cd_1!!.text.toString()
            if (et_cd_2!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = et_cd_2!!.text.toString()
            if (et_cd_3!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = et_cd_3!!.text.toString()
            if (et_cd_4!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension4] = et_cd_4!!.text.toString()
            if (et_cd_5!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension5] = et_cd_5!!.text.toString()
            if (et_cd_6!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension6] = et_cd_6!!.text.toString()
            if (et_cd_7!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension7] = et_cd_7!!.text.toString()
            if (et_cd_8!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension8] = et_cd_8!!.text.toString()
            if (et_cd_9!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension9] = et_cd_9!!.text.toString()
            if (et_cd_10!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension10] = et_cd_10!!.text.toString()
            if (et_cd_11!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension11] = et_cd_11!!.text.toString()
            if (et_cd_12!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension12] = et_cd_12!!.text.toString()
            if (et_cd_13!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension13] = et_cd_13!!.text.toString()
            if (et_cd_14!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension14] = et_cd_14!!.text.toString()
            if (et_cd_15!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension15] = et_cd_15!!.text.toString()
            if (et_cd_16!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension16] = et_cd_16!!.text.toString()
            if (et_cd_17!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension17] = et_cd_17!!.text.toString()
            if (et_cd_18!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension18] = et_cd_18!!.text.toString()
            if (et_cd_19!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension19] = et_cd_19!!.text.toString()
            if (et_cd_20!!.text.toString().length > 0) EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension20] = et_cd_20!!.text.toString()
            googleAnalyticsBuilder.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Click, ProductActionMap, product_Map, EcommerceMap)
            fragment = ProductDetail()
            val args = Bundle()
            if (et_cd_1!!.text.toString().length > 0) args.putString("cd1", et_cd_1!!.text.toString()) else {
                args.putString("cd1", "")
            }
            if (et_cd_2!!.text.toString().length > 0) args.putString("cd2", et_cd_2!!.text.toString()) else {
                args.putString("cd2", "")
            }
            if (et_cd_3!!.text.toString().length > 0) args.putString("cd3", et_cd_3!!.text.toString()) else {
                args.putString("cd3", "")
            }
            if (et_cd_4!!.text.toString().length > 0) args.putString("cd4", et_cd_4!!.text.toString()) else {
                args.putString("cd4", "")
            }
            if (et_cd_5!!.text.toString().length > 0) args.putString("cd5", et_cd_5!!.text.toString()) else {
                args.putString("cd5", "")
            }
            if (et_cd_6!!.text.toString().length > 0) args.putString("cd6", et_cd_6!!.text.toString()) else {
                args.putString("cd6", "")
            }
            if (et_cd_7!!.text.toString().length > 0) args.putString("cd7", et_cd_7!!.text.toString()) else {
                args.putString("cd7", "")
            }
            if (et_cd_8!!.text.toString().length > 0) args.putString("cd8", et_cd_8!!.text.toString()) else {
                args.putString("cd8", "")
            }
            if (et_cd_9!!.text.toString().length > 0) args.putString("cd9", et_cd_9!!.text.toString()) else {
                args.putString("cd9", "")
            }
            if (et_cd_10!!.text.toString().length > 0) args.putString("cd10", et_cd_10!!.text.toString()) else {
                args.putString("cd10", "")
            }
            if (et_cd_11!!.text.toString().length > 0) args.putString("cd11", et_cd_11!!.text.toString()) else {
                args.putString("cd11", "")
            }
            if (et_cd_12!!.text.toString().length > 0) args.putString("cd12", et_cd_12!!.text.toString()) else {
                args.putString("cd12", "")
            }
            if (et_cd_13!!.text.toString().length > 0) args.putString("cd13", et_cd_13!!.text.toString()) else {
                args.putString("cd13", "")
            }
            if (et_cd_14!!.text.toString().length > 0) args.putString("cd14", et_cd_14!!.text.toString()) else {
                args.putString("cd14", "")
            }
            if (et_cd_15!!.text.toString().length > 0) args.putString("cd15", et_cd_15!!.text.toString()) else {
                args.putString("cd15", "")
            }
            if (et_cd_16!!.text.toString().length > 0) args.putString("cd16", et_cd_16!!.text.toString()) else {
                args.putString("cd16", "")
            }
            if (et_cd_17!!.text.toString().length > 0) args.putString("cd17", et_cd_17!!.text.toString()) else {
                args.putString("cd17", "")
            }
            if (et_cd_18!!.text.toString().length > 0) args.putString("cd18", et_cd_18!!.text.toString()) else {
                args.putString("cd18", "")
            }
            if (et_cd_19!!.text.toString().length > 0) args.putString("cd19", et_cd_19!!.text.toString()) else {
                args.putString("cd19", "")
            }
            if (et_cd_20!!.text.toString().length > 0) args.putString("cd20", et_cd_20!!.text.toString()) else {
                args.putString("cd20", "")
            }
            (fragment as ProductDetail).setArguments(args)
            val ft = fragmentManager!!.beginTransaction()
            ft.replace(R.id.content_fragment_layout, fragment as ProductDetail)
            ft.commit()
        }
        return v
    }

    companion object {
        private var btn_screenview: Button? = null
        private var btn_event: Button? = null
        private var btn_product_click: Button? = null
        private var fragment: Fragment? = null
        private const val TAG = "NativeFragment"
        private var et_cd_1: EditText? = null
        private var et_cd_2: EditText? = null
        private var et_cd_3: EditText? = null
        private var et_cd_4: EditText? = null
        private var et_cd_5: EditText? = null
        private var et_cd_6: EditText? = null
        private var et_cd_7: EditText? = null
        private var et_cd_8: EditText? = null
        private var et_cd_9: EditText? = null
        private var et_cd_10: EditText? = null
        private var et_cd_11: EditText? = null
        private var et_cd_12: EditText? = null
        private var et_cd_13: EditText? = null
        private var et_cd_14: EditText? = null
        private var et_cd_15: EditText? = null
        private var et_cd_16: EditText? = null
        private var et_cd_17: EditText? = null
        private var et_cd_18: EditText? = null
        private var et_cd_19: EditText? = null
        private var et_cd_20: EditText? = null
        private var customdimensionNumber = 1
    }
}