package com.fh.sqh.model.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {

    private Integer userId;

    private String userName;

    private String userPassword;

    private String userIphone;

    private Integer userSex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userDate;

    private String userImgPath;

    private String areaIds;

    public String getUserIphone() {
        return userIphone;
    }

    public void setUserIphone(String userIphone) {
        this.userIphone = userIphone;
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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Date getUserDate() {
        return userDate;
    }

    public void setUserDate(Date userDate) {
        this.userDate = userDate;
    }

    public String getUserImgPath() {
        return userImgPath;
    }

    public void setUserImgPath(String userImgPath) {
        this.userImgPath = userImgPath;
    }

    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds;
    }
}
