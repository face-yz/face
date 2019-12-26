package com.ylf.manage.serviceAPI;

import com.ylf.manage.entity.Sign;

import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2019-12-19
 * @desc:
 */
public interface SignService {
    List selectUserSign(Sign sign);   //返回用户当天的考勤记录
    List selectUserAttendPlan(String uId);  //返回用户的考勤计划
    List selectUserSignList(Sign sign);   //返回用户某考勤计划下的考勤记录
}
