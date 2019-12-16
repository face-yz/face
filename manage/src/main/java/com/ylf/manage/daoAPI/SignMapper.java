package com.ylf.manage.daoAPI;

import com.ylf.manage.entity.Sign;
import org.springframework.stereotype.Repository;

@Repository
public interface SignMapper {
        int insert(Sign sign);     //添加考勤记录
}