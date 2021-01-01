package com.example.mallstable.pojo;
/**
 * Created by wangquanli 2020/12/30
 * 购物车第信息
 */

import android.content.Intent;

import java.math.BigDecimal;

public class CartItem {
    private Integer id;             //id
    private Integer userId;         //用户id
    private Integer productId;      //商品编号
    private String name;            //商品名称
    private Integer quantity;       //商品数量

    private BigDecimal price;       //商品价格
    private Integer status;         //商品状态
    private BigDecimal totalPrice;  //商品总价格
    private Integer stock;          //商品库存
    private String iconUrl;         //商品图片地址

    private boolean isEdit = false; //数量是否添加,是否被编辑

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }
}
