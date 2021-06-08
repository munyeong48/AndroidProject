package com.goldenplanet.www.gp_android.Fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goldenplanet.www.gp_android.GoogleAnalytics.GoogleAnalyticsBuilder;
import com.goldenplanet.www.gp_android.R;

import java.util.HashMap;
import java.util.Map;

public class ProductPurchase extends Fragment {
    private String Param_price;
    private String Param_CD1 = "";
    private String Param_CD2 = "";
    private String Param_CD3 = "";
    private String Param_CD4 = "";
    private String Param_CD5 = "";
    private String Param_CD6 = "";
    private String Param_CD7 = "";
    private String Param_CD8 = "";
    private String Param_CD9 = "";
    private String Param_CD10 = "";
    private String Param_CD11 = "";
    private String Param_CD12 = "";
    private String Param_CD13 = "";
    private String Param_CD14 = "";
    private String Param_CD15 = "";
    private String Param_CD16 = "";
    private String Param_CD17 = "";
    private String Param_CD18 = "";
    private String Param_CD19 = "";
    private String Param_CD20 = "";
    private static String TAG = "ProductPurchase";
    public static GoogleAnalyticsBuilder googleAnalyticsBuilder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Param_price = getArguments().getString("price");
            Param_CD1 = getArguments().getString("cd1");
            Param_CD2 = getArguments().getString("cd2");
            Param_CD3 = getArguments().getString("cd3");
            Param_CD4 = getArguments().getString("cd4");
            Param_CD5 = getArguments().getString("cd5");
            Param_CD6 = getArguments().getString("cd6");
            Param_CD7 = getArguments().getString("cd7");
            Param_CD8 = getArguments().getString("cd8");
            Param_CD9 = getArguments().getString("cd9");
            Param_CD10 = getArguments().getString("cd10");
            Param_CD11 = getArguments().getString("cd11");
            Param_CD12 = getArguments().getString("cd12");
            Param_CD13 = getArguments().getString("cd13");
            Param_CD14 = getArguments().getString("cd14");
            Param_CD15 = getArguments().getString("cd15");
            Param_CD16 = getArguments().getString("cd16");
            Param_CD17 = getArguments().getString("cd17");
            Param_CD18 = getArguments().getString("cd18");
            Param_CD19 = getArguments().getString("cd19");
            Param_CD20 = getArguments().getString("cd20");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_product_purchase, container, false);

        com.google.firebase.analytics.FirebaseAnalytics mFirebaseAnalytics;
        mFirebaseAnalytics = com.google.firebase.analytics.FirebaseAnalytics.getInstance(getContext());


        Bundle params = new Bundle();
        params.putString("ScreenDepth1", "screen_depth1_" + TAG);
        params.putString("ScreenDepth2", "screen_depth2_" + TAG);
        params.putString("ScreenDepth3", "screen_depth3_" + TAG);
        params.putString("ScreenDepth4", "screen_depth4_" + TAG);
        params.putString("ScreenDepth5", "screen_depth5_" + TAG);
        params.putFloat("FloatData_" + TAG, 1);
        params.putDouble("DoubleData_" + TAG, 1);
        params.putInt("IntData_" + TAG, 1);
        mFirebaseAnalytics.logEvent("screenview", params);
        
        googleAnalyticsBuilder = new GoogleAnalyticsBuilder(getActivity());

        int ProductPrice = Integer.parseInt(Param_price);
        Map<String, String> ProductMap = new HashMap<String, String>();
        ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductID, "GP_Product");
        ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductName, "GP_Product");
        ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductCategory, "GA Test");
        ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductBrand, "GP");
        ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductVariant, "Black");
        ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductPrice, String.valueOf(ProductPrice));
        ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductQuantity,"1");

        Map<String, Map<String, String>> product_Map = new HashMap<String, Map<String, String>>();
        product_Map.put("pr1",ProductMap);

        Map<String, String> ProductActionMap = new HashMap<String, String>();
        ProductActionMap.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionID, "94934672");
        ProductActionMap.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionAffiliation, "제휴 코드");
        ProductActionMap.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionRevenue, String.valueOf(ProductPrice+20000+3000));
        ProductActionMap.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionTax, String.valueOf(20000));
        ProductActionMap.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionShipping, String.valueOf(3000));
        ProductActionMap.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionCouponCode, "쿠폰 없음");

        Map<String, String> EcommerceMap = new HashMap<String, String>();
        if(!Param_CD1.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1,Param_CD1);
        if(!Param_CD2.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2,Param_CD2);
        if(!Param_CD3.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3,Param_CD3);
        if(!Param_CD4.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension4,Param_CD4);
        if(!Param_CD5.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension5,Param_CD5);
        if(!Param_CD6.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension6,Param_CD6);
        if(!Param_CD7.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension7,Param_CD7);
        if(!Param_CD8.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension8,Param_CD8);
        if(!Param_CD9.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension9,Param_CD9);
        if(!Param_CD10.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension10,Param_CD10);
        if(!Param_CD11.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension11,Param_CD11);
        if(!Param_CD12.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension12,Param_CD12);
        if(!Param_CD13.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension13,Param_CD13);
        if(!Param_CD14.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension14,Param_CD14);
        if(!Param_CD15.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension15,Param_CD15);
        if(!Param_CD16.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension16,Param_CD16);
        if(!Param_CD17.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension17,Param_CD17);
        if(!Param_CD18.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension18,Param_CD18);
        if(!Param_CD19.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension19,Param_CD19);
        if(!Param_CD20.equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension20,Param_CD20);

        EcommerceMap.put(GoogleAnalyticsBuilder.GAHitKey.Title,"PV_ProductPurchase");
//        googleAnalyticsBuilder.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Purchase,ProductActionMap,product_Map,EcommerceMap);
        Snackbar.make(getActivity().findViewById(android.R.id.content), "Purchase 전송 완료", Snackbar.LENGTH_LONG).show();

        return v;
    }

}
