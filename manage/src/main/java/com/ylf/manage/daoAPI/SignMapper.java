package com.ylf.manage.daoAPI;

import com.ylf.manage.entity.Sign;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignMapper {
        int insert(Sign sign);
        List selectUserSign(Sign sign);
        List selectUserAttendPlan(String uId);
        List selectUserSignList(Sign sign);
}