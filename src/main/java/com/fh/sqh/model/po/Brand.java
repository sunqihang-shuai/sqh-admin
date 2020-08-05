package com.fh.sqh.model.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Brand {

    private Integer brandId;

    private String brandName;

    private Integer brandUsing;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;

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

    public Integer getBrandUsing() {
        return brandUsing;
    }

    public void setBrandUsing(Integer brandUsing) {
        this.brandUsing = brandUsing;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
