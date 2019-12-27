package com.ylf.manage.serviceAPI;

import com.ylf.manage.entity.ResSign;
import com.ylf.manage.entity.ReqSign;
import com.ylf.manage.entity.ResSignPage;

import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2019-12-19
 * @desc:
 */
public interface SignService {
    List selectUserSign(ReqSign sign);   //返回用户当天的考勤记录
    List selectUserAttendPlan(String uId);  //返回用户的考勤计划
    ResSignPage selectUserSignList(ReqSign sign);   //返回用户某考勤计划下的考勤记录
    int selectUserSignListCount(ReqSign sign);  ///返回用户某考勤计划下的考勤记录数量
}
