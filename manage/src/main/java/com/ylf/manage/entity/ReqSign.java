package com.ylf.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ylf.manage.entity.BasePage.ReqPage;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ReqSign extends ReqPage {
    private String uId;

    private String groupname;

    private String clazzname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date signdate;

    private Integer state;

    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss")
    private Date marktime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date starttime;

    private String days;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date selecttime;


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

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getMarktime() {
        return marktime;
    }

    public void setMarktime(Date marktime) {
        this.marktime = marktime;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Date getSelecttime() {
        return selecttime;
    }

    public void setSelecttime(Date selecttime) {
        this.selecttime = selecttime;
    }
}