package com.goldenplanet.www.gp_android.Fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.goldenplanet.www.gp_android.GoogleAnalytics.GoogleAnalyticsBuilder;
import com.goldenplanet.www.gp_android.R;

import java.util.HashMap;
import java.util.Map;

public class ProductDetail extends Fragment {

    private static EditText et_cd_1,et_cd_2,et_cd_3,et_cd_4,et_cd_5
            ,et_cd_6,et_cd_7,et_cd_8,et_cd_9,et_cd_10,et_cd_11,et_cd_12
            ,et_cd_13,et_cd_14,et_cd_15,et_cd_16,et_cd_17,et_cd_18,et_cd_19,et_cd_20;
    private static String Param_CD1 = "";
    private static String Param_CD2 = "";
    private static String Param_CD3 = "";
    private static String Param_CD4 = "";
    private static String Param_CD5 = "";
    private static String Param_CD6 = "";
    private static String Param_CD7 = "";
    private static String Param_CD8 = "";
    private static String Param_CD9 = "";
    private static String Param_CD10 = "";
    private static String Param_CD11 = "";
    private static String Param_CD12 = "";
    private static String Param_CD13 = "";
    private static String Param_CD14 = "";
    private static String Param_CD15 = "";
    private static String Param_CD16 = "";
    private static String Param_CD17 = "";
    private static String Param_CD18 = "";
    private static String Param_CD19 = "";
    private static String Param_CD20 = "";
    private static String TAG = "ProductDetail";
    private static int customdimensionNumber = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
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
        // Inflate the layout for this fragment

        final View v = inflater.inflate(R.layout.activity_product_detail, container, false);
        Button btn_purchase = (Button)v.findViewById(R.id.btn_Purchase);
        final EditText editText_purchase = (EditText)v.findViewById(R.id.et_price);
        GoogleAnalyticsBuilder googleAnalyticsBuilder = new GoogleAnalyticsBuilder(getActivity());
        Button btn_customdimension_add_detail = (Button)v.findViewById(R.id.btn_customdimension_add_detail);
        Button btn_customdimension_remove_detail = (Button)v.findViewById(R.id.btn_customdimension_remove_detail);

        et_cd_1 = (EditText)v.findViewById(R.id.et_cd_d_1);
        et_cd_2 = (EditText)v.findViewById(R.id.et_cd_d_2);
        et_cd_3 = (EditText)v.findViewById(R.id.et_cd_d_3);
        et_cd_4 = (EditText)v.findViewById(R.id.et_cd_d_4);
        et_cd_5 = (EditText)v.findViewById(R.id.et_cd_d_5);
        et_cd_6 = (EditText)v.findViewById(R.id.et_cd_d_6);
        et_cd_7 = (EditText)v.findViewById(R.id.et_cd_d_7);
        et_cd_8 = (EditText)v.findViewById(R.id.et_cd_d_8);
        et_cd_9 = (EditText)v.findViewById(R.id.et_cd_d_9);
        et_cd_10 = (EditText)v.findViewById(R.id.et_cd_d_10);
        et_cd_11 = (EditText)v.findViewById(R.id.et_cd_d_11);
        et_cd_12 = (EditText)v.findViewById(R.id.et_cd_d_12);
        et_cd_13 = (EditText)v.findViewById(R.id.et_cd_d_13);
        et_cd_14 = (EditText)v.findViewById(R.id.et_cd_d_14);
        et_cd_15 = (EditText)v.findViewById(R.id.et_cd_d_15);
        et_cd_16 = (EditText)v.findViewById(R.id.et_cd_d_16);
        et_cd_17 = (EditText)v.findViewById(R.id.et_cd_d_17);
        et_cd_18 = (EditText)v.findViewById(R.id.et_cd_d_18);
        et_cd_19 = (EditText)v.findViewById(R.id.et_cd_d_19);
        et_cd_20 = (EditText)v.findViewById(R.id.et_cd_d_20);

        com.google.firebase.analytics.FirebaseAnalytics mFirebaseAnalytics;
        mFirebaseAnalytics = com.google.firebase.analytics.FirebaseAnalytics.getInstance(getContext());


        Bundle params = new Bundle();
        params.putString("ScreenDepth1", "screen_depth1_" + TAG);
        params.putString("ScreenDepth2", "screen_depth2_" + TAG);
        params.putString("ScreenDepth3", "screen_depth3_" + TAG);
        params.putString("ScreenDepth4", "screen_depth4_" + TAG);
        params.putString("ScreenDepth5", "screen_depth5_" + TAG);
        mFirebaseAnalytics.logEvent("screenview", params);

        Map<String, String> ProductMap = new HashMap<String, String>();
        ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductID, "GP_Product");
        ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductName, "GP_Product");
        ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductCategory, "GA Test");
        ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductBrand, "GP");
        ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductVariant, "Black");

        Map<String, Map<String, String>> product_Map = new HashMap<String, Map<String, String>>();
        product_Map.put("pr1",ProductMap);

        Map<String, String> ProductActionMap = new HashMap<String, String>();
        ProductActionMap.put(GoogleAnalyticsBuilder.GAActionFieldKey.ProductActionList, "ProductActionList");

        Map<String, String> EcommerceMap = new HashMap<String, String>();
        EcommerceMap.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory, "EV_ProductClick_Category");
        EcommerceMap.put(GoogleAnalyticsBuilder.GAHitKey.EventAction, "EV_ProductClick_Action");
        EcommerceMap.put(GoogleAnalyticsBuilder.GAHitKey.EventLabel, "EV_ProductClick_Label");

       if(!Param_CD1.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1, Param_CD1);
       if(!Param_CD2.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2, Param_CD2);
       if(!Param_CD3.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3, Param_CD3);
       if(!Param_CD4.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension4, Param_CD4);
       if(!Param_CD5.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension5, Param_CD5);
       if(!Param_CD6.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension6, Param_CD6);
       if(!Param_CD7.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension7, Param_CD7);
       if(!Param_CD8.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension8, Param_CD8);
       if(!Param_CD9.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension9, Param_CD9);
       if(!Param_CD10.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension10, Param_CD10);
       if(!Param_CD11.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension11, Param_CD11);
       if(!Param_CD12.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension12, Param_CD12);
       if(!Param_CD13.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension13, Param_CD13);
       if(!Param_CD14.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension14, Param_CD14);
       if(!Param_CD15.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension15, Param_CD15);
       if(!Param_CD16.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension16, Param_CD16);
       if(!Param_CD17.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension17, Param_CD17);
       if(!Param_CD18.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension18, Param_CD18);
       if(!Param_CD19.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension19, Param_CD19);
       if(!Param_CD20.toString().equals("")) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension20, Param_CD20);

        EcommerceMap.put(GoogleAnalyticsBuilder.GAHitKey.Title,"PV_ProductDetail");
//        googleAnalyticsBuilder.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Detail,ProductActionMap,product_Map,EcommerceMap);
        Snackbar.make(getActivity().findViewById(android.R.id.content), "Detail 전송 완료", Snackbar.LENGTH_LONG).show();

        btn_customdimension_add_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(customdimensionNumber != 20){
                    LinearLayout linearLayout = null;
                    switch (customdimensionNumber){
                        case 1: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail2)).setVisibility(View.VISIBLE); break;
                        case 2: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail3)).setVisibility(View.VISIBLE); break;
                        case 3: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail4)).setVisibility(View.VISIBLE); break;
                        case 4: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail5)).setVisibility(View.VISIBLE); break;
                        case 5: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail6)).setVisibility(View.VISIBLE); break;
                        case 6: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail7)).setVisibility(View.VISIBLE); break;
                        case 7: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail8)).setVisibility(View.VISIBLE); break;
                        case 8: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail9)).setVisibility(View.VISIBLE); break;
                        case 9: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail10)).setVisibility(View.VISIBLE); break;
                        case 10: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail11)).setVisibility(View.VISIBLE); break;
                        case 11: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail12)).setVisibility(View.VISIBLE); break;
                        case 12: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail13)).setVisibility(View.VISIBLE); break;
                        case 13: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail14)).setVisibility(View.VISIBLE); break;
                        case 14: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail15)).setVisibility(View.VISIBLE); break;
                        case 15: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail16)).setVisibility(View.VISIBLE); break;
                        case 16: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail17)).setVisibility(View.VISIBLE); break;
                        case 17: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail18)).setVisibility(View.VISIBLE); break;
                        case 18: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail19)).setVisibility(View.VISIBLE); break;
                        case 19: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail20)).setVisibility(View.VISIBLE); break;
                    }
                    customdimensionNumber++;
                }
            }
        });

        btn_customdimension_remove_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(customdimensionNumber != 1){
                    LinearLayout linearLayout = null;
                    switch (customdimensionNumber){
                        case 2: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail2)).setVisibility(View.GONE); break;
                        case 3: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail3)).setVisibility(View.GONE); break;
                        case 4: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail4)).setVisibility(View.GONE); break;
                        case 5: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail5)).setVisibility(View.GONE); break;
                        case 6: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail6)).setVisibility(View.GONE); break;
                        case 7: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail7)).setVisibility(View.GONE); break;
                        case 8: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail8)).setVisibility(View.GONE); break;
                        case 9: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail9)).setVisibility(View.GONE); break;
                        case 10: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail10)).setVisibility(View.GONE); break;
                        case 11: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail11)).setVisibility(View.GONE); break;
                        case 12: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail12)).setVisibility(View.GONE); break;
                        case 13: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail13)).setVisibility(View.GONE); break;
                        case 14: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail14)).setVisibility(View.GONE); break;
                        case 15: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail15)).setVisibility(View.GONE); break;
                        case 16: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail16)).setVisibility(View.GONE); break;
                        case 17: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail17)).setVisibility(View.GONE); break;
                        case 18: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail18)).setVisibility(View.GONE); break;
                        case 19: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail19)).setVisibility(View.GONE); break;
                        case 20: ((LinearLayout) v.findViewById(R.id.linearlayout_custom1_detail20)).setVisibility(View.GONE); break;
                    }
                    customdimensionNumber--;
                }
            }
        });

        btn_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText_purchase != null && editText_purchase.getText().toString().length() > 0 ) {
                    Fragment fragment = new ProductPurchase();

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Bundle args = new Bundle();
                    if (et_cd_1.getText().toString().length() > 0) args.putString("cd1", et_cd_1.getText().toString());
                    else args.putString("cd1", "");
                    if (et_cd_2.getText().toString().length() > 0) args.putString("cd2", et_cd_2.getText().toString());
                    else args.putString("cd2", "");
                    if (et_cd_3.getText().toString().length() > 0) args.putString("cd3", et_cd_3.getText().toString());
                    else args.putString("cd3", "");
                    if (et_cd_4.getText().toString().length() > 0) args.putString("cd4", et_cd_4.getText().toString());
                    else args.putString("cd4", "");
                    if (et_cd_5.getText().toString().length() > 0) args.putString("cd5", et_cd_5.getText().toString());
                    else args.putString("cd5", "");
                    if (et_cd_6.getText().toString().length() > 0) args.putString("cd6", et_cd_6.getText().toString());
                    else args.putString("cd6", "");
                    if (et_cd_7.getText().toString().length() > 0) args.putString("cd7", et_cd_7.getText().toString());
                    else args.putString("cd7", "");
                    if (et_cd_8.getText().toString().length() > 0) args.putString("cd8", et_cd_8.getText().toString());
                    else args.putString("cd8", "");
                    if (et_cd_9.getText().toString().length() > 0) args.putString("cd9", et_cd_9.getText().toString());
                    else args.putString("cd9", "");
                    if (et_cd_10.getText().toString().length() > 0) args.putString("cd10", et_cd_10.getText().toString());
                    else args.putString("cd10", "");
                    if (et_cd_11.getText().toString().length() > 0) args.putString("cd11", et_cd_11.getText().toString());
                    else args.putString("cd11", "");
                    if (et_cd_12.getText().toString().length() > 0) args.putString("cd12", et_cd_12.getText().toString());
                    else args.putString("cd12", "");
                    if (et_cd_13.getText().toString().length() > 0) args.putString("cd13", et_cd_13.getText().toString());
                    else args.putString("cd13", "");
                    if (et_cd_14.getText().toString().length() > 0) args.putString("cd14", et_cd_14.getText().toString());
                    else args.putString("cd14", "");
                    if (et_cd_15.getText().toString().length() > 0) args.putString("cd15", et_cd_15.getText().toString());
                    else args.putString("cd15", "");
                    if (et_cd_16.getText().toString().length() > 0) args.putString("cd16", et_cd_16.getText().toString());
                    else args.putString("cd16", "");
                    if (et_cd_17.getText().toString().length() > 0) args.putString("cd17", et_cd_17.getText().toString());
                    else args.putString("cd17", "");
                    if (et_cd_18.getText().toString().length() > 0) args.putString("cd18", et_cd_18.getText().toString());
                    else args.putString("cd18", "");
                    if (et_cd_19.getText().toString().length() > 0) args.putString("cd19", et_cd_19.getText().toString());
                    else args.putString("cd19", "");
                    if (et_cd_20.getText().toString().length() > 0) args.putString("cd20", et_cd_20.getText().toString());
                    else args.putString("cd20", "");

                    args.putString("price", editText_purchase.getText().toString());
                    fragment.setArguments(args);
                    ft.replace(R.id.content_fragment_layout, fragment);
                    ft.commit();
                }else { }
            }
        });

        return v;

    }

}
