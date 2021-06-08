package com.example.gatest12;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;

import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static String[]user_pseudo_id= new String[1];
    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sAnalytics = GoogleAnalytics.getInstance(this);

        Button imp = (Button)findViewById(R.id.imp);
        Button wb = (Button)findViewById(R.id.wb);
        Button checkout1 = (Button)findViewById(R.id.checkout1);
        Button checkout2 = (Button)findViewById(R.id.checkout2);
        Button click = (Button)findViewById(R.id.click);
        Button detail = (Button)findViewById(R.id.detail);
        Button cartadd = (Button)findViewById(R.id.cartadd);
        Button cartdelete = (Button)findViewById(R.id.cartdelete);
        Button purchase = (Button)findViewById(R.id.purchase);
        Button refund = (Button)findViewById(R.id.refund);
        Button promotion_click = (Button)findViewById(R.id.promotion_click);
        Button promotion_imp = (Button)findViewById(R.id.promotion_imp);

        Map<String,String> screen_Map = new HashMap<String,String>();
        screen_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1,"맞춤측정 기준1 값");
        screen_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2,"맞춤측정 기준2 값");
        screen_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3,"맞춤측정 기준3 값");
        screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.Title,"sh 스크린");
        screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.UserID,"사용자 ID");

        final GoogleAnalyticsBuilder gb = new GoogleAnalyticsBuilder(this);

        try{
            int a=1;
            int b=a/0;
            gb.GADataSend_Screen(screen_Map);

        }catch (Exception e) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
            sTracker.send(new HitBuilders.ExceptionBuilder().setDescription(new StandardExceptionParser(this, null).getDescription(Thread.currentThread().getName(), e)).setFatal(false).build());
            //moveTaskToBack(true);						// 태스크를 백그라운드로 이동
            //finishAndRemoveTask();						// 액티비티 종료 + 태스크 리스트에서 지우기
            //android.os.Process.killProcess(android.os.Process.myPid());
        }

        Map<String,String> event_Map = new HashMap<String,String>();
        event_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1,"맞춤측정 기준1 값");
        event_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2,"맞춤측정 기준2 값");
        event_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3,"맞춤측정 기준3 값");
        event_Map.put(GoogleAnalyticsBuilder.GAHitKey.Title,"sh 이벤트");
        event_Map.put(GoogleAnalyticsBuilder.GAHitKey.UserID,"사용자 ID");
        event_Map.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory,"test category");
        event_Map.put(GoogleAnalyticsBuilder.GAHitKey.EventAction,"test action");
        event_Map.put(GoogleAnalyticsBuilder.GAHitKey.EventLabel,"test label");

        gb.GADataSend_Event(event_Map);

        wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        // 딥링크 테스트
        Uri data = this.getIntent().getData();
        String url = "";
        if(data!=null && data.isHierarchical()){
            url = this.getIntent().getDataString();
        }
        if(url!="" && url.contains("utm_source")){
            Map<String,String>DeepLink_screen_Map = new HashMap<String,String>();
            DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1,"cam맞춤측정기준 1값");
            DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2,"cam맞춤측정기준 2값");
            DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3,"cam맞춤측정기준 3값");

            DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.UserID,"cam 사용자ID");
            DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.Title,"캠페인 타이틀");
            DeepLink_screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.CampaignUrl,url);

            gb.GADataSend_Screen(DeepLink_screen_Map);
        }
        promotion_imp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> promotion_Map_imp = new HashMap<String,String>();
                promotion_Map_imp.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1,"promotion맞춤측정기준 1값");
                promotion_Map_imp.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2,"promotion맞춤측정기준 2값");
                promotion_Map_imp.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3,"promotion맞춤측정기준 3값");

                promotion_Map_imp.put(GoogleAnalyticsBuilder.GAHitKey.UserID,"promotion 사용자ID");
                promotion_Map_imp.put(GoogleAnalyticsBuilder.GAHitKey.Title,"promotion 타이틀");
                promotion_Map_imp.put(GoogleAnalyticsBuilder.GAHitKey.PromotionID,"promotion ID");
                promotion_Map_imp.put(GoogleAnalyticsBuilder.GAHitKey.PromotionName,"promotion Name");
                promotion_Map_imp.put(GoogleAnalyticsBuilder.GAHitKey.PromotionCreative,"promotion 소재");
                promotion_Map_imp.put(GoogleAnalyticsBuilder.GAHitKey.PromotionPosition,"promotion 위치");
                promotion_Map_imp.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory,"Ecommerce");
                promotion_Map_imp.put(GoogleAnalyticsBuilder.GAHitKey.EventAction,"Promotion_Impression");

                gb.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.PromotionImpression,null,null,promotion_Map_imp);
            }
        });

        promotion_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> promotion_Map_click = new HashMap<String,String>();
                promotion_Map_click.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1,"promotion2맞춤측정기준 1값");
                promotion_Map_click.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2,"promotion2맞춤측정기준 2값");
                promotion_Map_click.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3,"promotion2맞춤측정기준 3값");

                promotion_Map_click.put(GoogleAnalyticsBuilder.GAHitKey.UserID,"promotion 사용자ID2");
                promotion_Map_click.put(GoogleAnalyticsBuilder.GAHitKey.Title,"promotion 타이틀2");
                promotion_Map_click.put(GoogleAnalyticsBuilder.GAHitKey.PromotionID,"promotion ID2");
                promotion_Map_click.put(GoogleAnalyticsBuilder.GAHitKey.PromotionName,"promotion Name2");
                promotion_Map_click.put(GoogleAnalyticsBuilder.GAHitKey.PromotionCreative,"promotion 소재2");
                promotion_Map_click.put(GoogleAnalyticsBuilder.GAHitKey.PromotionPosition,"promotion 위치2");
                promotion_Map_click.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory,"Ecommerce");
                promotion_Map_click.put(GoogleAnalyticsBuilder.GAHitKey.EventAction,"Promotion_Click");
                gb.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.PromotionClick, null,null, promotion_Map_click);

            }});
        imp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> action_Map = new HashMap<String, String>();
                Map<String, String> ecommerce_Map_imp = new HashMap<String, String>();
                Map<String, Map<String, String>> product_Map = new HashMap<String, Map<String, String>>();

                for(int i =0;i<2;i++){
                    Map<String,String> map = new HashMap<String,String>();
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductID,"9041"+(i+1));
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductName,"이어폰"+(i+1));
                    product_Map.put("pr"+(i+1),map);
                }
                ecommerce_Map_imp.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1,"7fd-ec42-492a-92df-c62cf");
                ecommerce_Map_imp.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2,"Y");
                ecommerce_Map_imp.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3,"M");

                ecommerce_Map_imp.put(GoogleAnalyticsBuilder.GAHitKey.UserID,"1920931201");
                ecommerce_Map_imp.put(GoogleAnalyticsBuilder.GAHitKey.Title,"ecommerce 화면명");
                ecommerce_Map_imp.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory,"Ecommerce");
                ecommerce_Map_imp.put(GoogleAnalyticsBuilder.GAHitKey.EventAction,"Impression");
                action_Map.put(GoogleAnalyticsBuilder.GAActionFieldKey.ProductActionList, "제품 리스트 명");	// 제품 리스트 명

                gb.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Impression, action_Map, product_Map ,ecommerce_Map_imp);
            }});
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> action_Map = new HashMap<String, String>();
                Map<String, String> ecommerce_click= new HashMap<String, String>();
                Map<String, Map<String, String>> product_Map = new HashMap<String, Map<String, String>>();

                for(int i =0;i<2;i++){
                    Map<String,String> map = new HashMap<String,String>();
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductID,"9041"+(i+1));
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductName,"이어폰"+(i+1));
                    product_Map.put("pr"+(i+1),map);
                }
                ecommerce_click.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1,"7fd-ec42-492a-92df-c62cf");
                ecommerce_click.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2,"Y");
                ecommerce_click.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3,"M");

                ecommerce_click.put(GoogleAnalyticsBuilder.GAHitKey.UserID,"1920931201");
                ecommerce_click.put(GoogleAnalyticsBuilder.GAHitKey.Title,"ecommerce 화면명");
                ecommerce_click.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory,"Ecommerce");
                ecommerce_click.put(GoogleAnalyticsBuilder.GAHitKey.EventAction,"Click");
                action_Map.put(GoogleAnalyticsBuilder.GAActionFieldKey.ProductActionList, "제품 리스트 명");	// 제품 리스트 명

                gb.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Click, action_Map, product_Map ,ecommerce_click);
            }});

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> action_Map = new HashMap<String, String>();
                Map<String, String> ecommerce_detail= new HashMap<String, String>();
                Map<String, Map<String, String>> product_Map = new HashMap<String, Map<String, String>>();

                for(int i =0;i<2;i++){
                    Map<String,String> map = new HashMap<String,String>();
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductID,"9041"+(i+1));
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductName,"이어폰"+(i+1));
                    product_Map.put("pr"+(i+1),map);
                }
                ecommerce_detail.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1,"7fd-ec42-492a-92df-c62cf");
                ecommerce_detail.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2,"Y");
                ecommerce_detail.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3,"M");

                ecommerce_detail.put(GoogleAnalyticsBuilder.GAHitKey.UserID,"1920931201");
                ecommerce_detail.put(GoogleAnalyticsBuilder.GAHitKey.Title,"ecommerce 화면명");
                ecommerce_detail.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory,"Ecommerce");
                ecommerce_detail.put(GoogleAnalyticsBuilder.GAHitKey.EventAction,"Detail");

                gb.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Detail, action_Map, product_Map ,ecommerce_detail);
            }});
        cartadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> action_Map = new HashMap<String, String>();
                Map<String, String> ecommerce_cartadd= new HashMap<String, String>();
                Map<String, Map<String, String>> product_Map = new HashMap<String, Map<String, String>>();

                for(int i =0;i<2;i++){
                    Map<String,String> map = new HashMap<String,String>();
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductID,"9041"+(i+1));
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductName,"이어폰"+(i+1));
                    product_Map.put("pr"+(i+1),map);
                }
                ecommerce_cartadd.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1,"7fd-ec42-492a-92df-c62cf");
                ecommerce_cartadd.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2,"Y");
                ecommerce_cartadd.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3,"M");

                ecommerce_cartadd.put(GoogleAnalyticsBuilder.GAHitKey.UserID,"1920931201");
                ecommerce_cartadd.put(GoogleAnalyticsBuilder.GAHitKey.Title,"ecommerce 화면명");
                ecommerce_cartadd.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory,"Ecommerce");
                ecommerce_cartadd.put(GoogleAnalyticsBuilder.GAHitKey.EventAction,"Cartadd");
                action_Map.put(GoogleAnalyticsBuilder.GAActionFieldKey.ProductActionList, "제품 리스트 명");	// 제품 리스트 명

                gb.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Add, action_Map, product_Map ,ecommerce_cartadd);
            }});
        cartdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> action_Map = new HashMap<String, String>();
                Map<String, String> ecommerce_cartdelete= new HashMap<String, String>();
                Map<String, Map<String, String>> product_Map = new HashMap<String, Map<String, String>>();

                for(int i =0;i<2;i++){
                    Map<String,String> map = new HashMap<String,String>();
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductID,"9041"+(i+1));
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductName,"이어폰"+(i+1));
                    product_Map.put("pr"+(i+1),map);
                }
                ecommerce_cartdelete.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1,"7fd-ec42-492a-92df-c62cf");
                ecommerce_cartdelete.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2,"Y");
                ecommerce_cartdelete.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3,"M");

                ecommerce_cartdelete.put(GoogleAnalyticsBuilder.GAHitKey.UserID,"1920931201");
                ecommerce_cartdelete.put(GoogleAnalyticsBuilder.GAHitKey.Title,"ecommerce 화면명");
                ecommerce_cartdelete.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory,"Ecommerce");
                ecommerce_cartdelete.put(GoogleAnalyticsBuilder.GAHitKey.EventAction,"Cartdelete");

                gb.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Remove, action_Map, product_Map ,ecommerce_cartdelete);
            }});
        checkout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> action_Map = new HashMap<String, String>();
                Map<String, String> ecommerce_checkout1= new HashMap<String, String>();
                Map<String, Map<String, String>> product_Map = new HashMap<String, Map<String, String>>();

                for(int i =0;i<2;i++){
                    Map<String,String> map = new HashMap<String,String>();
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductID,"9041"+(i+1));
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductName,"이어폰"+(i+1));
                    product_Map.put("pr"+(i+1),map);
                }
                ecommerce_checkout1.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1,"7fd-ec42-492a-92df-c62cf");
                ecommerce_checkout1.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2,"Y");
                ecommerce_checkout1.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3,"M");

                ecommerce_checkout1.put(GoogleAnalyticsBuilder.GAHitKey.UserID,"1920931201");
                ecommerce_checkout1.put(GoogleAnalyticsBuilder.GAHitKey.Title,"ecommerce 화면명");
                ecommerce_checkout1.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory,"Ecommerce");
                ecommerce_checkout1.put(GoogleAnalyticsBuilder.GAHitKey.EventAction,"Checkout1");// 제품 리스트 명
                ecommerce_checkout1.put(GoogleAnalyticsBuilder.GAActionFieldKey.CheckoutStep,"1");
                gb.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Checkout, action_Map, product_Map ,ecommerce_checkout1);
            }});
        checkout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> action_Map = new HashMap<String, String>();
                Map<String, String> ecommerce_checkout2= new HashMap<String, String>();
                Map<String, Map<String, String>> product_Map = new HashMap<String, Map<String, String>>();

                for(int i =0;i<2;i++){
                    Map<String,String> map = new HashMap<String,String>();
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductID,"9041"+(i+1));
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductName,"이어폰"+(i+1));
                    product_Map.put("pr"+(i+1),map);
                }
                ecommerce_checkout2.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1,"7fd-ec42-492a-92df-c62cf");
                ecommerce_checkout2.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2,"Y");
                ecommerce_checkout2.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3,"M");

                ecommerce_checkout2.put(GoogleAnalyticsBuilder.GAHitKey.UserID,"1920931201");
                ecommerce_checkout2.put(GoogleAnalyticsBuilder.GAHitKey.Title,"ecommerce 화면명");
                ecommerce_checkout2.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory,"Ecommerce");
                ecommerce_checkout2.put(GoogleAnalyticsBuilder.GAHitKey.EventAction,"Checkout2");	// 제품 리스트 명
                ecommerce_checkout2.put(GoogleAnalyticsBuilder.GAActionFieldKey.CheckoutStep,"2");
                ecommerce_checkout2.put(GoogleAnalyticsBuilder.GAActionFieldKey.CheckoutOptions,"신한카드");
                gb.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Checkout, action_Map, product_Map ,ecommerce_checkout2);
            }});
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> action_Map = new HashMap<String, String>();
                Map<String, String> ecommerce_purchase= new HashMap<String, String>();
                Map<String, Map<String, String>> product_Map = new HashMap<String, Map<String, String>>();

                for(int i =0;i<2;i++){
                    Map<String,String> map = new HashMap<String,String>();
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductID,"9041"+(i+1));
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductName,"이어폰"+(i+1));
                    product_Map.put("pr"+(i+1),map);
                }
                ecommerce_purchase.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1,"7fd-ec42-492a-92df-c62cf");
                ecommerce_purchase.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2,"Y");
                ecommerce_purchase.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3,"M");

                ecommerce_purchase.put(GoogleAnalyticsBuilder.GAHitKey.UserID,"1920931201");
                ecommerce_purchase.put(GoogleAnalyticsBuilder.GAHitKey.Title,"ecommerce 화면명");
                ecommerce_purchase.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory,"Ecommerce");
                ecommerce_purchase.put(GoogleAnalyticsBuilder.GAHitKey.EventAction,"Purchase");	// 제품 리스트 명
                ecommerce_purchase.put(GoogleAnalyticsBuilder.GAActionFieldKey.CurrencyCode, "KRW");             	//통화 코드

                action_Map.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionID, "900804647636");	//거래ID
                action_Map.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionRevenue, "15000");	//거래 총 수익
                action_Map.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionTax, "1500"); 	                                         //세금
                action_Map.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionShipping, "2500");	//배송비
                action_Map.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionCouponCode, "abc6921");	//거래쿠폰코드
                action_Map.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionAffiliation, "affcompany");	//제휴사

                gb.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Purchase, action_Map, product_Map ,ecommerce_purchase);
            }});
        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> action_Map = new HashMap<String, String>();
                Map<String, String> ecommerce_refund= new HashMap<String, String>();
                Map<String, Map<String, String>> product_Map = new HashMap<String, Map<String, String>>();

                for(int i =0;i<2;i++){
                    Map<String,String> map = new HashMap<String,String>();
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductID,"9041"+(i+1));
                    map.put(GoogleAnalyticsBuilder.GAProductKey.ProductName,"이어폰"+(i+1));
                    product_Map.put("pr"+(i+1),map);
                }
                ecommerce_refund.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1,"7fd-ec42-492a-92df-c62cf");
                ecommerce_refund.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2,"Y");
                ecommerce_refund.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3,"M");

                ecommerce_refund.put(GoogleAnalyticsBuilder.GAHitKey.UserID,"1920931201");
                ecommerce_refund.put(GoogleAnalyticsBuilder.GAHitKey.Title,"ecommerce 화면명");
                ecommerce_refund.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory,"Ecommerce");
                ecommerce_refund.put(GoogleAnalyticsBuilder.GAHitKey.EventAction,"Refund");
                action_Map.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionID, "900804647636");
                gb.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Refund, action_Map, product_Map ,ecommerce_refund);
            }});


    }
}
