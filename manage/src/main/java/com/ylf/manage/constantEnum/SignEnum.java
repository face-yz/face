package com.ylf.manage.constantEnum;


import java.util.HashMap;

/**
 * @author: leifeng.ye
 * @date: 2019-12-23
 * @desc: 签到记录枚举
 */
public enum SignEnum {
    DEAFULT(0,"未签到"),SUCCESS(1,"成功签到"),LEAVE(2,"请假"),
    LATE(3,"迟到");

    private Integer code;
    private String desc;

    private SignEnum(Integer code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public static HashMap<Integer,String> getSignEnum(){
        HashMap<Integer,String> map=new HashMap();
        for(SignEnum item:SignEnum.values()){
            map.put(item.code,item.desc);
        }
        return map;
    }
}
