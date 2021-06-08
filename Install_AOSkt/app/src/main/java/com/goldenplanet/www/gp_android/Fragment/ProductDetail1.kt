package com.goldenplanet.Install_AOSkt

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.google.firebase.analytics.FirebaseAnalytics
import java.util.*

class ProductDetail : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            Param_CD1 = arguments!!.getString("cd1")!!
            Param_CD2 = arguments!!.getString("cd2")!!
            Param_CD3 = arguments!!.getString("cd3")!!
            Param_CD4 = arguments!!.getString("cd4")!!
            Param_CD5 = arguments!!.getString("cd5")!!
            Param_CD6 = arguments!!.getString("cd6")!!
            Param_CD7 = arguments!!.getString("cd7")!!
            Param_CD8 = arguments!!.getString("cd8")!!
            Param_CD9 = arguments!!.getString("cd9")!!
            Param_CD10 = arguments!!.getString("cd10")!!
            Param_CD11 = arguments!!.getString("cd11")!!
            Param_CD12 = arguments!!.getString("cd12")!!
            Param_CD13 = arguments!!.getString("cd13")!!
            Param_CD14 = arguments!!.getString("cd14")!!
            Param_CD15 = arguments!!.getString("cd15")!!
            Param_CD16 = arguments!!.getString("cd16")!!
            Param_CD17 = arguments!!.getString("cd17")!!
            Param_CD18 = arguments!!.getString("cd18")!!
            Param_CD19 = arguments!!.getString("cd19")!!
            Param_CD20 = arguments!!.getString("cd20")!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.activity_product_detail, container, false)
        val btn_purchase = v.findViewById<View>(R.id.btn_Purchase) as Button
        val editText_purchase = v.findViewById<View>(R.id.et_price) as EditText
        val googleAnalyticsBuilder = GoogleAnalyticsBuilder(activity)
        val btn_customdimension_add_detail = v.findViewById<View>(R.id.btn_customdimension_add_detail) as Button
        val btn_customdimension_remove_detail = v.findViewById<View>(R.id.btn_customdimension_remove_detail) as Button
        et_cd_1 = v.findViewById<View>(R.id.et_cd_d_1) as EditText
        et_cd_2 = v.findViewById<View>(R.id.et_cd_d_2) as EditText
        et_cd_3 = v.findViewById<View>(R.id.et_cd_d_3) as EditText
        et_cd_4 = v.findViewById<View>(R.id.et_cd_d_4) as EditText
        et_cd_5 = v.findViewById<View>(R.id.et_cd_d_5) as EditText
        et_cd_6 = v.findViewById<View>(R.id.et_cd_d_6) as EditText
        et_cd_7 = v.findViewById<View>(R.id.et_cd_d_7) as EditText
        et_cd_8 = v.findViewById<View>(R.id.et_cd_d_8) as EditText
        et_cd_9 = v.findViewById<View>(R.id.et_cd_d_9) as EditText
        et_cd_10 = v.findViewById<View>(R.id.et_cd_d_10) as EditText
        et_cd_11 = v.findViewById<View>(R.id.et_cd_d_11) as EditText
        et_cd_12 = v.findViewById<View>(R.id.et_cd_d_12) as EditText
        et_cd_13 = v.findViewById<View>(R.id.et_cd_d_13) as EditText
        et_cd_14 = v.findViewById<View>(R.id.et_cd_d_14) as EditText
        et_cd_15 = v.findViewById<View>(R.id.et_cd_d_15) as EditText
        et_cd_16 = v.findViewById<View>(R.id.et_cd_d_16) as EditText
        et_cd_17 = v.findViewById<View>(R.id.et_cd_d_17) as EditText
        et_cd_18 = v.findViewById<View>(R.id.et_cd_d_18) as EditText
        et_cd_19 = v.findViewById<View>(R.id.et_cd_d_19) as EditText
        et_cd_20 = v.findViewById<View>(R.id.et_cd_d_20) as EditText
        val mFirebaseAnalytics: FirebaseAnalytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context!!)
        val params = Bundle()
        params.putString("ScreenDepth1", "screen_depth1_" + TAG)
        params.putString("ScreenDepth2", "screen_depth2_" + TAG)
        params.putString("ScreenDepth3", "screen_depth3_" + TAG)
        params.putString("ScreenDepth4", "screen_depth4_" + TAG)
        params.putString("ScreenDepth5", "screen_depth5_" + TAG)
        mFirebaseAnalytics.logEvent("screenview", params)
        val ProductMap: MutableMap<String?, String> = HashMap()
        ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductID] = "GP_Product"
        ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductName] = "GP_Product"
        ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductCategory] = "GA Test"
        ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductBrand] = "GP"
        ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductVariant] = "Black"
        val product_Map: MutableMap<String, Map<String?, String>> = HashMap()
        product_Map["pr1"] = ProductMap
        val ProductActionMap: MutableMap<String?, String> = HashMap()
        ProductActionMap[GoogleAnalyticsBuilder.GAActionFieldKey.ProductActionList] = "ProductActionList"
        val EcommerceMap: MutableMap<String?, String?> = HashMap()
        EcommerceMap[GoogleAnalyticsBuilder.GAHitKey.EventCategory] = "EV_ProductClick_Category"
        EcommerceMap[GoogleAnalyticsBuilder.GAHitKey.EventAction] = "EV_ProductClick_Action"
        EcommerceMap[GoogleAnalyticsBuilder.GAHitKey.EventLabel] = "EV_ProductClick_Label"
        if (Param_CD1 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension1] = Param_CD1
        if (Param_CD2 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = Param_CD2
        if (Param_CD3 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = Param_CD3
        if (Param_CD4 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension4] = Param_CD4
        if (Param_CD5 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension5] = Param_CD5
        if (Param_CD6 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension6] = Param_CD6
        if (Param_CD7 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension7] = Param_CD7
        if (Param_CD8 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension8] = Param_CD8
        if (Param_CD9 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension9] = Param_CD9
        if (Param_CD10 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension10] = Param_CD10
        if (Param_CD11 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension11] = Param_CD11
        if (Param_CD12 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension12] = Param_CD12
        if (Param_CD13 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension13] = Param_CD13
        if (Param_CD14 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension14] = Param_CD14
        if (Param_CD15 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension15] = Param_CD15
        if (Param_CD16 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension16] = Param_CD16
        if (Param_CD17 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension17] = Param_CD17
        if (Param_CD18 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension18] = Param_CD18
        if (Param_CD19 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension19] = Param_CD19
        if (Param_CD20 != "") EcommerceMap[GoogleAnalyticsBuilder.GACustomKey.Dimension20] = Param_CD20
        EcommerceMap[GoogleAnalyticsBuilder.GAHitKey.Title] = "PV_ProductDetail"
        //        googleAnalyticsBuilder.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Detail,ProductActionMap,product_Map,EcommerceMap);
        Snackbar.make(activity!!.findViewById(android.R.id.content), "Detail 전송 완료", Snackbar.LENGTH_LONG).show()
        btn_customdimension_add_detail.setOnClickListener {
            if (customdimensionNumber != 20) {
                val linearLayout: LinearLayout? = null
                when (customdimensionNumber) {
                    1 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail2) as LinearLayout).visibility = View.VISIBLE
                    2 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail3) as LinearLayout).visibility = View.VISIBLE
                    3 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail4) as LinearLayout).visibility = View.VISIBLE
                    4 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail5) as LinearLayout).visibility = View.VISIBLE
                    5 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail6) as LinearLayout).visibility = View.VISIBLE
                    6 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail7) as LinearLayout).visibility = View.VISIBLE
                    7 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail8) as LinearLayout).visibility = View.VISIBLE
                    8 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail9) as LinearLayout).visibility = View.VISIBLE
                    9 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail10) as LinearLayout).visibility = View.VISIBLE
                    10 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail11) as LinearLayout).visibility = View.VISIBLE
                    11 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail12) as LinearLayout).visibility = View.VISIBLE
                    12 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail13) as LinearLayout).visibility = View.VISIBLE
                    13 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail14) as LinearLayout).visibility = View.VISIBLE
                    14 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail15) as LinearLayout).visibility = View.VISIBLE
                    15 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail16) as LinearLayout).visibility = View.VISIBLE
                    16 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail17) as LinearLayout).visibility = View.VISIBLE
                    17 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail18) as LinearLayout).visibility = View.VISIBLE
                    18 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail19) as LinearLayout).visibility = View.VISIBLE
                    19 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail20) as LinearLayout).visibility = View.VISIBLE
                }
                customdimensionNumber++
            }
        }
        btn_customdimension_remove_detail.setOnClickListener {
            if (customdimensionNumber != 1) {
                val linearLayout: LinearLayout? = null
                when (customdimensionNumber) {
                    2 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail2) as LinearLayout).visibility = View.GONE
                    3 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail3) as LinearLayout).visibility = View.GONE
                    4 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail4) as LinearLayout).visibility = View.GONE
                    5 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail5) as LinearLayout).visibility = View.GONE
                    6 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail6) as LinearLayout).visibility = View.GONE
                    7 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail7) as LinearLayout).visibility = View.GONE
                    8 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail8) as LinearLayout).visibility = View.GONE
                    9 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail9) as LinearLayout).visibility = View.GONE
                    10 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail10) as LinearLayout).visibility = View.GONE
                    11 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail11) as LinearLayout).visibility = View.GONE
                    12 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail12) as LinearLayout).visibility = View.GONE
                    13 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail13) as LinearLayout).visibility = View.GONE
                    14 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail14) as LinearLayout).visibility = View.GONE
                    15 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail15) as LinearLayout).visibility = View.GONE
                    16 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail16) as LinearLayout).visibility = View.GONE
                    17 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail17) as LinearLayout).visibility = View.GONE
                    18 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail18) as LinearLayout).visibility = View.GONE
                    19 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail19) as LinearLayout).visibility = View.GONE
                    20 -> (v.findViewById<View>(R.id.linearlayout_custom1_detail20) as LinearLayout).visibility = View.GONE
                }
                customdimensionNumber--
            }
        }
        btn_purchase.setOnClickListener {
            if (editText_purchase.text.toString().length > 0) {
                val fragment: Fragment = ProductPurchase()
                val ft = fragmentManager!!.beginTransaction()
                val args = Bundle()
                if (et_cd_1!!.text.toString().length > 0) args.putString("cd1", et_cd_1!!.text.toString()) else args.putString("cd1", "")
                if (et_cd_2!!.text.toString().length > 0) args.putString("cd2", et_cd_2!!.text.toString()) else args.putString("cd2", "")
                if (et_cd_3!!.text.toString().length > 0) args.putString("cd3", et_cd_3!!.text.toString()) else args.putString("cd3", "")
                if (et_cd_4!!.text.toString().length > 0) args.putString("cd4", et_cd_4!!.text.toString()) else args.putString("cd4", "")
                if (et_cd_5!!.text.toString().length > 0) args.putString("cd5", et_cd_5!!.text.toString()) else args.putString("cd5", "")
                if (et_cd_6!!.text.toString().length > 0) args.putString("cd6", et_cd_6!!.text.toString()) else args.putString("cd6", "")
                if (et_cd_7!!.text.toString().length > 0) args.putString("cd7", et_cd_7!!.text.toString()) else args.putString("cd7", "")
                if (et_cd_8!!.text.toString().length > 0) args.putString("cd8", et_cd_8!!.text.toString()) else args.putString("cd8", "")
                if (et_cd_9!!.text.toString().length > 0) args.putString("cd9", et_cd_9!!.text.toString()) else args.putString("cd9", "")
                if (et_cd_10!!.text.toString().length > 0) args.putString("cd10", et_cd_10!!.text.toString()) else args.putString("cd10", "")
                if (et_cd_11!!.text.toString().length > 0) args.putString("cd11", et_cd_11!!.text.toString()) else args.putString("cd11", "")
                if (et_cd_12!!.text.toString().length > 0) args.putString("cd12", et_cd_12!!.text.toString()) else args.putString("cd12", "")
                if (et_cd_13!!.text.toString().length > 0) args.putString("cd13", et_cd_13!!.text.toString()) else args.putString("cd13", "")
                if (et_cd_14!!.text.toString().length > 0) args.putString("cd14", et_cd_14!!.text.toString()) else args.putString("cd14", "")
                if (et_cd_15!!.text.toString().length > 0) args.putString("cd15", et_cd_15!!.text.toString()) else args.putString("cd15", "")
                if (et_cd_16!!.text.toString().length > 0) args.putString("cd16", et_cd_16!!.text.toString()) else args.putString("cd16", "")
                if (et_cd_17!!.text.toString().length > 0) args.putString("cd17", et_cd_17!!.text.toString()) else args.putString("cd17", "")
                if (et_cd_18!!.text.toString().length > 0) args.putString("cd18", et_cd_18!!.text.toString()) else args.putString("cd18", "")
                if (et_cd_19!!.text.toString().length > 0) args.putString("cd19", et_cd_19!!.text.toString()) else args.putString("cd19", "")
                if (et_cd_20!!.text.toString().length > 0) args.putString("cd20", et_cd_20!!.text.toString()) else args.putString("cd20", "")
                args.putString("price", editText_purchase.text.toString())
                fragment.arguments = args
                ft.replace(R.id.content_fragment_layout, fragment)
                ft.commit()
            } else {
            }
        }
        return v
    }

    companion object {
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
        private var Param_CD1 = ""
        private var Param_CD2 = ""
        private var Param_CD3 = ""
        private var Param_CD4 = ""
        private var Param_CD5 = ""
        private var Param_CD6 = ""
        private var Param_CD7 = ""
        private var Param_CD8 = ""
        private var Param_CD9 = ""
        private var Param_CD10 = ""
        private var Param_CD11 = ""
        private var Param_CD12 = ""
        private var Param_CD13 = ""
        private var Param_CD14 = ""
        private var Param_CD15 = ""
        private var Param_CD16 = ""
        private var Param_CD17 = ""
        private var Param_CD18 = ""
        private var Param_CD19 = ""
        private var Param_CD20 = ""
        private const val TAG = "ProductDetail"
        private var customdimensionNumber = 1
    }
}