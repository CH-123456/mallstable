package com.example.mallstable.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 利   12.30
 */
// 接收 传来的数据
public class Cart {
    private List<CartItem> lists;
    private BigDecimal totalPrice;

    public List<CartItem> getLists() {

        return lists;
    }

    public void setLists(List<CartItem> lists) {

        this.lists = lists;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
