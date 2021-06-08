package com.example.cgvtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Button;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLDecoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        final Tracker FASTORDER_Tracker = application.getTracker(AnalyticsApplication.TrackerName.FASTORDER);    // 패스트오더 속성
        final Tracker INTEGRATED_PAYMENT_Tracker = application.getTracker(AnalyticsApplication.TrackerName.INTEGRATED_PAYMENT);    // 통합결재 속성

        Button btn0 = (Button) findViewById(R.id.btn0);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button wbtn = (Button) findViewById(R.id.wbtn);

        FASTORDER_Tracker.set("&uid", "사용자 ID");
        INTEGRATED_PAYMENT_Tracker.set("&uid", "사용자 ID");

        FASTORDER_Tracker.setScreenName("FASTORDER_screen");
        FASTORDER_Tracker.setClientId("FASTORDER_client");
        FASTORDER_Tracker.setTitle("FASTORDER_title");
        FASTORDER_Tracker.setPage("FASTORDER_page");
        FASTORDER_Tracker.setLocation("FASTORDER_location");
        INTEGRATED_PAYMENT_Tracker.setScreenName("NTEGRATED_PAYMENT");

        HitBuilders.ScreenViewBuilder screenview_builder = new HitBuilders.ScreenViewBuilder();

        screenview_builder.setCustomDimension(1, "dfb4419512ef9bf943a289888e164202");// User_ID
        screenview_builder.setCustomDimension(2, "M");            // User_gender
        screenview_builder.setCustomDimension(3, "25");            // User_age
        screenview_builder.setCustomDimension(4, "일반");            // User_rating
        screenview_builder.setCustomDimension(6, "Mozilla/5.0 Linux; Android 6.0");    // parma_agt
        screenview_builder.setCustomDimension(7, "Member");            // 로그인 여부
        screenview_builder.setCustomDimension(8, "android / ko");
        //FASTORDER_Tracker.setSampleRate(50.0d);
        // sdk에서 사용하는 샘플링이라고 하는데 이값 설정하면 아예 데이터가 전송이 되지않음
        FASTORDER_Tracker.send(screenview_builder.build());
        //INTEGRATED_PAYMENT_Tracker.send(screenview_builder.build());

        HitBuilders.EventBuilder event_builder = new HitBuilders.EventBuilder();
        event_builder.setCategory("category");
        event_builder.setAction("action");
        event_builder.setLabel("label");

        event_builder.setCustomDimension(1, "dfb4419512ef9bf943a289888e164202");    // User_ID
        event_builder.setCustomDimension(2, "M");            // User_gender
        event_builder.setCustomDimension(3, "25");            // User_age
        event_builder.setCustomDimension(4, "일반");            // User_rating
        event_builder.setCustomDimension(6, "Mozilla/5.0 Linux; Android 6.0");        // parma_agt
        event_builder.setCustomDimension(7, "Member");            // 로그인 여부
        event_builder.setCustomDimension(8, "android / ko");            // param_dvc
        event_builder.setNonInteraction(true);


        //FASTORDER_Tracker.send(event_builder.build());
        //INTEGRATED_PAYMENT_Tracker.send(event_builder.build());

        wbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }

        });



/*
        try {
            FASTORDER_Tracker.enableExceptionReporting(true);
            Log.d("Error : ", " message ");
            moveTaskToBack(true);						// 태스크를 백그라운드로 이동
            finishAndRemoveTask();						// 액티비티 종료 + 태스크 리스트에서 지우기
            android.os.Process.killProcess(android.os.Process.myPid());	// 앱 프로세스 종료
        }catch(Exception e) {
            FASTORDER_Tracker.send(new HitBuilders.ExceptionBuilder()
                    .setDescription(new StandardExceptionParser(this, null)
                            .getDescription(Thread.currentThread().getName(), e))
                    .setFatal(false)
                    .build());
            //FASTORDER_Tracker.exit = false;
        }
*/

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
                int a = 10 / 0;
                Log.d("Error : ", " message ");
                moveTaskToBack(true);                        // 태스크를 백그라운드로 이동
                finishAndRemoveTask();                        // 액티비티 종료 + 태스크 리스트에서 지우기
                Process.killProcess(Process.myPid());    // 앱 프로세스 종료
                System.exit(10);
            }

        });


        //======================== 전자상거래


        FASTORDER_Tracker.set("&cu", "KRW");
        INTEGRATED_PAYMENT_Tracker.set("&cu", "KRW");


        // 패스트 오더 상품 정보
        final Product product1 = new Product()
                .setId("C1254878")
                .setName("원더우먼1984 3종 스페셜팩")
                .setBrand("CGV강남")
                .setCategory("패스트오더/콤보");

        final Product product2 = new Product()
                .setId("C1254878")
                .setName("원더우먼1984 3종 스페셜팩")
                .setBrand("CGV강남")
                .setCategory("패스트오더/콤보")
                .setVariant("달콤팝콘(M)/콜라(M)/원더우먼1984 탑퍼컵3종/피규어케이스3개/칠리치즈핫도그")
                .setPrice(18000)
                .setQuantity(1);

        // 통합결제 상품 정보
        final Product total_product1 = new Product()
                .setId("20022886")
                .setName("반도")
                .setBrand("동수원")
                .setCategory("2D")                        // 상품 카테고리
                .setVariant("2관(Laser) 7층")                    // 상품 변형 종류
                .setPrice(9000)                        // 상품 가격
                .setQuantity(1)                        // 상품 수량
                .setCustomDimension(11, "15세이상관람가")
                .setCustomDimension(12, "씨네앤포레")
                .setCustomDimension(13, "1735")
                .setCustomDimension(14, "1923")
                .setCustomDimension(15, "프라임")
                .setCustomDimension(16, "일반")
                .setCustomDimension(17, "G")
                .setCustomDimension(18, "011")
                .setCustomDimension(19, "G-011")
                .setCustomDimension(20, "일반좌석")
                .setCustomDimension(21, "Prime석")
                .setCustomDimension(22, "20200709");

        final Product total_product2 = new Product()
                .setId("1234")
                .setName("미트볼핫도그")
                .setBrand("통합결제/콤보")
                .setCategory("스낵")
                .setPrice(4500)
                .setQuantity(1);

        final Product total_product3 = new Product()
                .setId("1324")
                .setName("CGV 콤보")
                .setBrand("통합결제/콤보")
                .setCategory("콤보")
                .setPrice(9000)
                .setQuantity(1);

        //======================================= FASTORDER_Tracker

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭
                ProductAction productAction_click = new ProductAction(ProductAction.ACTION_CLICK);

                HitBuilders.EventBuilder builder_click = new HitBuilders.EventBuilder()
                        .setCategory("Ecommerce")
                        .setAction("Click")
                        .addProduct(product1).setProductAction(productAction_click);

                // 디테일
                ProductAction productAction_detail = new ProductAction(ProductAction.ACTION_DETAIL);

                HitBuilders.EventBuilder builder_detail = new HitBuilders.EventBuilder()
                        .setCategory("Ecommerce")
                        .setAction("Detail")
                        .addProduct(product1).setProductAction(productAction_detail);

                builder_detail.setNonInteraction(true);

                // 카트 추가

                ProductAction productAction_addtocart = new ProductAction(ProductAction.ACTION_ADD);

                HitBuilders.EventBuilder builder_addtocart = new HitBuilders.EventBuilder()
                        .setCategory("Ecommerce")
                        .setAction("Add to Cart")
                        .addProduct(product2).setProductAction(productAction_addtocart);

                // 카트 제거
                ProductAction productAction_removefromcart = new ProductAction(ProductAction.ACTION_REMOVE);

                HitBuilders.EventBuilder builder_removefromcart = new HitBuilders.EventBuilder()
                        .setCategory("Ecommerce")
                        .setAction("Remove from Cart")
                        .addProduct(product2).setProductAction(productAction_removefromcart);


                // 체크아웃 1-A
                ProductAction productAction_checkout1a = new ProductAction(ProductAction.ACTION_CHECKOUT)
                        .setCheckoutStep('1');

                HitBuilders.EventBuilder builder_checkout1a = new HitBuilders.EventBuilder()
                        .setCategory("Ecommerce")
                        .setAction("Checkout 1-a")
                        .addProduct(product2).setProductAction(productAction_checkout1a);

                // 체크아웃 1-B
                ProductAction productAction_checkout1b = new ProductAction(ProductAction.ACTION_CHECKOUT);

                HitBuilders.EventBuilder builder_checkout1b = new HitBuilders.EventBuilder()
                        .setCategory("Ecommerce")
                        .setAction("Checkout 1-b")
                        .addProduct(product2).setProductAction(productAction_checkout1b);

                // 체크아웃 2
                ProductAction productAction_checkout2 = new ProductAction(ProductAction.ACTION_CHECKOUT);

                HitBuilders.EventBuilder builder_checkout2 = new HitBuilders.EventBuilder()
                        .setCategory("Ecommerce")
                        .setAction("Checkout2")
                        .addProduct(product2).setProductAction(productAction_checkout2);

                // 구매
                ProductAction productAction_purchase = new ProductAction(ProductAction.ACTION_PURCHASE)
                        .setTransactionId("546854775")
                        .setTransactionRevenue(18000);

                HitBuilders.EventBuilder builder_purchase = new HitBuilders.EventBuilder()
                        .setCategory("Ecommerce")
                        .setAction("Purchase")
                        .addProduct(product2).setProductAction(productAction_purchase);

                // 히트 맞춤측정 기준 전송
                builder_checkout2.setCustomDimension(23, "CJONE포인트");
                builder_checkout2.setCustomDimension(24, "신용카드");
                builder_checkout2.setCustomDimension(25, "국민카드");

                builder_purchase.setCustomDimension(23, "CJONE포인트");
                builder_purchase.setCustomDimension(24, "신용카드");
                builder_purchase.setCustomDimension(25, "국민카드");

                FASTORDER_Tracker.send(builder_click.build());
                FASTORDER_Tracker.send(builder_detail.build());
                FASTORDER_Tracker.send(builder_addtocart.build());
                FASTORDER_Tracker.send(builder_removefromcart.build());
                FASTORDER_Tracker.send(builder_checkout1a.build());
                FASTORDER_Tracker.send(builder_checkout1b.build());
                FASTORDER_Tracker.send(builder_checkout2.build());
                FASTORDER_Tracker.send(builder_purchase.build());
            }
        });

        //======================================= INTEGRATED_PAYMENT_Tracker
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 체크아웃 1
                ProductAction productAction_checkout1 = new ProductAction(ProductAction.ACTION_CHECKOUT);

                HitBuilders.EventBuilder builder_checkout1 = new HitBuilders.EventBuilder()
                        .setCategory("Ecommerce")
                        .setAction("Checkout1")
                        .addProduct(total_product1)
                        .setProductAction(productAction_checkout1);

                // 체크아웃 2
                ProductAction productAction_checkout2_2 = new ProductAction(ProductAction.ACTION_CHECKOUT);

                HitBuilders.EventBuilder builder_checkout2_2 = new HitBuilders.EventBuilder()
                        .setCategory("Ecommerce")
                        .setAction("Checkout2")
                        .addProduct(total_product1)
                        .setProductAction(productAction_checkout2_2);

                // 체크아웃 3
                ProductAction productAction_checkout3 = new ProductAction(ProductAction.ACTION_CHECKOUT);

                HitBuilders.EventBuilder builder_checkout3 = new HitBuilders.EventBuilder()
                        .setCategory("Ecommerce")
                        .setAction("Checkout3")
                        .addProduct(total_product1)
                        .addProduct(total_product2)
                        .addProduct(total_product3)
                        .setProductAction(productAction_checkout3);

                // 구매
                ProductAction productAction_purchase2 = new ProductAction(ProductAction.ACTION_PURCHASE)
                        .setTransactionId("0041202007104542314")
                        .setTransactionRevenue(22500);

                HitBuilders.EventBuilder builder_purchase2 = new HitBuilders.EventBuilder()
                        .setCategory("Ecommerce")
                        .setAction("Purchase")
                        .addProduct(total_product1)
                        .addProduct(total_product2)
                        .addProduct(total_product3)
                        .setProductAction(productAction_purchase2);

                // 환불
                ProductAction productAction_refund = new ProductAction(ProductAction.ACTION_REFUND)
                        .setTransactionId("0041202007104542314");

                HitBuilders.EventBuilder builder_refund = new HitBuilders.EventBuilder()
                        .setCategory("Ecommerce")
                        .setAction("Refund")
                        .setProductAction(productAction_refund);

                // 전자상거래 맞춤측정 기준

                builder_checkout3.setCustomDimension(23, "CJONE포인트");
                builder_checkout3.setCustomDimension(24, "신용카드");
                builder_checkout3.setCustomDimension(25, "국민카드");

                builder_purchase2.setCustomDimension(23, "CJONE포인트");
                builder_purchase2.setCustomDimension(24, "신용카드");
                builder_purchase2.setCustomDimension(25, "국민카드");

                INTEGRATED_PAYMENT_Tracker.send(builder_checkout1.build());
                INTEGRATED_PAYMENT_Tracker.send(builder_checkout2_2.build());
                INTEGRATED_PAYMENT_Tracker.send(builder_checkout3.build());
                INTEGRATED_PAYMENT_Tracker.send(builder_purchase2.build());
                INTEGRATED_PAYMENT_Tracker.send(builder_refund.build());
            }
        });
    }

    //하이브리드처리
    public static class WebAppInterface {
        Tracker FASTORDER_Tracker1;
        Tracker FASTORDER_Tracker2;
        Tracker INTEGRATED_PAYMENT_Tracker;

        public WebAppInterface(Main2Activity main2Activity) {
            AnalyticsApplication application = (AnalyticsApplication) ((Activity) main2Activity).getApplication();
            FASTORDER_Tracker1 = application.getTracker(AnalyticsApplication.TrackerName.FASTORDER);    // 패스트오더 속성1
            FASTORDER_Tracker2 = application.getTracker(AnalyticsApplication.TrackerName.FASTORDER);    // 패스트오더 속성2
            INTEGRATED_PAYMENT_Tracker = application.getTracker(AnalyticsApplication.TrackerName.INTEGRATED_PAYMENT);    // 통합결재 속성
        }

        @JavascriptInterface
        public void GA_DATA(String JsonData) {
            try {
                JSONObject data = new JSONObject(JsonData);
                String sType = "";
                String eco = "0";
                if (data.has("type"))
                    sType = data.getString("type");
                if (sType.equals("fo_P") == true) {
                    HitBuilders.ScreenViewBuilder Screenview_Builder = new HitBuilders.ScreenViewBuilder();
                    if (data.has("title")) {
                        FASTORDER_Tracker1.setScreenName(data.getString("title"));
                        FASTORDER_Tracker2.setScreenName(data.getString("title"));
                    }
                    if (data.has("dimension1")) {
                        FASTORDER_Tracker1.set("&uid", data.getString("dimension1"));
                        FASTORDER_Tracker2.set("&uid", data.getString("dimension1"));
                    }
                    if (data.has("dimension1"))Screenview_Builder.setCustomDimension(1, data.getString("dimension1"));
                    if (data.has("dimension2"))Screenview_Builder.setCustomDimension(2, data.getString("dimension2"));
                    if (data.has("dimension3"))Screenview_Builder.setCustomDimension(3, data.getString("dimension3"));
                    if (data.has("dimension4"))Screenview_Builder.setCustomDimension(4, data.getString("dimension4"));
                    if (data.has("dimension6"))Screenview_Builder.setCustomDimension(6, data.getString("dimension6"));
                    if (data.has("dimension7"))Screenview_Builder.setCustomDimension(7, data.getString("dimension7"));
                    if (data.has("dimension8"))Screenview_Builder.setCustomDimension(8, data.getString("dimension8"));
                    if (data.has("dimension9"))Screenview_Builder.setCustomDimension(9, data.getString("dimension9"));
                    FASTORDER_Tracker1.send(Screenview_Builder.build());
                    FASTORDER_Tracker2.send(Screenview_Builder.build());
                    FASTORDER_Tracker1 = NullSet(FASTORDER_Tracker1);
                    FASTORDER_Tracker2 = NullSet(FASTORDER_Tracker2);
                }
                else if (sType.equals("ip_P") == true) {
                    HitBuilders.ScreenViewBuilder Screenview_Builder = new HitBuilders.ScreenViewBuilder();
                    if (data.has("title")) {INTEGRATED_PAYMENT_Tracker.setScreenName(data.getString("title"));}
                    if (data.has("dimension1")) {INTEGRATED_PAYMENT_Tracker.set("&uid", data.getString("dimension1"));}
                    if (data.has("dimension1"))Screenview_Builder.setCustomDimension(1, data.getString("dimension1"));
                    if (data.has("dimension2"))Screenview_Builder.setCustomDimension(2, data.getString("dimension2"));
                    if (data.has("dimension3"))Screenview_Builder.setCustomDimension(3, data.getString("dimension3"));
                    if (data.has("dimension4"))Screenview_Builder.setCustomDimension(4, data.getString("dimension4"));
                    if (data.has("dimension6"))Screenview_Builder.setCustomDimension(6, data.getString("dimension6"));
                    if (data.has("dimension7"))Screenview_Builder.setCustomDimension(7, data.getString("dimension7"));
                    if (data.has("dimension8"))Screenview_Builder.setCustomDimension(8, data.getString("dimension8"));
                    if (data.has("dimension9"))Screenview_Builder.setCustomDimension(9, data.getString("dimension9"));
                    INTEGRATED_PAYMENT_Tracker.send(Screenview_Builder.build());
                    INTEGRATED_PAYMENT_Tracker = NullSet(INTEGRATED_PAYMENT_Tracker);
                } else if (sType.equals("fo_E") == true) {
                    HitBuilders.EventBuilder event_builder = new HitBuilders.EventBuilder();
                    if (data.has("title")) {
                        FASTORDER_Tracker1.setScreenName(data.getString("title"));
                        FASTORDER_Tracker2.setScreenName(data.getString("title"));
                    }
                    if (data.has("dimension1")) {
                        FASTORDER_Tracker1.set("&uid", data.getString("dimension1"));
                        FASTORDER_Tracker2.set("&uid", data.getString("dimension1"));
                    }
                    if (data.has("category"))event_builder.setCategory(data.getString("category"));
                    if (data.has("action"))event_builder.setAction(data.getString("action"));
                    if (data.has("label"))event_builder.setLabel(data.getString("label"));
                    if (data.has("dimension1"))event_builder.setCustomDimension(1, data.getString("dimension1"));
                    if (data.has("dimension2"))event_builder.setCustomDimension(2, data.getString("dimension2"));
                    if (data.has("dimension3"))event_builder.setCustomDimension(3, data.getString("dimension3"));
                    if (data.has("dimension4"))event_builder.setCustomDimension(4, data.getString("dimension4"));
                    if (data.has("dimension6"))event_builder.setCustomDimension(6, data.getString("dimension6"));
                    if (data.has("dimension7"))event_builder.setCustomDimension(7, data.getString("dimension7"));
                    if (data.has("dimension8"))event_builder.setCustomDimension(8, data.getString("dimension8"));
                    if (data.has("dimension9"))event_builder.setCustomDimension(9, data.getString("dimension9"));

                    if (data.has("ecommerce")) {
                        eco = "1";
                        JSONObject obj_ecommerce = data.getJSONObject("ecommerce");
                        if (obj_ecommerce.has("currencyCode")) {
                            FASTORDER_Tracker1.set("&cu", obj_ecommerce.getString("currencyCode"));
                            FASTORDER_Tracker2.set("&cu", obj_ecommerce.getString("currencyCode"));
                        }
                        ProductAction productAction = null;
                        JSONObject ecommerce_data = null;

                        if (obj_ecommerce.has("click")) {
                            productAction = new ProductAction(ProductAction.ACTION_CLICK);
                            ecommerce_data = obj_ecommerce.getJSONObject("click");
                        }

                        if (obj_ecommerce.has("detail")) {
                            productAction = new ProductAction(ProductAction.ACTION_DETAIL);
                            ecommerce_data = obj_ecommerce.getJSONObject("detail");
                            event_builder.setNonInteraction(true);
                        }
                        if (obj_ecommerce.has("add")) {
                            productAction = new ProductAction(ProductAction.ACTION_ADD);
                            ecommerce_data = obj_ecommerce.getJSONObject("add");
                        }

                        if (obj_ecommerce.has("remove")) {
                            productAction = new ProductAction(ProductAction.ACTION_REMOVE);
                            ecommerce_data = obj_ecommerce.getJSONObject("remove");
                        }

                        if (obj_ecommerce.has("checkout")) {
                            productAction = new ProductAction(ProductAction.ACTION_CHECKOUT);
                            ecommerce_data = obj_ecommerce.getJSONObject("checkout");
                            if (ecommerce_data.has("actionField")) {
                                JSONObject actionField_data = ecommerce_data.getJSONObject("actionField");
                                if (actionField_data.has("step"))
                                    productAction.setCheckoutStep(actionField_data.getInt("step"));
                                if (actionField_data.has("option"))
                                    productAction.setCheckoutOptions(actionField_data.getString("option"));
                                if (actionField_data.has("list"))
                                    productAction.setProductListSource(actionField_data.getString("list"));
                            }
                        }
                        if (obj_ecommerce.has("purchase")) {
                            productAction = new ProductAction(ProductAction.ACTION_PURCHASE);
                            ecommerce_data = obj_ecommerce.getJSONObject("purchase");
                            if (ecommerce_data.has("actionField")) {
                                JSONObject actionField_data = ecommerce_data.getJSONObject("actionField");
                                if (actionField_data.has("id"))
                                    productAction.setTransactionId(actionField_data.getString("id"));
                                if (actionField_data.has("revenue"))
                                    productAction.setTransactionRevenue(actionField_data.getDouble("revenue"));
                            }
                            event_builder.setNonInteraction(true);
                        }
                        if (obj_ecommerce.has("refund")) {
                            productAction = new ProductAction(ProductAction.ACTION_REFUND);
                            ecommerce_data = obj_ecommerce.getJSONObject("refund");
                            if (ecommerce_data.has("actionField")) {
                                JSONObject actionField_data = ecommerce_data.getJSONObject("actionField");
                                if (actionField_data.has("id"))
                                    productAction.setTransactionId(actionField_data.getString("id"));
                                if (actionField_data.has("revenue"))
                                    productAction.setTransactionRevenue(actionField_data.getDouble("revenue"));
                            }
                        }
                        if (ecommerce_data.has("products")) {
                            JSONArray product_array = ecommerce_data.getJSONArray("products");
                            for (int i = 0; i < product_array.length(); i++) {
                                JSONObject ecommerce_item = product_array.getJSONObject(i);
                                Product item = new Product();
                                if (ecommerce_item.has("id"))
                                    item.setId(ecommerce_item.getString("id"));
                                if (ecommerce_item.has("name"))
                                    item.setName(ecommerce_item.getString("name"));
                                if (ecommerce_item.has("brand"))
                                    item.setBrand(ecommerce_item.getString("brand"));
                                if (ecommerce_item.has("category"))
                                    item.setCategory(ecommerce_item.getString("category"));
                                if (ecommerce_item.has("price"))
                                    item.setPrice(ecommerce_item.getInt("price"));
                                if (ecommerce_item.has("quantity"))
                                    item.setQuantity(ecommerce_item.getInt("quantity"));
                                if (ecommerce_item.has("variant"))
                                    item.setVariant(ecommerce_item.getString("variant"));
                                if (ecommerce_item.has("coupon"))
                                    item.setCouponCode(ecommerce_item.getString("coupon"));
                                event_builder.addProduct(item);
                            }
                            event_builder.setProductAction(productAction);
                        }
                    }
                    if(eco=="1"){
                        eco="0";
                        FASTORDER_Tracker2.send(event_builder.build());
                        FASTORDER_Tracker2 = NullSet(FASTORDER_Tracker2);}
                    else {
                        FASTORDER_Tracker1.send(event_builder.build());
                        FASTORDER_Tracker1 = NullSet(FASTORDER_Tracker2);
                        FASTORDER_Tracker2.send(event_builder.build());
                        FASTORDER_Tracker2 = NullSet(FASTORDER_Tracker2);
                    }

                } else if (sType.equals("ip_E")) {
                    HitBuilders.EventBuilder event_builder = new HitBuilders.EventBuilder();
                    if (data.has("title")) {
                        INTEGRATED_PAYMENT_Tracker.setScreenName(data.getString("title"));
                    }
                    if (data.has("dimension1")) {
                        INTEGRATED_PAYMENT_Tracker.set("&uid", data.getString("dimension1"));
                    }
                    if (data.has("dimension1"))
                        event_builder.setCustomDimension(1, data.getString("dimension1"));
                    if (data.has("dimension2"))
                        event_builder.setCustomDimension(2, data.getString("dimension2"));
                    if (data.has("dimension3"))
                        event_builder.setCustomDimension(3, data.getString("dimension3"));
                    if (data.has("dimension4"))
                        event_builder.setCustomDimension(4, data.getString("dimension4"));
                    if (data.has("dimension6"))
                        event_builder.setCustomDimension(6, data.getString("dimension6"));
                    if (data.has("dimension7"))
                        event_builder.setCustomDimension(7, data.getString("dimension7"));
                    if (data.has("dimension8"))
                        event_builder.setCustomDimension(8, data.getString("dimension8"));
                    if (data.has("dimension9"))
                        event_builder.setCustomDimension(9, data.getString("dimension9"));
                    if (data.has("category")) event_builder.setCategory(data.getString("category"));
                    if (data.has("action")) event_builder.setAction(data.getString("action"));
                    if (data.has("label")) event_builder.setLabel(data.getString("label"));
                    if (data.has("ecommerce")) {
                        JSONObject obj_ecommerce = data.getJSONObject("ecommerce");
                        if (obj_ecommerce.has("currencyCode")) {
                            INTEGRATED_PAYMENT_Tracker.set("&cu", obj_ecommerce.getString("currencyCode"));
                        }
                        ProductAction productAction = null;
                        JSONObject ecommerce_data = null;
                        if (obj_ecommerce.has("click")) {
                            productAction = new ProductAction(ProductAction.ACTION_CLICK);
                            ecommerce_data = obj_ecommerce.getJSONObject("click");
                        }
                        if (obj_ecommerce.has("detail")) {
                            productAction = new ProductAction(ProductAction.ACTION_DETAIL);
                            ecommerce_data = obj_ecommerce.getJSONObject("detail");
                            event_builder.setNonInteraction(true);
                        }
                        if (obj_ecommerce.has("add")) {
                            productAction = new ProductAction(ProductAction.ACTION_ADD);
                            ecommerce_data = obj_ecommerce.getJSONObject("add");
                        }

                        if (obj_ecommerce.has("remove")) {
                            productAction = new ProductAction(ProductAction.ACTION_REMOVE);
                            ecommerce_data = obj_ecommerce.getJSONObject("remove");
                        }

                        if (obj_ecommerce.has("checkout")) {
                            productAction = new ProductAction(ProductAction.ACTION_CHECKOUT);
                            ecommerce_data = obj_ecommerce.getJSONObject("checkout");
                            if (ecommerce_data.has("actionField")) {
                                JSONObject actionField_data = ecommerce_data.getJSONObject("actionField");
                                if (actionField_data.has("step"))
                                    productAction.setCheckoutStep(actionField_data.getInt("step"));
                                if (actionField_data.has("option"))
                                    productAction.setCheckoutOptions(actionField_data.getString("option"));
                                if (actionField_data.has("list"))
                                    productAction.setProductListSource(actionField_data.getString("list"));
                            }
                        }
                        if (obj_ecommerce.has("purchase")) {
                            productAction = new ProductAction(ProductAction.ACTION_PURCHASE);
                            ecommerce_data = obj_ecommerce.getJSONObject("purchase");
                            if (ecommerce_data.has("actionField")) {
                                JSONObject actionField_data = ecommerce_data.getJSONObject("actionField");
                                if (actionField_data.has("id"))
                                    productAction.setTransactionId(actionField_data.getString("id"));
                                if (actionField_data.has("revenue"))
                                    productAction.setTransactionRevenue(actionField_data.getDouble("revenue"));
                            }
                            event_builder.setNonInteraction(true);
                        }
                        if (obj_ecommerce.has("refund")) {
                            productAction = new ProductAction(ProductAction.ACTION_REFUND);
                            ecommerce_data = obj_ecommerce.getJSONObject("refund");
                            if (ecommerce_data.has("actionField")) {
                                JSONObject actionField_data = ecommerce_data.getJSONObject("actionField");
                                if (actionField_data.has("id"))
                                    productAction.setTransactionId(actionField_data.getString("id"));
                                if (actionField_data.has("revenue"))
                                    productAction.setTransactionRevenue(actionField_data.getDouble("revenue"));
                            }
                        }
                        if (ecommerce_data.has("products")) {
                            JSONArray product_array = ecommerce_data.getJSONArray("products");
                            for (int i = 0; i < product_array.length(); i++) {
                                JSONObject ecommerce_item = product_array.getJSONObject(i);
                                Product item = new Product();
                                if (ecommerce_item.has("id"))
                                    item.setId(ecommerce_item.getString("id"));
                                if (ecommerce_item.has("name"))
                                    item.setName(ecommerce_item.getString("name"));
                                if (ecommerce_item.has("brand"))
                                    item.setBrand(ecommerce_item.getString("brand"));
                                if (ecommerce_item.has("category"))
                                    item.setCategory(ecommerce_item.getString("category"));
                                if (ecommerce_item.has("price"))
                                    item.setPrice(ecommerce_item.getInt("price"));
                                if (ecommerce_item.has("quantity"))
                                    item.setQuantity(ecommerce_item.getInt("quantity"));
                                if (ecommerce_item.has("variant"))
                                    item.setVariant(ecommerce_item.getString("variant"));
                                if (ecommerce_item.has("coupon"))
                                    item.setCouponCode(ecommerce_item.getString("coupon"));
                                event_builder.addProduct(item);
                            }
                            event_builder.setProductAction(productAction);
                        }
                    }
                    INTEGRATED_PAYMENT_Tracker.send(event_builder.build());
                    INTEGRATED_PAYMENT_Tracker = NullSet(INTEGRATED_PAYMENT_Tracker);
                }
            } catch (Exception ex) {
                Log.i("GA_Exception",ex.getMessage());
            }
        }
    }

    private static Tracker NullSet(Tracker mTracker) {
        mTracker.setScreenName(null);
        mTracker.set("&cu", null);
        mTracker.set("&uid", null);
        return mTracker;
    }

    public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            e.printStackTrace();
            Log.d("Error : ", " message ");
            AnalyticsApplication application = (AnalyticsApplication) getApplication();
            final Tracker FASTORDER_Tracker = application.getTracker(AnalyticsApplication.TrackerName.FASTORDER);    // 패스트오더 속성
            FASTORDER_Tracker.send(new HitBuilders.ExceptionBuilder()
                    .setDescription(new StandardExceptionParser(null, null)
                            .getDescription(Thread.currentThread().getName(), e))
                    .setFatal(false)
                    .build());

            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        }
    }
}

