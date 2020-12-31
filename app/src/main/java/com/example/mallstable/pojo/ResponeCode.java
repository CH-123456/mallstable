package com.example.mallstable.pojo;

public enum ResponeCode {
    //对应服务器返回的状态码，0，1
    SUCCESS(0, "SUCESS"),
    ERROR(1, "ERROR");
    private final int code;//码
    private final String desc;//描述

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private ResponeCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
