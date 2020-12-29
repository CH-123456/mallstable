package com.example.mallstable.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

//工具类，数据转换
public class JSONUtils {

    private  static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();//创建gson对象,转化传过来的就jVAV对象

    public static <T> T formJson(String json,Class<T> clz){
        return gson.fromJson(json,clz);
    }
    //fan xing fang fa
    public static <T> T fromJson(String json, Type type ){
        return gson.fromJson(json,type);
    }

    public static  String toJSON(Object obj){
        return gson.toJson(obj);
    }

    public static Gson getGson(){
        return  gson;
    }
}
