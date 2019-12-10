package com.ylf.user.service;

import com.ylf.user.daoAPI.UserMapper;
import com.ylf.user.entity.User;
import com.ylf.user.serviceAPI.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
