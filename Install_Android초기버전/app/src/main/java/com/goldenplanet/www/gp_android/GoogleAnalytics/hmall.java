package com.goldenplanet.www.gp_android.GoogleAnalytics;

import android.app.Activity;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class hmall {

    Tracker mTracker;

    public hmall(Activity activity) {
        AnalyticsApplication application = (AnalyticsApplication) ((Activity) activity).getApplication();
        mTracker = application.getDefaultTracker();
    }

    public static class GACustomKey {
        public static String Dimension1 = "dimension1", Dimension2 = "dimension2", Dimension3 = "dimension3", Dimension4 = "dimension4", Dimension5 = "dimension5";
        public static String Dimension6 = "dimension6", Dimension7 = "dimension7", Dimension8 = "dimension8", Dimension9 = "dimension9", Dimension10 = "dimension10";
        public static String Dimension11 = "dimension11", Dimension12 = "dimension12", Dimension13 = "dimension13", Dimension14 = "dimension14", Dimension15 = "dimension15";
        public static String Dimension16 = "dimension16", Dimension17 = "dimension17", Dimension18 = "dimension18", Dimension19 = "dimension4", Dimension20 = "dimension20";
        public static String Dimension21 = "dimension21", Dimension22 = "dimension22", Dimension23 = "dimension23", Dimension24 = "dimension24", Dimension25 = "dimension25";
        public static String Dimension26 = "dimension26", Dimension27 = "dimension27", Dimension28 = "dimension28", Dimension29 = "dimension29", Dimension30 = "dimension30";
        public static String Dimension31 = "dimension31", Dimension32 = "dimension32", Dimension33 = "dimension33", Dimension34 = "dimension34", Dimension35 = "dimension35";
        public static String Dimension36 = "dimension36", Dimension37 = "dimension37", Dimension38 = "dimension38", Dimension39 = "dimension39", Dimension40 = "dimension40";
        public static String Dimension41 = "dimension41", Dimension42 = "dimension42", Dimension43 = "dimension43", Dimension44 = "dimension44", Dimension45 = "dimension45";
        public static String Dimension46 = "dimension46", Dimension47 = "dimension47", Dimension48 = "dimension48", Dimension49 = "dimension49", Dimension50 = "dimension50";

        public static String Metric1 = "metric1", Metric2 = "metric2", Metric3 = "metric3", Metric4 = "metric4", Metric5 = "metric5";
        public static String Metric6 = "metric6", Metric7 = "metric7", Metric8 = "metric8", Metric9 = "metric9", Metric10 = "metric9";
        public static String Metric11 = "metric11", Metric12 = "metric12", Metric13 = "metric13", Metric14 = "metric14", Metric15 = "metric15";
        public static String Metric16 = "metric16", Metric17 = "metric17", Metric18 = "metric18", Metric19 = "metric19", Metric20 = "metric20";
    }

    public static class GAEcommerceStepKey {
        public static String Impression = "impression";
        public static String Detail = "detail";
        public static String Click = "click";
        public static String Add = "add";
        public static String Remove = "remove";
        public static String Checkout = "checkout";
        public static String Purchase = "purchase";
        public static String Refund = "refund";
        public static String PromotionImpression = "promotionimpression";
        public static String PromotionClick = "promotionclick";
    }

    public static class GAHitKey {
        public static String UserID = "uid";
        public static String CampaignUrl = "camp_url";
        public static String Title = "title";
        public static String EventCategory = "category";
        public static String EventAction = "action";
        public static String EventLabel = "label";
        public static String EventValue = "value";
        public static String TimingValue = "timingvalue";
        public static String TimingCategory = "";
        public static String NonInteraction = "ni";
        public static String PromotionID = "promo_id";
        public static String PromotionName = "promo_nm";
        public static String PromotionCreative = "promo_cr";
        public static String PromotionPosition = "promo_ps";
        public static String CurrencyCode = "currencycode";
    }

    public static class GAActionFieldKey {
        public static String TransactionID = "af_id";
        public static String TransactionRevenue = "af_revenue";
        public static String TransactionTax = "af_tax";
        public static String TransactionShipping = "af_shipping";
        public static String TransactionCouponCode = "af_coupon";
        public static String TransactionAffiliation = "af_affiliation";
        public static String ProductActionList = "af_list";
        public static String CheckoutStep = "af_step";
        public static String CheckoutOptions = "af_option";
    }

    public static class GAProductKey {
        public static String ProductID = "prid";
        public static String ProductName = "prnm";
        public static String ProductBrand = "prbr";
        public static String ProductCategory = "prca";
        public static String ProductVariant = "prva";
        public static String ProductPrice = "prpr";
        public static String ProductQuantity = "prqt";
        public static String ProductCouponCode = "prcc";
        public static String ProductPosition = "prps";
        public static String ImpressionList = "prlist";

        public static String ProdudctDimension1 = "prcd1", ProdudctDimension2 = "prcd2", ProdudctDimension3 = "prcd3", ProdudctDimension4 = "prcd4", ProdudctDimension5 = "prcd5";
        public static String ProdudctDimension6 = "prcd6", ProdudctDimension7 = "prcd7", ProdudctDimension8 = "prcd8", ProdudctDimension9 = "prcd9", ProdudctDimension10 = "prcd10";
        public static String ProdudctDimension11 = "prcd11", ProdudctDimension12 = "prcd12", ProdudctDimension13 = "prcd13", ProdudctDimension14 = "prcd14", ProdudctDimension15 = "prcd15";
        public static String ProdudctDimension16 = "prcd16", ProdudctDimension17 = "prcd17", ProdudctDimension18 = "prcd18", ProdudctDimension19 = "prcd19", ProdudctDimension20 = "prcd20";
        public static String ProdudctDimension21 = "prcd21", ProdudctDimension22 = "prcd22", ProdudctDimension23 = "prcd23", ProdudctDimension24 = "prcd24", ProdudctDimension25 = "prcd25";
        public static String ProdudctDimension26 = "prcd26", ProdudctDimension27 = "prcd27", ProdudctDimension28 = "prcd28", ProdudctDimension29 = "prcd29", ProdudctDimension30 = "prcd30";
        public static String ProdudctDimension31 = "prcd31", ProdudctDimension32 = "prcd32", ProdudctDimension33 = "prcd33", ProdudctDimension34 = "prcd34", ProdudctDimension35 = "prcd35";
        public static String ProdudctDimension36 = "prcd36", ProdudctDimension37 = "prcd37", ProdudctDimension38 = "prcd38", ProdudctDimension39 = "prcd39", ProdudctDimension40 = "prcd40";
        public static String ProdudctDimension41 = "prcd41", ProdudctDimension42 = "prcd42", ProdudctDimension43 = "prcd43", ProdudctDimension44 = "prcd44", ProdudctDimension45 = "prcd45";
        public static String ProdudctDimension46 = "prcd46", ProdudctDimension47 = "prcd47", ProdudctDimension48 = "prcd48", ProdudctDimension49 = "prcd49", ProdudctDimension50 = "prcd50";

        public static String ProdudctMetric1 = "prme1", ProdudctMetric2 = "prme2", ProdudctMetric3 = "prme3", ProdudctMetric4 = "prme4", ProdudctMetric5 = "prme5";
        public static String ProdudctMetric6 = "prme6", ProdudctMetric7 = "prme7", ProdudctMetric8 = "prme8", ProdudctMetric9 = "prme9", ProdudctMetric10 = "prme10";
        public static String ProdudctMetric11 = "prme11", ProdudctMetric12 = "prme12", ProdudctMetric13 = "prme13", ProdudctMetric14 = "prme14", ProdudctMetric15 = "prme15";
        public static String ProdudctMetric16 = "prme16", ProdudctMetric17 = "prme17", ProdudctMetric18 = "prme18", ProdudctMetric19 = "prme19", ProdudctMetric20 = "prme20";
    }

    public void GADataSend_Event(Map<String, String> GAInfo) {
        try {
            HitBuilders.EventBuilder eventBuilder = new HitBuilders.EventBuilder();
            String Category, Action, Label;
            Iterator<String> sIterator = GAInfo.keySet().iterator();
            Promotion promotion = new Promotion();
            while (sIterator.hasNext()) {
                String key = sIterator.next();
                if (GAInfo.get(key) != null && GAInfo.get(key).length() > 0) {
                    if (key.toLowerCase().equals("dimension")) {
                        int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                        eventBuilder.setCustomDimension(Number, GAInfo.get(key));
                    }

                    if (key.toLowerCase().equals("metric")) {
                        int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                        eventBuilder.setCustomMetric(Number, Float.parseFloat(GAInfo.get(key)));
                    }

                    if (key.toLowerCase().equals("uid"))
                        mTracker.set("&uid", GAInfo.get(key));

                    if (key.toLowerCase().equals("camp_url")) {
                        String URL = GAInfo.get(key);
                        URL = URLDecoder.decode(URL, "UTF-8");
                        URL = URLDecoder.decode(URL, "UTF-8");
                        eventBuilder.setCampaignParamsFromUrl(URL);
                    }

                    if (key.toLowerCase().equals("category"))
                        eventBuilder.setCategory(GAInfo.get(key));

                    if (key.toLowerCase().equals("action"))
                        eventBuilder.setAction(GAInfo.get(key));

                    if (key.toLowerCase().equals("label"))
                        eventBuilder.setLabel(GAInfo.get(key));

                    if (key.toLowerCase().equals("value"))
                        eventBuilder.setValue(Long.parseLong(GAInfo.get(key)));

                    if (key.toLowerCase().equals("title"))
                        mTracker.setScreenName(GAInfo.get(key));

                    if (key.toLowerCase().equals("ni") && GAInfo.get(key).equals("1"))
                        eventBuilder.setNonInteraction(true);
                }
            }
            if (promotion.toString() != "") {
                eventBuilder.addPromotion(promotion);
                eventBuilder.setPromotionAction(Promotion.ACTION_CLICK);
            }

            mTracker.send(eventBuilder.build());
            mTracker = NullSet(mTracker);
        } catch (Exception e) {
            Log.e("GAv4_Event", e.getMessage());
        }
    }

    public void GADataSend_Screen(Map<String, String> GAInfo) {
        try {

            HitBuilders.ScreenViewBuilder screenViewBuilder = new HitBuilders.ScreenViewBuilder();
            String Category, Action, Label;
            Iterator<String> sIterator = GAInfo.keySet().iterator();
            while (sIterator.hasNext()) {
                String key = sIterator.next();
                if (GAInfo.get(key) != null && GAInfo.get(key).length() > 0) {
                    if (key.toLowerCase().contains("dimension")) {
                        int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                        screenViewBuilder.setCustomDimension(Number, GAInfo.get(key));
                    }
                    if (key.toLowerCase().contains("metric")) {
                        int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                        screenViewBuilder.setCustomMetric(Number, Float.parseFloat(GAInfo.get(key)));
                    }
                    if (key.toLowerCase().equals("camp_url")) {
                        String URL = GAInfo.get(key);
                        URL = URLDecoder.decode(URL, "UTF-8");
                        URL = URLDecoder.decode(URL, "UTF-8");
                        screenViewBuilder.setCampaignParamsFromUrl(URL);
                    }
                    if (key.toLowerCase().equals("uid"))
                        mTracker.set("&uid", GAInfo.get(key));

                    if (key.toLowerCase().equals("title"))
                        mTracker.setScreenName(GAInfo.get(key));

                    if (key.toLowerCase().equals("ni") && GAInfo.get(key).equals("1"))
                        screenViewBuilder.setNonInteraction(true);

                }
            }
            mTracker.send(screenViewBuilder.build());
            mTracker = NullSet(mTracker);
        } catch (Exception e) {
            Log.e("GAv4_Screen", e.getMessage());
        }
    }

    public void GADataSend_Ecommerce(String EcommerceStep, Map<String, String> GAAction, Map<String, Map<String, String>> GAProduct, Map<String, String> GAInfo) {
        try {
            if (mTracker != null) {
                HitBuilders.EventBuilder ecommerceBuilder = new HitBuilders.EventBuilder();
                Iterator<String> sIterator_GAInfo = GAInfo.keySet().iterator();

                //제품클릭 단계
                if (EcommerceStep.toLowerCase().equals("click")) {
                    ProductAction pa = new ProductAction(ProductAction.ACTION_CLICK);
                    pa = GA_ActionField(GAAction, pa);
                    ecommerceBuilder.setProductAction(pa);
                }

                //디테일 단계
                if (EcommerceStep.toLowerCase().equals("detail")) {
                    ProductAction pa = new ProductAction(ProductAction.ACTION_DETAIL);
                    pa = GA_ActionField(GAAction, pa);
                    ecommerceBuilder.setProductAction(pa);
                }

                //장바구니추가 단계
                if (EcommerceStep.toLowerCase().equals("add")) {
                    ProductAction pa = new ProductAction(ProductAction.ACTION_ADD);
                    pa = GA_ActionField(GAAction, pa);
                    ecommerceBuilder.setProductAction(pa);
                }

                //장바구니삭제 단계
                if (EcommerceStep.toLowerCase().equals("remove")) {
                    ProductAction pa = new ProductAction(ProductAction.ACTION_REMOVE);
                    pa = GA_ActionField(GAAction, pa);
                    ecommerceBuilder.setProductAction(pa);
                }

                //체크아웃 단계
                if (EcommerceStep.toLowerCase().equals("checkout")) {
                    ProductAction pa = new ProductAction(ProductAction.ACTION_CHECKOUT);
                    pa = GA_ActionField(GAAction, pa);
                    ecommerceBuilder.setProductAction(pa);
                }

                //결제 단계
                if (EcommerceStep.toLowerCase().equals("purchase")) {
                    ProductAction pa = new ProductAction(ProductAction.ACTION_PURCHASE);
                    pa = GA_ActionField(GAAction, pa);
                    ecommerceBuilder.setProductAction(pa);
                }

                //리펀드 단계
                if (EcommerceStep.toLowerCase().equals("refund")) {
                    ProductAction pa = new ProductAction(ProductAction.ACTION_REFUND);
                    pa = GA_ActionField(GAAction, pa);
                    ecommerceBuilder.setProductAction(pa);
                }

                while (sIterator_GAInfo.hasNext()) {
                    String key = sIterator_GAInfo.next();
                    if (GAInfo.get(key) != null && GAInfo.get(key).length() > 0) {
                        if (key.toLowerCase().contains("dimension")) {
                            int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                            ecommerceBuilder.setCustomDimension(Number, GAInfo.get(key));
                        }
                        if (key.toLowerCase().contains("metric")) {
                            int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                            ecommerceBuilder.setCustomMetric(Number, Float.parseFloat(GAInfo.get(key)));
                        }
                        if (key.toLowerCase().equals("uid"))
                            mTracker.set("&uid", GAInfo.get(key));

                        if (key.toLowerCase().equals("title")) {
                            if (GAInfo.get("title") != null && GAInfo.get("title").length() > 0)
                                mTracker.setScreenName(GAInfo.get("title"));
                        }
                        if (key.toLowerCase().equals("currencycode")) {
                            mTracker.set("&cu", GAInfo.get("currencycode"));
                        }

                        if (key.toLowerCase().equals("camp_url")) {
                            String URL = GAInfo.get(key);
                            URL = URLDecoder.decode(URL, "UTF-8");
                            URL = URLDecoder.decode(URL, "UTF-8");
                            ecommerceBuilder.setCampaignParamsFromUrl(URL);
                        }

                        if (key.toLowerCase().equals("cu")) {
                            mTracker.set("&cu", GAInfo.get("cu"));
                        }

                        if (key.toLowerCase().equals("category"))
                            ecommerceBuilder.setCategory(GAInfo.get("category"));

                        if (key.toLowerCase().equals("action"))
                            ecommerceBuilder.setAction(GAInfo.get("action"));

                        if (key.toLowerCase().equals("label"))
                            ecommerceBuilder.setLabel(GAInfo.get("label"));

                        if (key.toLowerCase().equals("ni") && GAInfo.get(key).equals("1"))
                            ecommerceBuilder.setNonInteraction(true);
                    }
                }

                if (EcommerceStep.toLowerCase().contains("promotion")) {
                    Promotion promotion = new Promotion();
                    sIterator_GAInfo = GAInfo.keySet().iterator();
                    while (sIterator_GAInfo.hasNext()) {
                        String key = sIterator_GAInfo.next();
                        if (GAInfo.get(key) != null && GAInfo.get(key).length() > 0) {
                            if (key.toLowerCase().equals("promo_id"))
                                promotion.setId(GAInfo.get(key));

                            if (key.toLowerCase().equals("promo_nm"))
                                promotion.setName(GAInfo.get(key));

                            if (key.toLowerCase().equals("promo_cr"))
                                promotion.setCreative(GAInfo.get(key));

                            if (key.toLowerCase().equals("promo_ps"))
                                promotion.setPosition(GAInfo.get(key));
                        }
                    }
                    ecommerceBuilder.addPromotion(promotion);
                    if (EcommerceStep.toLowerCase().equals("promotionclick"))
                        ecommerceBuilder.setPromotionAction(Promotion.ACTION_CLICK);
                } else {
                    ecommerceBuilder = GA_Product(EcommerceStep, GAProduct, ecommerceBuilder);
                }

                mTracker.send(ecommerceBuilder.build());
                mTracker = NullSet(mTracker);
            }
        } catch (Exception e) {
            Log.e("GAv4_Ecommerce", e.getMessage());
        }
    }

    private static Tracker NullSet(Tracker mTracker) {
        mTracker.setScreenName(null);

        mTracker.set("&cu", null);
        mTracker.set("&uid", null);
        return mTracker;
    }

    private ProductAction GA_ActionField(Map<String, String> GAAction, ProductAction productAction) {
        try {
            Iterator<String> sIterator_GA_ActionField = GAAction.keySet().iterator();
            while (sIterator_GA_ActionField.hasNext()) {
                String actionField_key = sIterator_GA_ActionField.next();
                if (GAAction.get(actionField_key) != null && GAAction.get(actionField_key).length() > 0) {
                    if (actionField_key.toLowerCase().equals("af_id"))
                        productAction.setTransactionId(GAAction.get("af_id"));
                    if (actionField_key.toLowerCase().equals("af_revenue"))
                        productAction.setTransactionRevenue(Double.valueOf(GAAction.get("af_revenue")));
                    if (actionField_key.toLowerCase().equals("af_tax"))
                        productAction.setTransactionTax(Double.valueOf(GAAction.get("af_tax")));
                    if (actionField_key.toLowerCase().equals("af_shipping"))
                        productAction.setTransactionShipping(Double.valueOf(GAAction.get("af_shipping")));
                    if (actionField_key.toLowerCase().equals("af_coupon"))
                        productAction.setTransactionCouponCode(GAAction.get("af_coupon"));
                    if (actionField_key.toLowerCase().equals("af_affiliation"))
                        productAction.setTransactionAffiliation(GAAction.get("af_affiliation"));
                    if (actionField_key.toLowerCase().equals("af_list"))
                        productAction.setProductActionList(GAAction.get("af_list"));
                    if (actionField_key.toLowerCase().equals("af_step"))
                        productAction.setCheckoutStep(Integer.parseInt(GAAction.get("af_step")));
                    if (actionField_key.toLowerCase().equals("af_option"))
                        productAction.setCheckoutOptions(GAAction.get("af_option"));
                }
            }
            return productAction;
        } catch (Exception ex) {
            Log.e("GAv4_ActionField", ex.getMessage());
            return null;
        }
    }

    private HitBuilders.EventBuilder GA_Product(String EcommerceStep, Map<String, Map<String, String>> GAProduct, HitBuilders.EventBuilder ecommerceBuilder) {
        try {
            if (GAProduct != null && GAProduct.size() > 0) {
                Iterator<String> sIterator_GAProduct = GAProduct.keySet().iterator();
                while (sIterator_GAProduct.hasNext()) {
                    Product product = new Product();
                    String prd_list = "ImpressionList";
                    String key = sIterator_GAProduct.next();

                    if (key.contains("pr")) {

                        Map<String, String> Productinfo = GAProduct.get(key);
                        Iterator<String> sIterator_Productinfo = Productinfo.keySet().iterator();

                        while (sIterator_Productinfo.hasNext()) {
                            String product_key = sIterator_Productinfo.next();
                            if (Productinfo.get(product_key) != null && Productinfo.get(product_key).length() > 0) {
                                if (product_key.toLowerCase().equals("prid")) {
                                    product.setId(Productinfo.get("prid"));
                                }
                                if (product_key.toLowerCase().equals("prnm")) {
                                    product.setName(Productinfo.get("prnm"));
                                }
                                if (product_key.toLowerCase().equals("prbr")) {
                                    product.setBrand(Productinfo.get("prbr"));
                                }
                                if (product_key.toLowerCase().equals("prca")) {
                                    product.setCategory(Productinfo.get("prca"));
                                }
                                if (product_key.toLowerCase().equals("prva")) {
                                    product.setVariant(Productinfo.get("prva"));
                                }
                                if (product_key.toLowerCase().equals("prpr")) {
                                    product.setPrice(Double.valueOf(Productinfo.get("prpr")));
                                }
                                if (product_key.toLowerCase().equals("prqt")) {
                                    product.setQuantity(Integer.parseInt(Productinfo.get("prqt")));
                                }
                                if (product_key.toLowerCase().equals("prcc")) {
                                    product.setCouponCode(Productinfo.get("prcc"));
                                }
                                if (product_key.equals("prps")) {
                                    product.setPosition(Integer.parseInt(Productinfo.get("prps")));
                                }
                                if (product_key.toLowerCase().equals("prlist")) {
                                    prd_list = Productinfo.get("prlist");
                                }
                                if (product_key.toLowerCase().contains("prcd")) {
                                    int Number = Integer.parseInt(product_key.replaceAll("[^0-9]", ""));
                                    product.setCustomDimension(Number, Productinfo.get(product_key));
                                }
                                if (product_key.toLowerCase().contains("prme")) {
                                    int Number = Integer.parseInt(product_key.replaceAll("[^0-9]", ""));
                                    product.setCustomMetric(Number, Integer.valueOf(Productinfo.get(product_key)));
                                }
                            }
                        }
                    }
                    if (!EcommerceStep.toLowerCase().equals("impression")) {
                        ecommerceBuilder.addProduct(product);
                    } else {
                        ecommerceBuilder.addImpression(product, prd_list);
                    }
                }
            }
            return ecommerceBuilder;
        } catch (Exception e) {
            Log.e("GAv4_Product", e.getMessage());
            return null;
        }
    }

    public static class WebAppInterface {
        Tracker mTracker;

        public WebAppInterface(Activity activity) {
            AnalyticsApplication application = (AnalyticsApplication) ((Activity) activity).getApplication();
            mTracker = application.getDefaultTracker();
        }

        ProductAction productAction = null;
        JSONObject ecommerce_data = null;

        @JavascriptInterface
        public void GA_DATA(String JsonData) {
            try {
                HitBuilders.EventBuilder event_builder = new HitBuilders.EventBuilder();
                HitBuilders.ScreenViewBuilder Screenview_Builder = new HitBuilders.ScreenViewBuilder();
                JSONObject data = new JSONObject(JsonData);

                String sType = "";
                String Impression_List = "";
                if (data.has("type")) sType = data.getString("type");

                if (data.has("dimension")) {
                    JSONObject obj_dimension = data.getJSONObject("dimension");
                    Iterator<String> sIterator = obj_dimension.keys();
                    while (sIterator.hasNext()) {
                        String key = sIterator.next();
                        int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                        if (sType.equals("P"))
                            Screenview_Builder.setCustomDimension(Number, obj_dimension.getString(key));
                        else event_builder.setCustomDimension(Number, obj_dimension.getString(key));
                    }
                }

                if (data.has("metric")) {
                            JSONObject obj_metric = data.getJSONObject("metric");
                            Iterator<String> sIterator = obj_metric.keys();
                            while (sIterator.hasNext()) {
                                // get key
                                String key = sIterator.next();
                                int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                                if (sType.equals("P"))
                                    Screenview_Builder.setCustomMetric(Number, Float.parseFloat(Double.toString(obj_metric.getDouble(key))));
                                else
                            event_builder.setCustomMetric(Number, Float.parseFloat(Double.toString(obj_metric.getDouble(key))));
                    }
                }
                if (data.has("nonInteraction")) event_builder.setNonInteraction(true);

                if (data.has("title")) mTracker.setScreenName(data.getString("title"));

                //전자상거래
                if (data.has("ecommerce")) {

                    JSONObject obj_ecommerce = data.getJSONObject("ecommerce");
                    if (obj_ecommerce.has("currencyCode"))
                        mTracker.set("&cu", obj_ecommerce.getString("currencyCode"));


                    //제품 전시 단계
                    if (obj_ecommerce.has("promoview") || obj_ecommerce.has("promoclick")) {
                        if (obj_ecommerce.has("promoview"))
                            ecommerce_data = obj_ecommerce.getJSONObject("promoview");
                        if (obj_ecommerce.has("promoclick"))
                            ecommerce_data = obj_ecommerce.getJSONObject("promoclick");
                        if (ecommerce_data.has("promotions")) {
                            JSONArray product_array = new JSONArray(ecommerce_data.getString("promotions"));
                            List product_list = ConvertJsonArray(product_array);
                            Promotion promotion = new Promotion();
                            for (int i = 0; i < product_list.size(); i++) {
                                HashMap<String, String> product_Hashmap = (HashMap<String, String>) product_list.get(i);

                                Iterator<String> keys = product_Hashmap.keySet().iterator();
                                while (keys.hasNext()) {
                                    String key = keys.next();
                                    if (key.contains("id"))
                                        promotion.setId(product_Hashmap.get(key));
                                    if (key.contains("name"))
                                        promotion.setName(product_Hashmap.get(key));
                                    if (key.contains("creative"))
                                        promotion.setCreative(product_Hashmap.get(key));
                                    if (key.contains("position"))
                                        promotion.setPosition(product_Hashmap.get(key));
                                }
                            }

                            if (obj_ecommerce.has("promoview") && sType.equals("P")) {
                                Screenview_Builder.addPromotion(promotion);
                            } else if (obj_ecommerce.has("promoview") && !sType.equals("P")) {
                                event_builder.addPromotion(promotion);
                            } else if (obj_ecommerce.has("promoclick") && sType.equals("P")) {
                                Screenview_Builder.setPromotionAction(Promotion.ACTION_CLICK);
                                Screenview_Builder.addPromotion(promotion);
                            } else if (obj_ecommerce.has("promoclick") && !sType.equals("P")) {
                                event_builder.setPromotionAction(Promotion.ACTION_CLICK);
                                event_builder.addPromotion(promotion);
                            }

                        }
                    }

                    if (obj_ecommerce.has("impressions")) {
                        ecommerce_data = obj_ecommerce.getJSONObject("impressions");
                        if (ecommerce_data.has("actionField")) {
                            JSONObject actionField_data = ecommerce_data.getJSONObject("actionField");
                            if (actionField_data.has("list")) {
                                Impression_List = actionField_data.getString("list");
                            }
                        }
                    }

                    //제품클릭 단계
                    if (obj_ecommerce.has("click")) {
                        productAction = new ProductAction(ProductAction.ACTION_CLICK);
                        ecommerce_data = obj_ecommerce.getJSONObject("click");
                        GA_ActionField();
                    }

                    //디테일 단계
                    if (obj_ecommerce.has("detail")) {
                        productAction = new ProductAction(ProductAction.ACTION_DETAIL);
                        ecommerce_data = obj_ecommerce.getJSONObject("detail");
                        GA_ActionField();
                    }
                    //장바구니추가 단계
                    if (obj_ecommerce.has("add")) {
                        productAction = new ProductAction(ProductAction.ACTION_ADD);
                        ecommerce_data = obj_ecommerce.getJSONObject("add");
                        GA_ActionField();
                    }

                    //장바구니삭제 단계
                    if (obj_ecommerce.has("remove")) {
                        productAction = new ProductAction(ProductAction.ACTION_REMOVE);
                        ecommerce_data = obj_ecommerce.getJSONObject("remove");
                    }

                    //체크아웃 단계
                    if (obj_ecommerce.has("checkout")) {
                        productAction = new ProductAction(ProductAction.ACTION_CHECKOUT);
                        ecommerce_data = obj_ecommerce.getJSONObject("checkout");
                        GA_ActionField();
                    }

                    //결제 단계
                    if (obj_ecommerce.has("purchase")) {
                        productAction = new ProductAction(ProductAction.ACTION_PURCHASE);
                        ecommerce_data = obj_ecommerce.getJSONObject("purchase");
                        GA_ActionField();
                    }

                    //리펀드 단계
                    if (obj_ecommerce.has("refund")) {
                        productAction = new ProductAction(ProductAction.ACTION_REFUND);
                        ecommerce_data = obj_ecommerce.getJSONObject("refund");
                        GA_ActionField();
                    }

                    //제품 아이템 추가
                    if (ecommerce_data.has("products")) {
                        JSONArray product_array = ecommerce_data.getJSONArray("products");
                        List product_list = ConvertJsonArray(product_array);

                        for (int i = 0; i < product_list.size(); i++) {
                            HashMap<String, String> product_Hashmap = (HashMap<String, String>) product_list.get(i);

                            Product item = new Product();

                            Iterator<String> keys = product_Hashmap.keySet().iterator();
                            while (keys.hasNext()) {
                                String key = keys.next();
                                if (key.contains("id")) item.setId(product_Hashmap.get(key));
                                if (key.contains("name")) item.setName(product_Hashmap.get(key));
                                if (key.contains("brand")) item.setBrand(product_Hashmap.get(key));
                                if (key.contains("category"))
                                    item.setCategory(product_Hashmap.get(key));
                                if (key.contains("price"))
                                    item.setPrice(Double.parseDouble(product_Hashmap.get(key)));
                                if (key.contains("quantity"))
                                    item.setQuantity(Integer.parseInt(product_Hashmap.get(key)));
                                if (key.contains("variant"))
                                    item.setVariant(product_Hashmap.get(key));
                                if (key.contains("coupon"))
                                    item.setCouponCode(product_Hashmap.get(key));
                                if (key.contains("position")) {
                                    Object position_value = product_Hashmap.get(key);
                                    String position = String.valueOf(position_value);
                                    item.setPosition(Integer.parseInt(position));
                                }
                                if (key.contains("dimension")) {
                                    int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                                    item.setCustomDimension(Number, product_Hashmap.get(key));
                                }
                                if (key.contains("metric")) {
                                    int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                                    Object metric_value = product_Hashmap.get(key);
                                    String metric = String.valueOf(metric_value);
                                    item.setCustomMetric(Number, Integer.parseInt(metric));
                                }
                            }
                            if (!obj_ecommerce.has("impressions")) {
                                if (sType.equals("P")) Screenview_Builder.addProduct(item);
                                else event_builder.addProduct(item);
                            } else {
                                if (sType.equals("P"))
                                    Screenview_Builder.addImpression(item, Impression_List);
                                else event_builder.addImpression(item, Impression_List);
                            }
                        }
                    }
                    if (productAction != null) {
                        if (sType.equals("P")) Screenview_Builder.setProductAction(productAction);
                        else event_builder.setProductAction(productAction);
                    }
                }

                if (sType.equals("P")) {
                    Screenview_Builder.setCustomDimension(1, mTracker.get("&cid"));
                    mTracker.send(Screenview_Builder.build());
                } else {
                    event_builder.setCustomDimension(1, mTracker.get("&cid"));
                    if (data.has("category")) event_builder.setCategory(data.getString("category"));
                    if (data.has("action")) event_builder.setAction(data.getString("action"));
                    if (data.has("label")) event_builder.setLabel(data.getString("label"));
                    mTracker.send(event_builder.build());
                    mTracker = NullSet(mTracker);
                }
            } catch (Exception ex) {
                Log.i("GAv4_Bridge_Data", ex.getMessage());
            }
        }

        private static Object ConvertObjectData(Object json) throws JSONException {
            try {
                if (json == JSONObject.NULL) return null;
                else if (json instanceof JSONObject) return ConvertJsonObject((JSONObject) json);
                else if (json instanceof JSONArray) return ConvertJsonArray((JSONArray) json);
                else return json;
            } catch (Exception e) {
                Log.e("GAv4_ConvertObjectData", e.getMessage());
                return null;
            }
        }

        public static Map<String, Object> ConvertJsonObject(JSONObject object) throws JSONException {
            try {
                Map<String, Object> map = new HashMap();
                Iterator keys = object.keys();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    map.put(key, ConvertObjectData(object.get(key)));
                }
                return map;
            } catch (Exception e) {
                Log.e("GAv4_ConvertJsonObject", e.getMessage());
                return null;
            }
        }

        public static List ConvertJsonArray(JSONArray array) throws JSONException {
            try {
                List list = new ArrayList();
                for (int i = 0; i < array.length(); i++) {
                    list.add(ConvertObjectData(array.get(i)));
                }
                return list;
            } catch (Exception e) {
                Log.e("GAv4_ConvertJsonArray", e.getMessage());
                return null;
            }
        }

        public HitBuilders.EventBuilder GA_Promotion(JSONObject ecommerce_data, HitBuilders.EventBuilder eventBuilder) {
            try {
                if (ecommerce_data.has("promotions")) {
                    JSONArray promotion_array = ecommerce_data.getJSONArray("promotions");
                    for (int i = 0; i < promotion_array.length(); i++) {
                        JSONObject ecommerce_item = promotion_array.getJSONObject(i);
                        Promotion promotions = new Promotion();
                        if (ecommerce_item.has("id"))
                            promotions.setId(ecommerce_item.getString("id"));
                        if (ecommerce_item.has("name"))
                            promotions.setName(ecommerce_item.getString("name"));
                        if (ecommerce_item.has("position"))
                            promotions.setPosition(ecommerce_item.getString("position"));
                        if (ecommerce_item.has("creative"))
                            promotions.setCreative(ecommerce_item.getString("creative"));

                        eventBuilder.addPromotion(promotions);
                    }
                }
                return eventBuilder;
            } catch (Exception ex) {
                Log.e("GAv4_Bridge_Promotion", ex.getMessage());
                return null;
            }
        }

        public void GA_ActionField() {
            try {
                if (ecommerce_data.has("actionField")) {
                    JSONObject actionField_data = ecommerce_data.getJSONObject("actionField");
                    if (actionField_data.has("id"))
                        productAction.setTransactionId(actionField_data.getString("id"));
                    if (actionField_data.has("revenue"))
                        productAction.setTransactionRevenue(actionField_data.getDouble("revenue"));
                    if (actionField_data.has("tax"))
                        productAction.setTransactionTax(actionField_data.getDouble("tax"));
                    if (actionField_data.has("shipping"))
                        productAction.setTransactionShipping(actionField_data.getDouble("shipping"));
                    if (actionField_data.has("coupon"))
                        productAction.setTransactionCouponCode(actionField_data.getString("coupon"));
                    if (actionField_data.has("affiliation"))
                        productAction.setTransactionAffiliation(actionField_data.getString("affiliation"));
                    if (actionField_data.has("list"))
                        productAction.setProductActionList(actionField_data.getString("list"));
                    if (actionField_data.has("step"))
                        productAction.setCheckoutStep(actionField_data.getInt("step"));
                    if (actionField_data.has("option"))
                        productAction.setCheckoutOptions(actionField_data.getString("option"));
                }
            } catch (Exception ex) {
                Log.e("GAv4_Bridge_ActionField", ex.getMessage());
            }
        }
    }
}
