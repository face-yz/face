package com.ylf.manage.daoAPI;

import com.ylf.manage.entity.ReqSign;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignMapper {
    int insert(ReqSign sign);

    List selectUserSign(ReqSign sign);

    List selectUserAttendPlan(String uId);

    List selectUserSignList(ReqSign sign);

    int selectUserSignListCount(ReqSign sign);

    int updateUserSign(ReqSign sign);

    List selectAttendPlanSignList(ReqSign sign);

    int selectAttendPlanOneSignDateCount(ReqSign sign);

    int selectAttendPlanOneSignDateOkCount(ReqSign sign);

    List selectExistUserSign(ReqSign sign);
}