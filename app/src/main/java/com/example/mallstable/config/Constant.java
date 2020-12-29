package com.example.mallstable.config;

public class Constant {
    //api 接口
    public static class API{
        //基地址
        public static final  String BASE_URL = "http://192.168.1.60:8080/mallstable";//文件名待查
        //产品类型参数地址
        public static final String CATEGORY_PARAM =BASE_URL+"param/findallparams.do";
        //热销商品
        public static final String HOT_PRODUCT_URL = BASE_URL+"product/findhotproducts.do";
        //test

    }
    //广播
    public static class ACTION{

    }
}
