package com.example.firebase_log_event_kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.webkit.JavascriptInterface
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //NextActivity이동 버튼
        val button = findViewById<View>(R.id.button) as Button
        button.setOnClickListener {
            val i = Intent(this@MainActivity, NextActivity::class.java)
            startActivity(i)
        }
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        //clientId 가져오는 설정
        FirebaseAnalytics.getInstance(this).appInstanceId.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                user_pseudo_id[0] = task.result
                Log.v("Firebase Instance ID = ", user_pseudo_id[0])
                //UserProperty
                mFirebaseAnalytics!!.setUserProperty("user_property2", user_pseudo_id[0])
                //UserId
                mFirebaseAnalytics!!.setUserId(user_pseudo_id[0])

                // 여기 안으로 들어오면 onResume 단계가 되는듯
                val bundle = Bundle() //Bundle 객체 생성
                bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "firebasek_main") //보고서-페이지 제목 및 화면 이름 or 이벤트-firebase_screen
                bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "firebasek_main") //보고서-이벤트-screen_view-firebase_screen_class
                mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle) //logEvent(이벤트 명, bundle)

            }
        }
        // 상품 정의
        /*
        val itemJeggings = Bundle()
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_ID, "SKU_123")
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_NAME, "jeggings")
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "pants")
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "black")
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_BRAND, "Google")
        itemJeggings.putDouble(FirebaseAnalytics.Param.PRICE, 9.99)
        val itemBoots = Bundle()
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_ID, "SKU_456")
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_NAME, "boots")
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "shoes")
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "brown")
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_BRAND, "Google")
        itemBoots.putDouble(FirebaseAnalytics.Param.PRICE, 24.99)
        val itemSocks = Bundle()
        itemSocks.putString(FirebaseAnalytics.Param.ITEM_ID, "SKU_789")
        itemSocks.putString(FirebaseAnalytics.Param.ITEM_NAME, "ankle_socks")
        itemSocks.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "socks")
        itemSocks.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "red")
        itemSocks.putString(FirebaseAnalytics.Param.ITEM_BRAND, "Google")
        itemSocks.putDouble(FirebaseAnalytics.Param.PRICE, 5.99)

        val itemJeggingsWithIndex = Bundle(itemJeggings)
        itemJeggingsWithIndex.putLong(FirebaseAnalytics.Param.INDEX, 1)
        val itemBootsWithIndex = Bundle(itemBoots)
        itemBootsWithIndex.putLong(FirebaseAnalytics.Param.INDEX, 2)
        val itemSocksWithIndex = Bundle(itemSocks)
        itemSocksWithIndex.putLong(FirebaseAnalytics.Param.INDEX, 3)

        // 상품 imp
        val viewItemListParams = Bundle()
        viewItemListParams.putString(FirebaseAnalytics.Param.ITEM_LIST_ID, "L001")
        viewItemListParams.putString(FirebaseAnalytics.Param.ITEM_LIST_NAME, "Related products")
        viewItemListParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, arrayOf<Parcelable>(itemJeggingsWithIndex, itemBootsWithIndex, itemSocksWithIndex))
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.VIEW_ITEM_LIST, viewItemListParams)

        // 상품 선택
        val selectItemParams = Bundle()
        selectItemParams.putString(FirebaseAnalytics.Param.ITEM_LIST_ID, "L001")
        selectItemParams.putString(FirebaseAnalytics.Param.ITEM_LIST_NAME, "Related products")
        selectItemParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, arrayOf<Parcelable>(itemJeggings))
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, selectItemParams)

        //상품 디테일
        val viewItemParams = Bundle()
        viewItemParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD")
        viewItemParams.putDouble(FirebaseAnalytics.Param.VALUE, 9.99)
        viewItemParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, arrayOf<Parcelable>(itemJeggings))
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, viewItemParams)

        // 상품 위시리스트 추가
        val itemJeggingsWishlist = Bundle(itemJeggings)
        itemJeggingsWishlist.putLong(FirebaseAnalytics.Param.QUANTITY, 2)
        val addToWishlistParams = Bundle()
        addToWishlistParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD")
        addToWishlistParams.putDouble(FirebaseAnalytics.Param.VALUE, 2 * 9.99)
        addToWishlistParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, arrayOf<Parcelable>(itemJeggingsWishlist))
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.ADD_TO_WISHLIST, addToWishlistParams)

        // 카트 보기
        val itemJeggingsCart = Bundle(itemJeggings)
        itemJeggingsCart.putLong(FirebaseAnalytics.Param.QUANTITY, 2)
        val itemBootsCart = Bundle(itemBoots)
        itemBootsCart.putLong(FirebaseAnalytics.Param.QUANTITY, 1)
        val viewCartParams = Bundle()
        viewCartParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD")
        viewCartParams.putDouble(FirebaseAnalytics.Param.VALUE, 2 * 9.99 + 1 * 24.99)
        viewCartParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, arrayOf<Parcelable>(itemJeggingsCart, itemBootsCart))
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.VIEW_CART, viewCartParams)

        // 카트 제거
        val removeCartParams = Bundle()
        removeCartParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD")
        removeCartParams.putDouble(FirebaseAnalytics.Param.VALUE, 1 * 24.99)
        removeCartParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, arrayOf<Parcelable>(itemBootsCart))

        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.REMOVE_FROM_CART, removeCartParams)

        // 체크아웃 시작
        val beginCheckoutParams = Bundle()
        beginCheckoutParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD")
        beginCheckoutParams.putDouble(FirebaseAnalytics.Param.VALUE, 14.98)
        beginCheckoutParams.putString(FirebaseAnalytics.Param.COUPON, "SUMMER_FUN")
        beginCheckoutParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, arrayOf<Parcelable>(itemJeggingsCart))
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.BEGIN_CHECKOUT, beginCheckoutParams)

        // shipping 제품정보 추가
        val addShippingParams = Bundle()
        addShippingParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD")
        addShippingParams.putDouble(FirebaseAnalytics.Param.VALUE, 14.98)
        addShippingParams.putString(FirebaseAnalytics.Param.COUPON, "SUMMER_FUN")
        addShippingParams.putString(FirebaseAnalytics.Param.SHIPPING_TIER, "Ground")
        addShippingParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, arrayOf<Parcelable>(itemJeggingsCart))
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.ADD_SHIPPING_INFO, addShippingParams)

        // 결제 정보 추가
        val addPaymentParams = Bundle()
        addPaymentParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD")
        addPaymentParams.putDouble(FirebaseAnalytics.Param.VALUE, 14.98)
        addPaymentParams.putString(FirebaseAnalytics.Param.COUPON, "SUMMER_FUN")
        addPaymentParams.putString(FirebaseAnalytics.Param.PAYMENT_TYPE, "Visa")
        addPaymentParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, arrayOf<Parcelable>(itemJeggingsCart))
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.ADD_PAYMENT_INFO, addPaymentParams)

        //상품 구매
        val purchaseParams = Bundle()
        purchaseParams.putString(FirebaseAnalytics.Param.TRANSACTION_ID, "T12345")
        purchaseParams.putString(FirebaseAnalytics.Param.AFFILIATION, "Google Store")
        purchaseParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD")
        purchaseParams.putDouble(FirebaseAnalytics.Param.VALUE, 14.98)
        purchaseParams.putDouble(FirebaseAnalytics.Param.TAX, 2.58)
        purchaseParams.putDouble(FirebaseAnalytics.Param.SHIPPING, 5.34)
        purchaseParams.putString(FirebaseAnalytics.Param.COUPON, "SUMMER_FUN")
        purchaseParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, arrayOf<Parcelable>(itemJeggingsCart))
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.PURCHASE, purchaseParams)

        //상품 환불
        val refundParams = Bundle()
        refundParams.putString(FirebaseAnalytics.Param.TRANSACTION_ID, "T12345")
        refundParams.putString(FirebaseAnalytics.Param.AFFILIATION, "Google Store")
        refundParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD")
        refundParams.putDouble(FirebaseAnalytics.Param.VALUE, 9.99)
        refundParams.putString(FirebaseAnalytics.Param.ITEM_ID, "SKU_123")
        refundParams.putLong(FirebaseAnalytics.Param.QUANTITY, 1)
        refundParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, arrayOf<Parcelable>(itemJeggings))
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.REFUND, refundParams)

        //상품 프로모션
        val promoParams = Bundle()
        promoParams.putString(FirebaseAnalytics.Param.PROMOTION_ID, "SUMMER_FUN")
        promoParams.putString(FirebaseAnalytics.Param.PROMOTION_NAME, "Summer Sale")
        promoParams.putString(FirebaseAnalytics.Param.CREATIVE_NAME, "summer2020_promo.jpg")
        promoParams.putString(FirebaseAnalytics.Param.CREATIVE_SLOT, "featured_app_1")
        promoParams.putString(FirebaseAnalytics.Param.LOCATION_ID, "HERO_BANNER")
        promoParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, arrayOf<Parcelable>(itemJeggings))
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.VIEW_PROMOTION, promoParams)
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SELECT_PROMOTION, promoParams)
*/
    }

    //수동화면추적
    // Override 를 통해 정의되어 있는 onResume 을 사용해야만 이 메소드가 동작한다.
    /*
    override fun onResume() {
        super.onResume()
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        //수동화면추적
        //SCREEN_VIEW 이벤트를 수동으로 로깅할 수 있습니다.
        val bundle = Bundle() //Bundle 객체 생성
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, a + "mm") //보고서-페이지 제목 및 화면 이름 or 이벤트-firebase_screen
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, b + "mm") //보고서-이벤트-screen_view-firebase_screen_class
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle) //logEvent(이벤트 명, bundle)
        //더 이상 지원 안 되는 서비스3
        //mFirebaseAnalytics.setCurrentScreen(this, "FinalActivity", null ); //보고서-페이지 제목 및 화면 이름
    }
*/
    //웹앱 인터페이스
    class WebAppInterface : Activity() {
        private var mFirebaseAnalytics: FirebaseAnalytics? = null
        @JavascriptInterface
        @Throws(JSONException::class)
        fun GA_DATA(JsonData: String?) { //Webview 내에서 자바스크립트 코드내에서 불러올 클래
            val data = JSONObject(JsonData)
            var sType = ""
            var title: String? = "Hybrid_AOSmyshin3_"
            var en = "FirebaseAnalytics.Event."
            if (data.has("event_name")) en += data.getString("event_name").toUpperCase()
            if (data.has("type")) sType = data.getString("type")
            if (data.has("title")) title += data.getString("title")
            if (sType.contains("P")) { //스크린뷰 일때
                mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
                val bundle = Bundle() //Bundle 객체 생성
                bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "hybrid_k") //보고서-페이지 제목 및 화면 이름 or 이벤트-firebase_screen
                bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "hybrid_k") //보고서-이벤트-screen_view-firebase_screen_class
                mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle) //logEvent(이벤트 명, bundle)
            }
            if (sType.contains("E")) { //이벤트일 때
                val viewItemListParams = Bundle()
                val bArr = arrayOfNulls<Bundle>(data.length())
                var acnt = 0
                for (i in 0 until data.length()) {
                    val items = Bundle()
                    var item: JSONObject? = null
                    if (data.has(i.toString())) item = data.getJSONObject(i.toString())
                    if (item == null) {
                        break
                    }
                    if (item.has("item_id")) items.putString(FirebaseAnalytics.Param.ITEM_ID, item.getString("item_id"))
                    if (item.has("item_name")) items.putString(FirebaseAnalytics.Param.ITEM_NAME, item.getString("item_name"))
                    if (item.has("item_list_name")) items.putString(FirebaseAnalytics.Param.ITEM_LIST_NAME, item.getString("item_list_name"))
                    if (item.has("item_list_id")) items.putString(FirebaseAnalytics.Param.ITEM_LIST_ID, item.getString("item_list_id"))
                    if (item.has("index")) items.putString(FirebaseAnalytics.Param.INDEX, item.getString("index"))
                    if (item.has("item_brand")) items.putString(FirebaseAnalytics.Param.ITEM_BRAND, item.getString("item_brand"))
                    if (item.has("item_category")) items.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, item.getString("item_category"))
                    if (item.has("item_category2")) items.putString(FirebaseAnalytics.Param.ITEM_CATEGORY2, item.getString("item_category2"))
                    if (item.has("item_category3")) items.putString(FirebaseAnalytics.Param.ITEM_CATEGORY3, item.getString("item_category3"))
                    if (item.has("item_category4")) items.putString(FirebaseAnalytics.Param.ITEM_CATEGORY4, item.getString("item_category4"))
                    if (item.has("item_category5")) items.putString(FirebaseAnalytics.Param.ITEM_CATEGORY5, item.getString("item_category5"))
                    if (item.has("item_variant")) items.putString(FirebaseAnalytics.Param.ITEM_VARIANT, item.getString("item_variant"))
                    if (item.has("affiliation")) items.putString(FirebaseAnalytics.Param.AFFILIATION, item.getString("affiliation"))
                    if (item.has("discount")) items.putString(FirebaseAnalytics.Param.DISCOUNT, item.getString("discount"))
                    if (item.has("coupon")) items.putString(FirebaseAnalytics.Param.COUPON, item.getString("coupon"))
                    if (item.has("price")) items.putString(FirebaseAnalytics.Param.PRICE, item.getString("price"))
                    if (item.has("currency")) items.putString(FirebaseAnalytics.Param.CURRENCY, item.getString("currency"))
                    if (item.has("quantity")) items.putString(FirebaseAnalytics.Param.QUANTITY, item.getString("quantity"))
                    if (item.has("item_id")) { //type 등 모두 포함한 상태의 data에서 상품만 선택
                        items.putLong(FirebaseAnalytics.Param.INDEX, (i + 1).toLong())
                        bArr[i] = items
                        acnt++
                    }
                }
                val newbArr = arrayOfNulls<Bundle>(acnt)
                for (j in newbArr.indices) {
                    if (!bArr[j]!!.isEmpty || bArr[j] != null) {
                        newbArr[j] = bArr[j] //newbArr 이 상품 bundle
                    }
                }
                viewItemListParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, newbArr)
                viewItemListParams.putString(FirebaseAnalytics.Param.ITEM_LIST_ID, "qsw")
                viewItemListParams.putString(FirebaseAnalytics.Param.ITEM_LIST_NAME, "qsw")
                mFirebaseAnalytics!!.logEvent("view_item_list", viewItemListParams)
            }
        }
    }
    companion object {
        var user_pseudo_id = arrayOfNulls<String>(1)
        var a = "qqa"
        var b = "qqa"
    }
}