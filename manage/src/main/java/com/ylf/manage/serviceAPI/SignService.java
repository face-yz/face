package com.ylf.manage.serviceAPI;

import com.ylf.manage.entity.Sign;

import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2019-12-19
 * @desc:
 */
public interface SignService {
    List selectUserAttendPlan(Sign sign);   //返回用户当天的考勤计划
}
