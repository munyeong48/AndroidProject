package com.example.asyncaos;

import java.util.Iterator;
import java.util.Map;

public class producer {
    public static Map<String, String> abc(int a, Map<String, String> mapmap) {

        int random = (int) (Math.random() * 2000);
        int shipping = 2000 + random / 30;
        int tax = random / 50;
        int sum = random+shipping+tax;
        String str= Integer.toString(sum);
        String str1= Integer.toString(random);
        String str2= Integer.toString(shipping);
        String str3= Integer.toString(tax);
        String idn = Integer.toString(a);
        String pn = '#' + Integer.toString(a);
        // 스트링 예제 String[][] arrayIt={{"key0","value0"},{"key0","value0"},{"key0","value0"},{"key0","value0"}};
        //String[][] arraylt;
        /*arraylt = new String[][]{
                                {"ti", idn},{"ta", "munyeong"}, {"tr", str}, {"ts", str2}, {"tt", str3}, {"pr1", pn}, {"ec", "ecommerce category"}, {"ea", "ecommerce - impression"},
                                {"el", "ecommerce"}, {"dt", "전자상거래"}, {"dl", "http://www.goldenplanet.co.kr/ecommerce.do"}, {"uid", "uid"}, {"t", "event"}
                          };*/ //배열로 만들어서  map 형태로 바꾸어서 하려했으나 object object 형태로 나오게되어 string string 형태에 맞지않아 다시 map에 put 하는 방식으로 변경
        //mapmap = org.apache.commons.lang3.ArrayUtils.toMap(arraylt);

        mapmap.put("cd1", "맞춤 측정 기준1 값");
        mapmap.put("cd2", "맞춤 측정 기준2 값");
        mapmap.put("cd3", "맞춤 측정 기준3 값");
        mapmap.put("cd4", "맞춤 측정 기준4 값");
        mapmap.put("cd5", "맞춤 측정 기준5 값");
        mapmap.put("cd6", "맞춤 측정 기준6 값");
        mapmap.put("cd7", "맞춤 측정 기준7 값");
        mapmap.put("cd8", "맞춤 측정 기준8 값");
        mapmap.put("cd9", "맞춤 측정 기준9 값");
        mapmap.put("cd10", "맞춤 측정 기준10 값");
        mapmap.put("ec", "ecommerce category");
        mapmap.put("el", "ecommerce");
        mapmap.put("dt", "전자상거래");
        mapmap.put("dl", "http://www.goldenplanet.co.kr/ecommerce.do");
        //mapmap.put("uid", "uid"); 이값은 dev tool에 나오지 않음
        mapmap.put("t", "event");
//      mapmap.put("ni", "1");//non interaction hit 이거하면 실시간보고서에 안찍힘
        mapmap.put("ul", "en-us"); // user languager
        mapmap.put("sr", "800x600"); // screen resolution
        Iterator<String> sIterator= mapmap.values().iterator();
        String key= sIterator.next();
        if (key.contains("impression")) {
            mapmap.put("cu", "WON"); //currency code
            mapmap.put("dh", "/impression"); //document hostname
            mapmap.put("dp", "impression page"); //page
            mapmap.put("il" + a + "nm", "Listname" + a); // 물품 리스트네임
            mapmap.put("il" + a + "pi" + a + "id", "productID" + a); // 물품 id(sku)
            mapmap.put("il" + a + "pi" + a + "nm", "T-shirt" + a); // 물품명
            mapmap.put("il" + a + "pi" + a + "br", "munyeong"); // 물품 brand
            mapmap.put("il" + a + "pi" + a + "ca", "Apparel"); // 물품 category
            mapmap.put("il" + a + "pi" + a + "va", "Black"); // 물품 color
            mapmap.put("il" + a + "pi" + a + "ps", "" + a); // 물품 position
            mapmap.put("il" + a + "pi" + a + "pr", str); // 물품 노출가격
            mapmap.put("il" + a + "pi" + a + "cd" + a, "member"); // 물품 dimension
            mapmap.put("il" + a + "pi" + a + "cm" + a, "" + a); // custom metric
        }
        else if (key.contains("click")) {
            mapmap.put("pa","click"); //product action
            mapmap.put("pal","Search Results"); //product action list
            mapmap.put("pr"+a+"id", pn);// 상품 id
            mapmap.put("pr"+a+"nm", "T-shirt"+a);// 상품 이름
            mapmap.put("pr"+a+"br", "munyeong");// 상품 brand
            mapmap.put("pr"+a+"ca", "Apparel");// 상품 정보
            mapmap.put("pr"+a+"va", "Black");// 상품 variant
            mapmap.put("pr"+a+"ps", ""+a);// 상품 위치
        }
        else if (key.contains("detail")) {
            mapmap.put("pa","detail"); //product action
            mapmap.put("pal","Apparel Gallery"); //product action list
            mapmap.put("pr"+a+"id", pn);// 상품 id
            mapmap.put("pr"+a+"nm", "T-shirt"+a);// 상품 이름
            mapmap.put("pr"+a+"br", "munyeong");// 상품 brand
            mapmap.put("pr"+a+"ca", "Apparel");// 상품 정보
            mapmap.put("pr"+a+"va", "Black");// 상품 variant
            mapmap.put("pr"+a+"pr", ""+sum);// 상품 가격
            mapmap.put("pr"+a+"ps", ""+a);// 상품 위치
        }
        else if (key.contains("cartadd")) {
            mapmap.put("cu", "WON"); //currency code
            mapmap.put("pa","add"); //product action
            mapmap.put("pr"+a+"id", pn);// 상품 id
            mapmap.put("pr"+a+"nm", "T-shirt"+a);// 상품 이름
            mapmap.put("pr"+a+"br", "munyeong");// 상품 brand
            mapmap.put("pr"+a+"ca", "Apparel");// 상품 정보
            mapmap.put("pr"+a+"va", "Black");// 상품 variant
            mapmap.put("pr"+a+"pr", ""+sum);// 상품 가격
            mapmap.put("pr"+a+"ps", ""+a);// 상품 위치
        }
        else if (key.contains("cartdelete")) {
            mapmap.put("cu", "WON"); //currency code
            mapmap.put("pa","remove"); //product action
            mapmap.put("pr"+a+"id", pn);// 상품 id
            mapmap.put("pr"+a+"nm", "T-shirt"+a);// 상품 이름
            mapmap.put("pr"+a+"br", "munyeong");// 상품 brand
            mapmap.put("pr"+a+"ca", "Apparel");// 상품 정보
            mapmap.put("pr"+a+"va", "Black");// 상품 variant
            mapmap.put("pr"+a+"pr", ""+sum);// 상품 가격
            mapmap.put("pr"+a+"ps", ""+a);// 상품 위치
        }
        else if (key.contains("order1")) {
            mapmap.put("cos", "1"); // checkout step
            mapmap.put("pa","checkout"); //product action
            mapmap.put("pr"+a+"id", pn);// 상품 id
            mapmap.put("pr"+a+"nm", "T-shirt"+a);// 상품 이름
            mapmap.put("pr"+a+"br", "munyeong");// 상품 brand
            mapmap.put("pr"+a+"ca", "Apparel");// 상품 정보
            mapmap.put("pr"+a+"va", "Black");// 상품 variant
            mapmap.put("pr"+a+"pr", ""+sum);// 상품 가격
            mapmap.put("pr"+a+"ps", ""+a);// 상품 위치
    }
        else if (key.contains("order2")) {
            mapmap.put("cos", "2"); // checkout step
            mapmap.put("pa","checkout"); //product action
            mapmap.put("pr"+a+"id", pn);// 상품 id
            mapmap.put("pr"+a+"nm", "T-shirt"+a);// 상품 이름
            mapmap.put("pr"+a+"br", "munyeong");// 상품 brand
            mapmap.put("pr"+a+"ca", "Apparel");// 상품 정보
            mapmap.put("pr"+a+"va", "Black");// 상품 variant
            mapmap.put("pr"+a+"pr", ""+sum);// 상품 가격
            mapmap.put("pr"+a+"ps", ""+a);// 상품 위치
    }
        else if (key.contains("order3")) {
            mapmap.put("cos", "3"); // checkout step
            mapmap.put("pa","checkout"); //product action
            mapmap.put("pr"+a+"id", pn);// 상품 id
            mapmap.put("pr"+a+"nm", "T-shirt"+a);// 상품 이름
            mapmap.put("pr"+a+"br", "munyeong");// 상품 brand
            mapmap.put("pr"+a+"ca", "Apparel");// 상품 정보
            mapmap.put("pr"+a+"va", "Black");// 상품 variant
            mapmap.put("pr"+a+"pr", ""+sum);// 상품 가격
            mapmap.put("pr"+a+"ps", ""+a);// 상품 위치
            mapmap.put("col","Visa");// 결재 방법
    }
        else if (key.contains("complete")) {
            mapmap.put("dh", "/complete"); //document hostname
            mapmap.put("dp", "complete page"); //page
            mapmap.put("pa","purchase"); //product action
            mapmap.put("tcc","summer_sale");
            mapmap.put("ti", idn);//거래 id
            mapmap.put("ta", "munyeong");// 거래 제휴사
            mapmap.put("tr", str1);// 거래 금액
            mapmap.put("ts", str2);// 거래 배송비
            mapmap.put("tt", str3);// 거래 세금
            mapmap.put("pr"+a+"id", pn);// 상품 id
            mapmap.put("pr"+a+"nm", "T-shirt"+a);// 상품 이름
            mapmap.put("pr"+a+"ca", "Apparel");// 상품 정보
            mapmap.put("pr"+a+"br", "munyeong");// 상품 brand
            mapmap.put("pr"+a+"va", "Black");// 상품 variant
            mapmap.put("pr"+a+"ps", ""+a);// 상품 위치
        }
        else if (key.contains("refund")){
            mapmap.put("dh", "/refund"); //document hostname
            mapmap.put("dp", "refund page"); //page
            mapmap.put("pa","refund"); //product action
            mapmap.put("ti", idn);//거래 id
            mapmap.put("pr"+a+"id", pn);// 상품 id
            mapmap.put("pr"+a+"nm", "T-shirt"+a);// 상품 이름A
            mapmap.put("pr"+a+"br", "munyeong");// 상품 brand
            mapmap.put("pr"+a+"ca", "Apparel");// 상품 정보
            mapmap.put("pr"+a+"va", "Black");// 상품 variant
            mapmap.put("pr"+a+"ps", ""+a);// 상품 위치
        }

        return mapmap;
    }
  }


