package com.ylf.manage.daoAPI;

import com.ylf.manage.entity.AttendPlan;
import com.ylf.manage.entity.BasePage.ReqPage;
import com.ylf.manage.entity.ReqSign;
import com.ylf.manage.entity.ResSign;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendPlanMapper {

    int insert(AttendPlan record);

    List<AttendPlan> selectByPrimaryKey(String groupname);

    List<AttendPlan> selectList();

    int selectCount();

    List<AttendPlan> selectLimitList(ReqPage page);

    AttendPlan selectAttendPlan(ResSign sign);
}