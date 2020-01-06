package com.ylf.manage.service;

import com.ylf.manage.daoAPI.AcountMapper;
import com.ylf.manage.entity.Acount;
import com.ylf.manage.serviceAPI.AcountService;
import com.ylf.manage.util.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2019-12-04
 * @desc:
 */
@Service
public class AcountServiceImpl implements AcountService {

    @Autowired
    public AcountMapper dao;

    @Override
    public List<Acount> selectList() {
        return dao.selectList();
    }

    @Override
    public boolean isLegal(Acount acount) {
        ArrayList<Acount> list = (ArrayList<Acount>) selectList();
        for (Acount a : list) {
            if (Encryption.toEncryption(acount.getUsername()).equals(a.getUsername()) && Encryption.toEncryption(acount.getPassword()).equals(a.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isLegalByPhone(String phone) {
        Acount a=dao.selectByPrimaryKey(Encryption.toEncryption(phone));
        if(a!=null){
            return true;
        }
        return false;
    }
}
