package com.example.Kotlin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.RemoteException
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.android.installreferrer.api.ReferrerDetails
import com.example.Kotlin.GoogleAnalytics.gaThread
import com.example.Kotlin.GoogleAnalytics.userId.user_pseudo_id
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.firebase.analytics.FirebaseAnalytics
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.util.*

// splash activity
// main2 실행 후 main 실행 후 main3 실행순
class Main2Activity : AppCompatActivity(), InstallReferrerStateListener {
    private var mReferrerClient: InstallReferrerClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        class GAID():AsyncTask<Void, Void, String>(){
            override fun doInBackground(vararg params: Void?): String?{
                var advertisingId : String? =null
                try{
                    val info = AdvertisingIdClient.getAdvertisingIdInfo(applicationContext)
                    advertisingId = info.getId()
                }catch(e:GooglePlayServicesNotAvailableException){e.printStackTrace()}
                catch(e:GooglePlayServicesRepairableException){e.printStackTrace()}
                catch(e:IOException){e.printStackTrace()}
                return advertisingId
            }
            override fun onPostExecute(id:String?){
                super.onPostExecute(id)
                var intent = Intent(this@Main2Activity,MainActivity::class.java)
                intent.putExtra("GAID",id)
                startActivity(intent)
            }
        }
        FirebaseAnalytics.getInstance(this).appInstanceId.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                user_pseudo_id[0]= task.result


                GAID().execute() //gaid 실행안됨
                val intent = Intent(this@Main2Activity, MainActivity::class.java)
                startActivity(intent)
                mReferrerClient = InstallReferrerClient.newBuilder(this@Main2Activity).build() //install referrerclient 클래스의 인스턴스 생성
                mReferrerClient!!.startConnection(this@Main2Activity) //onInstallReferrerSetupFinished 실행

                if (null != this@Main2Activity.intent.data) {
                    val data = this@Main2Activity.intent.data // 이를 통해 매니패스트파일에서 설정한 스키마를 웹에 공유하기위해 스키마를불러온다.
                    // 이때 gadata 만오는것이아닌 url형태로 gata://호스트?param=? 이런식으로 온다. 호스트는 명시안했으므로 빈공간으로해서나올듯
                    //Uri는 URL과 비슷하나 그 주소의 method를 불러온다는개념
                    if (data != null && data.isHierarchical) { // isHierarchical = Returns true if this URI is hierarchical like "http://google.com".
                        try {
                            splitQuery(data, "deeplink")
                        } catch (e: UnsupportedEncodingException) {
                            e.printStackTrace()
                            throw e
                        }
                    }
                }
            }
        }
    }



    override fun onInstallReferrerServiceDisconnected() {
        // 다음 google play 요청때 연결을 재시작
    }


    override fun onInstallReferrerSetupFinished(responseCode: Int) {
        if (responseCode == InstallReferrerClient.InstallReferrerResponse.OK) {
            //구글 플레이 앱과 연결이 성공했을 때, 리퍼러 데이터를 얻기위한 작업을 수행합니다.
            try {
                if (CheckAppFirstExecute()) { //앱 최초실행 확인
                    getReferralUser()
                }
            } catch (e: RemoteException) {
                e.printStackTrace()
                throw e
            }
        } else{
            // 실패이유 2가지는 현재 play store app에서 api를 이용할수 없을때와
            // connection 이 설립안됬을 수 있다.
            mReferrerClient!!.endConnection()
        }
    }

    //앱 설치하고 처음실행했는지 확인하는 함수
    fun CheckAppFirstExecute(): Boolean {
        val pref = getSharedPreferences("isFirst", Activity.MODE_PRIVATE) // referrer 정보를 가져옴  - 경로 : data/data/패키지명/shared_prefs/SharedPreference 에 파일이 저장
        // isfirst 라는 이름으로 sharedpreferences 객체를 만들어 pref에 저장
        val isFirst = pref.getBoolean("isFirst", false) // key isfirst 에 대한 값이 false인지 확인 false라면 if문에서 true 아니라면, true return

        if (!isFirst) {
            val editor = pref.edit()
            editor.putBoolean("isFirst", true) // 억지로 true값을 넣어줌
            editor.commit() // commit : 확정되어 저장
        }
        return !isFirst
    }

    // 위의 checkappfirstexecute가 참이면 실행됨
    private fun getReferralUser() {
        val response : ReferrerDetails = mReferrerClient!!.getInstallReferrer()
        val referrerData = response.installReferrer
        try {
            Main2Activity.splitQuery(Uri.parse(referrerData), "install")

            response.installReferrer
            response.referrerClickTimestampSeconds
            response.installBeginTimestampSeconds
            // 이 3줄은 설치 타임스탬프와 리퍼러 url을 가져온다.
            // 이때 리퍼러정보는 90일동안 사용가능하므로 애플리케이션을 재설치하지 않는 한 변경되지 않는다. 따라서 앱에서 불필요한 api 호출을 방지하려면
            // 처음 실행할때 한번만 api를 호출해야한다.

        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            throw e
        }
        mReferrerClient!!.endConnection()
    }

    companion object {
        var firebaseAnalytics: FirebaseAnalytics? = null
        @Throws(UnsupportedEncodingException::class)
        fun splitQuery(uri: Uri, data: String): Map<String, String> { // throws un~ 이것은 if this isn't a hierarchical url 일때 throw를 반환

            // 이 splitquery를 호출할때는 Main2Activity.splitQuery()로 부를 수 있다. // 같은 클래스내에선 클래스명 생략가능

            val campaignMap: MutableMap<String, String> = HashMap()
            var query: String? = uri.toString() // 위에서 받은 uri를 string 으로 받아옴
            Log.i("MyApp", "Deep link clicked $query") // log 출력하는 영역
            if (data == "install") campaignMap["dt"] = "캠페인>앱설치"
            if (data == "deeplink") campaignMap["dt"] = "캠페인>딥링크"
            campaignMap["t"] = "pageview"
            campaignMap["dl"] = "http://www.goldenplanet.co.kr/campaign.do" // 각 map에 필요한 값 put
            campaignMap["ni"] = "1"

            if (uri.toString().contains("://")) query = uri.query // url 부분중 // 로 된 부분에대한 query들을 가져온다.
            if (query != null) {
                val pairs = query.split("&").toTypedArray()//totypedarray는 없어도 상관없다.
                for (pair in pairs) {
                    val idx = pair.indexOf("=")
                    if (pair.contains("utm_source")) campaignMap["cs"] = URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                    else if (pair.contains("utm_medium")) campaignMap["cm"] = URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                    else if (pair.contains("utm_term")) campaignMap["ck"] = URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                    else if (pair.contains("utm_content")) campaignMap["cc"] = URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                    else if (pair.contains("utm_campaign")) campaignMap["cn"] = URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                }
            }
            val t = Thread(gaThread(campaignMap)) //  campaignmap에 put 시켜놓은 것들을 gathread로 보냄
            t.start()
            return campaignMap
        }
    }
}