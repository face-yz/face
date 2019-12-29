package com.ylf.manage.service;

import com.ylf.manage.daoAPI.SignMapper;
import com.ylf.manage.entity.ResSign;
import com.ylf.manage.entity.ReqSign;
import com.ylf.manage.entity.ResSignPage;
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
    public List selectUserSign(ReqSign sign) {
        sign.setuId(Encoder.encoder(sign.getuId()));
        return dao.selectUserSign(sign);
    }

    @Override
    public List selectUserAttendPlan(String uId) {
        String id = Encoder.encoder(uId);
        return dao.selectUserAttendPlan(id);
    }

    @Override
    public ResSignPage selectUserSignList(ReqSign sign) {
        ResSignPage res = new ResSignPage();
        res.setPageNo(sign.getPageNo());
        res.setPageSize(sign.getPageSize());
        sign.setuId(Encoder.encoder(sign.getuId()));
        sign.setPageNo(sign.getPageNo() * sign.getPageSize());
        ArrayList<ResSign> list = (ArrayList<ResSign>) dao.selectUserSignList(sign);
        if (list.size() > 0) {
            for (ResSign a : list) {
                a.setuId(Encoder.decoder(a.getuId()));
            }
        }
        res.setTotal(selectUserSignListCount(sign));
        res.setResult(list);
        return res;
    }

    @Override
    public int selectUserSignListCount(ReqSign sign) {
        return dao.selectUserSignListCount(sign);
    }

    @Override
    public int updateUserSign(ReqSign sign) {
        sign.setuId(Encoder.encoder(sign.getuId()));
        return dao.updateUserSign(sign);
    }
}
