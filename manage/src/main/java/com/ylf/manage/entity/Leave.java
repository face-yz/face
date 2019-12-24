package com.ylf.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Leave {
    private String id;

    private String groupname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date starttime;
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss")
    private Date marktime;

    private String days;

    private String clazzname;

    private String uId;

    private String username;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endt;

    private String userreason;

    private Integer state;

    private String teachersay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
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

    public String getClazzname() {
        return clazzname;
    }

    public void setClazzname(String clazzname) {
        this.clazzname = clazzname;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTeachersay() {
        return teachersay;
    }

    public void setTeachersay(String teachersay) {
        this.teachersay = teachersay;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUserreason() {
        return userreason;
    }

    public void setUserreason(String userreason) {
        this.userreason = userreason;
    }

    public Date getStartt() {
        return startt;
    }

    public void setStartt(Date startt) {
        this.startt = startt;
    }

    public Date getEndt() {
        return endt;
    }

    public void setEndt(Date endt) {
        this.endt = endt;
    }
}