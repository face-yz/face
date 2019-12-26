package com.ylf.user.daoAPI;

import com.ylf.user.entity.Leave;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveMapper {

    int insert(Leave record);

    List selectUserLeave(String uId);

    List selectList();

    int updateArgee(String id);

    int updateNoArgee(Leave leave);

    int updateUserSign(Leave leave);

    List isLegalLeave(Leave leave);
}