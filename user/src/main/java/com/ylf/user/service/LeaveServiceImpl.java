package com.ylf.user.service;

import com.ylf.user.daoAPI.LeaveMapper;
import com.ylf.user.entity.Leave;
import com.ylf.user.serviceAPI.LeaveService;
import com.ylf.user.util.Encoder;
import com.ylf.user.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2019-12-23
 * @desc:
 */
@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveMapper dao;

    @Override
    public int askLeave(Leave leave) {
        leave.setId(UUID.getUUID());
        leave.setuId(Encoder.encoder(leave.getuId()));
        leave.setState(-1);
        leave.setTeachersay("");
        return dao.insert(leave);
    }

    @Override
    public List selectUserLeave(String uId) {
        ArrayList<Leave> list=(ArrayList<Leave>)dao.selectUserLeave(uId);
        for(Leave a:list){
            a.setuId(Encoder.decoder(a.getuId()));
        }
        return list;
    }

    @Override
    public List selectList() {
        ArrayList<Leave> list=(ArrayList<Leave>)dao.selectList();
        for(Leave a:list){
            a.setuId(Encoder.decoder(a.getuId()));
        }
        return list;
    }

    @Override
    public int updateNoArgee(Leave leave) {
        return dao.updateNoArgee(leave);
    }

    @Override
    public int updateUserSign(Leave leave) {
        leave.setuId(Encoder.encoder(leave.getuId()));
        return dao.updateUserSign(leave);
    }

    @Override
    public int updateArgee(Leave leave) {
        updateUserSign(leave);
        return dao.updateArgee(leave.getId());
    }
}
