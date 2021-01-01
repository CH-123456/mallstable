package com.example.mallstable.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Integer id;
    private String name;
    private Integer productId;//产品类型便哈
    private Integer partsId;//配件分类
    private String iconUrl;//商品主图片
    private String subImages;//一组小图的地址
    private String detail;//
    private String specParam;//规格参数
    private BigDecimal price;//价格
    private Integer stock;//库存
    private Integer status;//商品状态：1待售；2上架；3下架停售.默认值1
    private Integer hot;//是否热销：1热销；2不热销。默认2
    private Date created;//创建时间
    private Date updated;//更新时间
    public Product(){}
    public Product(Integer id, String name, Integer productId, Integer partsId, String iconUrl, String subImages, String detail, String specParam, BigDecimal price, Integer stock, Integer status, Integer hot, Date created, Date updated) {
        this.id = id;
        this.name = name;
        this.productId = productId;
        this.partsId = partsId;
        this.iconUrl = iconUrl;
        this.subImages = subImages;
        this.detail = detail;
        this.specParam = specParam;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.hot = hot;
        this.created = created;
        this.updated = updated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPartsId() {
        return partsId;
    }

    public void setPartsId(Integer partsId) {
        this.partsId = partsId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getSubImages() {
        return subImages;
    }

    public void setSubImages(String subImages) {
        this.subImages = subImages;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSpecParam() {
        return specParam;
    }

    public void setSpecParam(String specParam) {
        this.specParam = specParam;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
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
