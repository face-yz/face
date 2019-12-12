package com.ylf.manage.daoAPI;

import com.ylf.manage.entity.AttendPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendPlanMapper {
    int deleteByPrimaryKey(String groupname);

    int insert(AttendPlan record);

    int insertSelective(AttendPlan record);

    List<AttendPlan> selectByPrimaryKey(String groupname);

    int updateByPrimaryKeySelective(AttendPlan record);

    int updateByPrimaryKey(AttendPlan record);
}