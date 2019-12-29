package com.ylf.manage.constantEnum;

import java.util.HashMap;

/**
 * @author: leifeng.ye
 * @date: 2019-12-26
 * @desc: 用户列出当日考勤计划时的状态描述
 */
public enum UserSignEnum {

    NOSTART(-1, "未开始"), START(-2, "正常签到"), LATESTART(-3, "迟到签到"), END(-4, "旷课");

    private UserSignEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static HashMap<Integer, String> getUserSignEnum() {
        HashMap<Integer, String> map = new HashMap();
        for (UserSignEnum item : UserSignEnum.values()) {
            map.put(item.code, item.desc);
        }
        return map;
    }
}
