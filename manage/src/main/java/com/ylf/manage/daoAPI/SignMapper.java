package com.ylf.manage.daoAPI;

import com.ylf.manage.entity.Sign;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignMapper {
        int insert(Sign sign);
        List selectUserAttendPlan(Sign sign);
}