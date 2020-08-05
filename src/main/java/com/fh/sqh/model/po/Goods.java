package com.fh.sqh.model.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Goods {

    private Integer goodsId;

    private String goodsName;

    private Double goodsPrice;

    private Integer goodsIsup;

    private Integer goodsIshot;

    private String goodsPhoto;

    private String areaIds;

    private String typeIds;

    private Integer goodsStoryCount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date goodsDate;

    private Integer brandId;

    private String brandName;

    public Integer getGoodsStoryCount() {
        return goodsStoryCount;
    }

    public void setGoodsStoryCount(Integer goodsStoryCount) {
        this.goodsStoryCount = goodsStoryCount;
    }

    public String getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(String typeIds) {
        this.typeIds = typeIds;
    }

    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds;
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

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsIsup() {
        return goodsIsup;
    }

    public void setGoodsIsup(Integer goodsIsup) {
        this.goodsIsup = goodsIsup;
    }

    public Integer getGoodsIshot() {
        return goodsIshot;
    }

    public void setGoodsIshot(Integer goodsIshot) {
        this.goodsIshot = goodsIshot;
    }

    public String getGoodsPhoto() {
        return goodsPhoto;
    }

    public void setGoodsPhoto(String goodsPhoto) {
        this.goodsPhoto = goodsPhoto;
    }

    public Date getGoodsDate() {
        return goodsDate;
    }

    public void setGoodsDate(Date goodsDate) {
        this.goodsDate = goodsDate;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
