package com.example.mallstable.config;
/*
* created by wangquanli 12.29
* modified by liben 12.30
* modified by bing 12.30 10:21
* modified by bing 12.30 10:42
 */
public class Constant {
    //api 接口
    public static class API{
        //基地址
        public static final  String BASE_URL = "http://192.168.1.3:8011/jxshop/";//文件名待查
        //public static final  String BASE_URL = "http://10.21.154.45:8011/jxshop/";//文件名待查
        //产品类型参数地址
        public static final String CATEGORY_PARAM_URL =BASE_URL+"param/findallparams.do";
        //热销商品
        public static final String HOT_PRODUCT_URL = BASE_URL+"product/findHotProducts.do";

        //商品分类查询
        public static final String CATEGORY_PRODUCT_URL= BASE_URL+"product/findProductsByParam.do";
        //商品分类查询
        public static final String SEARCH_PRODUCT_URL= BASE_URL+"product/searchproduct.do";
        //购物车列表
        public static final String CART_LIST_URL= BASE_URL+"cart/findallcarts.do";
        //加入购物车
        public static final String CART_ADD_URL= BASE_URL+"cart/savecart.do";
        //更新购物车中的商品的数量
        public static final String CART_UPDATE_URL= BASE_URL+"cart/updatecarts.do";
        //删除购物车中商品
        public static final String CART_DEL_URL= BASE_URL+"cart/deletecarts.do";
        //登录接口
        public static final String USER_LOGIN_URL= BASE_URL+"user/login.do";
        //获取用户信息
        public static final String USER_INFO_URL= BASE_URL+"user/getuserinfo.do";
        //地址列表
        public static final String USER_ADDR_LIST_URL= BASE_URL+"addr/findaddress.do";
        //删除地址
        public static final String USER_ADDR_DEL_URL= BASE_URL+"addr/dekaddr.do";
        //设置默认地址
        public static final String USER_ADDR_DEFAULT_URL= BASE_URL+"addr/setdefault.do";
        //添加新地址
        public static final String USER_ADDR_ADD_URL= BASE_URL+"addr/saveaddr.do";
        //提交订单
        public static final String ORDER_CREATED_URL= BASE_URL+"order/createorder.do";
        //订单详情
        public static final String ORDER_DETAIL_URL= BASE_URL+"order/getdetail.do";
        //订单列表
        public static final String ORDER_LIST_URL= BASE_URL+"order/getlist.do";


        //商品详情 by bing 12.30 10:21
        public static final String PRODUCT_DETAIL_URL=BASE_URL+"product/getdetail.do";
        public static final String USER_REGISTER_URL= BASE_URL+"user/aregisterUser.do";
    }
    //广播
    public static class ACTION{
        //加载购物车列表的
  //public static final String  LOAD_CART_ACTION="cn.techaction.mall.LOAD_CART_ACTION";
        public static final String  LOAD_CART_ACTION="com.example.mallstable.LOAD_CART_ACTION";
    }


}
