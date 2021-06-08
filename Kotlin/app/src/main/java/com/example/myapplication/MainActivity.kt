package com.example.Kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.Kotlin.GoogleAnalytics.gaThread
import com.example.Kotlin.producer.abc
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val r1: RadioGroup
        val r_1: RadioButton
        val r_2: RadioButton
        val r_3: RadioButton
        val r_4: RadioButton
        val r_5: RadioButton
        val r_6: RadioButton
        val r_7: RadioButton
        val r_8: RadioButton
        val r_9: RadioButton
        val r_10: RadioButton
        val bt1 = findViewById<View?>(R.id.bt1) as Button
        val bt2 = findViewById<View?>(R.id.c1) as Button
        val bt3 = findViewById<View?>(R.id.w1) as Button
        r1 = findViewById<View?>(R.id.commerce) as RadioGroup
        r_1 = findViewById<View?>(R.id.im) as RadioButton
        r_2 = findViewById<View?>(R.id.cl) as RadioButton
        r_3 = findViewById<View?>(R.id.de) as RadioButton
        r_4 = findViewById<View?>(R.id.caa) as RadioButton
        r_5 = findViewById<View?>(R.id.cad) as RadioButton
        r_6 = findViewById<View?>(R.id.o1) as RadioButton
        r_7 = findViewById<View?>(R.id.o2) as RadioButton
        r_8 = findViewById<View?>(R.id.o3) as RadioButton
        r_9 = findViewById<View?>(R.id.co) as RadioButton
        r_10 = findViewById<View?>(R.id.re) as RadioButton

        r1.setVisibility(View.INVISIBLE)
        val pageMap = HashMap<String, String>()
        pageMap["cd1"] = "맞춤 측정 기준1 값"
        pageMap["cd2"] = "맞춤 측정 기준2 값"
        pageMap["cd3"] = "맞춤 측정 기준3 값"
        pageMap["cd4"] = "맞춤 측정 기준4 값"
        pageMap["cd5"] = "맞춤 측정 기준5 값"
        pageMap["cd6"] = "맞춤 측정 기준6 값"
        pageMap["cd7"] = "맞춤 측정 기준7 값"
        pageMap["cd8"] = "맞춤 측정 기준8 값"
        pageMap["cd9"] = "맞춤 측정 기준9 값"
        pageMap["cd10"] = "맞춤 측정 기준10 값"

        pageMap["dt"] = "페이지 뷰"
        pageMap["dl"] = "http://www.goldenplanet.co.kr/kotiln_login/do"

        pageMap["uid"] = "사용자 Id"
        pageMap["t"] = "pageview"

        val pageThread = Thread(GoogleAnalytics.gaThread(pageMap))
        pageThread.start()


        bt3.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, Main3Activity::class.java)
            startActivity(intent)
        })

        bt1.setOnClickListener(View.OnClickListener {
            val eventMap= HashMap<String, String>()
            eventMap["cd5"] = "맞춤 측정 기준5 값"
            eventMap["cd6"] = "맞춤 측정 기준6 값"
            eventMap["cd7"] = "맞춤 측정 기준7 값"
            eventMap["cd8"] = "맞춤 측정 기준8 값"
            eventMap["ec"] = "테스트 카테고리"
            eventMap["ea"] = "테스트 이벤트클릭"
            eventMap["el"] = "테스트 라벨"
            eventMap["dt"] = "이벤트 페이지"
            eventMap["dl"] = "http://www.goldenplanet.co.kr/event.do"
            eventMap["uid"] = "사용자 Id"
            eventMap["t"] = "event"
            val eventThread = Thread(gaThread(eventMap))
            eventThread.start()
        })

        bt2.setOnClickListener(View.OnClickListener {
            r1.setVisibility(View.VISIBLE)
            r_1.setOnClickListener(View.OnClickListener {
                for (i in 1..2) {
                    var mapmap = HashMap<String, String>()
                    mapmap = LinkedHashMap()
                    mapmap["ea"] = "impression"
                    val commerceThread = Thread(gaThread(abc(i, mapmap)))
                    commerceThread.start()
                }
            })
            r_2.setOnClickListener(View.OnClickListener {
                for (i in 1..2) {
                    var mapmap = HashMap<String, String>()
                    mapmap = LinkedHashMap()
                    mapmap["ea"] = "click"
                    val commerceThread = Thread(gaThread(abc(i, mapmap)))
                    commerceThread.start()
                }
            })
            r_3.setOnClickListener(View.OnClickListener {
                for (i in 1..10) {
                    var mapmap = HashMap<String, String>()
                    mapmap = LinkedHashMap()
                    mapmap["ea"] = "detail"
                    val commerceThread = Thread(gaThread(abc(i, mapmap)))
                    commerceThread.start()
                }
            })
            r_4.setOnClickListener(View.OnClickListener {
                for (i in 1..10) {
                    var mapmap = HashMap<String, String>()
                    mapmap = LinkedHashMap()
                    mapmap["ea"] = "cartadd"
                    val commerceThread = Thread(gaThread(abc(i, mapmap)))
                    commerceThread.start()
                }
            })
            r_5.setOnClickListener(View.OnClickListener {
                for (i in 1..10) {
                    var mapmap = HashMap<String, String>()
                    mapmap = LinkedHashMap()
                    mapmap["ea"] = "cartdelete"
                    val commerceThread = Thread(gaThread(abc(i, mapmap)))
                    commerceThread.start()
                }
            })
            r_6.setOnClickListener(View.OnClickListener {
                for (i in 1..10) {
                    var mapmap = HashMap<String, String>()
                    mapmap = LinkedHashMap()
                    mapmap["ea"] = "order1"
                    val commerceThread = Thread(gaThread(abc(i, mapmap)))
                    commerceThread.start()
                }
            })
            r_7.setOnClickListener(View.OnClickListener {
                for (i in 0..9) {
                    var mapmap = HashMap<String, String>()
                    mapmap = LinkedHashMap()
                    mapmap["ea"] = "order2"
                    val commerceThread = Thread(gaThread(abc(i, mapmap)))
                    commerceThread.start()
                }
            })
            r_8.setOnClickListener(View.OnClickListener {
                for (i in 1..10) {
                    var mapmap = HashMap<String, String>()
                    mapmap = LinkedHashMap()
                    mapmap["ea"] = "order3"
                    val commerceThread = Thread(gaThread(abc(i, mapmap)))
                    commerceThread.start()
                }
            })
            r_9.setOnClickListener(View.OnClickListener {
                for (i in 1..10) {
                    var mapmap = HashMap<String, String>()
                    mapmap = LinkedHashMap()
                    mapmap["ea"] = "complete"
                    val commerceThread = Thread(gaThread(abc(i, mapmap)))
                    commerceThread.start()
                }
            })
            r_10.setOnClickListener(View.OnClickListener {
                val a = 11 ////////// 20개와 30개 구매시 환불은 처음 10개의 상품 종류만 환불
                if (a != 21 || a != 31) {
                    for (i in 1 until a) {
                        var mapmap = HashMap<String, String>()
                        mapmap = LinkedHashMap()
                        mapmap["ea"] = "refund"
                        val commerceThread = Thread(gaThread(abc(i, mapmap)))
                        commerceThread.start()
                    }
                } else {
                    for (i in 1..10) {
                        var mapmap = HashMap<String, String>()
                        mapmap = LinkedHashMap()
                        mapmap["ea"] = "refund"
                        val commerceThread = Thread(gaThread(abc(i, mapmap)))
                        commerceThread.start()
                    }
                }
            })
        })
    }
}