package com.goldenplanet.Install_AOSkt

import android.app.Activity
import android.util.Log
import android.webkit.JavascriptInterface
import com.google.android.gms.analytics.HitBuilders.EventBuilder
import com.google.android.gms.analytics.HitBuilders.ScreenViewBuilder
import com.google.android.gms.analytics.Tracker
import com.google.android.gms.analytics.ecommerce.Product
import com.google.android.gms.analytics.ecommerce.ProductAction
import com.google.android.gms.analytics.ecommerce.Promotion
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.net.URLDecoder
import java.util.*

class hmall(activity: Activity) {
    var mTracker: Tracker?

    object GACustomKey {
        var Dimension1 = "dimension1"
        var Dimension2 = "dimension2"
        var Dimension3 = "dimension3"
        var Dimension4 = "dimension4"
        var Dimension5 = "dimension5"
        var Dimension6 = "dimension6"
        var Dimension7 = "dimension7"
        var Dimension8 = "dimension8"
        var Dimension9 = "dimension9"
        var Dimension10 = "dimension10"
        var Dimension11 = "dimension11"
        var Dimension12 = "dimension12"
        var Dimension13 = "dimension13"
        var Dimension14 = "dimension14"
        var Dimension15 = "dimension15"
        var Dimension16 = "dimension16"
        var Dimension17 = "dimension17"
        var Dimension18 = "dimension18"
        var Dimension19 = "dimension4"
        var Dimension20 = "dimension20"
        var Dimension21 = "dimension21"
        var Dimension22 = "dimension22"
        var Dimension23 = "dimension23"
        var Dimension24 = "dimension24"
        var Dimension25 = "dimension25"
        var Dimension26 = "dimension26"
        var Dimension27 = "dimension27"
        var Dimension28 = "dimension28"
        var Dimension29 = "dimension29"
        var Dimension30 = "dimension30"
        var Dimension31 = "dimension31"
        var Dimension32 = "dimension32"
        var Dimension33 = "dimension33"
        var Dimension34 = "dimension34"
        var Dimension35 = "dimension35"
        var Dimension36 = "dimension36"
        var Dimension37 = "dimension37"
        var Dimension38 = "dimension38"
        var Dimension39 = "dimension39"
        var Dimension40 = "dimension40"
        var Dimension41 = "dimension41"
        var Dimension42 = "dimension42"
        var Dimension43 = "dimension43"
        var Dimension44 = "dimension44"
        var Dimension45 = "dimension45"
        var Dimension46 = "dimension46"
        var Dimension47 = "dimension47"
        var Dimension48 = "dimension48"
        var Dimension49 = "dimension49"
        var Dimension50 = "dimension50"
        var Metric1 = "metric1"
        var Metric2 = "metric2"
        var Metric3 = "metric3"
        var Metric4 = "metric4"
        var Metric5 = "metric5"
        var Metric6 = "metric6"
        var Metric7 = "metric7"
        var Metric8 = "metric8"
        var Metric9 = "metric9"
        var Metric10 = "metric9"
        var Metric11 = "metric11"
        var Metric12 = "metric12"
        var Metric13 = "metric13"
        var Metric14 = "metric14"
        var Metric15 = "metric15"
        var Metric16 = "metric16"
        var Metric17 = "metric17"
        var Metric18 = "metric18"
        var Metric19 = "metric19"
        var Metric20 = "metric20"
    }

    object GAEcommerceStepKey {
        var Impression = "impression"
        var Detail = "detail"
        var Click = "click"
        var Add = "add"
        var Remove = "remove"
        var Checkout = "checkout"
        var Purchase = "purchase"
        var Refund = "refund"
        var PromotionImpression = "promotionimpression"
        var PromotionClick = "promotionclick"
    }

    object GAHitKey {
        var UserID = "uid"
        var CampaignUrl = "camp_url"
        var Title = "title"
        var EventCategory = "category"
        var EventAction = "action"
        var EventLabel = "label"
        var EventValue = "value"
        var TimingValue = "timingvalue"
        var TimingCategory = ""
        var NonInteraction = "ni"
        var PromotionID = "promo_id"
        var PromotionName = "promo_nm"
        var PromotionCreative = "promo_cr"
        var PromotionPosition = "promo_ps"
        var CurrencyCode = "currencycode"
    }

    object GAActionFieldKey {
        var TransactionID = "af_id"
        var TransactionRevenue = "af_revenue"
        var TransactionTax = "af_tax"
        var TransactionShipping = "af_shipping"
        var TransactionCouponCode = "af_coupon"
        var TransactionAffiliation = "af_affiliation"
        var ProductActionList = "af_list"
        var CheckoutStep = "af_step"
        var CheckoutOptions = "af_option"
    }

    object GAProductKey {
        var ProductID = "prid"
        var ProductName = "prnm"
        var ProductBrand = "prbr"
        var ProductCategory = "prca"
        var ProductVariant = "prva"
        var ProductPrice = "prpr"
        var ProductQuantity = "prqt"
        var ProductCouponCode = "prcc"
        var ProductPosition = "prps"
        var ImpressionList = "prlist"
        var ProdudctDimension1 = "prcd1"
        var ProdudctDimension2 = "prcd2"
        var ProdudctDimension3 = "prcd3"
        var ProdudctDimension4 = "prcd4"
        var ProdudctDimension5 = "prcd5"
        var ProdudctDimension6 = "prcd6"
        var ProdudctDimension7 = "prcd7"
        var ProdudctDimension8 = "prcd8"
        var ProdudctDimension9 = "prcd9"
        var ProdudctDimension10 = "prcd10"
        var ProdudctDimension11 = "prcd11"
        var ProdudctDimension12 = "prcd12"
        var ProdudctDimension13 = "prcd13"
        var ProdudctDimension14 = "prcd14"
        var ProdudctDimension15 = "prcd15"
        var ProdudctDimension16 = "prcd16"
        var ProdudctDimension17 = "prcd17"
        var ProdudctDimension18 = "prcd18"
        var ProdudctDimension19 = "prcd19"
        var ProdudctDimension20 = "prcd20"
        var ProdudctDimension21 = "prcd21"
        var ProdudctDimension22 = "prcd22"
        var ProdudctDimension23 = "prcd23"
        var ProdudctDimension24 = "prcd24"
        var ProdudctDimension25 = "prcd25"
        var ProdudctDimension26 = "prcd26"
        var ProdudctDimension27 = "prcd27"
        var ProdudctDimension28 = "prcd28"
        var ProdudctDimension29 = "prcd29"
        var ProdudctDimension30 = "prcd30"
        var ProdudctDimension31 = "prcd31"
        var ProdudctDimension32 = "prcd32"
        var ProdudctDimension33 = "prcd33"
        var ProdudctDimension34 = "prcd34"
        var ProdudctDimension35 = "prcd35"
        var ProdudctDimension36 = "prcd36"
        var ProdudctDimension37 = "prcd37"
        var ProdudctDimension38 = "prcd38"
        var ProdudctDimension39 = "prcd39"
        var ProdudctDimension40 = "prcd40"
        var ProdudctDimension41 = "prcd41"
        var ProdudctDimension42 = "prcd42"
        var ProdudctDimension43 = "prcd43"
        var ProdudctDimension44 = "prcd44"
        var ProdudctDimension45 = "prcd45"
        var ProdudctDimension46 = "prcd46"
        var ProdudctDimension47 = "prcd47"
        var ProdudctDimension48 = "prcd48"
        var ProdudctDimension49 = "prcd49"
        var ProdudctDimension50 = "prcd50"
        var ProdudctMetric1 = "prme1"
        var ProdudctMetric2 = "prme2"
        var ProdudctMetric3 = "prme3"
        var ProdudctMetric4 = "prme4"
        var ProdudctMetric5 = "prme5"
        var ProdudctMetric6 = "prme6"
        var ProdudctMetric7 = "prme7"
        var ProdudctMetric8 = "prme8"
        var ProdudctMetric9 = "prme9"
        var ProdudctMetric10 = "prme10"
        var ProdudctMetric11 = "prme11"
        var ProdudctMetric12 = "prme12"
        var ProdudctMetric13 = "prme13"
        var ProdudctMetric14 = "prme14"
        var ProdudctMetric15 = "prme15"
        var ProdudctMetric16 = "prme16"
        var ProdudctMetric17 = "prme17"
        var ProdudctMetric18 = "prme18"
        var ProdudctMetric19 = "prme19"
        var ProdudctMetric20 = "prme20"
    }

    fun GADataSend_Event(GAInfo: Map<String, String?>) {
        try {
            val eventBuilder = EventBuilder()
            var Category: String
            var Action: String
            var Label: String
            val sIterator = GAInfo.keys.iterator()
            val promotion = Promotion()
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
                    if (key.toLowerCase() == "title") mTracker!!.setScreenName(GAInfo[key])
                    if (key.toLowerCase() == "ni" && GAInfo[key] == "1") eventBuilder.setNonInteraction(true)
                }
            }
            if (promotion.toString() !== "") {
                eventBuilder.addPromotion(promotion)
                eventBuilder.setPromotionAction(Promotion.ACTION_CLICK)
            }
            mTracker!!.send(eventBuilder.build())
            mTracker = NullSet(mTracker!!)
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
                    if (key.toLowerCase() == "title") mTracker!!.setScreenName(GAInfo[key])
                    if (key.toLowerCase() == "ni" && GAInfo[key] == "1") screenViewBuilder.setNonInteraction(true)
                }
            }
            mTracker!!.send(screenViewBuilder.build())
            mTracker = NullSet(mTracker!!)
        } catch (e: Exception) {
            Log.e("GAv4_Screen", e.message!!)
        }
    }

    fun GADataSend_Ecommerce(EcommerceStep: String, GAAction: Map<String, String?>, GAProduct: Map<String, Map<String, String?>>?, GAInfo: Map<String, String?>) {
        try {
            if (mTracker != null) {
                var ecommerceBuilder: EventBuilder? = EventBuilder()
                var sIterator_GAInfo = GAInfo.keys.iterator()

                //제품클릭 단계
                if (EcommerceStep.toLowerCase() == "click") {
                    var pa: ProductAction? = ProductAction(ProductAction.ACTION_CLICK)
                    pa = GA_ActionField(GAAction, pa)
                    ecommerceBuilder!!.setProductAction(pa)
                }

                //디테일 단계
                if (EcommerceStep.toLowerCase() == "detail") {
                    var pa: ProductAction? = ProductAction(ProductAction.ACTION_DETAIL)
                    pa = GA_ActionField(GAAction, pa)
                    ecommerceBuilder!!.setProductAction(pa)
                }

                //장바구니추가 단계
                if (EcommerceStep.toLowerCase() == "add") {
                    var pa: ProductAction? = ProductAction(ProductAction.ACTION_ADD)
                    pa = GA_ActionField(GAAction, pa)
                    ecommerceBuilder!!.setProductAction(pa)
                }

                //장바구니삭제 단계
                if (EcommerceStep.toLowerCase() == "remove") {
                    var pa: ProductAction? = ProductAction(ProductAction.ACTION_REMOVE)
                    pa = GA_ActionField(GAAction, pa)
                    ecommerceBuilder!!.setProductAction(pa)
                }

                //체크아웃 단계
                if (EcommerceStep.toLowerCase() == "checkout") {
                    var pa: ProductAction? = ProductAction(ProductAction.ACTION_CHECKOUT)
                    pa = GA_ActionField(GAAction, pa)
                    ecommerceBuilder!!.setProductAction(pa)
                }

                //결제 단계
                if (EcommerceStep.toLowerCase() == "purchase") {
                    var pa: ProductAction? = ProductAction(ProductAction.ACTION_PURCHASE)
                    pa = GA_ActionField(GAAction, pa)
                    ecommerceBuilder!!.setProductAction(pa)
                }

                //리펀드 단계
                if (EcommerceStep.toLowerCase() == "refund") {
                    var pa: ProductAction? = ProductAction(ProductAction.ACTION_REFUND)
                    pa = GA_ActionField(GAAction, pa)
                    ecommerceBuilder!!.setProductAction(pa)
                }
                while (sIterator_GAInfo.hasNext()) {
                    val key = sIterator_GAInfo.next()
                    if (GAInfo[key] != null && GAInfo[key]!!.length > 0) {
                        if (key.toLowerCase().contains("dimension")) {
                            val Number = key.replace("[^0-9]".toRegex(), "").toInt()
                            ecommerceBuilder!!.setCustomDimension(Number, GAInfo[key])
                        }
                        if (key.toLowerCase().contains("metric")) {
                            val Number = key.replace("[^0-9]".toRegex(), "").toInt()
                            ecommerceBuilder!!.setCustomMetric(Number, GAInfo[key]!!.toFloat())
                        }
                        if (key.toLowerCase() == "uid") mTracker!!["&uid"] = GAInfo[key]
                        if (key.toLowerCase() == "title") {
                            if (GAInfo["title"] != null && GAInfo["title"]!!.length > 0) mTracker!!.setScreenName(GAInfo["title"])
                        }
                        if (key.toLowerCase() == "currencycode") {
                            mTracker!!["&cu"] = GAInfo["currencycode"]
                        }
                        if (key.toLowerCase() == "camp_url") {
                            var URL = GAInfo[key]
                            URL = URLDecoder.decode(URL, "UTF-8")
                            URL = URLDecoder.decode(URL, "UTF-8")
                            ecommerceBuilder!!.setCampaignParamsFromUrl(URL)
                        }
                        if (key.toLowerCase() == "cu") {
                            mTracker!!["&cu"] = GAInfo["cu"]
                        }
                        if (key.toLowerCase() == "category") ecommerceBuilder!!.setCategory(GAInfo["category"])
                        if (key.toLowerCase() == "action") ecommerceBuilder!!.setAction(GAInfo["action"])
                        if (key.toLowerCase() == "label") ecommerceBuilder!!.setLabel(GAInfo["label"])
                        if (key.toLowerCase() == "ni" && GAInfo[key] == "1") ecommerceBuilder!!.setNonInteraction(true)
                    }
                }
                if (EcommerceStep.toLowerCase().contains("promotion")) {
                    val promotion = Promotion()
                    sIterator_GAInfo = GAInfo.keys.iterator()
                    while (sIterator_GAInfo.hasNext()) {
                        val key = sIterator_GAInfo.next()
                        if (GAInfo[key] != null && GAInfo[key]!!.length > 0) {
                            if (key.toLowerCase() == "promo_id") promotion.setId(GAInfo[key])
                            if (key.toLowerCase() == "promo_nm") promotion.setName(GAInfo[key])
                            if (key.toLowerCase() == "promo_cr") promotion.setCreative(GAInfo[key])
                            if (key.toLowerCase() == "promo_ps") promotion.setPosition(GAInfo[key])
                        }
                    }
                    ecommerceBuilder!!.addPromotion(promotion)
                    if (EcommerceStep.toLowerCase() == "promotionclick") ecommerceBuilder.setPromotionAction(Promotion.ACTION_CLICK)
                } else {
                    ecommerceBuilder = GA_Product(EcommerceStep, GAProduct, ecommerceBuilder)
                }
                mTracker!!.send(ecommerceBuilder!!.build())
                mTracker = NullSet(mTracker!!)
            }
        } catch (e: Exception) {
            Log.e("GAv4_Ecommerce", e.message!!)
        }
    }

    private fun GA_ActionField(GAAction: Map<String, String?>, productAction: ProductAction?): ProductAction? {
        return try {
            val sIterator_GA_ActionField = GAAction.keys.iterator()
            while (sIterator_GA_ActionField.hasNext()) {
                val actionField_key = sIterator_GA_ActionField.next()
                if (GAAction[actionField_key] != null && GAAction[actionField_key]!!.length > 0) {
                    if (actionField_key.toLowerCase() == "af_id") productAction!!.setTransactionId(GAAction["af_id"])
                    if (actionField_key.toLowerCase() == "af_revenue") productAction!!.setTransactionRevenue(java.lang.Double.valueOf(GAAction["af_revenue"]!!))
                    if (actionField_key.toLowerCase() == "af_tax") productAction!!.setTransactionTax(java.lang.Double.valueOf(GAAction["af_tax"]!!))
                    if (actionField_key.toLowerCase() == "af_shipping") productAction!!.setTransactionShipping(java.lang.Double.valueOf(GAAction["af_shipping"]!!))
                    if (actionField_key.toLowerCase() == "af_coupon") productAction!!.setTransactionCouponCode(GAAction["af_coupon"])
                    if (actionField_key.toLowerCase() == "af_affiliation") productAction!!.setTransactionAffiliation(GAAction["af_affiliation"])
                    if (actionField_key.toLowerCase() == "af_list") productAction!!.setProductActionList(GAAction["af_list"])
                    if (actionField_key.toLowerCase() == "af_step") productAction!!.setCheckoutStep(GAAction["af_step"]!!.toInt())
                    if (actionField_key.toLowerCase() == "af_option") productAction!!.setCheckoutOptions(GAAction["af_option"])
                }
            }
            productAction
        } catch (ex: Exception) {
            Log.e("GAv4_ActionField", ex.message!!)
            null
        }
    }

    private fun GA_Product(EcommerceStep: String, GAProduct: Map<String, Map<String, String?>>?, ecommerceBuilder: EventBuilder?): EventBuilder? {
        return try {
            if (GAProduct != null && GAProduct.size > 0) {
                val sIterator_GAProduct = GAProduct.keys.iterator()
                while (sIterator_GAProduct.hasNext()) {
                    val product = Product()
                    var prd_list = "ImpressionList"
                    val key = sIterator_GAProduct.next()
                    if (key.contains("pr")) {
                        val Productinfo = GAProduct[key]!!
                        val sIterator_Productinfo = Productinfo.keys.iterator()
                        while (sIterator_Productinfo.hasNext()) {
                            val product_key = sIterator_Productinfo.next()
                            if (Productinfo[product_key] != null && Productinfo[product_key]!!.length > 0) {
                                if (product_key.toLowerCase() == "prid") {
                                    product.setId(Productinfo["prid"])
                                }
                                if (product_key.toLowerCase() == "prnm") {
                                    product.setName(Productinfo["prnm"])
                                }
                                if (product_key.toLowerCase() == "prbr") {
                                    product.setBrand(Productinfo["prbr"])
                                }
                                if (product_key.toLowerCase() == "prca") {
                                    product.setCategory(Productinfo["prca"])
                                }
                                if (product_key.toLowerCase() == "prva") {
                                    product.setVariant(Productinfo["prva"])
                                }
                                if (product_key.toLowerCase() == "prpr") {
                                    product.setPrice(java.lang.Double.valueOf(Productinfo["prpr"]!!))
                                }
                                if (product_key.toLowerCase() == "prqt") {
                                    product.setQuantity(Productinfo["prqt"]!!.toInt())
                                }
                                if (product_key.toLowerCase() == "prcc") {
                                    product.setCouponCode(Productinfo["prcc"])
                                }
                                if (product_key == "prps") {
                                    product.setPosition(Productinfo["prps"]!!.toInt())
                                }
                                if (product_key.toLowerCase() == "prlist") {
                                    prd_list = Productinfo["prlist"]!!
                                }
                                if (product_key.toLowerCase().contains("prcd")) {
                                    val Number = product_key.replace("[^0-9]".toRegex(), "").toInt()
                                    product.setCustomDimension(Number, Productinfo[product_key])
                                }
                                if (product_key.toLowerCase().contains("prme")) {
                                    val Number = product_key.replace("[^0-9]".toRegex(), "").toInt()
                                    product.setCustomMetric(Number, Integer.valueOf(Productinfo[product_key]!!))
                                }
                            }
                        }
                    }
                    if (EcommerceStep.toLowerCase() != "impression") {
                        ecommerceBuilder!!.addProduct(product)
                    } else {
                        ecommerceBuilder!!.addImpression(product, prd_list)
                    }
                }
            }
            ecommerceBuilder
        } catch (e: Exception) {
            Log.e("GAv4_Product", e.message!!)
            null
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "UNREACHABLE_CODE")
    class WebAppInterface(activity: Activity) {
        var mTracker: Tracker?
        var productAction: ProductAction? = null
        var ecommerce_data: JSONObject? = null
        @JavascriptInterface
        fun GA_DATA(JsonData: String?) {
            try {
                val event_builder = EventBuilder()
                val Screenview_Builder = ScreenViewBuilder()
                val data = JSONObject(JsonData)
                var sType = ""
                var Impression_List = ""
                if (data.has("type")) sType = data.getString("type")
                if (data.has("dimension")) {
                    val obj_dimension = data.getJSONObject("dimension")
                    val sIterator = obj_dimension.keys()
                    while (sIterator.hasNext()) {
                        val key = sIterator.next()
                        val Number = key.replace("[^0-9]".toRegex(), "").toInt()
                        if (sType == "P") Screenview_Builder.setCustomDimension(Number, obj_dimension.getString(key)) else event_builder.setCustomDimension(Number, obj_dimension.getString(key))
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
                if (data.has("nonInteraction")) event_builder.setNonInteraction(true)
                if (data.has("title")) mTracker!!.setScreenName(data.getString("title"))

                //전자상거래
                if (data.has("ecommerce")) {
                    val obj_ecommerce = data.getJSONObject("ecommerce")
                    if (obj_ecommerce.has("currencyCode")) mTracker!!["&cu"] = obj_ecommerce.getString("currencyCode")


                    //제품 전시 단계
                    if (obj_ecommerce.has("promoview") || obj_ecommerce.has("promoclick")) {
                        if (obj_ecommerce.has("promoview")) ecommerce_data = obj_ecommerce.getJSONObject("promoview")
                        if (obj_ecommerce.has("promoclick")) ecommerce_data = obj_ecommerce.getJSONObject("promoclick")
                        if (ecommerce_data!!.has("promotions")) {
                            val product_array = JSONArray(ecommerce_data!!.getString("promotions"))
                            val product_list = ConvertJsonArray(product_array)
                            val promotion = Promotion()
                            for (i in product_list!!.indices) {
                                val product_Hashmap = product_list[i] as HashMap<String, String>
                                val keys: Iterator<String> = product_Hashmap.keys.iterator()
                                while (keys.hasNext()) {
                                    val key = keys.next()
                                    if (key.contains("id")) promotion.setId(product_Hashmap[key])
                                    if (key.contains("name")) promotion.setName(product_Hashmap[key])
                                    if (key.contains("creative")) promotion.setCreative(product_Hashmap[key])
                                    if (key.contains("position")) promotion.setPosition(product_Hashmap[key])
                                }
                            }
                            if (obj_ecommerce.has("promoview") && sType == "P") {
                                Screenview_Builder.addPromotion(promotion)
                            } else if (obj_ecommerce.has("promoview") && sType != "P") {
                                event_builder.addPromotion(promotion)
                            } else if (obj_ecommerce.has("promoclick") && sType == "P") {
                                Screenview_Builder.setPromotionAction(Promotion.ACTION_CLICK)
                                Screenview_Builder.addPromotion(promotion)
                            } else if (obj_ecommerce.has("promoclick") && sType != "P") {
                                event_builder.setPromotionAction(Promotion.ACTION_CLICK)
                                event_builder.addPromotion(promotion)
                            }
                        }
                    }
                    if (obj_ecommerce.has("impressions")) {
                        ecommerce_data = obj_ecommerce.getJSONObject("impressions")
                        if (ecommerce_data!!.has("actionField")) {
                            val actionField_data = ecommerce_data!!.getJSONObject("actionField")
                            if (actionField_data.has("list")) {
                                Impression_List = actionField_data.getString("list")
                            }
                        }
                    }

                    //제품클릭 단계
                    if (obj_ecommerce.has("click")) {
                        productAction = ProductAction(ProductAction.ACTION_CLICK)
                        ecommerce_data = obj_ecommerce.getJSONObject("click")
                        GA_ActionField()
                    }

                    //디테일 단계
                    if (obj_ecommerce.has("detail")) {
                        productAction = ProductAction(ProductAction.ACTION_DETAIL)
                        ecommerce_data = obj_ecommerce.getJSONObject("detail")
                        GA_ActionField()
                    }
                    //장바구니추가 단계
                    if (obj_ecommerce.has("add")) {
                        productAction = ProductAction(ProductAction.ACTION_ADD)
                        ecommerce_data = obj_ecommerce.getJSONObject("add")
                        GA_ActionField()
                    }

                    //장바구니삭제 단계
                    if (obj_ecommerce.has("remove")) {
                        productAction = ProductAction(ProductAction.ACTION_REMOVE)
                        ecommerce_data = obj_ecommerce.getJSONObject("remove")
                    }

                    //체크아웃 단계
                    if (obj_ecommerce.has("checkout")) {
                        productAction = ProductAction(ProductAction.ACTION_CHECKOUT)
                        ecommerce_data = obj_ecommerce.getJSONObject("checkout")
                        GA_ActionField()
                    }

                    //결제 단계
                    if (obj_ecommerce.has("purchase")) {
                        productAction = ProductAction(ProductAction.ACTION_PURCHASE)
                        ecommerce_data = obj_ecommerce.getJSONObject("purchase")
                        GA_ActionField()
                    }

                    //리펀드 단계
                    if (obj_ecommerce.has("refund")) {
                        productAction = ProductAction(ProductAction.ACTION_REFUND)
                        ecommerce_data = obj_ecommerce.getJSONObject("refund")
                        GA_ActionField()
                    }

                    //제품 아이템 추가
                    if (ecommerce_data!!.has("products")) {
                        val product_array = ecommerce_data!!.getJSONArray("products")
                        val product_list = ConvertJsonArray(product_array)
                        for (i in product_list!!.indices) {
                            val product_Hashmap = product_list[i] as HashMap<String, String>
                            val item = Product()
                            val keys: Iterator<String> = product_Hashmap.keys.iterator()
                            while (keys.hasNext()) {
                                val key = keys.next()
                                if (key.contains("id")) item.setId(product_Hashmap[key])
                                if (key.contains("name")) item.setName(product_Hashmap[key])
                                if (key.contains("brand")) item.setBrand(product_Hashmap[key])
                                if (key.contains("category")) item.setCategory(product_Hashmap[key])
                                if (key.contains("price")) item.setPrice(product_Hashmap[key]!!.toDouble())
                                if (key.contains("quantity")) item.setQuantity(product_Hashmap[key]!!.toInt())
                                if (key.contains("variant")) item.setVariant(product_Hashmap[key])
                                if (key.contains("coupon")) item.setCouponCode(product_Hashmap[key])
                                if (key.contains("position")) {
                                    val position_value: Any? = product_Hashmap[key]
                                    val position = position_value.toString()
                                    item.setPosition(position.toInt())
                                }
                                if (key.contains("dimension")) {
                                    val Number = key.replace("[^0-9]".toRegex(), "").toInt()
                                    item.setCustomDimension(Number, product_Hashmap[key])
                                }
                                if (key.contains("metric")) {
                                    val Number = key.replace("[^0-9]".toRegex(), "").toInt()
                                    val metric_value: Any? = product_Hashmap[key]
                                    val metric = metric_value.toString()
                                    item.setCustomMetric(Number, metric.toInt())
                                }
                            }
                            if (!obj_ecommerce.has("impressions")) {
                                if (sType == "P") Screenview_Builder.addProduct(item) else event_builder.addProduct(item)
                            } else {
                                if (sType == "P") Screenview_Builder.addImpression(item, Impression_List) else event_builder.addImpression(item, Impression_List)
                            }
                        }
                    }
                    if (productAction != null) {
                        if (sType == "P") Screenview_Builder.setProductAction(productAction) else event_builder.setProductAction(productAction)
                    }
                }
                if (sType == "P") {
                    Screenview_Builder.setCustomDimension(1, mTracker!!["&cid"])
                    mTracker!!.send(Screenview_Builder.build())
                } else {
                    event_builder.setCustomDimension(1, mTracker!!["&cid"])
                    if (data.has("category")) event_builder.setCategory(data.getString("category"))
                    if (data.has("action")) event_builder.setAction(data.getString("action"))
                    if (data.has("label")) event_builder.setLabel(data.getString("label"))
                    mTracker!!.send(event_builder.build())
                    mTracker = NullSet(mTracker!!)
                }
            } catch (ex: Exception) {
                Log.i("GAv4_Bridge_Data", ex.message)
            }
        }

        fun GA_Promotion(ecommerce_data: JSONObject, eventBuilder: EventBuilder): EventBuilder? {
            return try {
                if (ecommerce_data.has("promotions")) {
                    val promotion_array = ecommerce_data.getJSONArray("promotions")
                    for (i in 0 until promotion_array.length()) {
                        val ecommerce_item = promotion_array.getJSONObject(i)
                        val promotions = Promotion()
                        if (ecommerce_item.has("id")) promotions.setId(ecommerce_item.getString("id"))
                        if (ecommerce_item.has("name")) promotions.setName(ecommerce_item.getString("name"))
                        if (ecommerce_item.has("position")) promotions.setPosition(ecommerce_item.getString("position"))
                        if (ecommerce_item.has("creative")) promotions.setCreative(ecommerce_item.getString("creative"))
                        eventBuilder.addPromotion(promotions)
                    }
                }
                eventBuilder
            } catch (ex: Exception) {
                Log.e("GAv4_Bridge_Promotion", ex.message)
                null
            }
        }

        fun GA_ActionField() {
            try {
                if (ecommerce_data!!.has("actionField")) {
                    val actionField_data = ecommerce_data!!.getJSONObject("actionField")
                    if (actionField_data.has("id")) productAction!!.setTransactionId(actionField_data.getString("id"))
                    if (actionField_data.has("revenue")) productAction!!.setTransactionRevenue(actionField_data.getDouble("revenue"))
                    if (actionField_data.has("tax")) productAction!!.setTransactionTax(actionField_data.getDouble("tax"))
                    if (actionField_data.has("shipping")) productAction!!.setTransactionShipping(actionField_data.getDouble("shipping"))
                    if (actionField_data.has("coupon")) productAction!!.setTransactionCouponCode(actionField_data.getString("coupon"))
                    if (actionField_data.has("affiliation")) productAction!!.setTransactionAffiliation(actionField_data.getString("affiliation"))
                    if (actionField_data.has("list")) productAction!!.setProductActionList(actionField_data.getString("list"))
                    if (actionField_data.has("step")) productAction!!.setCheckoutStep(actionField_data.getInt("step"))
                    if (actionField_data.has("option")) productAction!!.setCheckoutOptions(actionField_data.getString("option"))
                }
            } catch (ex: Exception) {
                Log.e("GAv4_Bridge_ActionField", ex.message)
            }
        }

        companion object {
            @Throws(JSONException::class)
            private fun ConvertObjectData(json: Any): Any? {
                return try {
                    if (json === JSONObject.NULL) null else if (json is JSONObject) ConvertJsonObject(json) else if (json is JSONArray) ConvertJsonArray(json) else json
                } catch (e: Exception) {
                    Log.e("GAv4_ConvertObjectData", e.message)
                    null
                }
            }

            @Throws(JSONException::class)
            fun ConvertJsonObject(`object`: JSONObject): Map<String?, Any?>? {
                return try {
                    //val map: MutableMap<String?, Any?> = HashMap<Any?, Any?>()
                    val map: MutableMap<String?, Any?> = HashMap<String?, Any?>()
                    val keys: Iterator<*> = `object`.keys()
                    while (keys.hasNext()) {
                        val key = keys.next() as String
                        map[key] = ConvertObjectData(`object`[key])
                    }
                    map
                } catch (e: Exception) {
                    Log.e("GAv4_ConvertJsonObject", e.message!!)
                    null
                }
            }

            @Throws(JSONException::class)
            fun ConvertJsonArray(array: JSONArray): List<*>? {
                return try {
                    val list: MutableList<*> = ArrayList<Any?>()
                    for (i in 0 until array.length()) {
                        list.add(ConvertObjectData(array[i]) as Nothing)
                    }
                    list
                } catch (e: Exception) {
                    Log.e("GAv4_ConvertJsonArray", e.message!!)
                    null
                }
            }
        }

        init {
            val application = activity.application as AnalyticsApplication
            mTracker = application.defaultTracker
        }
    }

    companion object {
        private fun NullSet(mTracker: Tracker): Tracker {
            mTracker.setScreenName(null)
            mTracker["&cu"] = null
            mTracker["&uid"] = null
            return mTracker
        }
    }

    init {
        val application = activity.application as AnalyticsApplication
        mTracker = application.defaultTracker
    }
}