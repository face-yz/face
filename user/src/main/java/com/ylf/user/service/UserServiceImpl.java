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
        return dao.insertUser(user);
    }

    @Override
    public User getUser(String uId) {
        User user=dao.selectUser(uId);
        user.setuId(Encoder.decoder(user.getuId()));
        if(user.getPhone()!=null){
            user.setPhone(Encoder.decoder(user.getPhone()));
        }
        return dao.selectUser(uId);
    }

    @Override
    public List getUserList() {
       ArrayList<User> list=(ArrayList<User>) dao.selectUserList();
       for(User user:list){
           user.setuId(Encoder.decoder(user.getuId()));
           if(user.getPhone()!=null){
               user.setPhone(Encoder.decoder(user.getPhone()));
           }
       }
       return list;
    }

    @Override
    public boolean isLegal(User user) {
        ArrayList<User> list=(ArrayList<User>) getUserList();
        for(User a:list){
            if(Encoder.decoder(a.getuId()).equals(user.getuId())&&Encoder.decoder(a.getPassword()).equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int updateUser(User user) {
        return dao.updateUser(user);
    }


}
