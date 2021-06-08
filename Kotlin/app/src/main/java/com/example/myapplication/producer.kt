package com.example.Kotlin

object producer {
    @JvmStatic
    fun abc(a: Int, mapmap: HashMap<String,String>): Map<String, String> {

        val random = (Math.random() * 2000).toInt()
        val shipping = 2000 + random / 30
        val tax = random / 50
        val sum = random + shipping + tax
        val str = Integer.toString(sum)
        val str1 = Integer.toString(random)
        val str2 = Integer.toString(shipping)
        val str3 = Integer.toString(tax)
        val idn = Integer.toString(a)
        val pn = '#'.toString() + Integer.toString(a)
        // 스트링 예제 String[][] arrayIt={{"key0","value0"},{"key0","value0"},{"key0","value0"},{"key0","value0"}};
        //String[][] arraylt;
        /*arraylt = new String[][]{
                                {"ti", idn},{"ta", "munyeong"}, {"tr", str}, {"ts", str2}, {"tt", str3}, {"pr1", pn}, {"ec", "ecommerce category"}, {"ea", "ecommerce - impression"},
                                {"el", "ecommerce"}, {"dt", "전자상거래"}, {"dl", "http://www.goldenplanet.co.kr/ecommerce.do"}, {"uid", "uid"}, {"t", "event"}
                          };*/
        //배열로 만들어서  map 형태로 바꾸어서 하려했으나 object object 형태로 나오게되어 string string 형태에 맞지않아 다시 map에 put 하는 방식으로 변경
        //mapmap = org.apache.commons.lang3.ArrayUtils.toMap(arraylt);
        //mapmap.put("cd1","맞춤측정기준1값") 이렇게 해도 된다.
        mapmap["cd1"] = "맞춤 측정 기준1 값"
        mapmap["cd2"] = "맞춤 측정 기준2 값"
        mapmap["cd3"] = "맞춤 측정 기준3 값"
        mapmap["cd4"] = "맞춤 측정 기준4 값"
        mapmap["cd5"] = "맞춤 측정 기준5 값"
        mapmap["cd6"] = "맞춤 측정 기준6 값"
        mapmap["cd7"] = "맞춤 측정 기준7 값"
        mapmap["cd8"] = "맞춤 측정 기준8 값"
        mapmap["cd9"] = "맞춤 측정 기준9 값"
        mapmap["cd10"] = "맞춤 측정 기준10 값"
        mapmap["ec"] = "ecommerce category"
        mapmap["el"] = "ecommerce"
        mapmap["dt"] = "전자상거래"
        mapmap["dl"] = "http://www.goldenplanet.co.kr/ecommerce.do"
        //mapmap.put("uid", "uid"); 이값은 dev tool에 나오지 않음
        mapmap["t"] = "event"
        //      mapmap.put("ni", "1");//non interaction hit 이거하면 실시간보고서에 안찍힘
        mapmap["ul"] = "en-us" // user language
        mapmap["sr"] = "800x600" // screen resolution
        val sIterator: Iterator<String> = mapmap.values.iterator()
        val key = sIterator.next()
        if (key.contains("impression")) {
            mapmap["cu"] = "WON" //currency code
            mapmap["dh"] = "/impression" //document hostname
            mapmap["dp"] = "impression page" //page
            mapmap["il" + a + "nm"] = "Listname$a" // 물품 리스트네임
            mapmap["il" + a + "pi" + a + "id"] = "productID$a" // 물품 id(sku)
            mapmap["il" + a + "pi" + a + "nm"] = "T-shirt$a" // 물품명
            mapmap["il" + a + "pi" + a + "br"] = "munyeong" // 물품 brand
            mapmap["il" + a + "pi" + a + "ca"] = "Apparel" // 물품 category
            mapmap["il" + a + "pi" + a + "va"] = "Black" // 물품 color
            mapmap["il" + a + "pi" + a + "ps"] = "" + a // 물품 position
            mapmap["il" + a + "pi" + a + "pr"] = str // 물품 노출가격
            mapmap["il" + a + "pi" + a + "cd" + a] = "member" // 물품 dimension
            mapmap["il" + a + "pi" + a + "cm" + a] = "" + a // custom metric
        } else if (key.contains("click")) {
            mapmap["pa"] = "click" //product action
            mapmap["pal"] = "Search Results" //product action list
            mapmap["pr" + a + "id"] = pn // 상품 id
            mapmap["pr" + a + "nm"] = "T-shirt$a" // 상품 이름
            mapmap["pr" + a + "br"] = "munyeong" // 상품 brand
            mapmap["pr" + a + "ca"] = "Apparel" // 상품 정보
            mapmap["pr" + a + "va"] = "Black" // 상품 variant
            mapmap["pr" + a + "ps"] = "" + a // 상품 위치
        } else if (key.contains("detail")) {
            mapmap["pa"] = "detail" //product action
            mapmap["pal"] = "Apparel Gallery" //product action list
            mapmap["pr" + a + "id"] = pn // 상품 id
            mapmap["pr" + a + "nm"] = "T-shirt$a" // 상품 이름
            mapmap["pr" + a + "br"] = "munyeong" // 상품 brand
            mapmap["pr" + a + "ca"] = "Apparel" // 상품 정보
            mapmap["pr" + a + "va"] = "Black" // 상품 variant
            mapmap["pr" + a + "pr"] = "" + sum // 상품 가격
            mapmap["pr" + a + "ps"] = "" + a // 상품 위치
        } else if (key.contains("cartadd")) {
            mapmap["cu"] = "WON" //currency code
            mapmap["pa"] = "add" //product action
            mapmap["pr" + a + "id"] = pn // 상품 id
            mapmap["pr" + a + "nm"] = "T-shirt$a" // 상품 이름
            mapmap["pr" + a + "br"] = "munyeong" // 상품 brand
            mapmap["pr" + a + "ca"] = "Apparel" // 상품 정보
            mapmap["pr" + a + "va"] = "Black" // 상품 variant
            mapmap["pr" + a + "pr"] = "" + sum // 상품 가격
            mapmap["pr" + a + "ps"] = "" + a // 상품 위치
        } else if (key.contains("cartdelete")) {
            mapmap["cu"] = "WON" //currency code
            mapmap["pa"] = "remove" //product action
            mapmap["pr" + a + "id"] = pn // 상품 id
            mapmap["pr" + a + "nm"] = "T-shirt$a" // 상품 이름
            mapmap["pr" + a + "br"] = "munyeong" // 상품 brand
            mapmap["pr" + a + "ca"] = "Apparel" // 상품 정보
            mapmap["pr" + a + "va"] = "Black" // 상품 variant
            mapmap["pr" + a + "pr"] = "" + sum // 상품 가격
            mapmap["pr" + a + "ps"] = "" + a // 상품 위치
        } else if (key.contains("order1")) {
            mapmap["cos"] = "1" // checkout step
            mapmap["pa"] = "checkout" //product action
            mapmap["pr" + a + "id"] = pn // 상품 id
            mapmap["pr" + a + "nm"] = "T-shirt$a" // 상품 이름
            mapmap["pr" + a + "br"] = "munyeong" // 상품 brand
            mapmap["pr" + a + "ca"] = "Apparel" // 상품 정보
            mapmap["pr" + a + "va"] = "Black" // 상품 variant
            mapmap["pr" + a + "pr"] = "" + sum // 상품 가격
            mapmap["pr" + a + "ps"] = "" + a // 상품 위치
        } else if (key.contains("order2")) {
            mapmap["cos"] = "2" // checkout step
            mapmap["pa"] = "checkout" //product action
            mapmap["pr" + a + "id"] = pn // 상품 id
            mapmap["pr" + a + "nm"] = "T-shirt$a" // 상품 이름
            mapmap["pr" + a + "br"] = "munyeong" // 상품 brand
            mapmap["pr" + a + "ca"] = "Apparel" // 상품 정보
            mapmap["pr" + a + "va"] = "Black" // 상품 variant
            mapmap["pr" + a + "pr"] = "" + sum // 상품 가격
            mapmap["pr" + a + "ps"] = "" + a // 상품 위치
        } else if (key.contains("order3")) {
            mapmap["cos"] = "3" // checkout step
            mapmap["pa"] = "checkout" //product action
            mapmap["pr" + a + "id"] = pn // 상품 id
            mapmap["pr" + a + "nm"] = "T-shirt$a" // 상품 이름
            mapmap["pr" + a + "br"] = "munyeong" // 상품 brand
            mapmap["pr" + a + "ca"] = "Apparel" // 상품 정보
            mapmap["pr" + a + "va"] = "Black" // 상품 variant
            mapmap["pr" + a + "pr"] = "" + sum // 상품 가격
            mapmap["pr" + a + "ps"] = "" + a // 상품 위치
            mapmap["col"] = "Visa" // 결재 방법
        } else if (key.contains("complete")) {
            mapmap["dh"] = "/complete" //document hostname
            mapmap["dp"] = "complete page" //page
            mapmap["pa"] = "purchase" //product action
            mapmap["tcc"] = "summer_sale"
            mapmap["ti"] = idn //거래 id
            mapmap["ta"] = "munyeong" // 거래 제휴사
            mapmap["tr"] = str1 // 거래 금액
            mapmap["ts"] = str2 // 거래 배송비
            mapmap["tt"] = str3 // 거래 세금
            mapmap["pr" + a + "id"] = pn // 상품 id
            mapmap["pr" + a + "nm"] = "T-shirt$a" // 상품 이름
            mapmap["pr" + a + "ca"] = "Apparel" // 상품 정보
            mapmap["pr" + a + "br"] = "munyeong" // 상품 brand
            mapmap["pr" + a + "va"] = "Black" // 상품 variant
            mapmap["pr" + a + "ps"] = "" + a // 상품 위치
        } else if (key.contains("refund")) {
            mapmap["dh"] = "/refund" //document hostname
            mapmap["dp"] = "refund page" //page
            mapmap["pa"] = "refund" //product action
            mapmap["ti"] = idn //거래 id
            mapmap["pr" + a + "id"] = pn // 상품 id
            mapmap["pr" + a + "nm"] = "T-shirt$a" // 상품 이름A
            mapmap["pr" + a + "br"] = "munyeong" // 상품 brand
            mapmap["pr" + a + "ca"] = "Apparel" // 상품 정보
            mapmap["pr" + a + "va"] = "Black" // 상품 variant
            mapmap["pr" + a + "ps"] = "" + a // 상품 위치
        }
        return mapmap
    }
}