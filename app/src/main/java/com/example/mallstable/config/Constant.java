package com.example.mallstable.config;
/*
*created by wangquanli 12.29
* modified by liben 12.30
 */
public class Constant {
    //api 接口
    public static class API{
        //基地址
        public static final  String BASE_URL = "http://192.168.1.60:8080/mallstable";//文件名待查
        //产品类型参数地址
        public static final String CATEGORY_PARAM =BASE_URL+"param/findallparams.do";
        //热销商品
        public static final String HOT_PRODUCT_URL = BASE_URL+"product/findhotproducts.do";
        //用户数据
        public static final String USER_INFO_URL= BASE_URL+"";


    }
    //广播
    public static class ACTION{
        //加载购物车列表的
  public static final String  LOAD_CART_ACTION="cn.techaction.mall.LOAD_CART_ACTION";
    }


}
