package com.ylf.user.entity;

import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2019-12-04
 * @desc: 响应
 */
public class Response {

    private String code;
    private String msg;
    private List data;

    public static Response success(List data,String msg){
        Response res=new Response();
        res.setCode("1");
        res.setData(data);
        res.setMsg(msg);
        return res;
    }

    public static Response error(String msg){
        Response res=new Response();
        res.setCode("0");
        res.setMsg(msg);
        return res;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
