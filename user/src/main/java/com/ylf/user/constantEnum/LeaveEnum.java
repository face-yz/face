package com.ylf.user.constantEnum;

import java.util.HashMap;

/**
 * @author: leifeng.ye
 * @date: 2019-12-23
 * @desc: 请假枚举
 */
public enum LeaveEnum {

    NOSEE(-1, "未查看"), APPROVAL(1, "批准"), NOAPPROVAL(0, "未批准");

    private LeaveEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;

    public static HashMap<Integer, String> getLeaveEnum() {
        HashMap<Integer, String> map = new HashMap();
        for (LeaveEnum item : LeaveEnum.values()) {
            map.put(item.code, item.desc);
        }
        return map;
    }
}
