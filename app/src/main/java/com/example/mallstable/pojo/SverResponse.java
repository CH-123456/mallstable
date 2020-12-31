package com.example.mallstable.pojo;

import java.io.Serializable;

public class SverResponse<T> implements Serializable {
    private int status;//封装响应码
    private String msg;//响应信息
    private T data;   //泛型，可序列化 java中 serializable

    //构造函数无参 ，有参
    public SverResponse() {
    }

    public SverResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
