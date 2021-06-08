package com.example.myapplication;

public class screenname {
    public static String sn_previous, sc_previous;
    // 생성자를 이용하여 값을 저장 및 불러오기기
    public screenname(String a, String b) {
        sn_previous = a;
        sc_previous = b;
    }

    public static String getSn_previous() {
        return sn_previous;
    }
    public static String getSc_previous() {
        return sc_previous;
    }
    public static void setSn_previous(String a, String b) {
        screenname.sn_previous = a;
        screenname.sc_previous = b;
    }
}
