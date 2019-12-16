package com.ylf.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AttendPlan {
    private String groupname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date starttime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endtime;
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss")
    private Date marktime;
    private String clazzname;

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

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getMarktime() {
        return marktime;
    }

    public void setMarktime(Date marktime) {
        this.marktime = marktime;
    }

    public String getClazzname() {
        return clazzname;
    }

    public void setClazzname(String clazzname) {
        this.clazzname = clazzname;
    }
}