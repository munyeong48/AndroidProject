package app.suro.myapplication

import android.app.Activity
import android.util.Log
import android.webkit.JavascriptInterface
import app.suro.myapplication.GoogleAnalyticsBuilder.WebAppInterface.Companion.NullSet
import com.google.android.gms.analytics.HitBuilders
import com.google.android.gms.analytics.Tracker
import com.google.android.gms.analytics.ecommerce.Product
import com.google.android.gms.analytics.ecommerce.ProductAction
import com.google.android.gms.analytics.ecommerce.Promotion
import org.json.JSONArray
import org.json.JSONObject
import java.net.URLDecoder


class GoogleAnalyticsBuilder(activity: Activity) {

    internal var mTracker: Tracker? = null

    init {
        val application = activity.application as AnalyticsApplication
        mTracker = application.getDefaultTracker()
    }

    object GACustomKey {
        var Dimension1 = "dimension1"; var Dimension2 = "dimension2"; var Dimension3 = "dimension3"; var Dimension4 = "dimension4"; var Dimension5 = "dimension5";
        var Dimension6 = "dimension6"; var Dimension7 = "dimension7"; var Dimension8 = "dimension8"; var Dimension9 = "dimension9"; var Dimension10 = "dimension10";
        var Dimension11 = "dimension11"; var Dimension12 = "dimension12"; var Dimension13 = "dimension13"; var Dimension14 = "dimension14"; var Dimension15 = "dimension15";
        var Dimension16 = "dimension16"; var Dimension17 = "dimension17"; var Dimension18 = "dimension18"; var Dimension19 = "dimension19"; var Dimension20 = "dimension20";
        var Dimension21 = "dimension21"; var Dimension22 = "dimension22"; var Dimension23 = "dimension23"; var Dimension24 = "dimension24"; var Dimension25 = "dimension25";
        var Dimension26 = "dimension26"; var Dimension27 = "dimension27"; var Dimension28 = "dimension28"; var Dimension29 = "dimension29"; var Dimension30 = "dimension30";
        var Dimension31 = "dimension31"; var Dimension32 = "dimension32"; var Dimension33 = "dimension33"; var Dimension34 = "dimension34"; var Dimension35 = "dimension35";
        var Dimension36 = "dimension36"; var Dimension37 = "dimension37"; var Dimension38 = "dimension38"; var Dimension39 = "dimension39"; var Dimension40 = "dimension40";
        var Dimension41 = "dimension41"; var Dimension42 = "dimension42"; var Dimension43 = "dimension43"; var Dimension44 = "dimension44"; var Dimension45 = "dimension45";
        var Dimension46 = "dimension46"; var Dimension47 = "dimension47"; var Dimension48 = "dimension48"; var Dimension49 = "dimension49"; var Dimension50 = "dimension50";
        var Dimension51 = "dimension51"; var Dimension52 = "dimension52"; var Dimension53 = "dimension53"; var Dimension54 = "dimension54"; var Dimension55 = "dimension55";
        var Dimension56 = "dimension56"; var Dimension57 = "dimension57"; var Dimension58 = "dimension58"; var Dimension59 = "dimension59"; var Dimension60 = "dimension60";
        var Dimension61 = "dimension61"; var Dimension62 = "dimension62"; var Dimension63 = "dimension63"; var Dimension64 = "dimension64"; var Dimension65 = "dimension65";
        var Dimension66 = "dimension66"; var Dimension67 = "dimension67"; var Dimension68 = "dimension68"; var Dimension69 = "dimension69"; var Dimension70 = "dimension70";
        var Dimension71 = "dimension71"; var Dimension72 = "dimension72"; var Dimension73 = "dimension73"; var Dimension74 = "dimension74"; var Dimension75 = "dimension75";
        var Dimension76 = "dimension76"; var Dimension77 = "dimension77"; var Dimension78 = "dimension78"; var Dimension79 = "dimension79"; var Dimension80 = "dimension80";
        var Dimension81 = "dimension81"; var Dimension82 = "dimension82"; var Dimension83 = "dimension83"; var Dimension84 = "dimension84"; var Dimension85 = "dimension85";
        var Dimension86 = "dimension86"; var Dimension87 = "dimension87"; var Dimension88 = "dimension88"; var Dimension89 = "dimension89"; var Dimension90 = "dimension90";
        var Dimension91 = "dimension91"; var Dimension92 = "dimension92"; var Dimension93 = "dimension93"; var Dimension94 = "dimension94"; var Dimension95 = "dimension95";
        var Dimension96 = "dimension96"; var Dimension97 = "dimension97"; var Dimension98 = "dimension98"; var Dimension99 = "dimension99"; var Dimension100 = "dimension100";
        var Dimension101 = "dimension101"; var Dimension102 = "dimension102"; var Dimension103 = "dimension103"; var Dimension104 = "dimension104"; var Dimension105 = "dimension105";
        var Dimension106 = "dimension106"; var Dimension107 = "dimension107"; var Dimension108 = "dimension108"; var Dimension109 = "dimension109"; var Dimension110 = "dimension110";
        var Dimension111 = "dimension111"; var Dimension112 = "dimension112"; var Dimension113 = "dimension113"; var Dimension114 = "dimension114"; var Dimension115 = "dimension115";
        var Dimension116 = "dimension116"; var Dimension117 = "dimension117"; var Dimension118 = "dimension118"; var Dimension119 = "dimension119"; var Dimension120 = "dimension120";
        var Dimension121 = "dimension121"; var Dimension122 = "dimension122"; var Dimension123 = "dimension123"; var Dimension124 = "dimension124"; var Dimension125 = "dimension125";
        var Dimension126 = "dimension126"; var Dimension127 = "dimension127"; var Dimension128 = "dimension128"; var Dimension129 = "dimension129"; var Dimension130 = "dimension130";
        var Dimension131 = "dimension131"; var Dimension132 = "dimension132"; var Dimension133 = "dimension133"; var Dimension134 = "dimension134"; var Dimension135 = "dimension135";
        var Dimension136 = "dimension136"; var Dimension137 = "dimension137"; var Dimension138 = "dimension138"; var Dimension139 = "dimension139"; var Dimension140 = "dimension140";
        var Dimension141 = "dimension141"; var Dimension142 = "dimension142"; var Dimension143 = "dimension143"; var Dimension144 = "dimension144"; var Dimension145 = "dimension145";
        var Dimension146 = "dimension146"; var Dimension147 = "dimension147"; var Dimension148 = "dimension148"; var Dimension149 = "dimension149"; var Dimension150 = "dimension150";
        var Dimension151 = "dimension151"; var Dimension152 = "dimension152"; var Dimension153 = "dimension153"; var Dimension154 = "dimension154"; var Dimension155 = "dimension155";
        var Dimension156 = "dimension156"; var Dimension157 = "dimension157"; var Dimension158 = "dimension158"; var Dimension159 = "dimension159"; var Dimension160 = "dimension160";
        var Dimension161 = "dimension161"; var Dimension162 = "dimension162"; var Dimension163 = "dimension163"; var Dimension164 = "dimension164"; var Dimension165 = "dimension165";
        var Dimension166 = "dimension166"; var Dimension167 = "dimension167"; var Dimension168 = "dimension168"; var Dimension169 = "dimension169"; var Dimension170 = "dimension170";
        var Dimension171 = "dimension171"; var Dimension172 = "dimension172"; var Dimension173 = "dimension173"; var Dimension174 = "dimension174"; var Dimension175 = "dimension175";
        var Dimension176 = "dimension176"; var Dimension177 = "dimension177"; var Dimension178 = "dimension178"; var Dimension179 = "dimension179"; var Dimension180 = "dimension180";
        var Dimension181 = "dimension181"; var Dimension182 = "dimension182"; var Dimension183 = "dimension183"; var Dimension184 = "dimension184"; var Dimension185 = "dimension185";
        var Dimension186 = "dimension186"; var Dimension187 = "dimension187"; var Dimension188 = "dimension188"; var Dimension189 = "dimension189"; var Dimension190 = "dimension190";
        var Dimension191 = "dimension191"; var Dimension192 = "dimension192"; var Dimension193 = "dimension193"; var Dimension194 = "dimension194"; var Dimension195 = "dimension195";
        var Dimension196 = "dimension196"; var Dimension197 = "dimension197"; var Dimension198 = "dimension198"; var Dimension199 = "dimension199"; var Dimension200 = "dimension200";

        var Metric1 = "metric1"; var Metric2 = "metric2"; var Metric3 = "metric3"; var Metric4 = "metric4"; var Metric5 = "metric5";
        var Metric6 = "metric6"; var Metric7 = "metric7"; var Metric8 = "metric8"; var Metric9 = "metric9"; var Metric10 = "metric10";
        var Metric11 = "metric11"; var Metric12 = "metric12"; var Metric13 = "metric13"; var Metric14 = "metric14"; var Metric15 = "metric15";
        var Metric16 = "metric16"; var Metric17 = "metric17"; var Metric18 = "metric18"; var Metric19 = "metric19"; var Metric20 = "metric20";
        var Metric21 = "metric21"; var Metric22 = "metric22"; var Metric23 = "metric23"; var Metric24 = "metric24"; var Metric25 = "metric25";
        var Metric26 = "metric26"; var Metric27 = "metric27"; var Metric28 = "metric28"; var Metric29 = "metric29"; var Metric30 = "metric30";
        var Metric31 = "metric31"; var Metric32 = "metric32"; var Metric33 = "metric33"; var Metric34 = "metric34"; var Metric35 = "metric35";
        var Metric36 = "metric36"; var Metric37 = "metric37"; var Metric38 = "metric38"; var Metric39 = "metric39"; var Metric40 = "metric40";
        var Metric41 = "metric41"; var Metric42 = "metric42"; var Metric43 = "metric43"; var Metric44 = "metric44"; var Metric45 = "metric45";
        var Metric46 = "metric46"; var Metric47 = "metric47"; var Metric48 = "metric48"; var Metric49 = "metric49"; var Metric50 = "metric50";
        var Metric51 = "metric51"; var Metric52 = "metric52"; var Metric53 = "metric53"; var Metric54 = "metric54"; var Metric55 = "metric55";
        var Metric56 = "metric56"; var Metric57 = "metric57"; var Metric58 = "metric58"; var Metric59 = "metric59"; var Metric60 = "metric60";
        var Metric61 = "metric61"; var Metric62 = "metric62"; var Metric63 = "metric63"; var Metric64 = "metric64"; var Metric65 = "metric65";
        var Metric66 = "metric66"; var Metric67 = "metric67"; var Metric68 = "metric68"; var Metric69 = "metric69"; var Metric70 = "metric70";
        var Metric71 = "metric71"; var Metric72 = "metric72"; var Metric73 = "metric73"; var Metric74 = "metric74"; var Metric75 = "metric75";
        var Metric76 = "metric76"; var Metric77 = "metric77"; var Metric78 = "metric78"; var Metric79 = "metric79"; var Metric80 = "metric80";
        var Metric81 = "metric81"; var Metric82 = "metric82"; var Metric83 = "metric83"; var Metric84 = "metric84"; var Metric85 = "metric85";
        var Metric86 = "metric86"; var Metric87 = "metric87"; var Metric88 = "metric88"; var Metric89 = "metric89"; var Metric90 = "metric90";
        var Metric91 = "metric91"; var Metric92 = "metric92"; var Metric93 = "metric93"; var Metric94 = "metric94"; var Metric95 = "metric95";
        var Metric96 = "metric96"; var Metric97 = "metric97"; var Metric98 = "metric98"; var Metric99 = "metric99"; var Metric100 = "metric100";
        var Metric101 = "metric101"; var Metric102 = "metric102"; var Metric103 = "metric103"; var Metric104 = "metric104"; var Metric105 = "metric105";
        var Metric106 = "metric106"; var Metric107 = "metric107"; var Metric108 = "metric108"; var Metric109 = "metric109"; var Metric110 = "metric110";
        var Metric111 = "metric111"; var Metric112 = "metric112"; var Metric113 = "metric113"; var Metric114 = "metric114"; var Metric115 = "metric115";
        var Metric116 = "metric116"; var Metric117 = "metric117"; var Metric118 = "metric118"; var Metric119 = "metric119"; var Metric120 = "metric120";
        var Metric121 = "metric121"; var Metric122 = "metric122"; var Metric123 = "metric123"; var Metric124 = "metric124"; var Metric125 = "metric125";
        var Metric126 = "metric126"; var Metric127 = "metric127"; var Metric128 = "metric128"; var Metric129 = "metric129"; var Metric130 = "metric130";
        var Metric131 = "metric131"; var Metric132 = "metric132"; var Metric133 = "metric133"; var Metric134 = "metric134"; var Metric135 = "metric135";
        var Metric136 = "metric136"; var Metric137 = "metric137"; var Metric138 = "metric138"; var Metric139 = "metric139"; var Metric140 = "metric140";
        var Metric141 = "metric141"; var Metric142 = "metric142"; var Metric143 = "metric143"; var Metric144 = "metric144"; var Metric145 = "metric145";
        var Metric146 = "metric146"; var Metric147 = "metric147"; var Metric148 = "metric148"; var Metric149 = "metric149"; var Metric150 = "metric150";
        var Metric151 = "metric151"; var Metric152 = "metric152"; var Metric153 = "metric153"; var Metric154 = "metric154"; var Metric155 = "metric155";
        var Metric156 = "metric156"; var Metric157 = "metric157"; var Metric158 = "metric158"; var Metric159 = "metric159"; var Metric160 = "metric160";
        var Metric161 = "metric161"; var Metric162 = "metric162"; var Metric163 = "metric163"; var Metric164 = "metric164"; var Metric165 = "metric165";
        var Metric166 = "metric166"; var Metric167 = "metric167"; var Metric168 = "metric168"; var Metric169 = "metric169"; var Metric170 = "metric170";
        var Metric171 = "metric171"; var Metric172 = "metric172"; var Metric173 = "metric173"; var Metric174 = "metric174"; var Metric175 = "metric175";
        var Metric176 = "metric176"; var Metric177 = "metric177"; var Metric178 = "metric178"; var Metric179 = "metric179"; var Metric180 = "metric180";
        var Metric181 = "metric181"; var Metric182 = "metric182"; var Metric183 = "metric183"; var Metric184 = "metric184"; var Metric185 = "metric185";
        var Metric186 = "metric186"; var Metric187 = "metric187"; var Metric188 = "metric188"; var Metric189 = "metric189"; var Metric190 = "metric190";
        var Metric191 = "metric191"; var Metric192 = "metric192"; var Metric193 = "metric193"; var Metric194 = "metric194"; var Metric195 = "metric195";
        var Metric196 = "metric196"; var Metric197 = "metric197"; var Metric198 = "metric198"; var Metric199 = "metric199"; var Metric200 = "metric200";
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

        var ProductDimension1 = "prcd1"; var ProductDimension2 = "prcd2"; var ProductDimension3 = "prcd3"; var ProductDimension4 = "prcd4"; var ProductDimension5 = "prcd5";
        var ProductDimension6 = "prcd6"; var ProductDimension7 = "prcd7"; var ProductDimension8 = "prcd8"; var ProductDimension9 = "prcd9"; var ProductDimension10 = "prcd10";
        var ProductDimension11 = "prcd11"; var ProductDimension12 = "prcd12"; var ProductDimension13 = "prcd13"; var ProductDimension14 = "prcd14"; var ProductDimension15 = "prcd15";
        var ProductDimension16 = "prcd16"; var ProductDimension17 = "prcd17"; var ProductDimension18 = "prcd18"; var ProductDimension19 = "prcd19"; var ProductDimension20 = "prcd20";
        var ProductDimension21 = "prcd21"; var ProductDimension22 = "prcd22"; var ProductDimension23 = "prcd23"; var ProductDimension24 = "prcd24"; var ProductDimension25 = "prcd25";
        var ProductDimension26 = "prcd26"; var ProductDimension27 = "prcd27"; var ProductDimension28 = "prcd28"; var ProductDimension29 = "prcd29"; var ProductDimension30 = "prcd30";
        var ProductDimension31 = "prcd31"; var ProductDimension32 = "prcd32"; var ProductDimension33 = "prcd33"; var ProductDimension34 = "prcd34"; var ProductDimension35 = "prcd35";
        var ProductDimension36 = "prcd36"; var ProductDimension37 = "prcd37"; var ProductDimension38 = "prcd38"; var ProductDimension39 = "prcd39"; var ProductDimension40 = "prcd40";
        var ProductDimension41 = "prcd41"; var ProductDimension42 = "prcd42"; var ProductDimension43 = "prcd43"; var ProductDimension44 = "prcd44"; var ProductDimension45 = "prcd45";
        var ProductDimension46 = "prcd46"; var ProductDimension47 = "prcd47"; var ProductDimension48 = "prcd48"; var ProductDimension49 = "prcd49"; var ProductDimension50 = "prcd50";
        var ProductDimension51 = "prcd51"; var ProductDimension52 = "prcd52"; var ProductDimension53 = "prcd53"; var ProductDimension54 = "prcd54"; var ProductDimension55 = "prcd55";
        var ProductDimension56 = "prcd56"; var ProductDimension57 = "prcd57"; var ProductDimension58 = "prcd58"; var ProductDimension59 = "prcd59"; var ProductDimension60 = "prcd60";
        var ProductDimension61 = "prcd61"; var ProductDimension62 = "prcd62"; var ProductDimension63 = "prcd63"; var ProductDimension64 = "prcd64"; var ProductDimension65 = "prcd65";
        var ProductDimension66 = "prcd66"; var ProductDimension67 = "prcd67"; var ProductDimension68 = "prcd68"; var ProductDimension69 = "prcd69"; var ProductDimension70 = "prcd70";
        var ProductDimension71 = "prcd71"; var ProductDimension72 = "prcd72"; var ProductDimension73 = "prcd73"; var ProductDimension74 = "prcd74"; var ProductDimension75 = "prcd75";
        var ProductDimension76 = "prcd76"; var ProductDimension77 = "prcd77"; var ProductDimension78 = "prcd78"; var ProductDimension79 = "prcd79"; var ProductDimension80 = "prcd80";
        var ProductDimension81 = "prcd81"; var ProductDimension82 = "prcd82"; var ProductDimension83 = "prcd83"; var ProductDimension84 = "prcd84"; var ProductDimension85 = "prcd85";
        var ProductDimension86 = "prcd86"; var ProductDimension87 = "prcd87"; var ProductDimension88 = "prcd88"; var ProductDimension89 = "prcd89"; var ProductDimension90 = "prcd90";
        var ProductDimension91 = "prcd91"; var ProductDimension92 = "prcd92"; var ProductDimension93 = "prcd93"; var ProductDimension94 = "prcd94"; var ProductDimension95 = "prcd95";
        var ProductDimension96 = "prcd96"; var ProductDimension97 = "prcd97"; var ProductDimension98 = "prcd98"; var ProductDimension99 = "prcd99"; var ProductDimension100 = "prcd100";
        var ProductDimension101 = "prcd101"; var ProductDimension102 = "prcd102"; var ProductDimension103 = "prcd103"; var ProductDimension104 = "prcd104"; var ProductDimension105 = "prcd105";
        var ProductDimension106 = "prcd106"; var ProductDimension107 = "prcd107"; var ProductDimension108 = "prcd108"; var ProductDimension109 = "prcd109"; var ProductDimension110 = "prcd110";
        var ProductDimension111 = "prcd111"; var ProductDimension112 = "prcd112"; var ProductDimension113 = "prcd113"; var ProductDimension114 = "prcd114"; var ProductDimension115 = "prcd115";
        var ProductDimension116 = "prcd116"; var ProductDimension117 = "prcd117"; var ProductDimension118 = "prcd118"; var ProductDimension119 = "prcd119"; var ProductDimension120 = "prcd120";
        var ProductDimension121 = "prcd121"; var ProductDimension122 = "prcd122"; var ProductDimension123 = "prcd123"; var ProductDimension124 = "prcd124"; var ProductDimension125 = "prcd125";
        var ProductDimension126 = "prcd126"; var ProductDimension127 = "prcd127"; var ProductDimension128 = "prcd128"; var ProductDimension129 = "prcd129"; var ProductDimension130 = "prcd130";
        var ProductDimension131 = "prcd131"; var ProductDimension132 = "prcd132"; var ProductDimension133 = "prcd133"; var ProductDimension134 = "prcd134"; var ProductDimension135 = "prcd135";
        var ProductDimension136 = "prcd136"; var ProductDimension137 = "prcd137"; var ProductDimension138 = "prcd138"; var ProductDimension139 = "prcd139"; var ProductDimension140 = "prcd140";
        var ProductDimension141 = "prcd141"; var ProductDimension142 = "prcd142"; var ProductDimension143 = "prcd143"; var ProductDimension144 = "prcd144"; var ProductDimension145 = "prcd145";
        var ProductDimension146 = "prcd146"; var ProductDimension147 = "prcd147"; var ProductDimension148 = "prcd148"; var ProductDimension149 = "prcd149"; var ProductDimension150 = "prcd150";
        var ProductDimension151 = "prcd151"; var ProductDimension152 = "prcd152"; var ProductDimension153 = "prcd153"; var ProductDimension154 = "prcd154"; var ProductDimension155 = "prcd155";
        var ProductDimension156 = "prcd156"; var ProductDimension157 = "prcd157"; var ProductDimension158 = "prcd158"; var ProductDimension159 = "prcd159"; var ProductDimension160 = "prcd160";
        var ProductDimension161 = "prcd161"; var ProductDimension162 = "prcd162"; var ProductDimension163 = "prcd163"; var ProductDimension164 = "prcd164"; var ProductDimension165 = "prcd165";
        var ProductDimension166 = "prcd166"; var ProductDimension167 = "prcd167"; var ProductDimension168 = "prcd168"; var ProductDimension169 = "prcd169"; var ProductDimension170 = "prcd170";
        var ProductDimension171 = "prcd171"; var ProductDimension172 = "prcd172"; var ProductDimension173 = "prcd173"; var ProductDimension174 = "prcd174"; var ProductDimension175 = "prcd175";
        var ProductDimension176 = "prcd176"; var ProductDimension177 = "prcd177"; var ProductDimension178 = "prcd178"; var ProductDimension179 = "prcd179"; var ProductDimension180 = "prcd180";
        var ProductDimension181 = "prcd181"; var ProductDimension182 = "prcd182"; var ProductDimension183 = "prcd183"; var ProductDimension184 = "prcd184"; var ProductDimension185 = "prcd185";
        var ProductDimension186 = "prcd186"; var ProductDimension187 = "prcd187"; var ProductDimension188 = "prcd188"; var ProductDimension189 = "prcd189"; var ProductDimension190 = "prcd190";
        var ProductDimension191 = "prcd191"; var ProductDimension192 = "prcd192"; var ProductDimension193 = "prcd193"; var ProductDimension194 = "prcd194"; var ProductDimension195 = "prcd195";
        var ProductDimension196 = "prcd196"; var ProductDimension197 = "prcd197"; var ProductDimension198 = "prcd198"; var ProductDimension199 = "prcd199"; var ProductDimension200 = "prcd200";

        var ProductMetric1 = "prme1"; var ProductMetric2 = "prme2"; var ProductMetric3 = "prme3"; var ProductMetric4 = "prme4"; var ProductMetric5 = "prme5";
        var ProductMetric6 = "prme6"; var ProductMetric7 = "prme7"; var ProductMetric8 = "prme8"; var ProductMetric9 = "prme9"; var ProductMetric10 = "prme10";
        var ProductMetric11 = "prme11"; var ProductMetric12 = "prme12"; var ProductMetric13 = "prme13"; var ProductMetric14 = "prme14"; var ProductMetric15 = "prme15";
        var ProductMetric16 = "prme16"; var ProductMetric17 = "prme17"; var ProductMetric18 = "prme18"; var ProductMetric19 = "prme19"; var ProductMetric20 = "prme20";
        var ProductMetric21 = "prme21"; var ProductMetric22 = "prme22"; var ProductMetric23 = "prme23"; var ProductMetric24 = "prme24"; var ProductMetric25 = "prme25";
        var ProductMetric26 = "prme26"; var ProductMetric27 = "prme27"; var ProductMetric28 = "prme28"; var ProductMetric29 = "prme29"; var ProductMetric30 = "prme30";
        var ProductMetric31 = "prme31"; var ProductMetric32 = "prme32"; var ProductMetric33 = "prme33"; var ProductMetric34 = "prme34"; var ProductMetric35 = "prme35";
        var ProductMetric36 = "prme36"; var ProductMetric37 = "prme37"; var ProductMetric38 = "prme38"; var ProductMetric39 = "prme39"; var ProductMetric40 = "prme40";
        var ProductMetric41 = "prme41"; var ProductMetric42 = "prme42"; var ProductMetric43 = "prme43"; var ProductMetric44 = "prme44"; var ProductMetric45 = "prme45";
        var ProductMetric46 = "prme46"; var ProductMetric47 = "prme47"; var ProductMetric48 = "prme48"; var ProductMetric49 = "prme49"; var ProductMetric50 = "prme50";
        var ProductMetric51 = "prme51"; var ProductMetric52 = "prme52"; var ProductMetric53 = "prme53"; var ProductMetric54 = "prme54"; var ProductMetric55 = "prme55";
        var ProductMetric56 = "prme56"; var ProductMetric57 = "prme57"; var ProductMetric58 = "prme58"; var ProductMetric59 = "prme59"; var ProductMetric60 = "prme60";
        var ProductMetric61 = "prme61"; var ProductMetric62 = "prme62"; var ProductMetric63 = "prme63"; var ProductMetric64 = "prme64"; var ProductMetric65 = "prme65";
        var ProductMetric66 = "prme66"; var ProductMetric67 = "prme67"; var ProductMetric68 = "prme68"; var ProductMetric69 = "prme69"; var ProductMetric70 = "prme70";
        var ProductMetric71 = "prme71"; var ProductMetric72 = "prme72"; var ProductMetric73 = "prme73"; var ProductMetric74 = "prme74"; var ProductMetric75 = "prme75";
        var ProductMetric76 = "prme76"; var ProductMetric77 = "prme77"; var ProductMetric78 = "prme78"; var ProductMetric79 = "prme79"; var ProductMetric80 = "prme80";
        var ProductMetric81 = "prme81"; var ProductMetric82 = "prme82"; var ProductMetric83 = "prme83"; var ProductMetric84 = "prme84"; var ProductMetric85 = "prme85";
        var ProductMetric86 = "prme86"; var ProductMetric87 = "prme87"; var ProductMetric88 = "prme88"; var ProductMetric89 = "prme89"; var ProductMetric90 = "prme90";
        var ProductMetric91 = "prme91"; var ProductMetric92 = "prme92"; var ProductMetric93 = "prme93"; var ProductMetric94 = "prme94"; var ProductMetric95 = "prme95";
        var ProductMetric96 = "prme96"; var ProductMetric97 = "prme97"; var ProductMetric98 = "prme98"; var ProductMetric99 = "prme99"; var ProductMetric100 = "prme100";
        var ProductMetric101 = "prme101"; var ProductMetric102 = "prme102"; var ProductMetric103 = "prme103"; var ProductMetric104 = "prme104"; var ProductMetric105 = "prme105";
        var ProductMetric106 = "prme106"; var ProductMetric107 = "prme107"; var ProductMetric108 = "prme108"; var ProductMetric109 = "prme109"; var ProductMetric110 = "prme110";
        var ProductMetric111 = "prme111"; var ProductMetric112 = "prme112"; var ProductMetric113 = "prme113"; var ProductMetric114 = "prme114"; var ProductMetric115 = "prme115";
        var ProductMetric116 = "prme116"; var ProductMetric117 = "prme117"; var ProductMetric118 = "prme118"; var ProductMetric119 = "prme119"; var ProductMetric120 = "prme120";
        var ProductMetric121 = "prme121"; var ProductMetric122 = "prme122"; var ProductMetric123 = "prme123"; var ProductMetric124 = "prme124"; var ProductMetric125 = "prme125";
        var ProductMetric126 = "prme126"; var ProductMetric127 = "prme127"; var ProductMetric128 = "prme128"; var ProductMetric129 = "prme129"; var ProductMetric130 = "prme130";
        var ProductMetric131 = "prme131"; var ProductMetric132 = "prme132"; var ProductMetric133 = "prme133"; var ProductMetric134 = "prme134"; var ProductMetric135 = "prme135";
        var ProductMetric136 = "prme136"; var ProductMetric137 = "prme137"; var ProductMetric138 = "prme138"; var ProductMetric139 = "prme139"; var ProductMetric140 = "prme140";
        var ProductMetric141 = "prme141"; var ProductMetric142 = "prme142"; var ProductMetric143 = "prme143"; var ProductMetric144 = "prme144"; var ProductMetric145 = "prme145";
        var ProductMetric146 = "prme146"; var ProductMetric147 = "prme147"; var ProductMetric148 = "prme148"; var ProductMetric149 = "prme149"; var ProductMetric150 = "prme150";
        var ProductMetric151 = "prme151"; var ProductMetric152 = "prme152"; var ProductMetric153 = "prme153"; var ProductMetric154 = "prme154"; var ProductMetric155 = "prme155";
        var ProductMetric156 = "prme156"; var ProductMetric157 = "prme157"; var ProductMetric158 = "prme158"; var ProductMetric159 = "prme159"; var ProductMetric160 = "prme160";
        var ProductMetric161 = "prme161"; var ProductMetric162 = "prme162"; var ProductMetric163 = "prme163"; var ProductMetric164 = "prme164"; var ProductMetric165 = "prme165";
        var ProductMetric166 = "prme166"; var ProductMetric167 = "prme167"; var ProductMetric168 = "prme168"; var ProductMetric169 = "prme169"; var ProductMetric170 = "prme170";
        var ProductMetric171 = "prme171"; var ProductMetric172 = "prme172"; var ProductMetric173 = "prme173"; var ProductMetric174 = "prme174"; var ProductMetric175 = "prme175";
        var ProductMetric176 = "prme176"; var ProductMetric177 = "prme177"; var ProductMetric178 = "prme178"; var ProductMetric179 = "prme179"; var ProductMetric180 = "prme180";
        var ProductMetric181 = "prme181"; var ProductMetric182 = "prme182"; var ProductMetric183 = "prme183"; var ProductMetric184 = "prme184"; var ProductMetric185 = "prme185";
        var ProductMetric186 = "prme186"; var ProductMetric187 = "prme187"; var ProductMetric188 = "prme188"; var ProductMetric189 = "prme189"; var ProductMetric190 = "prme190";
        var ProductMetric191 = "prme191"; var ProductMetric192 = "prme192"; var ProductMetric193 = "prme193"; var ProductMetric194 = "prme194"; var ProductMetric195 = "prme195";
        var ProductMetric196 = "prme196"; var ProductMetric197 = "prme197"; var ProductMetric198 = "prme198"; var ProductMetric199 = "prme199"; var ProductMetric200 = "prme200";
    }

    fun GADataSend_Event(GAInfo: Map<String, String>) {
        try {
            val eventBuilder = HitBuilders.EventBuilder()
            val sIterator = GAInfo.keys.iterator()
            val promotion = Promotion()
            while (sIterator.hasNext()) {
                val key = sIterator.next()
                if (GAInfo[key] != null && GAInfo[key]!!.length > 0) {
                    if (key.toLowerCase().contains("dimension")) {
                        val Number = Integer.parseInt(key.replace("[^0-9]".toRegex(), ""))
                        eventBuilder.setCustomDimension(Number, GAInfo[key])
                    }
                    if (key.toLowerCase().contains("metric")) {
                        val Number = Integer.parseInt(key.replace("[^0-9]".toRegex(), ""))
                        eventBuilder.setCustomMetric(Number, java.lang.Float.parseFloat(GAInfo[key]!!))
                    }
                    if (key.toLowerCase() == "uid") mTracker!!.set("&uid", GAInfo[key])
                    if (key.toLowerCase() == "camp_url") {
                        var URL = GAInfo[key]
                        URL = URLDecoder.decode(URL, "UTF-8")
                        URL = URLDecoder.decode(URL, "UTF-8")
                        eventBuilder.setCampaignParamsFromUrl(URL)
                    }
                    if (key.toLowerCase() == "category") eventBuilder.setCategory(GAInfo[key])
                    if (key.toLowerCase() == "action") eventBuilder.setAction(GAInfo[key])
                    if (key.toLowerCase() == "label") eventBuilder.setLabel(GAInfo[key])
                    if (key.toLowerCase() == "value") eventBuilder.setValue(java.lang.Long.parseLong(GAInfo[key]!!))
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
            Log.e("GAv4_Event", e.message)
        }
    }

    fun GADataSend_Screen(GAInfo: Map<String, String>) {
        try {
            val screenViewBuilder = HitBuilders.ScreenViewBuilder()
            val sIterator = GAInfo.keys.iterator()
            while (sIterator.hasNext()) {
                val key = sIterator.next()
                if (GAInfo[key] != null && GAInfo[key]!!.length > 0) {
                    if (key.toLowerCase().contains("dimension")) {
                        val Number = Integer.parseInt(key.replace("[^0-9]".toRegex(), ""))
                        screenViewBuilder.setCustomDimension(Number, GAInfo[key])
                    }
                    if (key.toLowerCase().contains("metric")) {
                        val Number = Integer.parseInt(key.replace("[^0-9]".toRegex(), ""))
                        screenViewBuilder.setCustomMetric(Number, java.lang.Float.parseFloat(GAInfo[key]!!))
                    }
                    if (key.toLowerCase() == "camp_url") {
                        var URL = GAInfo[key]
                        URL = URLDecoder.decode(URL, "UTF-8")
                        URL = URLDecoder.decode(URL, "UTF-8")
                        screenViewBuilder.setCampaignParamsFromUrl(URL)
                    }
                    if (key.toLowerCase() == "uid") mTracker!!.set("&uid", GAInfo[key])
                    if (key.toLowerCase() == "title") mTracker!!.setScreenName(GAInfo[key])
                    if (key.toLowerCase() == "ni" && GAInfo[key] == "1") screenViewBuilder.setNonInteraction(true)
                }
            }
            mTracker!!.send(screenViewBuilder.build())
            mTracker = NullSet(mTracker!!)
        } catch (e: Exception) {
            Log.e("GAv4_Screen", e.message)
        }
    }

    fun GADataSend_Ecommerce(EcommerceStep: String, GAAction: Map<String, String>, GAProduct: Map<String, Map<String, String>>, GAInfo: Map<String, String>) {
        try {
            if (mTracker != null) {
                var ecommerceBuilder: HitBuilders.EventBuilder? = HitBuilders.EventBuilder()
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
                            val Number = Integer.parseInt(key.replace("[^0-9]".toRegex(), ""))
                            ecommerceBuilder!!.setCustomDimension(Number, GAInfo[key])
                        }
                        if (key.toLowerCase().contains("metric")) {
                            val Number = Integer.parseInt(key.replace("[^0-9]".toRegex(), ""))
                            ecommerceBuilder!!.setCustomMetric(
                                Number,
                                java.lang.Float.parseFloat(GAInfo[key]!!)
                            )
                        }
                        if (key.toLowerCase() == "uid") mTracker!!.set("&uid", GAInfo[key])
                        if (key.toLowerCase() == "title") {
                            if (GAInfo["title"] != null && GAInfo["title"]!!.length > 0)
                                mTracker!!.setScreenName(GAInfo["title"])
                        }
                        if (key.toLowerCase() == "currencycode") {
                            mTracker!!.set("&cu", GAInfo["currencycode"])
                        }
                        if (key.toLowerCase() == "camp_url") {
                            var URL = GAInfo[key]
                            URL = URLDecoder.decode(URL, "UTF-8")
                            URL = URLDecoder.decode(URL, "UTF-8")
                            ecommerceBuilder!!.setCampaignParamsFromUrl(URL)
                        }
                        if (key.toLowerCase() == "cu") {
                            mTracker!!.set("&cu", GAInfo["cu"])
                        }
                        if (key.toLowerCase() == "category") ecommerceBuilder!!.setCategory(GAInfo["category"])
                        if (key.toLowerCase() == "action") ecommerceBuilder!!.setAction(GAInfo["action"])
                        if (key.toLowerCase() == "label") ecommerceBuilder!!.setLabel(GAInfo["label"])
                        if (key.toLowerCase() == "ni" && GAInfo[key] == "1") ecommerceBuilder!!.setNonInteraction(
                            true
                        )
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
                    if (EcommerceStep.toLowerCase() == "promotionclick")
                        ecommerceBuilder.setPromotionAction(Promotion.ACTION_CLICK)
                } else {
                    ecommerceBuilder = GA_Product(EcommerceStep, GAProduct, ecommerceBuilder)
                }

                mTracker!!.send(ecommerceBuilder!!.build())
                mTracker = NullSet(mTracker!!)
            }
        } catch (e: Exception) {
            Log.e("GAv4_Ecommerce", e.message)
        }
    }

    private fun GA_ActionField(GAAction: Map<String, String>, productAction: ProductAction?): ProductAction? {
        try {
            val sIterator_GA_ActionField = GAAction.keys.iterator()
            while (sIterator_GA_ActionField.hasNext()) {
                val actionField_key = sIterator_GA_ActionField.next()
                if (GAAction[actionField_key] != null && GAAction[actionField_key]!!.length > 0) {
                    if (actionField_key.toLowerCase() == "af_id") productAction?.setTransactionId(
                        GAAction["af_id"]
                    )
                    if (actionField_key.toLowerCase() == "af_revenue") productAction?.setTransactionRevenue(
                        java.lang.Double.valueOf(GAAction["af_revenue"]!!)
                    )
                    if (actionField_key.toLowerCase() == "af_tax") productAction?.setTransactionTax(
                        java.lang.Double.valueOf(GAAction["af_tax"]!!)
                    )
                    if (actionField_key.toLowerCase() == "af_shipping") productAction?.setTransactionShipping(
                        java.lang.Double.valueOf(GAAction["af_shipping"]!!)
                    )
                    if (actionField_key.toLowerCase() == "af_coupon") productAction?.setTransactionCouponCode(
                        GAAction["af_coupon"]
                    )
                    if (actionField_key.toLowerCase() == "af_affiliation") productAction?.setTransactionAffiliation(
                        GAAction["af_affiliation"]
                    )
                    if (actionField_key.toLowerCase() == "af_list") productAction?.setProductActionList(
                        GAAction["af_list"]
                    )
                    if (actionField_key.toLowerCase() == "af_step") productAction?.setCheckoutStep(
                        Integer.parseInt(GAAction["af_step"]!!)
                    )
                    if (actionField_key.toLowerCase() == "af_option") productAction?.setCheckoutOptions(
                        GAAction["af_option"]
                    )
                }
            }
            return productAction
        } catch (ex: Exception) {
            Log.e("GAv4_ActionField", ex.message)
            return null
        }
    }

    private fun GA_Product(EcommerceStep: String, GAProduct: Map<String, Map<String, String>>?, ecommerceBuilder: HitBuilders.EventBuilder?): HitBuilders.EventBuilder? {
        try {
            if (GAProduct != null && GAProduct.size > 0) {
                val sIterator_GAProduct = GAProduct.keys.iterator()
                while (sIterator_GAProduct.hasNext()) {
                    val product = Product()
                    var prd_list: String? = "ImpressionList"
                    val key = sIterator_GAProduct.next()

                    if (key.contains("pr")) {

                        val Productinfo = GAProduct[key]
                        val sIterator_Productinfo = Productinfo!!.keys.iterator()

                        while (sIterator_Productinfo.hasNext()) {
                            val product_key = sIterator_Productinfo.next()
                            if (Productinfo[product_key] != null && Productinfo[product_key]!!.length > 0) {
                                if (product_key.toLowerCase() == "prid") { product.setId(Productinfo["prid"]) }
                                if (product_key.toLowerCase() == "prnm") { product.setName(Productinfo["prnm"]) }
                                if (product_key.toLowerCase() == "prbr") { product.setBrand(Productinfo["prbr"]) }
                                if (product_key.toLowerCase() == "prca") { product.setCategory(Productinfo["prca"]) }
                                if (product_key.toLowerCase() == "prva") { product.setVariant(Productinfo["prva"]) }
                                if (product_key.toLowerCase() == "prpr") { product.setPrice(java.lang.Double.valueOf(Productinfo["prpr"]!!)) }
                                if (product_key.toLowerCase() == "prqt") { product.setQuantity(Integer.parseInt(Productinfo["prqt"]!!)) }
                                if (product_key.toLowerCase() == "prcc") { product.setCouponCode(Productinfo["prcc"]) }
                                if (product_key.toLowerCase() == "prps") { product.setPosition(Integer.parseInt(Productinfo["prps"]!!)) }
                                if (product_key.toLowerCase() == "prlist") { prd_list = Productinfo["prlist"] }
                                if (product_key.toLowerCase().contains("prcd")) {
                                    val Number = Integer.parseInt(product_key.replace("[^0-9]".toRegex(),""))
                                    product.setCustomDimension(Number, Productinfo[product_key])
                                }
                                if (product_key.toLowerCase().contains("prme")) {
                                    val Number = Integer.parseInt(product_key.replace("[^0-9]".toRegex(),""))
                                    product.setCustomMetric(Number, Integer.valueOf(Productinfo[product_key]!!))
                                }
                            }
                        }
                    }
                    if (EcommerceStep.toLowerCase() != "impression") {
                        ecommerceBuilder?.addProduct(product)
                    } else {
                        ecommerceBuilder?.addImpression(product, prd_list)
                    }
                }
            }
            return ecommerceBuilder
        } catch (e: Exception) {
            Log.e("GAv4_Product", e.message)
            return null
        }
    }

    class WebAppInterface(activity: Activity) {
        internal var mTracker: Tracker? = null

        internal var productAction: ProductAction? = null
        internal var ecommerce_data: JSONObject? = null

        init {
            val application = activity.application as AnalyticsApplication
            mTracker = application.getDefaultTracker()
        }

        @JavascriptInterface
        fun GA_DATA(JsonData: String) {
            try {
                val event_builder = HitBuilders.EventBuilder()
                val Screenview_Builder = HitBuilders.ScreenViewBuilder()
                val data = JSONObject(JsonData)

                var sType = ""
                var Impression_List = ""
                if (data.has("type")) sType = data.getString("type")

                if (data.has("dimension")) {
                    val obj_dimension = data.getJSONObject("dimension")
                    val sIterator = obj_dimension.keys()
                    while (sIterator.hasNext()) {
                        val key = sIterator.next()
                        val Number = Integer.parseInt(key.replace("[^0-9]".toRegex(), ""))

                        if (sType == "P" ) Screenview_Builder.setCustomDimension(Number, obj_dimension.get(key).toString())
                        else event_builder.setCustomDimension(Number, obj_dimension.get(key).toString())
                    }
                }

                if (data.has("metric")) {
                    val obj_metric = data.getJSONObject("metric")
                    val sIterator = obj_metric.keys()
                    while (sIterator.hasNext()) {
                        val key = sIterator.next()
                        val Number = Integer.parseInt(key.replace("[^0-9]".toRegex(), ""))
                        if (sType == "P") Screenview_Builder.setCustomMetric(Number, java.lang.Float.parseFloat(obj_metric.get(key).toString()))
                        else event_builder.setCustomMetric(Number, java.lang.Float.parseFloat(obj_metric.get(key).toString()))
                    }
                }

                if (data.has("nonInteraction")) event_builder.setNonInteraction(true)
                if (data.has("userId"))  mTracker!!.set("&uid", data.getString("userId"))
                if (data.has("title")) mTracker!!.setScreenName(data.getString("title"))

                //전자상거래
                if (data.has("ecommerce")) {
                    val obj_ecommerce = data.getJSONObject("ecommerce")
                    if (obj_ecommerce.has("currencyCode"))
                        mTracker!!.set("&cu", obj_ecommerce.getString("currencyCode"))

                    //제품 전시 단계
                    if (obj_ecommerce.has("promoview") || obj_ecommerce.has("promoclick")) {
                        if (obj_ecommerce.has("promoview")) ecommerce_data =
                            obj_ecommerce.getJSONObject("promoview")
                        if (obj_ecommerce.has("promoclick")) ecommerce_data =
                            obj_ecommerce.getJSONObject("promoclick")
                        if (ecommerce_data!!.has("promotions")) {
                            val product_array = JSONArray(ecommerce_data!!.getString("promotions"))
                            val promotion = Promotion()
                            for (i in 0 until product_array.length()) {
                                val product_Hashmap = product_array[i] as JSONObject
                                val keys = product_Hashmap.keys().iterator()
                                while (keys.hasNext()) {
                                    val key = keys.next()
                                    if (key.contains("id")) promotion.setId(product_Hashmap[key] as String)
                                    if (key.contains("name")) promotion.setName(product_Hashmap[key] as String)
                                    if (key.contains("creative")) promotion.setCreative(product_Hashmap[key] as String)
                                    if (key.contains("position")) promotion.setPosition(product_Hashmap[key] as String)
                                }
                            }

                            if (obj_ecommerce.has("promoview") && sType == "P") { Screenview_Builder.addPromotion(promotion)
                            } else if (obj_ecommerce.has("promoview") && sType != "P") { event_builder.addPromotion(promotion)
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
                        for (i in 0 until product_array.length()) {
                            val product_Hashmap = product_array[i] as JSONObject
                            val item = Product()
                            val keys = product_Hashmap.keys().iterator()
                            while (keys.hasNext()) {
                                val key = keys.next()
                                if (key.contains("id")) item.setId(product_Hashmap[key] as String)
                                if (key.contains("name")) item.setName(product_Hashmap[key] as String)
                                if (key.contains("brand")) item.setBrand(product_Hashmap[key] as String)
                                if (key.contains("category")) item.setCategory(product_Hashmap[key] as String)
                                if (key.contains("price")) item.setPrice(java.lang.Double.parseDouble(product_Hashmap[key]!! as String))
                                if (key.contains("quantity")) item.setQuantity(Integer.parseInt(product_Hashmap[key]!! as String))
                                if (key.contains("variant")) item.setVariant(product_Hashmap[key] as String)
                                if (key.contains("coupon")) item.setCouponCode(product_Hashmap[key] as String)
                                if (key.contains("position")) {
                                    val position_value = product_Hashmap[key]
                                    val position = position_value.toString()
                                    item.setPosition(Integer.parseInt(position))
                                }
                                if (key.contains("dimension")) {
                                    val Number = Integer.parseInt(key.replace("[^0-9]".toRegex(), ""))
                                    item.setCustomDimension(Number, product_Hashmap.get(key).toString())
                                }
                                if (key.contains("metric")) {
                                    val Number = Integer.parseInt(key.replace("[^0-9]".toRegex(), ""))
                                    item.setCustomMetric(Number, Integer.parseInt(product_Hashmap.get(key).toString()))
                                }
                            }
                            if (!obj_ecommerce.has("impressions")) {
                                if (sType == "P") Screenview_Builder.addProduct(item)
                                else event_builder.addProduct(item)
                            } else {
                                if (sType == "P") Screenview_Builder.addImpression(item, Impression_List)
                                else event_builder.addImpression(item, Impression_List)
                            }
                        }
                    }
                    if (productAction != null) {
                        if (sType == "P") Screenview_Builder.setProductAction(productAction)
                        else event_builder.setProductAction(productAction)
                    }
                }

                if (sType == "P") {
                    mTracker!!.send(Screenview_Builder.build())
                } else {
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

        fun GA_Promotion(
            ecommerce_data: JSONObject,
            eventBuilder: HitBuilders.EventBuilder
        ): HitBuilders.EventBuilder? {
            try {
                if (ecommerce_data.has("promotions")) {
                    val promotion_array = ecommerce_data.getJSONArray("promotions")
                    for (i in 0 until promotion_array.length()) {
                        val ecommerce_item = promotion_array.getJSONObject(i)
                        val promotions = Promotion()
                        if (ecommerce_item.has("id"))
                            promotions.setId(ecommerce_item.getString("id"))
                        if (ecommerce_item.has("name"))
                            promotions.setName(ecommerce_item.getString("name"))
                        if (ecommerce_item.has("position"))
                            promotions.setPosition(ecommerce_item.getString("position"))
                        if (ecommerce_item.has("creative"))
                            promotions.setCreative(ecommerce_item.getString("creative"))

                        eventBuilder.addPromotion(promotions)
                    }
                }
                return eventBuilder
            } catch (ex: Exception) {
                Log.e("GAv4_Bridge_Promotion", ex.message)
                return null
            }

        }

        fun GA_ActionField() {
            try {
                if (ecommerce_data!!.has("actionField")) {
                    val actionField_data = ecommerce_data!!.getJSONObject("actionField")
                    if (actionField_data.has("id"))
                        productAction!!.setTransactionId(actionField_data.getString("id"))
                    if (actionField_data.has("revenue"))
                        productAction!!.setTransactionRevenue(actionField_data.getDouble("revenue"))
                    if (actionField_data.has("tax"))
                        productAction!!.setTransactionTax(actionField_data.getDouble("tax"))
                    if (actionField_data.has("shipping"))
                        productAction!!.setTransactionShipping(actionField_data.getDouble("shipping"))
                    if (actionField_data.has("coupon"))
                        productAction!!.setTransactionCouponCode(actionField_data.getString("coupon"))
                    if (actionField_data.has("affiliation"))
                        productAction!!.setTransactionAffiliation(actionField_data.getString("affiliation"))
                    if (actionField_data.has("list"))
                        productAction!!.setProductActionList(actionField_data.getString("list"))
                    if (actionField_data.has("step"))
                        productAction!!.setCheckoutStep(actionField_data.getInt("step"))
                    if (actionField_data.has("option"))
                        productAction!!.setCheckoutOptions(actionField_data.getString("option"))
                }
            } catch (ex: Exception) {
                Log.e("GAv4_Bridge_ActionField", ex.message)
            }

        }

        companion object {
            fun NullSet(mTracker: Tracker): Tracker {
                mTracker.setScreenName(null)
                mTracker.set("&cu", null)
                mTracker.set("&uid", null)
                return mTracker
            }
        }
    }
}
