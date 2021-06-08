package com.example.kotlinbuilder

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.RemoteException
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.android.installreferrer.api.ReferrerDetails
import com.google.android.gms.analytics.Tracker
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.util.*


class MainActivity : AppCompatActivity() {
    private val sTracker: Tracker? = null
    lateinit var referrerClient: InstallReferrerClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imp = findViewById<View>(R.id.imp) as Button
        val wb = findViewById<View>(R.id.wb) as Button
        val checkout1 = findViewById<View>(R.id.checkout1) as Button
        val checkout2 = findViewById<View>(R.id.checkout2) as Button
        val click = findViewById<View>(R.id.click) as Button
        val detail = findViewById<View>(R.id.detail) as Button
        val cartadd = findViewById<View>(R.id.cartadd) as Button
        val cartdelete = findViewById<View>(R.id.cartdelete) as Button
        val purchase = findViewById<View>(R.id.purchase) as Button
        val refund = findViewById<View>(R.id.refund) as Button
        val promotion_click = findViewById<View>(R.id.promotion_click) as Button
        val promotion_imp = findViewById<View>(R.id.promotion_imp) as Button
        val gb = GoogleAnalyticsBuilder(this)
/*
        val screen_Map: MutableMap<String, String?> = HashMap()
        screen_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension1] = "맞춤측정 기준1 값"
        screen_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = "맞춤측정 기준2 값"
        screen_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = "맞춤측정 기준3 값"
        screen_Map[GoogleAnalyticsBuilder.GAHitKey.Title] = "sh 스크린"
        screen_Map[GoogleAnalyticsBuilder.GAHitKey.UserID] = "사용자 ID"


        gb.GADataSend_Screen(screen_Map)

        val event_Map: MutableMap<String, String> = HashMap()
        event_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension1] = "맞춤측정 기준1 값"
        event_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = "맞춤측정 기준2 값"
        event_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = "맞춤측정 기준3 값"
        event_Map[GoogleAnalyticsBuilder.GAHitKey.Title] = "sh 이벤트"
        event_Map[GoogleAnalyticsBuilder.GAHitKey.UserID] = "사용자 ID"
        event_Map[GoogleAnalyticsBuilder.GAHitKey.EventCategory] = "test category"
        event_Map[GoogleAnalyticsBuilder.GAHitKey.EventAction] = "test action"
        event_Map[GoogleAnalyticsBuilder.GAHitKey.EventLabel] = "test label"

        gb.GADataSend_Event(event_Map)
*/
        wb.setOnClickListener {
            val intent = Intent(this@MainActivity, Main2Activity::class.java)
            startActivity(intent)
        }
        // 앱링크 테스트

        referrerClient = InstallReferrerClient.newBuilder(this).build()
        referrerClient.startConnection(object : InstallReferrerStateListener {

            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                when (responseCode) {
                    InstallReferrerClient.InstallReferrerResponse.OK -> {
                        if(CheckAppFirstExecute()){
                            getReferral()
                        }
                    }
                    InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED -> {
                        // API not available on the current Play Store app.
                    }
                    InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE -> {
                        // Connection couldn't be established.
                    }
                }
            }

            override fun onInstallReferrerServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })
        // 딥링크 테스트

        val data = this.intent.data
        var url: String? = ""
        if (data != null && data.isHierarchical) {
            url = this.intent.dataString
        }
        if (url !== "" && url!!.contains("utm_source")) {
            val DeepLink_screen_Map: MutableMap<String, String> = HashMap()
            
            val cs = data?.getQueryParameter("utm_source")
            val cm = data?.getQueryParameter("utm_medium")
            val ck = data?.getQueryParameter("utm_term")
            val cc = data?.getQueryParameter("utm_content")
            val cn = data?.getQueryParameter("utm_campaign")
            if (!cs.isNullOrEmpty()) DeepLink_screen_Map.put(
                GoogleAnalyticsBuilder.GAUtmKey.UtmSource,
                cs.toString()
            )
            if (!cm.isNullOrEmpty()) DeepLink_screen_Map.put(
                GoogleAnalyticsBuilder.GAUtmKey.UtmMedium,
                cm.toString()
            )
            if (!ck.isNullOrEmpty()) DeepLink_screen_Map.put(
                GoogleAnalyticsBuilder.GAUtmKey.UtmTerm,
                ck.toString()
            )
            if (!cc.isNullOrEmpty()) DeepLink_screen_Map.put(
                GoogleAnalyticsBuilder.GAUtmKey.UtmContent,
                cc.toString()
            )
            if (!cn.isNullOrEmpty()) DeepLink_screen_Map.put(
                GoogleAnalyticsBuilder.GAUtmKey.UtmCampaign,
                cn.toString()
            )
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension1] = "cam맞춤측정기준 1값"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = "cam맞춤측정기준 2값"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = "cam맞춤측정기준 3값"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.UserID] = "cam 사용자ID"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.Title] = "캠페인 이벤트 타이틀2"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.CampaignUrl] = url
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.EventCategory] = "캠페인 카테고리2"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.EventAction] = "캠페인 액션2"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.EventLabel] = "캠페인 라벨2"

            gb.GADataSend_Event(DeepLink_screen_Map)
        }

        /*
        if (url !== "" && url!!.contains("utm_source")) {

            val DeepLink_screen_Map: MutableMap<String, String?> = HashMap()
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension1] = "cam맞춤측정기준 1값"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = "cam맞춤측정기준 2값"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = "cam맞춤측정기준 3값"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.UserID] = "cam 사용자ID"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.Title] = "캠페인 타이틀"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.CampaignUrl] = url
            gb.GADataSend_Screen(DeepLink_screen_Map)

            val DeepLink_screen_Map: MutableMap<String, String> = HashMap()
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension1] = "cam맞춤측정기준 1값"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = "cam맞춤측정기준 2값"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = "cam맞춤측정기준 3값"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.UserID] = "cam 사용자ID"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.Title] = "캠페인 이벤트 타이틀"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.CampaignUrl] = url
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.EventCategory] = "캠페인 카테고리"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.EventAction] = "캠페인 액션"
            DeepLink_screen_Map[GoogleAnalyticsBuilder.GAHitKey.EventLabel] = "캠페인 라벨"
            gb.GADataSend_Event(DeepLink_screen_Map)

        }
        */
        promotion_imp.setOnClickListener {
            val promotion_Map_imp: MutableMap<String, String> =
                HashMap()
            promotion_Map_imp[GoogleAnalyticsBuilder.GACustomKey.Dimension1] = "promotion맞춤측정기준 1값"
            promotion_Map_imp[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = "promotion맞춤측정기준 2값"
            promotion_Map_imp[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = "promotion맞춤측정기준 3값"
            promotion_Map_imp[GoogleAnalyticsBuilder.GAHitKey.UserID] = "promotion 사용자ID"
            promotion_Map_imp[GoogleAnalyticsBuilder.GAHitKey.Title] = "promotion 타이틀"
            promotion_Map_imp[GoogleAnalyticsBuilder.GAHitKey.PromotionID] = "promotion ID"
            promotion_Map_imp[GoogleAnalyticsBuilder.GAHitKey.PromotionName] = "promotion Name"
            promotion_Map_imp[GoogleAnalyticsBuilder.GAHitKey.PromotionCreative] = "promotion 소재"
            promotion_Map_imp[GoogleAnalyticsBuilder.GAHitKey.PromotionPosition] = "promotion 위치"
            promotion_Map_imp[GoogleAnalyticsBuilder.GAHitKey.EventCategory] = "Ecommerce"
            promotion_Map_imp[GoogleAnalyticsBuilder.GAHitKey.EventAction] = "Promotion_Impression"
            gb.GADataSend_Ecommerce(
                GoogleAnalyticsBuilder.GAEcommerceStepKey.PromotionImpression,
                null,
                null,
                promotion_Map_imp
            )
        }
        promotion_click.setOnClickListener {
            val promotion_Map_click: MutableMap<String, String> =
                HashMap()
            promotion_Map_click[GoogleAnalyticsBuilder.GACustomKey.Dimension1] =
                "promotion2맞춤측정기준 1값"
            promotion_Map_click[GoogleAnalyticsBuilder.GACustomKey.Dimension2] =
                "promotion2맞춤측정기준 2값"
            promotion_Map_click[GoogleAnalyticsBuilder.GACustomKey.Dimension3] =
                "promotion2맞춤측정기준 3값"
            promotion_Map_click[GoogleAnalyticsBuilder.GAHitKey.UserID] = "promotion 사용자ID2"
            promotion_Map_click[GoogleAnalyticsBuilder.GAHitKey.Title] = "promotion 타이틀2"
            promotion_Map_click[GoogleAnalyticsBuilder.GAHitKey.PromotionID] = "promotion ID2"
            promotion_Map_click[GoogleAnalyticsBuilder.GAHitKey.PromotionName] = "promotion Name2"
            promotion_Map_click[GoogleAnalyticsBuilder.GAHitKey.PromotionCreative] = "promotion 소재2"
            promotion_Map_click[GoogleAnalyticsBuilder.GAHitKey.PromotionPosition] = "promotion 위치2"
            promotion_Map_click[GoogleAnalyticsBuilder.GAHitKey.EventCategory] = "Ecommerce"
            promotion_Map_click[GoogleAnalyticsBuilder.GAHitKey.EventAction] = "Promotion_Click"
            gb.GADataSend_Ecommerce(
                GoogleAnalyticsBuilder.GAEcommerceStepKey.PromotionClick,
                null,
                null,
                promotion_Map_click
            )
        }
        imp.setOnClickListener {
            val action_Map: MutableMap<String, String> =
                HashMap()
            val ecommerce_Map_imp: MutableMap<String, String> =
                HashMap()
            val product_Map: MutableMap<String, Map<String, String>> =
                HashMap()
            for (i in 0..1) {
                val map: MutableMap<String, String> =
                    HashMap()
                map[GoogleAnalyticsBuilder.GAProductKey.ProductID] = "9041" + (i + 1)
                map[GoogleAnalyticsBuilder.GAProductKey.ProductName] = "이어폰" + (i + 1)
                product_Map["pr" + (i + 1)] = map
            }
            ecommerce_Map_imp[GoogleAnalyticsBuilder.GACustomKey.Dimension1] =
                "7fd-ec42-492a-92df-c62cf"
            ecommerce_Map_imp[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = "Y"
            ecommerce_Map_imp[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = "M"
            ecommerce_Map_imp[GoogleAnalyticsBuilder.GAHitKey.UserID] = "1920931201"
            ecommerce_Map_imp[GoogleAnalyticsBuilder.GAHitKey.Title] = "ecommerce 화면명"
            ecommerce_Map_imp[GoogleAnalyticsBuilder.GAHitKey.EventCategory] = "Ecommerce"
            ecommerce_Map_imp[GoogleAnalyticsBuilder.GAHitKey.EventAction] = "Impression"
            action_Map[GoogleAnalyticsBuilder.GAActionFieldKey.ProductActionList] =
                "제품 리스트 명" // 제품 리스트 명
            gb.GADataSend_Ecommerce(
                GoogleAnalyticsBuilder.GAEcommerceStepKey.Impression,
                action_Map,
                product_Map,
                ecommerce_Map_imp
            )
        }
        click.setOnClickListener {
            val action_Map: MutableMap<String, String> =
                HashMap()
            val ecommerce_click: MutableMap<String, String> =
                HashMap()
            val product_Map: MutableMap<String, Map<String, String>> =
                HashMap()
            for (i in 0..1) {
                val map: MutableMap<String, String> =
                    HashMap()
                map[GoogleAnalyticsBuilder.GAProductKey.ProductID] = "9041" + (i + 1)
                map[GoogleAnalyticsBuilder.GAProductKey.ProductName] = "이어폰" + (i + 1)
                product_Map["pr" + (i + 1)] = map
            }
            ecommerce_click[GoogleAnalyticsBuilder.GACustomKey.Dimension1] =
                "7fd-ec42-492a-92df-c62cf"
            ecommerce_click[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = "Y"
            ecommerce_click[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = "M"
            ecommerce_click[GoogleAnalyticsBuilder.GAHitKey.UserID] = "1920931201"
            ecommerce_click[GoogleAnalyticsBuilder.GAHitKey.Title] = "ecommerce 화면명"
            ecommerce_click[GoogleAnalyticsBuilder.GAHitKey.EventCategory] = "Ecommerce"
            ecommerce_click[GoogleAnalyticsBuilder.GAHitKey.EventAction] = "Click"
            action_Map[GoogleAnalyticsBuilder.GAActionFieldKey.ProductActionList] =
                "제품 리스트 명" // 제품 리스트 명
            gb.GADataSend_Ecommerce(
                GoogleAnalyticsBuilder.GAEcommerceStepKey.Click,
                action_Map,
                product_Map,
                ecommerce_click
            )
        }

        detail.setOnClickListener {
            val action_Map: Map<String, String> =
                HashMap()
            val ecommerce_detail: MutableMap<String, String> =
                HashMap()
            val product_Map: MutableMap<String, Map<String, String>> =
                HashMap()
            for (i in 0..1) {
                val map: MutableMap<String, String> =
                    HashMap()
                map[GoogleAnalyticsBuilder.GAProductKey.ProductID] = "9041" + (i + 1)
                map[GoogleAnalyticsBuilder.GAProductKey.ProductName] = "이어폰" + (i + 1)
                product_Map["pr" + (i + 1)] = map
            }
            ecommerce_detail[GoogleAnalyticsBuilder.GACustomKey.Dimension1] =
                "7fd-ec42-492a-92df-c62cf"
            ecommerce_detail[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = "Y"
            ecommerce_detail[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = "M"
            ecommerce_detail[GoogleAnalyticsBuilder.GAHitKey.UserID] = "1920931201"
            ecommerce_detail[GoogleAnalyticsBuilder.GAHitKey.Title] = "ecommerce 화면명"
            ecommerce_detail[GoogleAnalyticsBuilder.GAHitKey.EventCategory] = "Ecommerce"
            ecommerce_detail[GoogleAnalyticsBuilder.GAHitKey.EventAction] = "Detail"
            gb.GADataSend_Ecommerce(
                GoogleAnalyticsBuilder.GAEcommerceStepKey.Detail,
                action_Map,
                product_Map,
                ecommerce_detail
            )
        }
        cartadd.setOnClickListener {
            val action_Map: MutableMap<String, String> =
                HashMap()
            val ecommerce_cartadd: MutableMap<String, String> =
                HashMap()
            val product_Map: MutableMap<String, Map<String, String>> =
                HashMap()
            for (i in 0..1) {
                val map: MutableMap<String, String> =
                    HashMap()
                map[GoogleAnalyticsBuilder.GAProductKey.ProductID] = "9041" + (i + 1)
                map[GoogleAnalyticsBuilder.GAProductKey.ProductName] = "이어폰" + (i + 1)
                product_Map["pr" + (i + 1)] = map
            }
            ecommerce_cartadd[GoogleAnalyticsBuilder.GACustomKey.Dimension1] =
                "7fd-ec42-492a-92df-c62cf"
            ecommerce_cartadd[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = "Y"
            ecommerce_cartadd[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = "M"
            ecommerce_cartadd[GoogleAnalyticsBuilder.GAHitKey.UserID] = "1920931201"
            ecommerce_cartadd[GoogleAnalyticsBuilder.GAHitKey.Title] = "ecommerce 화면명"
            ecommerce_cartadd[GoogleAnalyticsBuilder.GAHitKey.EventCategory] = "Ecommerce"
            ecommerce_cartadd[GoogleAnalyticsBuilder.GAHitKey.EventAction] = "Cartadd"
            action_Map[GoogleAnalyticsBuilder.GAActionFieldKey.ProductActionList] =
                "제품 리스트 명" // 제품 리스트 명
            gb.GADataSend_Ecommerce(
                GoogleAnalyticsBuilder.GAEcommerceStepKey.Add,
                action_Map,
                product_Map,
                ecommerce_cartadd
            )
        }
        cartdelete.setOnClickListener {
            val action_Map: Map<String, String> =
                HashMap()
            val ecommerce_cartdelete: MutableMap<String, String> =
                HashMap()
            val product_Map: MutableMap<String, Map<String, String>> =
                HashMap()
            for (i in 0..1) {
                val map: MutableMap<String, String> =
                    HashMap()
                map[GoogleAnalyticsBuilder.GAProductKey.ProductID] = "9041" + (i + 1)
                map[GoogleAnalyticsBuilder.GAProductKey.ProductName] = "이어폰" + (i + 1)
                product_Map["pr" + (i + 1)] = map
            }
            ecommerce_cartdelete[GoogleAnalyticsBuilder.GACustomKey.Dimension1] =
                "7fd-ec42-492a-92df-c62cf"
            ecommerce_cartdelete[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = "Y"
            ecommerce_cartdelete[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = "M"
            ecommerce_cartdelete[GoogleAnalyticsBuilder.GAHitKey.UserID] = "1920931201"
            ecommerce_cartdelete[GoogleAnalyticsBuilder.GAHitKey.Title] = "ecommerce 화면명"
            ecommerce_cartdelete[GoogleAnalyticsBuilder.GAHitKey.EventCategory] = "Ecommerce"
            ecommerce_cartdelete[GoogleAnalyticsBuilder.GAHitKey.EventAction] = "Cartdelete"
            gb.GADataSend_Ecommerce(
                GoogleAnalyticsBuilder.GAEcommerceStepKey.Remove,
                action_Map,
                product_Map,
                ecommerce_cartdelete
            )
        }
        checkout1.setOnClickListener {
            val action_Map: Map<String, String> =
                HashMap()
            val ecommerce_checkout1: MutableMap<String, String> =
                HashMap()
            val product_Map: MutableMap<String, Map<String, String>> =
                HashMap()
            for (i in 0..1) {
                val map: MutableMap<String, String> =
                    HashMap()
                map[GoogleAnalyticsBuilder.GAProductKey.ProductID] = "9041" + (i + 1)
                map[GoogleAnalyticsBuilder.GAProductKey.ProductName] = "이어폰" + (i + 1)
                product_Map["pr" + (i + 1)] = map
            }
            ecommerce_checkout1[GoogleAnalyticsBuilder.GACustomKey.Dimension1] =
                "7fd-ec42-492a-92df-c62cf"
            ecommerce_checkout1[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = "Y"
            ecommerce_checkout1[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = "M"
            ecommerce_checkout1[GoogleAnalyticsBuilder.GAHitKey.UserID] = "1920931201"
            ecommerce_checkout1[GoogleAnalyticsBuilder.GAHitKey.Title] = "ecommerce 화면명"
            ecommerce_checkout1[GoogleAnalyticsBuilder.GAHitKey.EventCategory] = "Ecommerce"
            ecommerce_checkout1[GoogleAnalyticsBuilder.GAHitKey.EventAction] =
                "Checkout1" // 제품 리스트 명
            ecommerce_checkout1[GoogleAnalyticsBuilder.GAActionFieldKey.CheckoutStep] = "1"
            gb.GADataSend_Ecommerce(
                GoogleAnalyticsBuilder.GAEcommerceStepKey.Checkout,
                action_Map,
                product_Map,
                ecommerce_checkout1
            )
        }
        checkout2.setOnClickListener {
            val action_Map: Map<String, String> =
                HashMap()
            val ecommerce_checkout2: MutableMap<String, String> =
                HashMap()
            val product_Map: MutableMap<String, Map<String, String>> =
                HashMap()
            for (i in 0..1) {
                val map: MutableMap<String, String> =
                    HashMap()
                map[GoogleAnalyticsBuilder.GAProductKey.ProductID] = "9041" + (i + 1)
                map[GoogleAnalyticsBuilder.GAProductKey.ProductName] = "이어폰" + (i + 1)
                product_Map["pr" + (i + 1)] = map
            }
            ecommerce_checkout2[GoogleAnalyticsBuilder.GACustomKey.Dimension1] =
                "7fd-ec42-492a-92df-c62cf"
            ecommerce_checkout2[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = "Y"
            ecommerce_checkout2[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = "M"
            ecommerce_checkout2[GoogleAnalyticsBuilder.GAHitKey.UserID] = "1920931201"
            ecommerce_checkout2[GoogleAnalyticsBuilder.GAHitKey.Title] = "ecommerce 화면명"
            ecommerce_checkout2[GoogleAnalyticsBuilder.GAHitKey.EventCategory] = "Ecommerce"
            ecommerce_checkout2[GoogleAnalyticsBuilder.GAHitKey.EventAction] =
                "Checkout2" // 제품 리스트 명
            ecommerce_checkout2[GoogleAnalyticsBuilder.GAActionFieldKey.CheckoutStep] = "2"
            ecommerce_checkout2[GoogleAnalyticsBuilder.GAActionFieldKey.CheckoutOptions] = "신한카드"
            gb.GADataSend_Ecommerce(
                GoogleAnalyticsBuilder.GAEcommerceStepKey.Checkout,
                action_Map,
                product_Map,
                ecommerce_checkout2
            )
        }
        purchase.setOnClickListener {
            val action_Map: MutableMap<String, String> =
                HashMap()
            val ecommerce_purchase: MutableMap<String, String> =
                HashMap()
            val product_Map: MutableMap<String, Map<String, String>> =
                HashMap()
            for (i in 0..1) {
                val map: MutableMap<String, String> =
                    HashMap()
                map[GoogleAnalyticsBuilder.GAProductKey.ProductID] = "9041" + (i + 1)
                map[GoogleAnalyticsBuilder.GAProductKey.ProductName] = "이어폰" + (i + 1)
                product_Map["pr" + (i + 1)] = map
            }
            ecommerce_purchase[GoogleAnalyticsBuilder.GACustomKey.Dimension1] =
                "7fd-ec42-492a-92df-c62cf"
            ecommerce_purchase[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = "Y"
            ecommerce_purchase[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = "M"
            ecommerce_purchase[GoogleAnalyticsBuilder.GAHitKey.UserID] = "1920931201"
            ecommerce_purchase[GoogleAnalyticsBuilder.GAHitKey.Title] = "ecommerce 화면명"
            ecommerce_purchase[GoogleAnalyticsBuilder.GAHitKey.EventCategory] = "Ecommerce"
            ecommerce_purchase[GoogleAnalyticsBuilder.GAHitKey.EventAction] = "Purchase" // 제품 리스트 명
            ecommerce_purchase[GoogleAnalyticsBuilder.GAActionFieldKey.CurrencyCode] = "KRW" //통화 코드
            action_Map[GoogleAnalyticsBuilder.GAActionFieldKey.TransactionID] =
                "900804647636" //거래ID
            action_Map[GoogleAnalyticsBuilder.GAActionFieldKey.TransactionRevenue] =
                "15000" //거래 총 수익
            action_Map[GoogleAnalyticsBuilder.GAActionFieldKey.TransactionTax] = "1500" //세금
            action_Map[GoogleAnalyticsBuilder.GAActionFieldKey.TransactionShipping] = "2500" //배송비
            action_Map[GoogleAnalyticsBuilder.GAActionFieldKey.TransactionCouponCode] =
                "abc6921" //거래쿠폰코드
            action_Map[GoogleAnalyticsBuilder.GAActionFieldKey.TransactionAffiliation] =
                "affcompany" //제휴사
            gb.GADataSend_Ecommerce(
                GoogleAnalyticsBuilder.GAEcommerceStepKey.Purchase,
                action_Map,
                product_Map,
                ecommerce_purchase
            )
        }
        refund.setOnClickListener {
            val action_Map: MutableMap<String, String> =
                HashMap()
            val ecommerce_refund: MutableMap<String, String> =
                HashMap()
            val product_Map: MutableMap<String, Map<String, String>> =
                HashMap()
            for (i in 0..1) {
                val map: MutableMap<String, String> =
                    HashMap()
                map[GoogleAnalyticsBuilder.GAProductKey.ProductID] = "9041" + (i + 1)
                map[GoogleAnalyticsBuilder.GAProductKey.ProductName] = "이어폰" + (i + 1)
                product_Map["pr" + (i + 1)] = map
            }
            ecommerce_refund[GoogleAnalyticsBuilder.GACustomKey.Dimension1] =
                "7fd-ec42-492a-92df-c62cf"
            ecommerce_refund[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = "Y"
            ecommerce_refund[GoogleAnalyticsBuilder.GACustomKey.Dimension3] = "M"
            ecommerce_refund[GoogleAnalyticsBuilder.GAHitKey.UserID] = "1920931201"
            ecommerce_refund[GoogleAnalyticsBuilder.GAHitKey.Title] = "ecommerce 화면명"
            ecommerce_refund[GoogleAnalyticsBuilder.GAHitKey.EventCategory] = "Ecommerce"
            ecommerce_refund[GoogleAnalyticsBuilder.GAHitKey.EventAction] = "Refund"
            action_Map[GoogleAnalyticsBuilder.GAActionFieldKey.TransactionID] = "900804647636"
            gb.GADataSend_Ecommerce(
                GoogleAnalyticsBuilder.GAEcommerceStepKey.Refund,
                action_Map,
                product_Map,
                ecommerce_refund
            )
        }
    }



    fun CheckAppFirstExecute(): Boolean {
        val pref = getSharedPreferences("isFirst", MODE_PRIVATE)
        val isFirst = pref.getBoolean("isFirst", false)
        if (!isFirst) {
            val editor = pref.edit()
            editor.putBoolean("isFirst", true)
            editor.commit()
        }
        return !isFirst
    }

    //@Throws(RemoteException::class)
    private fun getReferral() {
        val response: ReferrerDetails = referrerClient.getInstallReferrer()
        val referrerData = response.installReferrer
        try {
            splitQuery(Uri.parse(referrerData))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        referrerClient.endConnection()
    }

    //@Throws(UnsupportedEncodingException::class)
    fun splitQuery(uri: Uri) {
        val query = uri.toString()
        var i = 1
        var url = "gadata://?"
        if (query != null) {
            val pairs = query.split("&".toRegex()).toTypedArray()
            for (pair in pairs) {
                val idx = pair.indexOf("=")
                if (pair.contains("utm_source")) url = url + "utm_source=" + URLDecoder.decode(
                    pair.substring(idx + 1),
                    "UTF-8"
                ) else if (pair.contains("utm_medium")) url =
                    url + "utm_medium=" + URLDecoder.decode(
                        pair.substring(idx + 1),
                        "UTF-8"
                    ) else if (pair.contains("utm_term")) url =
                    url + "utm_term=" + URLDecoder.decode(
                        pair.substring(idx + 1),
                        "UTF-8"
                    ) else if (pair.contains("utm_content")) url =
                    url + "utm_content=" + URLDecoder.decode(
                        pair.substring(idx + 1),
                        "UTF-8"
                    ) else if (pair.contains("utm_campaign")) url =
                    url + "utm_campaign=" + URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                if (i != pairs.size) {
                    url += "&"
                    i++
                }
            }
        }
        val appInstallMap: MutableMap<String, String?> = HashMap()
        appInstallMap[GoogleAnalyticsBuilder.GACustomKey.Dimension1] = "맞춤 측정 기준1 값"
        appInstallMap[GoogleAnalyticsBuilder.GACustomKey.Dimension2] = "맞춤 측정 기준2 값"
        appInstallMap[GoogleAnalyticsBuilder.GAHitKey.UserID] = "사용자 ID"
        appInstallMap[GoogleAnalyticsBuilder.GAHitKey.Title] = "화면 명"
        appInstallMap[GoogleAnalyticsBuilder.GAHitKey.CampaignUrl] = url
        val gb = GoogleAnalyticsBuilder(this)
        gb.GADataSend_Screen(appInstallMap)
    }

}
