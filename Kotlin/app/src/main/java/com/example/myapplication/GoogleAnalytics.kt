package com.example.Kotlin

import com.example.Kotlin.GoogleAnalytics.userId.user_pseudo_id
import java.util.*
class GoogleAnalytics {

    object userId {
        var user_pseudo_id = arrayOfNulls<String>(1) // 이 user_pseudo_id 에 접근하기 위해서는 userId.user_pseudo_id 로 접근해야한다.
    }

    class gaThread(private val _gaData: Map<String, String>?) : Runnable {
        internal var gaData: Map<String, String> = hashMapOf("NaN" to "NaN")
        init {
            gaData = _gaData as Map<String, String>;
        } // 기본 주 생성자(constructor) 에서는 코드가 포함될 수 없으며, 주생성자의 초기화 코드는 init block에서 해야되고 그 외
          // 추가로 변수선언 및 초기화를 위해서는 부생성자를 작성해야하며, 실행순서는 init실행이후에 부생성자순서이다.
        override fun run() {
            try {
                if (gaData != null && user_pseudo_id[0]!= null) {
                    val parameters = LinkedHashMap<String, String>()
                    parameters["v"] = "1"
                    parameters["tid"] = "UA-155376157-1"
                    parameters.put("cid", user_pseudo_id[0]!!)
                    parameters["ds"] = "1"
                    parameters["aip"] = "1"
                    val sIterator = gaData.keys.iterator()
                    while (sIterator.hasNext()) {
                        val key = sIterator.next()
                        if (key.contains("aip")) parameters[key] = gaData[key]!!
                        if (key.contains("v")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("cid")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("t")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("ds")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("dl")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("dt")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("ec")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("ea")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("el")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("ev")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("ni")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("cd")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("cm")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("uid")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("cn")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("cs")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("ck")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("cc")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("ci")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("sr")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("ul")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("an")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("aid")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("av")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("pa")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("ti")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("ta")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("tr")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("ts")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("tt")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("tcc")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("pal")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("cos")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("col")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("cu")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("pr")) parameters.put(key, gaData.get(key)!!)
                        if (key.contains("il")) parameters.put(key, gaData.get(key)!!)
                    }
                    sendHit.hitSend(parameters)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}