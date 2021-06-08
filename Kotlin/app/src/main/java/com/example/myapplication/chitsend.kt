package com.example.Kotlin

import android.util.Log
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class sendHit { //클래스 정의시 class 형식자로 정의해도되지만 object 형식자로 정의하게되면 싱글턴 패턴(여러번 호출해도 객체는 하나만생성)이 적용되어
    // 객체가 한번만 생성되도록하는 클래스가 작성된다. 그 외 익명객체를 생성할때에도 object 사용
    companion object {
        fun hitSend(it: LinkedHashMap<String, String>) {
            val builder = StringBuilder()
            for ((key, value) in it) {
                builder.append(URLEncoder.encode(key.toString(), "UTF-8"))
                builder.append("=")
                builder.append(URLEncoder.encode(value.toString(), "UTF-8"))
                builder.append("&")
            }


            builder.setLength(builder.length - 1)
            val request = "http://www.google-analytics.com/collect"

            val url = URL(request)
            val conn = url.openConnection() as HttpURLConnection //HttpURLConnection 을 통해 web데이터 가져오기
            conn.requestMethod = "POST" //url 요청에 대한 메소드 설정
            conn.setRequestProperty("Content-Length", Integer.toString(builder.toString().toByteArray().size)) //property 설정
            conn.doOutput = true

            val wr = DataOutputStream(conn.outputStream)
            wr.writeBytes(builder.toString())
            wr.close()

            val status = conn.responseCode
            try {
                if (status != 200) {
                    throw Exception("Google Analytics tracking did not return OK 200")
                } else {
                    Log.v("GADATA", builder.toString()) //GADATA 로 시작하는 형태의 로그출력
                }
            } catch (e: Exception) {
                throw Exception(e.message)
            }
        }
    }
}