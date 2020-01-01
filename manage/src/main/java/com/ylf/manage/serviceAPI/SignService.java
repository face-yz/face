package com.ylf.manage.serviceAPI;

import com.ylf.manage.entity.ReqSign;
import com.ylf.manage.entity.ResSignPage;
import org.bouncycastle.cert.ocsp.Req;

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

    int selectUserSignListCount(ReqSign sign);  //返回用户某考勤计划下的考勤记录数量

    int updateUserSign(ReqSign sign);       //更新用户考勤状态

    List selectAttendPlanSignList(ReqSign sign);   //返回某考勤计划下某天的考勤记录

    List selectExistUserSign(ReqSign sign);     //返回用户某考勤计划下的考勤记录
}
