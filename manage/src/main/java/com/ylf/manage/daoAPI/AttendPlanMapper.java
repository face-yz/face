package com.ylf.manage.daoAPI;

import com.ylf.manage.entity.AttendPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendPlanMapper {

    int insert(AttendPlan record);

    List<AttendPlan> selectByPrimaryKey(String groupname);
}