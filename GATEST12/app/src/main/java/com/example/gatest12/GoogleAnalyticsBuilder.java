package com.example.gatest12;

import android.app.Activity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GoogleAnalyticsBuilder {

    Tracker mTracker;
    public static String[]user_pseudo_id= new String[1];

    public GoogleAnalyticsBuilder(Activity activity) { //tracker 변수 생성
        AnalyticsApplication application = (AnalyticsApplication) ((Activity) activity).getApplication();
        mTracker = application.getDefaultTracker();
    }

    public static class GACustomKey { //dimension, metric 정의
        public static String Dimension1 = "dimension1", Dimension2 = "dimension2", Dimension3 = "dimension3", Dimension4 = "dimension4", Dimension5 = "dimension5";
        public static String Dimension6 = "dimension6", Dimension7 = "dimension7", Dimension8 = "dimension8", Dimension9 = "dimension9", Dimension10 = "dimension10";
        public static String Dimension11 = "dimension11", Dimension12 = "dimension12", Dimension13 = "dimension13", Dimension14 = "dimension14", Dimension15 = "dimension15";
        public static String Dimension16 = "dimension16", Dimension17 = "dimension17", Dimension18 = "dimension18", Dimension19 = "dimension19", Dimension20 = "dimension20";
        public static String Dimension21 = "dimension21", Dimension22 = "dimension22", Dimension23 = "dimension23", Dimension24 = "dimension24", Dimension25 = "dimension25";
        public static String Dimension26 = "dimension26", Dimension27 = "dimension27", Dimension28 = "dimension28", Dimension29 = "dimension29", Dimension30 = "dimension30";
        public static String Dimension31 = "dimension31", Dimension32 = "dimension32", Dimension33 = "dimension33", Dimension34 = "dimension34", Dimension35 = "dimension35";
        public static String Dimension36 = "dimension36", Dimension37 = "dimension37", Dimension38 = "dimension38", Dimension39 = "dimension39", Dimension40 = "dimension40";
        public static String Dimension41 = "dimension41", Dimension42 = "dimension42", Dimension43 = "dimension43", Dimension44 = "dimension44", Dimension45 = "dimension45";
        public static String Dimension46 = "dimension46", Dimension47 = "dimension47", Dimension48 = "dimension48", Dimension49 = "dimension49", Dimension50 = "dimension50";
        public static String Dimension51 = "dimension51", Dimension52 = "dimension52", Dimension53 = "dimension53", Dimension54 = "dimension54", Dimension55 = "dimension55";
        public static String Dimension56 = "dimension56", Dimension57 = "dimension57", Dimension58 = "dimension58", Dimension59 = "dimension59", Dimension60 = "dimension60";
        public static String Dimension61 = "dimension61", Dimension62 = "dimension62", Dimension63 = "dimension63", Dimension64 = "dimension64", Dimension65 = "dimension65";
        public static String Dimension66 = "dimension66", Dimension67 = "dimension67", Dimension68 = "dimension68", Dimension69 = "dimension69", Dimension70 = "dimension70";
        public static String Dimension71 = "dimension71", Dimension72 = "dimension72", Dimension73 = "dimension73", Dimension74 = "dimension74", Dimension75 = "dimension75";
        public static String Dimension76 = "dimension76", Dimension77 = "dimension77", Dimension78 = "dimension78", Dimension79 = "dimension79", Dimension80 = "dimension80";
        public static String Dimension81 = "dimension81", Dimension82 = "dimension82", Dimension83 = "dimension83", Dimension84 = "dimension84", Dimension85 = "dimension85";
        public static String Dimension86 = "dimension86", Dimension87 = "dimension87", Dimension88 = "dimension88", Dimension89 = "dimension89", Dimension90 = "dimension90";
        public static String Dimension91 = "dimension91", Dimension92 = "dimension92", Dimension93 = "dimension93", Dimension94 = "dimension94", Dimension95 = "dimension95";
        public static String Dimension96 = "dimension96", Dimension97 = "dimension97", Dimension98 = "dimension98", Dimension99 = "dimension99", Dimension100 = "dimension100";
        public static String Dimension101 = "dimension101", Dimension102 = "dimension102", Dimension103 = "dimension103", Dimension104 = "dimension104", Dimension105 = "dimension105";
        public static String Dimension106 = "dimension106", Dimension107 = "dimension107", Dimension108 = "dimension108", Dimension109 = "dimension109", Dimension110 = "dimension110";
        public static String Dimension111 = "dimension111", Dimension112 = "dimension112", Dimension113 = "dimension113", Dimension114 = "dimension114", Dimension115 = "dimension115";
        public static String Dimension116 = "dimension116", Dimension117 = "dimension117", Dimension118 = "dimension118", Dimension119 = "dimension119", Dimension120 = "dimension120";
        public static String Dimension121 = "dimension121", Dimension122 = "dimension122", Dimension123 = "dimension123", Dimension124 = "dimension124", Dimension125 = "dimension125";
        public static String Dimension126 = "dimension126", Dimension127 = "dimension127", Dimension128 = "dimension128", Dimension129 = "dimension129", Dimension130 = "dimension130";
        public static String Dimension131 = "dimension131", Dimension132 = "dimension132", Dimension133 = "dimension133", Dimension134 = "dimension134", Dimension135 = "dimension135";
        public static String Dimension136 = "dimension136", Dimension137 = "dimension137", Dimension138 = "dimension138", Dimension139 = "dimension139", Dimension140 = "dimension140";
        public static String Dimension141 = "dimension141", Dimension142 = "dimension142", Dimension143 = "dimension143", Dimension144 = "dimension144", Dimension145 = "dimension145";
        public static String Dimension146 = "dimension146", Dimension147 = "dimension147", Dimension148 = "dimension148", Dimension149 = "dimension149", Dimension150 = "dimension150";
        public static String Dimension151 = "dimension151", Dimension152 = "dimension152", Dimension153 = "dimension153", Dimension154 = "dimension154", Dimension155 = "dimension155";
        public static String Dimension156 = "dimension156", Dimension157 = "dimension157", Dimension158 = "dimension158", Dimension159 = "dimension159", Dimension160 = "dimension160";
        public static String Dimension161 = "dimension161", Dimension162 = "dimension162", Dimension163 = "dimension163", Dimension164 = "dimension164", Dimension165 = "dimension165";
        public static String Dimension166 = "dimension166", Dimension167 = "dimension167", Dimension168 = "dimension168", Dimension169 = "dimension169", Dimension170 = "dimension170";
        public static String Dimension171 = "dimension171", Dimension172 = "dimension172", Dimension173 = "dimension173", Dimension174 = "dimension174", Dimension175 = "dimension175";
        public static String Dimension176 = "dimension176", Dimension177 = "dimension177", Dimension178 = "dimension178", Dimension179 = "dimension179", Dimension180 = "dimension180";
        public static String Dimension181 = "dimension181", Dimension182 = "dimension182", Dimension183 = "dimension183", Dimension184 = "dimension184", Dimension185 = "dimension185";
        public static String Dimension186 = "dimension186", Dimension187 = "dimension187", Dimension188 = "dimension188", Dimension189 = "dimension189", Dimension190 = "dimension190";
        public static String Dimension191 = "dimension191", Dimension192 = "dimension192", Dimension193 = "dimension193", Dimension194 = "dimension194", Dimension195 = "dimension195";
        public static String Dimension196 = "dimension196", Dimension197 = "dimension197", Dimension198 = "dimension198", Dimension199 = "dimension199", Dimension200 = "dimension200";

        public static String Metric1 = "metric1", Metric2 = "metric2", Metric3 = "metric3", Metric4 = "metric4", Metric5 = "metric5";
        public static String Metric6 = "metric6", Metric7 = "metric7", Metric8 = "metric8", Metric9 = "metric9", Metric10 = "metric10";
        public static String Metric11 = "metric11", Metric12 = "metric12", Metric13 = "metric13", Metric14 = "metric14", Metric15 = "metric15";
        public static String Metric16 = "metric16", Metric17 = "metric17", Metric18 = "metric18", Metric19 = "metric19", Metric20 = "metric20";
        public static String Metric21 = "metric21", Metric22 = "metric22", Metric23 = "metric23", Metric24 = "metric24", Metric25 = "metric25";
        public static String Metric26 = "metric26", Metric27 = "metric27", Metric28 = "metric28", Metric29 = "metric29", Metric30 = "metric30";
        public static String Metric31 = "metric31", Metric32 = "metric32", Metric33 = "metric33", Metric34 = "metric34", Metric35 = "metric35";
        public static String Metric36 = "metric36", Metric37 = "metric37", Metric38 = "metric38", Metric39 = "metric39", Metric40 = "metric40";
        public static String Metric41 = "metric41", Metric42 = "metric42", Metric43 = "metric43", Metric44 = "metric44", Metric45 = "metric45";
        public static String Metric46 = "metric46", Metric47 = "metric47", Metric48 = "metric48", Metric49 = "metric49", Metric50 = "metric50";
        public static String Metric51 = "metric51", Metric52 = "metric52", Metric53 = "metric53", Metric54 = "metric54", Metric55 = "metric55";
        public static String Metric56 = "metric56", Metric57 = "metric57", Metric58 = "metric58", Metric59 = "metric59", Metric60 = "metric60";
        public static String Metric61 = "metric61", Metric62 = "metric62", Metric63 = "metric63", Metric64 = "metric64", Metric65 = "metric65";
        public static String Metric66 = "metric66", Metric67 = "metric67", Metric68 = "metric68", Metric69 = "metric69", Metric70 = "metric70";
        public static String Metric71 = "metric71", Metric72 = "metric72", Metric73 = "metric73", Metric74 = "metric74", Metric75 = "metric75";
        public static String Metric76 = "metric76", Metric77 = "metric77", Metric78 = "metric78", Metric79 = "metric79", Metric80 = "metric80";
        public static String Metric81 = "metric81", Metric82 = "metric82", Metric83 = "metric83", Metric84 = "metric84", Metric85 = "metric85";
        public static String Metric86 = "metric86", Metric87 = "metric87", Metric88 = "metric88", Metric89 = "metric89", Metric90 = "metric90";
        public static String Metric91 = "metric91", Metric92 = "metric92", Metric93 = "metric93", Metric94 = "metric94", Metric95 = "metric95";
        public static String Metric96 = "metric96", Metric97 = "metric97", Metric98 = "metric98", Metric99 = "metric99", Metric100 = "metric100";
        public static String Metric101 = "metric101", Metric102 = "metric102", Metric103 = "metric103", Metric104 = "metric104", Metric105 = "metric105";
        public static String Metric106 = "metric106", Metric107 = "metric107", Metric108 = "metric108", Metric109 = "metric109", Metric110 = "metric110";
        public static String Metric111 = "metric111", Metric112 = "metric112", Metric113 = "metric113", Metric114 = "metric114", Metric115 = "metric115";
        public static String Metric116 = "metric116", Metric117 = "metric117", Metric118 = "metric118", Metric119 = "metric119", Metric120 = "metric120";
        public static String Metric121 = "metric121", Metric122 = "metric122", Metric123 = "metric123", Metric124 = "metric124", Metric125 = "metric125";
        public static String Metric126 = "metric126", Metric127 = "metric127", Metric128 = "metric128", Metric129 = "metric129", Metric130 = "metric130";
        public static String Metric131 = "metric131", Metric132 = "metric132", Metric133 = "metric133", Metric134 = "metric134", Metric135 = "metric135";
        public static String Metric136 = "metric136", Metric137 = "metric137", Metric138 = "metric138", Metric139 = "metric139", Metric140 = "metric140";
        public static String Metric141 = "metric141", Metric142 = "metric142", Metric143 = "metric143", Metric144 = "metric144", Metric145 = "metric145";
        public static String Metric146 = "metric146", Metric147 = "metric147", Metric148 = "metric148", Metric149 = "metric149", Metric150 = "metric150";
        public static String Metric151 = "metric151", Metric152 = "metric152", Metric153 = "metric153", Metric154 = "metric154", Metric155 = "metric155";
        public static String Metric156 = "metric156", Metric157 = "metric157", Metric158 = "metric158", Metric159 = "metric159", Metric160 = "metric160";
        public static String Metric161 = "metric161", Metric162 = "metric162", Metric163 = "metric163", Metric164 = "metric164", Metric165 = "metric165";
        public static String Metric166 = "metric166", Metric167 = "metric167", Metric168 = "metric168", Metric169 = "metric169", Metric170 = "metric170";
        public static String Metric171 = "metric171", Metric172 = "metric172", Metric173 = "metric173", Metric174 = "metric174", Metric175 = "metric175";
        public static String Metric176 = "metric176", Metric177 = "metric177", Metric178 = "metric178", Metric179 = "metric179", Metric180 = "metric180";
        public static String Metric181 = "metric181", Metric182 = "metric182", Metric183 = "metric183", Metric184 = "metric184", Metric185 = "metric185";
        public static String Metric186 = "metric186", Metric187 = "metric187", Metric188 = "metric188", Metric189 = "metric189", Metric190 = "metric190";
        public static String Metric191 = "metric191", Metric192 = "metric192", Metric193 = "metric193", Metric194 = "metric194", Metric195 = "metric195";
        public static String Metric196 = "metric196", Metric197 = "metric197", Metric198 = "metric198", Metric199 = "metric199", Metric200 = "metric200";
    }

    public static class GAEcommerceStepKey { // ecommerce 관련 변수 생성
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

    public static class GAHitKey { // uid, title 같은 기본정보들에 promotion 관련 변수 생성
        public static String UserID = "uid";
        public static String CampaignUrl = "camp_url";
        public static String Title = "title";
        public static String EventCategory = "category";
        public static String EventAction = "action";
        public static String EventLabel = "label";
        public static String EventValue = "value";
        public static String TimingValue = "timingvalue";
        public static String TimingCategory = "timingcategory";
        public static String TimingVariable = "timingvariable";
        public static String TimingLabel = "timinglabel";
        public static String NonInteraction = "ni";
        public static String PromotionID = "promo_id";
        public static String PromotionName = "promo_nm";
        public static String PromotionCreative = "promo_cr";
        public static String PromotionPosition = "promo_ps";
        public static String Fatal = "fatal";
        public static String ErrorDescription = "errordescription";
        public static String CurrencyCode = "currencycode";

    }

    public static class GAActionFieldKey { // refund와 purchase에서 사용한 거래변수생성
        public static String TransactionID = "af_id";
        public static String TransactionRevenue = "af_revenue";
        public static String TransactionTax = "af_tax";
        public static String TransactionShipping = "af_shipping";
        public static String TransactionCouponCode = "af_coupon";
        public static String TransactionAffiliation = "af_affiliation";
        public static String ProductActionList = "af_list";
        public static String CheckoutStep = "af_step";
        public static String CheckoutOptions = "af_option";
        public static String CurrencyCode = "cu";
    }

    public static class GAProductKey { //상품 정보생성
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

        public static String ProductDimension1 = "prcd1", ProductDimension2 = "prcd2", ProductDimension3 = "prcd3", ProductDimension4 = "prcd4", ProductDimension5 = "prcd5";
        public static String ProductDimension6 = "prcd6", ProductDimension7 = "prcd7", ProductDimension8 = "prcd8", ProductDimension9 = "prcd9", ProductDimension10 = "prcd10";
        public static String ProductDimension11 = "prcd11", ProductDimension12 = "prcd12", ProductDimension13 = "prcd13", ProductDimension14 = "prcd14", ProductDimension15 = "prcd15";
        public static String ProductDimension16 = "prcd16", ProductDimension17 = "prcd17", ProductDimension18 = "prcd18", ProductDimension19 = "prcd19", ProductDimension20 = "prcd20";
        public static String ProductDimension21 = "prcd21", ProductDimension22 = "prcd22", ProductDimension23 = "prcd23", ProductDimension24 = "prcd24", ProductDimension25 = "prcd25";
        public static String ProductDimension26 = "prcd26", ProductDimension27 = "prcd27", ProductDimension28 = "prcd28", ProductDimension29 = "prcd29", ProductDimension30 = "prcd30";
        public static String ProductDimension31 = "prcd31", ProductDimension32 = "prcd32", ProductDimension33 = "prcd33", ProductDimension34 = "prcd34", ProductDimension35 = "prcd35";
        public static String ProductDimension36 = "prcd36", ProductDimension37 = "prcd37", ProductDimension38 = "prcd38", ProductDimension39 = "prcd39", ProductDimension40 = "prcd40";
        public static String ProductDimension41 = "prcd41", ProductDimension42 = "prcd42", ProductDimension43 = "prcd43", ProductDimension44 = "prcd44", ProductDimension45 = "prcd45";
        public static String ProductDimension46 = "prcd46", ProductDimension47 = "prcd47", ProductDimension48 = "prcd48", ProductDimension49 = "prcd49", ProductDimension50 = "prcd50";
        public static String ProductDimension51 = "prcd51", ProductDimension52 = "prcd52", ProductDimension53 = "prcd53", ProductDimension54 = "prcd54", ProductDimension55 = "prcd55";
        public static String ProductDimension56 = "prcd56", ProductDimension57 = "prcd57", ProductDimension58 = "prcd58", ProductDimension59 = "prcd59", ProductDimension60 = "prcd60";
        public static String ProductDimension61 = "prcd61", ProductDimension62 = "prcd62", ProductDimension63 = "prcd63", ProductDimension64 = "prcd64", ProductDimension65 = "prcd65";
        public static String ProductDimension66 = "prcd66", ProductDimension67 = "prcd67", ProductDimension68 = "prcd68", ProductDimension69 = "prcd69", ProductDimension70 = "prcd70";
        public static String ProductDimension71 = "prcd71", ProductDimension72 = "prcd72", ProductDimension73 = "prcd73", ProductDimension74 = "prcd74", ProductDimension75 = "prcd75";
        public static String ProductDimension76 = "prcd76", ProductDimension77 = "prcd77", ProductDimension78 = "prcd78", ProductDimension79 = "prcd79", ProductDimension80 = "prcd80";
        public static String ProductDimension81 = "prcd81", ProductDimension82 = "prcd82", ProductDimension83 = "prcd83", ProductDimension84 = "prcd84", ProductDimension85 = "prcd85";
        public static String ProductDimension86 = "prcd86", ProductDimension87 = "prcd87", ProductDimension88 = "prcd88", ProductDimension89 = "prcd89", ProductDimension90 = "prcd90";
        public static String ProductDimension91 = "prcd91", ProductDimension92 = "prcd92", ProductDimension93 = "prcd93", ProductDimension94 = "prcd94", ProductDimension95 = "prcd95";
        public static String ProductDimension96 = "prcd96", ProductDimension97 = "prcd97", ProductDimension98 = "prcd98", ProductDimension99 = "prcd99", ProductDimension100 = "prcd100";
        public static String ProductDimension101 = "prcd101", ProductDimension102 = "prcd102", ProductDimension103 = "prcd103", ProductDimension104 = "prcd104", ProductDimension105 = "prcd105";
        public static String ProductDimension106 = "prcd106", ProductDimension107 = "prcd107", ProductDimension108 = "prcd108", ProductDimension109 = "prcd109", ProductDimension110 = "prcd110";
        public static String ProductDimension111 = "prcd111", ProductDimension112 = "prcd112", ProductDimension113 = "prcd113", ProductDimension114 = "prcd114", ProductDimension115 = "prcd115";
        public static String ProductDimension116 = "prcd116", ProductDimension117 = "prcd117", ProductDimension118 = "prcd118", ProductDimension119 = "prcd119", ProductDimension120 = "prcd120";
        public static String ProductDimension121 = "prcd121", ProductDimension122 = "prcd122", ProductDimension123 = "prcd123", ProductDimension124 = "prcd124", ProductDimension125 = "prcd125";
        public static String ProductDimension126 = "prcd126", ProductDimension127 = "prcd127", ProductDimension128 = "prcd128", ProductDimension129 = "prcd129", ProductDimension130 = "prcd130";
        public static String ProductDimension131 = "prcd131", ProductDimension132 = "prcd132", ProductDimension133 = "prcd133", ProductDimension134 = "prcd134", ProductDimension135 = "prcd135";
        public static String ProductDimension136 = "prcd136", ProductDimension137 = "prcd137", ProductDimension138 = "prcd138", ProductDimension139 = "prcd139", ProductDimension140 = "prcd140";
        public static String ProductDimension141 = "prcd141", ProductDimension142 = "prcd142", ProductDimension143 = "prcd143", ProductDimension144 = "prcd144", ProductDimension145 = "prcd145";
        public static String ProductDimension146 = "prcd146", ProductDimension147 = "prcd147", ProductDimension148 = "prcd148", ProductDimension149 = "prcd149", ProductDimension150 = "prcd150";
        public static String ProductDimension151 = "prcd151", ProductDimension152 = "prcd152", ProductDimension153 = "prcd153", ProductDimension154 = "prcd154", ProductDimension155 = "prcd155";
        public static String ProductDimension156 = "prcd156", ProductDimension157 = "prcd157", ProductDimension158 = "prcd158", ProductDimension159 = "prcd159", ProductDimension160 = "prcd160";
        public static String ProductDimension161 = "prcd161", ProductDimension162 = "prcd162", ProductDimension163 = "prcd163", ProductDimension164 = "prcd164", ProductDimension165 = "prcd165";
        public static String ProductDimension166 = "prcd166", ProductDimension167 = "prcd167", ProductDimension168 = "prcd168", ProductDimension169 = "prcd169", ProductDimension170 = "prcd170";
        public static String ProductDimension171 = "prcd171", ProductDimension172 = "prcd172", ProductDimension173 = "prcd173", ProductDimension174 = "prcd174", ProductDimension175 = "prcd175";
        public static String ProductDimension176 = "prcd176", ProductDimension177 = "prcd177", ProductDimension178 = "prcd178", ProductDimension179 = "prcd179", ProductDimension180 = "prcd180";
        public static String ProductDimension181 = "prcd181", ProductDimension182 = "prcd182", ProductDimension183 = "prcd183", ProductDimension184 = "prcd184", ProductDimension185 = "prcd185";
        public static String ProductDimension186 = "prcd186", ProductDimension187 = "prcd187", ProductDimension188 = "prcd188", ProductDimension189 = "prcd189", ProductDimension190 = "prcd190";
        public static String ProductDimension191 = "prcd191", ProductDimension192 = "prcd192", ProductDimension193 = "prcd193", ProductDimension194 = "prcd194", ProductDimension195 = "prcd195";
        public static String ProductDimension196 = "prcd196", ProductDimension197 = "prcd197", ProductDimension198 = "prcd198", ProductDimension199 = "prcd199", ProductDimension200 = "prcd200";

        public static String ProdudctMetric1 = "prme1", ProdudctMetric2 = "prme2", ProdudctMetric3 = "prme3", ProdudctMetric4 = "prme4", ProdudctMetric5 = "prme5";
        public static String ProdudctMetric6 = "prme6", ProdudctMetric7 = "prme7", ProdudctMetric8 = "prme8", ProdudctMetric9 = "prme9", ProdudctMetric10 = "prme10";
        public static String ProdudctMetric11 = "prme11", ProdudctMetric12 = "prme12", ProdudctMetric13 = "prme13", ProdudctMetric14 = "prme14", ProdudctMetric15 = "prme15";
        public static String ProdudctMetric16 = "prme16", ProdudctMetric17 = "prme17", ProdudctMetric18 = "prme18", ProdudctMetric19 = "prme19", ProdudctMetric20 = "prme20";
        public static String ProdudctMetric21 = "prme21", ProdudctMetric22 = "prme22", ProdudctMetric23 = "prme23", ProdudctMetric24 = "prme24", ProdudctMetric25 = "prme25";
        public static String ProdudctMetric26 = "prme26", ProdudctMetric27 = "prme27", ProdudctMetric28 = "prme28", ProdudctMetric29 = "prme29", ProdudctMetric30 = "prme30";
        public static String ProdudctMetric31 = "prme31", ProdudctMetric32 = "prme32", ProdudctMetric33 = "prme33", ProdudctMetric34 = "prme34", ProdudctMetric35 = "prme35";
        public static String ProdudctMetric36 = "prme36", ProdudctMetric37 = "prme37", ProdudctMetric38 = "prme38", ProdudctMetric39 = "prme39", ProdudctMetric40 = "prme40";
        public static String ProdudctMetric41 = "prme41", ProdudctMetric42 = "prme42", ProdudctMetric43 = "prme43", ProdudctMetric44 = "prme44", ProdudctMetric45 = "prme45";
        public static String ProdudctMetric46 = "prme46", ProdudctMetric47 = "prme47", ProdudctMetric48 = "prme48", ProdudctMetric49 = "prme49", ProdudctMetric50 = "prme50";
        public static String ProdudctMetric51 = "prme51", ProdudctMetric52 = "prme52", ProdudctMetric53 = "prme53", ProdudctMetric54 = "prme54", ProdudctMetric55 = "prme55";
        public static String ProdudctMetric56 = "prme56", ProdudctMetric57 = "prme57", ProdudctMetric58 = "prme58", ProdudctMetric59 = "prme59", ProdudctMetric60 = "prme60";
        public static String ProdudctMetric61 = "prme61", ProdudctMetric62 = "prme62", ProdudctMetric63 = "prme63", ProdudctMetric64 = "prme64", ProdudctMetric65 = "prme65";
        public static String ProdudctMetric66 = "prme66", ProdudctMetric67 = "prme67", ProdudctMetric68 = "prme68", ProdudctMetric69 = "prme69", ProdudctMetric70 = "prme70";
        public static String ProdudctMetric71 = "prme71", ProdudctMetric72 = "prme72", ProdudctMetric73 = "prme73", ProdudctMetric74 = "prme74", ProdudctMetric75 = "prme75";
        public static String ProdudctMetric76 = "prme76", ProdudctMetric77 = "prme77", ProdudctMetric78 = "prme78", ProdudctMetric79 = "prme79", ProdudctMetric80 = "prme80";
        public static String ProdudctMetric81 = "prme81", ProdudctMetric82 = "prme82", ProdudctMetric83 = "prme83", ProdudctMetric84 = "prme84", ProdudctMetric85 = "prme85";
        public static String ProdudctMetric86 = "prme86", ProdudctMetric87 = "prme87", ProdudctMetric88 = "prme88", ProdudctMetric89 = "prme89", ProdudctMetric90 = "prme90";
        public static String ProdudctMetric91 = "prme91", ProdudctMetric92 = "prme92", ProdudctMetric93 = "prme93", ProdudctMetric94 = "prme94", ProdudctMetric95 = "prme95";
        public static String ProdudctMetric96 = "prme96", ProdudctMetric97 = "prme97", ProdudctMetric98 = "prme98", ProdudctMetric99 = "prme99", ProdudctMetric100 = "prme100";
        public static String ProdudctMetric101 = "prme101", ProdudctMetric102 = "prme102", ProdudctMetric103 = "prme103", ProdudctMetric104 = "prme104", ProdudctMetric105 = "prme105";
        public static String ProdudctMetric106 = "prme106", ProdudctMetric107 = "prme107", ProdudctMetric108 = "prme108", ProdudctMetric109 = "prme109", ProdudctMetric110 = "prme110";
        public static String ProdudctMetric111 = "prme111", ProdudctMetric112 = "prme112", ProdudctMetric113 = "prme113", ProdudctMetric114 = "prme114", ProdudctMetric115 = "prme115";
        public static String ProdudctMetric116 = "prme116", ProdudctMetric117 = "prme117", ProdudctMetric118 = "prme118", ProdudctMetric119 = "prme119", ProdudctMetric120 = "prme120";
        public static String ProdudctMetric121 = "prme121", ProdudctMetric122 = "prme122", ProdudctMetric123 = "prme123", ProdudctMetric124 = "prme124", ProdudctMetric125 = "prme125";
        public static String ProdudctMetric126 = "prme126", ProdudctMetric127 = "prme127", ProdudctMetric128 = "prme128", ProdudctMetric129 = "prme129", ProdudctMetric130 = "prme130";
        public static String ProdudctMetric131 = "prme131", ProdudctMetric132 = "prme132", ProdudctMetric133 = "prme133", ProdudctMetric134 = "prme134", ProdudctMetric135 = "prme135";
        public static String ProdudctMetric136 = "prme136", ProdudctMetric137 = "prme137", ProdudctMetric138 = "prme138", ProdudctMetric139 = "prme139", ProdudctMetric140 = "prme140";
        public static String ProdudctMetric141 = "prme141", ProdudctMetric142 = "prme142", ProdudctMetric143 = "prme143", ProdudctMetric144 = "prme144", ProdudctMetric145 = "prme145";
        public static String ProdudctMetric146 = "prme146", ProdudctMetric147 = "prme147", ProdudctMetric148 = "prme148", ProdudctMetric149 = "prme149", ProdudctMetric150 = "prme150";
        public static String ProdudctMetric151 = "prme151", ProdudctMetric152 = "prme152", ProdudctMetric153 = "prme153", ProdudctMetric154 = "prme154", ProdudctMetric155 = "prme155";
        public static String ProdudctMetric156 = "prme156", ProdudctMetric157 = "prme157", ProdudctMetric158 = "prme158", ProdudctMetric159 = "prme159", ProdudctMetric160 = "prme160";
        public static String ProdudctMetric161 = "prme161", ProdudctMetric162 = "prme162", ProdudctMetric163 = "prme163", ProdudctMetric164 = "prme164", ProdudctMetric165 = "prme165";
        public static String ProdudctMetric166 = "prme166", ProdudctMetric167 = "prme167", ProdudctMetric168 = "prme168", ProdudctMetric169 = "prme169", ProdudctMetric170 = "prme170";
        public static String ProdudctMetric171 = "prme171", ProdudctMetric172 = "prme172", ProdudctMetric173 = "prme173", ProdudctMetric174 = "prme174", ProdudctMetric175 = "prme175";
        public static String ProdudctMetric176 = "prme176", ProdudctMetric177 = "prme177", ProdudctMetric178 = "prme178", ProdudctMetric179 = "prme179", ProdudctMetric180 = "prme180";
        public static String ProdudctMetric181 = "prme181", ProdudctMetric182 = "prme182", ProdudctMetric183 = "prme183", ProdudctMetric184 = "prme184", ProdudctMetric185 = "prme185";
        public static String ProdudctMetric186 = "prme186", ProdudctMetric187 = "prme187", ProdudctMetric188 = "prme188", ProdudctMetric189 = "prme189", ProdudctMetric190 = "prme190";
        public static String ProdudctMetric191 = "prme191", ProdudctMetric192 = "prme192", ProdudctMetric193 = "prme193", ProdudctMetric194 = "prme194", ProdudctMetric195 = "prme195";
        public static String ProdudctMetric196 = "prme196", ProdudctMetric197 = "prme197", ProdudctMetric198 = "prme198", ProdudctMetric199 = "prme199", ProdudctMetric200 = "prme200";
    }

    public void GADataSend_Event(Map<String, String> GAInfo) { // 이벤트 전송 클래스
        try {
            HitBuilders.EventBuilder eventBuilder = new HitBuilders.EventBuilder();
            String Category, Action, Label;
            Iterator<String> sIterator = GAInfo.keySet().iterator();
            Promotion promotion = new Promotion();
            while (sIterator.hasNext()) {
                String key = sIterator.next();
                if (GAInfo.get(key) != null && GAInfo.get(key).length() > 0) {
                    if (key.toLowerCase().contains("dimension")) {
                        int Number = Integer.parseInt(key.replaceAll("[^0-9]", ""));
                        eventBuilder.setCustomDimension(Number, GAInfo.get(key));
                    }

                    if (key.toLowerCase().contains("metric")) {
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
            //mTracker.send(new HitBuilders.ExceptionBuilder().setDescription(new StandardExceptionParser(this, null).getDescription(Thread.currentThread().getName(), e)).setFatal(false).build());
            Log.e("GAv4_Event", e.getMessage());
        }
    }

    public void GADataSend_Screen(Map<String, String> GAInfo) { //스크린뷰 전송 클래스
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
            }catch (Exception e) {
            Log.e("GAv4_Ecommerce", e.getMessage());
        }

    }

    public void GADataSend_Ecommerce(String EcommerceStep, Map<String, String> GAAction, Map<String, Map<String, String>> GAProduct, Map<String, String> GAInfo) { // 전자상거래 전송 클래스
        try {
            if (mTracker != null) {
                HitBuilders.EventBuilder ecommerceBuilder = new HitBuilders.EventBuilder();
                Iterator<String> sIterator_GAInfo = GAInfo.keySet().iterator();

                //제품클릭 단계
                if (EcommerceStep.toLowerCase().equals("click")) {
                    ProductAction pa = new ProductAction(ProductAction.ACTION_CLICK); // gaaction을 받아 productaction(import한 analytics.js의 ecommerce에) 추가
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

                while (sIterator_GAInfo.hasNext()) { //추가 맞춤측정기준 및 변수들에 대해 있을경우
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
                    ecommerceBuilder = GA_Product(EcommerceStep, GAProduct, ecommerceBuilder); // 위에서 만들어놓은 ecommercebuilder를 ga_product에 담아 저장
                }

                mTracker.send(ecommerceBuilder.build()); // 모아진 정보들을 담은 ecommercebuilder 변수를 mtracker를 통해 send
                mTracker = NullSet(mTracker);
            }
        } catch (Exception e) {
            Log.e("GAv4_Ecommerce", e.getMessage());
        }
    }

    private static Tracker NullSet(Tracker mTracker) { // tracker 초기화
        mTracker.setScreenName(null);
        mTracker.set("&cu", null);
        mTracker.set("&uid", null);
        return mTracker;
    }
    private ProductAction GA_ActionField(Map<String, String> GAAction, ProductAction productAction) { //
        try {
            Iterator<String> sIterator_GA_ActionField = GAAction.keySet().iterator();
            while (sIterator_GA_ActionField.hasNext()) {
                String actionField_key = sIterator_GA_ActionField.next();
                if (GAAction.get(actionField_key) != null && GAAction.get(actionField_key).length() > 0) { // GAActionFieldKey 각 속성값명들을 변경해놨기때문에 아래와같이 사용가능
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

    private HitBuilders.EventBuilder GA_Product(String EcommerceStep, Map<String, Map<String, String>> GAProduct, HitBuilders.EventBuilder ecommerceBuilder) { //impression 단계에서 사용한 GA_product
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
                        ecommerceBuilder.addImpression(product, prd_list);//prd_list 사용시 수정필
                    }
                }
            }
            return ecommerceBuilder;
        } catch (Exception e) {
            Log.e("GAv4_Product", e.getMessage());
            return null;
        }
    }

    public static class WebAppInterface {  //WebView 시 사용할 class
        Tracker mTracker;

        public WebAppInterface(Activity activity) {
            AnalyticsApplication application = (AnalyticsApplication) ((Activity) activity).getApplication();
            mTracker = application.getDefaultTracker();
        }

        ProductAction productAction = null;
        JSONObject ecommerce_data = null;


        @JavascriptInterface
        public void GA_DATA(String JsonData) { //Webview 내에서 자바스크립트 코드내에서 불러올 클래스
            try {
                HitBuilders.EventBuilder event_builder = new HitBuilders.EventBuilder();
                HitBuilders.ScreenViewBuilder Screenview_Builder = new HitBuilders.ScreenViewBuilder();
                JSONObject data = new JSONObject(JsonData);

                String sType = "";
                String Impression_List = "";
                if (data.has("type")) sType = data.getString("type");

                // 불러온 정보에 따라 스크린뷰나 이벤트 정보, 맞춤 추가 정보 전송

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
                if (data.has("userId"))  mTracker.set("&uid", data.getString("userId"));
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

        private static Object ConvertObjectData(Object json) throws JSONException { //json 데이터를 받아 object인지, array인지 확인하여 map형태와 list형태로 변환해주는 class 3개
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
