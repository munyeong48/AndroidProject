package com.goldenplanet.Install_AOSkt

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.analytics.FirebaseAnalytics
import java.util.*

class ProductPurchase : Fragment() {
    private var Param_price: String? = null
    private var Param_CD1: String? = ""
    private var Param_CD2: String? = ""
    private var Param_CD3: String? = ""
    private var Param_CD4: String? = ""
    private var Param_CD5: String? = ""
    private var Param_CD6: String? = ""
    private var Param_CD7: String? = ""
    private var Param_CD8: String? = ""
    private var Param_CD9: String? = ""
    private var Param_CD10: String? = ""
    private var Param_CD11: String? = ""
    private var Param_CD12: String? = ""
    private var Param_CD13: String? = ""
    private var Param_CD14: String? = ""
    private var Param_CD15: String? = ""
    private var Param_CD16: String? = ""
    private var Param_CD17: String? = ""
    private var Param_CD18: String? = ""
    private var Param_CD19: String? = ""
    private var Param_CD20: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            Param_price = arguments!!.getString("price")
            Param_CD1 = arguments!!.getString("cd1")
            Param_CD2 = arguments!!.getString("cd2")
            Param_CD3 = arguments!!.getString("cd3")
            Param_CD4 = arguments!!.getString("cd4")
            Param_CD5 = arguments!!.getString("cd5")
            Param_CD6 = arguments!!.getString("cd6")
            Param_CD7 = arguments!!.getString("cd7")
            Param_CD8 = arguments!!.getString("cd8")
            Param_CD9 = arguments!!.getString("cd9")
            Param_CD10 = arguments!!.getString("cd10")
            Param_CD11 = arguments!!.getString("cd11")
            Param_CD12 = arguments!!.getString("cd12")
            Param_CD13 = arguments!!.getString("cd13")
            Param_CD14 = arguments!!.getString("cd14")
            Param_CD15 = arguments!!.getString("cd15")
            Param_CD16 = arguments!!.getString("cd16")
            Param_CD17 = arguments!!.getString("cd17")
            Param_CD18 = arguments!!.getString("cd18")
            Param_CD19 = arguments!!.getString("cd19")
            Param_CD20 = arguments!!.getString("cd20")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.activity_product_purchase, container, false)
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
        googleAnalyticsBuilder = GoogleAnalyticsBuilder(activity)
        val ProductPrice = Param_price!!.toInt()
        val ProductMap: MutableMap<String?, String> = HashMap()
        ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductID] = "GP_Product"
        ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductName] = "GP_Product"
        ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductCategory] = "GA Test"
        ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductBrand] = "GP"
        ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductVariant] = "Black"
        ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductPrice] = ProductPrice.toString()
        ProductMap[GoogleAnalyticsBuilder.GAProductKey.ProductQuantity] = "1"
        val product_Map: MutableMap<String, Map<String?, String>> = HashMap()
        product_Map["pr1"] = ProductMap
        val ProductActionMap: MutableMap<String?, String> = HashMap()
        ProductActionMap[GoogleAnalyticsBuilder.GAActionFieldKey.TransactionID] = "94934672"
        ProductActionMap[GoogleAnalyticsBuilder.GAActionFieldKey.TransactionAffiliation] = "제휴 코드"
        ProductActionMap[GoogleAnalyticsBuilder.GAActionFieldKey.TransactionRevenue] = (ProductPrice + 20000 + 3000).toString()
        ProductActionMap[GoogleAnalyticsBuilder.GAActionFieldKey.TransactionTax] = 20000.toString()
        ProductActionMap[GoogleAnalyticsBuilder.GAActionFieldKey.TransactionShipping] = 3000.toString()
        ProductActionMap[GoogleAnalyticsBuilder.GAActionFieldKey.TransactionCouponCode] = "쿠폰 없음"
        val EcommerceMap: MutableMap<String?, String?> = HashMap()
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
        EcommerceMap[GoogleAnalyticsBuilder.GAHitKey.Title] = "PV_ProductPurchase"
        //        googleAnalyticsBuilder.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Purchase,ProductActionMap,product_Map,EcommerceMap);
        Snackbar.make(activity!!.findViewById(android.R.id.content), "Purchase 전송 완료", Snackbar.LENGTH_LONG).show()
        return v
    }

    companion object {
        private const val TAG = "ProductPurchase"
        var googleAnalyticsBuilder: GoogleAnalyticsBuilder? = null
    }
}