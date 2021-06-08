package app.suro.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RemoteException
import com.google.android.gms.analytics.HitBuilders
import com.android.installreferrer.api.InstallReferrerClient
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.analytics.StandardExceptionParser
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import com.google.android.gms.common.GooglePlayServicesRepairableException
import java.io.IOException
import app.suro.myapplication.GoogleAnalytics.sendHit.splitQuery
import app.suro.myapplication.GoogleAnalytics.userId.user_pseudo_id
import app.suro.myapplication.MainActivity.GoogleAnalytics.mReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.google.firebase.analytics.FirebaseAnalytics
import java.io.UnsupportedEncodingException


class MainActivity : AppCompatActivity(), InstallReferrerStateListener {

    var CampaignData : String? = null

    override fun onInstallReferrerServiceDisconnected() {

    }

    override fun onInstallReferrerSetupFinished(responseCode: Int) {
        if(responseCode == InstallReferrerClient.InstallReferrerResponse.OK){
            try {
                if (CheckAppFirstExecute()) {
                    getReferralUser()
                }
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        } else {
            mReferrerClient!!.endConnection()
        }
    }

    private fun getReferralUser() {
        val response = mReferrerClient!!.getInstallReferrer()
        val referrerData = response.installReferrer
        try {
            splitQuery(Uri.parse(referrerData), "install")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        mReferrerClient!!.endConnection()
    }

    fun CheckAppFirstExecute(): Boolean {
        val pref = getSharedPreferences("isFirst", Activity.MODE_PRIVATE)
        val isFirst = pref.getBoolean("isFirst", false)
        if (!isFirst) {
            val editor = pref.edit()
            editor.putBoolean("isFirst", true)
            editor.commit()
        }
        return !isFirst
    }

    object GoogleAnalytics {
        var mReferrerClient: InstallReferrerClient? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        
        var firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        FirebaseAnalytics.getInstance(this).appInstanceId.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                user_pseudo_id[0] = task.result

                mReferrerClient = InstallReferrerClient.newBuilder(this@MainActivity).build()
                mReferrerClient!!.startConnection(this@MainActivity)

                if (null != this@MainActivity.intent.data) {
                    val data = this@MainActivity.intent.data
                    if (data != null && data.isHierarchical) {
                        try {
                            splitQuery(data, "deeplink")
                        } catch (e: UnsupportedEncodingException) {
                            e.printStackTrace()
                        }

                    }
                }
            }
        }

        btn_next_activity.setOnClickListener{
            val intent = Intent(this@MainActivity, Main2Activity::class.java)
            startActivity(intent)
        }




        var gab : GoogleAnalyticsBuilder = GoogleAnalyticsBuilder(this)
        var application = application as AnalyticsApplication
        var mTracker = application.getDefaultTracker()

        val action_Mapa : HashMap<String, String> = hashMapOf("NaN" to "NaN")
        val ecommerce_Mapa : HashMap<String, String> = hashMapOf("NaN" to "NaN")
        val product_Mapa = HashMap<String, Map<String, String>>()
        val mapa : HashMap<String, String> = hashMapOf("NaN" to "NaN")
        mapa.put(GoogleAnalyticsBuilder.GAProductKey.ProductID, "14670")                                                            //상품ID
        mapa.put(GoogleAnalyticsBuilder.GAProductKey.ProductName, "타짜: 원 아이드 잭")	                            //상품명
        mapa.put(GoogleAnalyticsBuilder.GAProductKey.ProductCategory, "영화예매")                                                //상품카테고리
        mapa.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension70, "(주)곰픽쳐스")                                  //cinema_movie_distributor
        mapa.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension71, "청불")                                               // cinema_movie_viewRating
        mapa.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension72, "20130704")                                    // cinema_movie_releaseDate
        mapa.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension73, "미국")                                               // cinema_movie_country

        product_Mapa.put("pr1", mapa);
        ecommerce_Mapa.put(GoogleAnalyticsBuilder.GAHitKey.Title, "화면명")                                                                //화면명
        ecommerce_Mapa.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory, "Ecommerce")                                     //전자상거래 카테고리
        ecommerce_Mapa.put(GoogleAnalyticsBuilder.GAHitKey.EventAction, "Click")  	                                 //전자상거래 액션
        action_Mapa.put(GoogleAnalyticsBuilder.GAActionFieldKey.ProductActionList, "제품리스트")                            //제품 리스트 명
//        gab.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Click,action_Mapa,product_Mapa,ecommerce_Mapa)


        val action_Mapb : HashMap<String, String> = hashMapOf("NaN" to "NaN")
        val ecommerce_Mapb : HashMap<String, String> = hashMapOf("NaN" to "NaN")
        val product_Mapb = HashMap<String, Map<String, String>>()
        val mapb : HashMap<String, String> = hashMapOf("NaN" to "NaN")
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductID, "14670")                                                           //상품ID
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductName, "타짜: 원 아이드 잭")	            //상품명
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductCategory, "영화예매")		            //상품카테고리
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension67, "2D")                                              //cinema_movie_filmCode
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension68, "일반사운드")                                   //cinema_movie_sound
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension70, "(주)곰픽쳐스")                                   //cinema_movie_distributor
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension71, "청불")                                                 // cinema_movie_viewRating
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension72, "20130704")                                   // cinema_movie_releaseDate
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension73, "미국")                                              // cinema_movie_country
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension74, "1016")                                              // cinema_ticket_channel
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension75, "2001")                                              // cinema_ticket_screenDate
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension77, "심야")                                              //cinema_ticket_viewClassification
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension78, "월")                                              //cinema_ticket_viewWeekDayEnd
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension79, "건대입구")                                              // cinema_ticket_theater
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension80, "20190902")                                              // cinema_ticket_today
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension81, "6")                                              // cinema_ticket_screenTime_start_hour
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension82, "샤롯데")                                              // cinema_ticket_theater_detail
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension83, "1관")                                              // cinema_ticket_theater_number
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension92, "0")                                              // cinema_ticket_screenTime_start_min
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension93, "6")                                              // cinema_ticket_screenTime_end_hour
        mapb.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension94, "0")                                              // cinema_ticket_screenTime_end_min
        product_Mapb.put("pr1", mapb);
        ecommerce_Mapb.put(GoogleAnalyticsBuilder.GAHitKey.Title, "화면명")                                                                //화면명
        ecommerce_Mapb.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory, "Ecommerce")                                     //전자상거래 카테고리
        ecommerce_Mapb.put(GoogleAnalyticsBuilder.GAHitKey.EventAction, "Detail")  	                    //전자상거래 액션

//        gab.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Detail,action_Mapb,product_Mapb,ecommerce_Mapb)

        val action_Mapc : HashMap<String, String> = hashMapOf("NaN" to "NaN")
        val ecommerce_Mapc : HashMap<String, String> = hashMapOf("NaN" to "NaN")
        val product_Mapc = HashMap<String, Map<String, String>>()
        val mapc : HashMap<String, String> = hashMapOf("NaN" to "NaN")
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductID, "14670")                                                           //상품ID
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductName, "타짜: 원 아이드 잭")	            //상품명
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductCategory, "영화예매")		            //상품카테고리
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductQuantity, "1")		            //상품수량
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension67, "2D")                                              //cinema_movie_filmCode
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension68, "일반사운드")                                   //cinema_movie_sound
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension70, "(주)곰픽쳐스")                                   //cinema_movie_distributor
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension71, "청불")                                                 // cinema_movie_viewRating
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension72, "20130704")                                   // cinema_movie_releaseDate
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension73, "미국")                                              // cinema_movie_country
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension74, "1016")                                              // cinema_ticket_channel
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension75, "2001")                                              // cinema_ticket_screenDate
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension76, "A01")                                              // cinema_ticket_seat
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension77, "심야")                                              //cinema_ticket_viewClassification
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension78, "월")                                              //cinema_ticket_viewWeekDayEnd
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension79, "건대입구")                                              // cinema_ticket_theater
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension80, "20190902")                                              // cinema_ticket_today
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension81, "6")                                              // cinema_ticket_screenTime_start_hour
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension82, "샤롯데")                                              // cinema_ticket_theater_detail
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension83, "1관")                                              // cinema_ticket_theater_number
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension86, "성인")                                              // cinema_ticket_personSelect
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension92, "0")                                              // cinema_ticket_screenTime_start_min
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension93, "6")                                              // cinema_ticket_screenTime_end_hour
        mapc.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension94, "0")                                              // cinema_ticket_screenTime_end_min
        product_Mapc.put("pr1", mapc);
        ecommerce_Mapc.put(GoogleAnalyticsBuilder.GACustomKey.Dimension102, "일반결제")                                    // cinema_ticket_division
        ecommerce_Mapc.put(GoogleAnalyticsBuilder.GAHitKey.Title, "화면명")                                                                //화면명
        ecommerce_Mapc.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory, "Ecommerce")                                     //전자상거래 카테고리
        ecommerce_Mapc.put(GoogleAnalyticsBuilder.GAHitKey.EventAction, "Chekcout")  	                    //전자상거래 액션

        action_Mapc.put(GoogleAnalyticsBuilder.GAActionFieldKey.CheckoutStep,"1")                                                 // Checkout단계

//        gab.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Checkout,action_Mapc,product_Mapc,ecommerce_Mapc)


        val action_Mapd : HashMap<String, String> = hashMapOf("NaN" to "NaN")
        val ecommerce_Mapd : HashMap<String, String> = hashMapOf("NaN" to "NaN")
        val product_Mapd = HashMap<String, Map<String, String>>()
        val mapd : HashMap<String, String> = hashMapOf("NaN" to "NaN")
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductID, "14670")                                                           //상품ID
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductName, "타짜: 원 아이드 잭")	            //상품명
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductCategory, "영화예매")		            //상품카테고리
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductQuantity, "1")		            //상품수량
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension67, "2D")                                              //cinema_movie_filmCode
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension68, "일반사운드")                                   //cinema_movie_sound
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension70, "(주)곰픽쳐스")                                   //cinema_movie_distributor
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension71, "청불")                                                 // cinema_movie_viewRating
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension72, "20130704")                                   // cinema_movie_releaseDate
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension73, "미국")                                              // cinema_movie_country
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension74, "1016")                                              // cinema_ticket_channel
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension75, "2001")                                              // cinema_ticket_screenDate
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension76, "A01")                                              // cinema_ticket_seat
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension77, "심야")                                              //cinema_ticket_viewClassification
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension78, "월")                                              //cinema_ticket_viewWeekDayEnd
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension79, "건대입구")                                              // cinema_ticket_theater
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension80, "20190902")                                              // cinema_ticket_today
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension81, "6")                                              // cinema_ticket_screenTime_start_hour
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension82, "샤롯데")                                              // cinema_ticket_theater_detail
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension83, "1관")                                              // cinema_ticket_theater_number
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension86, "성인")                                              // cinema_ticket_personSelect
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension92, "0")                                              // cinema_ticket_screenTime_start_min
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension93, "6")                                              // cinema_ticket_screenTime_end_hour
        mapd.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension94, "0")                                              // cinema_ticket_screenTime_end_min
        product_Mapd.put("pr1", mapd);
        ecommerce_Mapd.put(GoogleAnalyticsBuilder.GACustomKey.Dimension102, "일반결제")                                    // cinema_ticket_division
        ecommerce_Mapd.put(GoogleAnalyticsBuilder.GAHitKey.Title, "화면명")                                                                //화면명
        ecommerce_Mapd.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory, "Ecommerce")                                     //전자상거래 카테고리
        ecommerce_Mapd.put(GoogleAnalyticsBuilder.GAHitKey.EventAction, "Chekcout")  	                    //전자상거래 액션
        action_Mapd.put(GoogleAnalyticsBuilder.GAActionFieldKey.CheckoutStep,"1")                                                 // Checkout단계
//        gab.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Checkout,action_Mapd,product_Mapd,ecommerce_Mapd)




        var ClientId : String? = null
        if(mTracker != null) {
            ClientId = mTracker.get("&cid")
        }

        try {
            var NonNumericData = "데이터 숫자로 변경"
            var NumericData = java.lang.Float.parseFloat(NonNumericData)
        } catch (e : Exception){
            mTracker?.send(HitBuilders.ExceptionBuilder()
                .setDescription(StandardExceptionParser(this, null)
                    .getDescription(Thread.currentThread().name, e))
                .setFatal(false)
                .build())
        }

        var suro = "suro"

        var screen = HitBuilders.ScreenViewBuilder()

        if (mTracker != null) {
            mTracker.setScreenName("스크린네임")
            mTracker.send(screen.build())
        }

//        var gab : GoogleAnalyticsBuilder = GoogleAnalyticsBuilder(this)

        val screen_Map : HashMap<String, String> = hashMapOf("NaN" to "NaN")

        if (ClientId != null) {
            screen_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1,ClientId)
        }
        screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.Title, "타이틀")
        screen_Map.put(GoogleAnalyticsBuilder.GAHitKey.UserID, "사용자 ID")

//        gab.GADataSend_Screen(screen_Map)

        val event_Map : HashMap<String, String> = hashMapOf("NaN" to "NaN")

        event_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1,"맞춤 측정 기준 1 값")
        event_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2,"맞춤 측정 기준 2 값")
        event_Map.put(GoogleAnalyticsBuilder.GACustomKey.Metric1, "1000")
        event_Map.put(GoogleAnalyticsBuilder.GACustomKey.Metric2, "2000")
        event_Map.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory, "카테고리")
        event_Map.put(GoogleAnalyticsBuilder.GAHitKey.EventAction, "액션")
        event_Map.put(GoogleAnalyticsBuilder.GAHitKey.EventLabel, "라벨")

        event_Map.put(GoogleAnalyticsBuilder.GAHitKey.Title, "타이틀")
        event_Map.put(GoogleAnalyticsBuilder.GAHitKey.UserID, "사용자 ID")

//        gab.GADataSend_Event(event_Map)

        val action_Map : HashMap<String, String> = hashMapOf("NaN" to "NaN")
        val ecommerce_Map : HashMap<String, String> = hashMapOf("NaN" to "NaN")
        val product_Map = HashMap<String, Map<String, String>>()

        val map1 : HashMap<String, String> = hashMapOf("NaN" to "NaN")
        map1.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension200, "상품 디멘전 200")
        map1.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension199, "상품 디멘전 199")
        map1.put(GoogleAnalyticsBuilder.GAProductKey.ProductMetric200, "20001")
        map1.put(GoogleAnalyticsBuilder.GAProductKey.ProductMetric199, "19902")
        map1.put(GoogleAnalyticsBuilder.GAProductKey.ProductID, "90411")
        map1.put(GoogleAnalyticsBuilder.GAProductKey.ProductName, "이어폰2")
        product_Map.put("pr1", map1);

        val map2 : HashMap<String, String> = hashMapOf("NaN" to "NaN")
        map2.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension200, "상품 디멘전 200")
        map2.put(GoogleAnalyticsBuilder.GAProductKey.ProductDimension199, "상품 디멘전 199")
        map2.put(GoogleAnalyticsBuilder.GAProductKey.ProductMetric200, "20002")
        map2.put(GoogleAnalyticsBuilder.GAProductKey.ProductMetric199, "19902")
        map2.put(GoogleAnalyticsBuilder.GAProductKey.ProductID, "90412" )
        map2.put(GoogleAnalyticsBuilder.GAProductKey.ProductName, "이어폰2")
        product_Map.put("pr2", map2);

        ecommerce_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension1, "7fd-ec42-492a-92df-c62cf");	//맞춤측정기준 1: Client ID
        ecommerce_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension2, "Y" );		//맞춤측정기준 2: 로그인 여부
        ecommerce_Map.put(GoogleAnalyticsBuilder.GACustomKey.Dimension3, "M");		//맞춤측정기준 3: 성별
        ecommerce_Map.put(GoogleAnalyticsBuilder.GAHitKey.Title, "화면명");		//화면명
        ecommerce_Map.put(GoogleAnalyticsBuilder.GAHitKey.UserID, "1920931201");		//사용자 ID

        ecommerce_Map.put(GoogleAnalyticsBuilder.GAHitKey.NonInteraction, "1");		//비 상호 작용
        ecommerce_Map.put(GoogleAnalyticsBuilder.GAHitKey.EventCategory, "Ecommerce");	//전자상거래 카테고리
        ecommerce_Map.put(GoogleAnalyticsBuilder.GAHitKey.EventAction, "Purchase");		//전자상거래 액션

        action_Map.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionID, "900804647636");	//거래ID
        action_Map.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionRevenue, "15000");	//거래 총 수익
        action_Map.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionTax, "1500");	//세금
        action_Map.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionShipping, "2500");	//배송비
        action_Map.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionCouponCode, "abc6921");	//거래쿠폰코드
        action_Map.put(GoogleAnalyticsBuilder.GAActionFieldKey.TransactionAffiliation, "affcompany");	//제휴사

//        gab.GADataSend_Ecommerce(GoogleAnalyticsBuilder.GAEcommerceStepKey.Purchase, action_Map, product_Map ,ecommerce_Map);

//        var mWebView = wv_hybrid as WebView
//        var userAgent = mWebView.getSettings().getUserAgentString()
//        userAgent += "/GA_Android"
//        mWebView.getSettings().setUserAgentString(userAgent)
//        mWebView.setWebChromeClient(WebChromeClient())
//        mWebView.getSettings().setJavaScriptEnabled(true)
//        mWebView.addJavascriptInterface(GoogleAnalyticsBuilder.WebAppInterface(this), "gascriptAndroid")
//        mWebView.loadUrl("http://211.49.99.88:8011/alphalab/djkim/default")
//        val url = mWebView.getUrl().toString()


        class GAID() : AsyncTask<Void, Void, String>() {
            override fun doInBackground(vararg params: Void?): String? {
                var advertisingId : String? = null
                try{
                    val info = AdvertisingIdClient.getAdvertisingIdInfo(applicationContext);
                    advertisingId = info.getId()
                }catch (e: GooglePlayServicesNotAvailableException) { e.printStackTrace() }
                catch (e: GooglePlayServicesRepairableException) { e.printStackTrace() }
                catch (e: IOException) { e.printStackTrace() }

                return advertisingId
            }

            override fun onPostExecute(id: String?) {
                super.onPostExecute(id)
                var intent = Intent(this@MainActivity, Main2Activity::class.java)
                intent.putExtra("GAID",id)
                startActivity(intent)
            }
        }

        class ADID : AsyncTask<Void, Void, Boolean>() {
            var idinfo: AdvertisingIdClient.Info? = null
            override fun doInBackground(vararg voids: Void): Boolean? {
                try { idinfo = AdvertisingIdClient.getAdvertisingIdInfo(applicationContext) }
                catch (e: GooglePlayServicesNotAvailableException) { e.printStackTrace() }
                catch (e: GooglePlayServicesRepairableException) { e.printStackTrace() }
                catch (e: IOException) { e.printStackTrace() }

                var TrackingEnable: Boolean? = null

                try { TrackingEnable = idinfo!!.isLimitAdTrackingEnabled }
                catch (e: NullPointerException) { e.printStackTrace() }
                return TrackingEnable
            }

            override fun onPostExecute(aBoolean: Boolean?) {
                super.onPostExecute(aBoolean)
                if ((aBoolean)!! == false) {
                    mTracker?.enableAdvertisingIdCollection(true)
                }
            }
        }

        ADID().execute()




    }



}
