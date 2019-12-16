package com.ylf.manage.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Sign {
    private String uId;

    private String groupname;

    private String clazzname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date signdate;

    private Integer state;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getClazzname() {
        return clazzname;
    }

    public void setClazzname(String clazzname) {
        this.clazzname = clazzname;
    }

    public Date getSigndate() {
        return signdate;
    }

    public void setSigndate(Date signdate) {
        this.signdate = signdate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}