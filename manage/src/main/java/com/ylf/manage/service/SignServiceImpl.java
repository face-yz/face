package com.ylf.manage.service;

import com.ylf.manage.daoAPI.SignMapper;
import com.ylf.manage.entity.Sign;
import com.ylf.manage.serviceAPI.SignService;
import com.ylf.manage.util.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2019-12-19
 * @desc:
 */
@Service
public class SignServiceImpl implements SignService {

    @Autowired
    private SignMapper dao;

    @Override
    public List selectUserSign(Sign sign) {
        sign.setuId(Encoder.encoder(sign.getuId()));
        return dao.selectUserSign(sign);
    }

    @Override
    public List selectUserAttendPlan(String uId) {
        String id=Encoder.encoder(uId);
        return dao.selectUserAttendPlan(id);
    }

    @Override
    public List selectUserSignList(Sign sign) {
        sign.setuId(Encoder.encoder(sign.getuId()));
        ArrayList<Sign> list=(ArrayList<Sign>) dao.selectUserSignList(sign);
        for(Sign a:list){
            a.setuId(Encoder.decoder(a.getuId()));
        }
        return list;
    }
}
