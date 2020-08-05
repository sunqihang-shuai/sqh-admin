package com.fh.sqh.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserMessage {

    private String siteId;  //详细地址的ID

    private Integer userId; //用户的Id

    private String userName;  //用户的名字

    private String areaIds;  //地区的ID

    private String userIphone;  //电话号

    private Integer userIsCheck;  //默认地址

    private String detailArea;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH-mm-ss",
            timezone = "GMT+8"
    )
    private Date createDate;  //创建时间

    public String getDetailArea() {
        return detailArea;
    }

    public void setDetailArea(String detailArea) {
        this.detailArea = detailArea;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds;
    }

    public String getUserIphone() {
        return userIphone;
    }

    public void setUserIphone(String userIphone) {
        this.userIphone = userIphone;
    }

    public Integer getUserIsCheck() {
        return userIsCheck;
    }

    public void setUserIsCheck(Integer userIsCheck) {
        this.userIsCheck = userIsCheck;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
