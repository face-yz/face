package com.ylf.user.service;

import com.ylf.user.daoAPI.UserMapper;
import com.ylf.user.entity.User;
import com.ylf.user.serviceAPI.UserService;
import com.ylf.user.util.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2019-12-10
 * @desc:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper dao;

    @Override
    public int addUser(User user) {
        user.setFlag("0");
        return dao.insertUser(user);
    }

    @Override
    public User getUser(String uId) {
        User user = dao.selectUser(uId);
        if (user != null) {
            user.setuId(Encoder.decoder(user.getuId()));
            if (user.getPhone() != null) {
                user.setPhone(Encoder.decoder(user.getPhone()));
            }
        }
        return user;
    }

    @Override
    public List getUserList() {
        ArrayList<User> list = (ArrayList<User>) dao.selectUserList();
        if (list.size() > 0) {
            for (User user : list) {
                user.setuId(Encoder.decoder(user.getuId()));
                user.setPassword(Encoder.decoder(user.getPassword()));
                if (user.getPhone() != null) {
                    user.setPhone(Encoder.decoder(user.getPhone()));
                }
            }
        }
        return list;
    }

    @Override
    public boolean isLegal(User user) {
        ArrayList<User> list = (ArrayList<User>) getUserList();
        for (User a : list) {
            if (a.getuId().equals(user.getuId()) && a.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int updateUser(User user) {
        return dao.updateUser(user);
    }

    @Override
    public boolean isLegalByPhone(String phone) {
       User user=dao.selectUserByPhone(Encoder.encoder(phone));
       if(user!=null){
           return true;
       }
        return false;
    }

    @Override
    public User getUserByPhone(String phone) {
        User user=dao.selectUserByPhone(Encoder.encoder(phone));
        user.setuId(Encoder.decoder(user.getuId()));
        return user;
    }


}
