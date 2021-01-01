package com.example.mallstable.pojo;

import java.io.Serializable;
import java.util.Date;

/*
 * created by liben 12.30
 * modified bt bing 12.31 添加序列化
 */

public class Address implements Serializable {
    private Integer id;
    private Integer uid;
    private String name;
    private String phone;//固定电话
    private String mobile;//手机号
    private String province;
    private String city;
    private String district;//区/县
    private String addr;//详细地址
    private String zip;//邮编
    private boolean dfault;//是否为默认地址，默认为是
    private boolean isDel;//是否删除
    private Date created;//创建时间
    private Date updated;//更新时间


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public boolean isDfault() {
        return dfault;
    }

    public void setDfault(boolean dfault) {
        this.dfault = dfault;
    }

    public boolean isDel() {
        return isDel;
    }

    public void setDel(boolean del) {
        isDel = del;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
