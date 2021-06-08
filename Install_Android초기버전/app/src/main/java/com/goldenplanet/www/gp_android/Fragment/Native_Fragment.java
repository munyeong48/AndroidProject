package com.goldenplanet.www.gp_android.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.goldenplanet.www.gp_android.GoogleAnalytics.AnalyticsApplication;
import com.goldenplanet.www.gp_android.GoogleAnalytics.GoogleAnalyticsBuilder;
import com.goldenplanet.www.gp_android.MainActivity;
import com.goldenplanet.www.gp_android.R;
import com.goldenplanet.www.gp_android.SplashActivity;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.HashMap;
import java.util.Map;

public class Native_Fragment extends Fragment {

    private Tracker mTracker;
    private static Button btn_screenview;
    private static Button btn_event;
    private static Button btn_product_click;
    private static Fragment fragment = null;
    private static String TAG = "NativeFragment";
    private static EditText et_cd_1,et_cd_2,et_cd_3,et_cd_4,et_cd_5
            ,et_cd_6,et_cd_7,et_cd_8,et_cd_9,et_cd_10,et_cd_11,et_cd_12
            ,et_cd_13,et_cd_14,et_cd_15,et_cd_16,et_cd_17,et_cd_18,et_cd_19,et_cd_20;
    private static int customdimensionNumber = 1;
    public Native_Fragment() {

        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.activity_native, container, false);

        // [START shared_tracker]
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getActivity().getApplication();
        mTracker = application.getDefaultTracker();
        // [END shared_tracker]
        final GoogleAnalyticsBuilder googleAnalyticsBuilder = new GoogleAnalyticsBuilder(getActivity());

        com.google.firebase.analytics.FirebaseAnalytics mFirebaseAnalytics;
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());

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

        Button btn_customdimension_add = (Button)v.findViewById(R.id.btn_customdimension_add);
        Button btn_customdimension_remove = (Button)v.findViewById(R.id.btn_customdimension_remove);

        btn_customdimension_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(customdimensionNumber != 20){
                    LinearLayout linearLayout = null;
                    switch (customdimensionNumber){
                        case 1: ((LinearLayout) v.findViewById(R.id.linearlayout_custom2)).setVisibility(View.VISIBLE); break;
                        case 2: ((LinearLayout) v.findViewById(R.id.linearlayout_custom3)).setVisibility(View.VISIBLE); break;
                        case 3: ((LinearLayout) v.findViewById(R.id.linearlayout_custom4)).setVisibility(View.VISIBLE); break;
                        case 4: ((LinearLayout) v.findViewById(R.id.linearlayout_custom5)).setVisibility(View.VISIBLE); break;
                        case 5: ((LinearLayout) v.findViewById(R.id.linearlayout_custom6)).setVisibility(View.VISIBLE); break;
                        case 6: ((LinearLayout) v.findViewById(R.id.linearlayout_custom7)).setVisibility(View.VISIBLE); break;
                        case 7: ((LinearLayout) v.findViewById(R.id.linearlayout_custom8)).setVisibility(View.VISIBLE); break;
                        case 8: ((LinearLayout) v.findViewById(R.id.linearlayout_custom9)).setVisibility(View.VISIBLE); break;
                        case 9: ((LinearLayout) v.findViewById(R.id.linearlayout_custom10)).setVisibility(View.VISIBLE); break;
                        case 10: ((LinearLayout) v.findViewById(R.id.linearlayout_custom11)).setVisibility(View.VISIBLE); break;
                        case 11: ((LinearLayout) v.findViewById(R.id.linearlayout_custom12)).setVisibility(View.VISIBLE); break;
                        case 12: ((LinearLayout) v.findViewById(R.id.linearlayout_custom13)).setVisibility(View.VISIBLE); break;
                        case 13: ((LinearLayout) v.findViewById(R.id.linearlayout_custom14)).setVisibility(View.VISIBLE); break;
                        case 14: ((LinearLayout) v.findViewById(R.id.linearlayout_custom15)).setVisibility(View.VISIBLE); break;
                        case 15: ((LinearLayout) v.findViewById(R.id.linearlayout_custom16)).setVisibility(View.VISIBLE); break;
                        case 16: ((LinearLayout) v.findViewById(R.id.linearlayout_custom17)).setVisibility(View.VISIBLE); break;
                        case 17: ((LinearLayout) v.findViewById(R.id.linearlayout_custom18)).setVisibility(View.VISIBLE); break;
                        case 18: ((LinearLayout) v.findViewById(R.id.linearlayout_custom19)).setVisibility(View.VISIBLE); break;
                        case 19: ((LinearLayout) v.findViewById(R.id.linearlayout_custom20)).setVisibility(View.VISIBLE); break;
                    }
                    customdimensionNumber++;
                }
            }
        });

        btn_customdimension_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(customdimensionNumber != 1){
                    LinearLayout linearLayout = null;
                    switch (customdimensionNumber){
                        case 2: ((LinearLayout) v.findViewById(R.id.linearlayout_custom2)).setVisibility(View.GONE); break;
                        case 3: ((LinearLayout) v.findViewById(R.id.linearlayout_custom3)).setVisibility(View.GONE); break;
                        case 4: ((LinearLayout) v.findViewById(R.id.linearlayout_custom4)).setVisibility(View.GONE); break;
                        case 5: ((LinearLayout) v.findViewById(R.id.linearlayout_custom5)).setVisibility(View.GONE); break;
                        case 6: ((LinearLayout) v.findViewById(R.id.linearlayout_custom6)).setVisibility(View.GONE); break;
                        case 7: ((LinearLayout) v.findViewById(R.id.linearlayout_custom7)).setVisibility(View.GONE); break;
                        case 8: ((LinearLayout) v.findViewById(R.id.linearlayout_custom8)).setVisibility(View.GONE); break;
                        case 9: ((LinearLayout) v.findViewById(R.id.linearlayout_custom9)).setVisibility(View.GONE); break;
                        case 10: ((LinearLayout) v.findViewById(R.id.linearlayout_custom10)).setVisibility(View.GONE); break;
                        case 11: ((LinearLayout) v.findViewById(R.id.linearlayout_custom11)).setVisibility(View.GONE); break;
                        case 12: ((LinearLayout) v.findViewById(R.id.linearlayout_custom12)).setVisibility(View.GONE); break;
                        case 13: ((LinearLayout) v.findViewById(R.id.linearlayout_custom13)).setVisibility(View.GONE); break;
                        case 14: ((LinearLayout) v.findViewById(R.id.linearlayout_custom14)).setVisibility(View.GONE); break;
                        case 15: ((LinearLayout) v.findViewById(R.id.linearlayout_custom15)).setVisibility(View.GONE); break;
                        case 16: ((LinearLayout) v.findViewById(R.id.linearlayout_custom16)).setVisibility(View.GONE); break;
                        case 17: ((LinearLayout) v.findViewById(R.id.linearlayout_custom17)).setVisibility(View.GONE); break;
                        case 18: ((LinearLayout) v.findViewById(R.id.linearlayout_custom18)).setVisibility(View.GONE); break;
                        case 19: ((LinearLayout) v.findViewById(R.id.linearlayout_custom19)).setVisibility(View.GONE); break;
                        case 20: ((LinearLayout) v.findViewById(R.id.linearlayout_custom20)).setVisibility(View.GONE); break;
                    }
                    customdimensionNumber--;
                }
            }
        });
        btn_screenview = (Button)v.findViewById(R.id.button2);
        btn_event = (Button)v.findViewById(R.id.button3);
        btn_product_click = (Button)v.findViewById(R.id.btn_product_click);
        et_cd_1 = (EditText)v.findViewById(R.id.et_cd_1);
        et_cd_2 = (EditText)v.findViewById(R.id.et_cd_2);
        et_cd_3 = (EditText)v.findViewById(R.id.et_cd_3);
        et_cd_4 = (EditText)v.findViewById(R.id.et_cd_4);
        et_cd_5 = (EditText)v.findViewById(R.id.et_cd_5);
        et_cd_6 = (EditText)v.findViewById(R.id.et_cd_6);
        et_cd_7 = (EditText)v.findViewById(R.id.et_cd_7);
        et_cd_8 = (EditText)v.findViewById(R.id.et_cd_8);
        et_cd_9 = (EditText)v.findViewById(R.id.et_cd_9);
        et_cd_10 = (EditText)v.findViewById(R.id.et_cd_10);
        et_cd_11 = (EditText)v.findViewById(R.id.et_cd_11);
        et_cd_12 = (EditText)v.findViewById(R.id.et_cd_12);
        et_cd_13 = (EditText)v.findViewById(R.id.et_cd_13);
        et_cd_14 = (EditText)v.findViewById(R.id.et_cd_14);
        et_cd_15 = (EditText)v.findViewById(R.id.et_cd_15);
        et_cd_16 = (EditText)v.findViewById(R.id.et_cd_16);
        et_cd_17 = (EditText)v.findViewById(R.id.et_cd_17);
        et_cd_18 = (EditText)v.findViewById(R.id.et_cd_18);
        et_cd_19 = (EditText)v.findViewById(R.id.et_cd_19);
        et_cd_20 = (EditText)v.findViewById(R.id.et_cd_20);

        btn_screenview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> sendScreenMap = new HashMap<String, String>();
                if(et_cd_1.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1, et_cd_1.getText().toString());
                if(et_cd_2.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2, et_cd_2.getText().toString());
                if(et_cd_3.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3, et_cd_3.getText().toString());
                if(et_cd_4.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension4, et_cd_4.getText().toString());
                if(et_cd_5.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension5, et_cd_5.getText().toString());
                if(et_cd_6.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension6, et_cd_6.getText().toString());
                if(et_cd_7.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension7, et_cd_7.getText().toString());
                if(et_cd_8.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension8, et_cd_8.getText().toString());
                if(et_cd_9.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension9, et_cd_9.getText().toString());
                if(et_cd_10.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension10, et_cd_10.getText().toString());
                if(et_cd_11.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension11, et_cd_11.getText().toString());
                if(et_cd_12.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension12, et_cd_12.getText().toString());
                if(et_cd_13.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension13, et_cd_13.getText().toString());
                if(et_cd_14.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension14, et_cd_14.getText().toString());
                if(et_cd_15.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension15, et_cd_15.getText().toString());
                if(et_cd_16.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension16, et_cd_16.getText().toString());
                if(et_cd_17.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension17, et_cd_17.getText().toString());
                if(et_cd_18.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension18, et_cd_18.getText().toString());
                if(et_cd_19.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension19, et_cd_19.getText().toString());
                if(et_cd_20.getText().toString().length() > 0) sendScreenMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension20, et_cd_20.getText().toString());

                sendScreenMap.put(GoogleAnalyticsBuilder.GAHitKey.Title,SplashActivity.title);
                googleAnalyticsBuilder.GADataSend_Screen(sendScreenMap);
                Toast.makeText(getActivity(), "스크린뷰 전송", Toast.LENGTH_LONG).show();
            }
        });

        btn_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> sendEventMap = new HashMap<String, String>();
                if(et_cd_1.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1, et_cd_1.getText().toString());
                if(et_cd_2.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2, et_cd_2.getText().toString());
                if(et_cd_3.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3, et_cd_3.getText().toString());
                if(et_cd_4.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension4, et_cd_4.getText().toString());
                if(et_cd_5.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension5, et_cd_5.getText().toString());
                if(et_cd_6.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension6, et_cd_6.getText().toString());
                if(et_cd_7.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension7, et_cd_7.getText().toString());
                if(et_cd_8.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension8, et_cd_8.getText().toString());
                if(et_cd_9.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension9, et_cd_9.getText().toString());
                if(et_cd_10.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension10, et_cd_10.getText().toString());
                if(et_cd_11.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension11, et_cd_11.getText().toString());
                if(et_cd_12.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension12, et_cd_12.getText().toString());
                if(et_cd_13.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension13, et_cd_13.getText().toString());
                if(et_cd_14.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension14, et_cd_14.getText().toString());
                if(et_cd_15.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension15, et_cd_15.getText().toString());
                if(et_cd_16.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension16, et_cd_16.getText().toString());
                if(et_cd_17.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension17, et_cd_17.getText().toString());
                if(et_cd_18.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension18, et_cd_18.getText().toString());
                if(et_cd_19.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension19, et_cd_19.getText().toString());
                if(et_cd_20.getText().toString().length() > 0) sendEventMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension20, et_cd_20.getText().toString());
                sendEventMap.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory, "Category_Android");
                sendEventMap.put(GoogleAnalyticsBuilder.GAHitKey.EventAction, "Action_Android");
                sendEventMap.put(GoogleAnalyticsBuilder.GAHitKey.EventLabel, "Label_Android");
                googleAnalyticsBuilder.GADataSend_Event(sendEventMap);
                Toast.makeText(getActivity(), "이벤트 전송", Toast.LENGTH_LONG).show();
            }
        });


        btn_product_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> ProductMap = new HashMap<String, String>();
                ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductID, "GP_Product");
                ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductName, "GP_Product");
                ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductCategory, "GA Test");
                ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductBrand, "GP");
                ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductVariant, "Black");
                ProductMap.put(GoogleAnalyticsBuilder.GAProductKey.ProductPosition, "1");

                Map<String, Map<String, String>> product_Map = new HashMap<String, Map<String, String>>();
                product_Map.put("pr1",ProductMap);

                Map<String, String> ProductActionMap = new HashMap<String, String>();
                ProductActionMap.put(GoogleAnalyticsBuilder.GAActionFieldKey.ProductActionList, "ProductActionList");

                Map<String, String> EcommerceMap = new HashMap<String, String>();
                EcommerceMap.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory, "EV_ProductClick_Category");
                EcommerceMap.put(GoogleAnalyticsBuilder.GAHitKey.EventAction, "EV_ProductClick_Action");
                EcommerceMap.put(GoogleAnalyticsBuilder.GAHitKey.EventLabel, "EV_ProductClick_Label");


                if(et_cd_1.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1, et_cd_1.getText().toString());
                if(et_cd_2.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2, et_cd_2.getText().toString());
                if(et_cd_3.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3, et_cd_3.getText().toString());
                if(et_cd_4.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension4, et_cd_4.getText().toString());
                if(et_cd_5.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension5, et_cd_5.getText().toString());
                if(et_cd_6.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension6, et_cd_6.getText().toString());
                if(et_cd_7.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension7, et_cd_7.getText().toString());
                if(et_cd_8.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension8, et_cd_8.getText().toString());
                if(et_cd_9.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension9, et_cd_9.getText().toString());
                if(et_cd_10.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension10, et_cd_10.getText().toString());
                if(et_cd_11.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension11, et_cd_11.getText().toString());
                if(et_cd_12.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension12, et_cd_12.getText().toString());
                if(et_cd_13.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension13, et_cd_13.getText().toString());
                if(et_cd_14.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension14, et_cd_14.getText().toString());
                if(et_cd_15.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension15, et_cd_15.getText().toString());
                if(et_cd_16.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension16, et_cd_16.getText().toString());
                if(et_cd_17.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension17, et_cd_17.getText().toString());
                if(et_cd_18.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension18, et_cd_18.getText().toString());
                if(et_cd_19.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension19, et_cd_19.getText().toString());
                if(et_cd_20.getText().toString().length() > 0) EcommerceMap.put(GoogleAnalyticsBuilder.GACustomKey.Dimension20, et_cd_20.getText().toString());

                googleAnalyticsBuilder.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Click,ProductActionMap,product_Map,EcommerceMap);

                fragment = new ProductDetail();
                Bundle args = new Bundle();
                if(et_cd_1.getText().toString().length() > 0) args.putString("cd1", et_cd_1.getText().toString());
                else { args.putString("cd1","");}
                if(et_cd_2.getText().toString().length() > 0) args.putString("cd2", et_cd_2.getText().toString());
                else { args.putString("cd2","");}
                if(et_cd_3.getText().toString().length() > 0) args.putString("cd3", et_cd_3.getText().toString());
                else { args.putString("cd3","");}
                if(et_cd_4.getText().toString().length() > 0) args.putString("cd4", et_cd_4.getText().toString());
                else { args.putString("cd4","");}
                if(et_cd_5.getText().toString().length() > 0) args.putString("cd5", et_cd_5.getText().toString());
                else { args.putString("cd5","");}
                if(et_cd_6.getText().toString().length() > 0) args.putString("cd6", et_cd_6.getText().toString());
                else { args.putString("cd6","");}
                if(et_cd_7.getText().toString().length() > 0) args.putString("cd7", et_cd_7.getText().toString());
                else { args.putString("cd7","");}
                if(et_cd_8.getText().toString().length() > 0) args.putString("cd8", et_cd_8.getText().toString());
                else { args.putString("cd8","");}
                if(et_cd_9.getText().toString().length() > 0) args.putString("cd9", et_cd_9.getText().toString());
                else { args.putString("cd9","");}
                if(et_cd_10.getText().toString().length() > 0) args.putString("cd10", et_cd_10.getText().toString());
                else { args.putString("cd10","");}
                if(et_cd_11.getText().toString().length() > 0) args.putString("cd11", et_cd_11.getText().toString());
                else { args.putString("cd11","");}
                if(et_cd_12.getText().toString().length() > 0) args.putString("cd12", et_cd_12.getText().toString());
                else { args.putString("cd12","");}
                if(et_cd_13.getText().toString().length() > 0) args.putString("cd13", et_cd_13.getText().toString());
                else { args.putString("cd13","");}
                if(et_cd_14.getText().toString().length() > 0) args.putString("cd14", et_cd_14.getText().toString());
                else { args.putString("cd14","");}
                if(et_cd_15.getText().toString().length() > 0) args.putString("cd15", et_cd_15.getText().toString());
                else { args.putString("cd15","");}
                if(et_cd_16.getText().toString().length() > 0) args.putString("cd16", et_cd_16.getText().toString());
                else { args.putString("cd16","");}
                if(et_cd_17.getText().toString().length() > 0) args.putString("cd17", et_cd_17.getText().toString());
                else { args.putString("cd17","");}
                if(et_cd_18.getText().toString().length() > 0) args.putString("cd18", et_cd_18.getText().toString());
                else { args.putString("cd18","");}
                if(et_cd_19.getText().toString().length() > 0) args.putString("cd19", et_cd_19.getText().toString());
                else { args.putString("cd19","");}
                if(et_cd_20.getText().toString().length() > 0) args.putString("cd20", et_cd_20.getText().toString());
                else { args.putString("cd20","");}

                fragment.setArguments(args);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_fragment_layout, fragment);
                ft.commit();
            }
        });


        return v;
    }
}
