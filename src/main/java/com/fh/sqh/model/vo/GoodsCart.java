package com.fh.sqh.model.vo;

import java.math.BigDecimal;

public class GoodsCart {

    private Integer goodsId;

    private String goodsName;

    private BigDecimal goodsPrice;

    private String goodsPhoto;

    private Integer goodsCount;

    private BigDecimal goodsMoney;

    private boolean goodsIsCheck;

    private Integer goodsIsup;

    private Integer goodsStoryCount;

    public Integer getGoodsStoryCount() {
        return goodsStoryCount;
    }

    public void setGoodsStoryCount(Integer goodsStoryCount) {
        this.goodsStoryCount = goodsStoryCount;
    }

    public Integer getGoodsIsup() {
        return goodsIsup;
    }

    public void setGoodsIsup(Integer goodsIsup) {
        this.goodsIsup = goodsIsup;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsPhoto() {
        return goodsPhoto;
    }

    public void setGoodsPhoto(String goodsPhoto) {
        this.goodsPhoto = goodsPhoto;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public BigDecimal getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(BigDecimal goodsMoney) {
        this.goodsMoney = goodsMoney;
    }

    public boolean isGoodsIsCheck() {
        return goodsIsCheck;
    }

    public void setGoodsIsCheck(boolean goodsIsCheck) {
        this.goodsIsCheck = goodsIsCheck;
    }
}
