package com.ylf.manage.serviceAPI;

import com.ylf.manage.entity.ReqSign;

import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2020-01-01
 * @desc:
 */
public interface AttendPlanSituationService {
    List usersAttendPlanSituation(ReqSign sign);
    List selectDistinctUserCount(ReqSign sign);
    List selectClazzCurrentPercentage(ReqSign sign);
}
