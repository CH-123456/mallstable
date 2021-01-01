package com.example.mallstable.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangquanli 2020/12/30
 * 接受后台传来的数据
 */

public class Cart {
    private List<CartItem> lists;      // 总体列表界面
    private BigDecimal totalPrice;     // 总体的价值信息

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
