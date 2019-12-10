package com.ylf.user.entity;


import lombok.Data;

/**
 * @author: leifeng.ye
 * @date: 2019-12-10
 * @desc:
 */
public class User {
    private String uId;
    private String userName;
    private String password;
    private String phone;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
