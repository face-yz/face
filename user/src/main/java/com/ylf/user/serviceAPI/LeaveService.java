package com.ylf.user.serviceAPI;

import com.ylf.user.entity.Leave;

import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2019-12-23
 * @desc:
 */
public interface LeaveService {
    int askLeave(Leave leave); //请假

    List selectUserLeave(String uId); //查看用户请假记录

    List selectList();  //老师查看所有请假记录

    int updateNoArgee(Leave leave);  //老师拒绝请假

    int updateUserSign(Leave leave); //审批通过后更新考勤记录

    int updateArgee(Leave leave);   //老师同意请假

    boolean isLegalLeave(Leave leave); //查重请假
}
