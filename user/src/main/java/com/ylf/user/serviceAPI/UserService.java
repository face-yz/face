package com.ylf.user.serviceAPI;

import com.ylf.user.entity.User;

import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2019-12-10
 * @desc:
 */
public interface UserService {
    int addUser(User user);    //添加用户信息

    User getUser(String uId);  //获取用户信息

    List getUserList();        //获取用户列表

    boolean isLegal(User user);//登录是否合法

    int updateUser(User user); //更新用户信息

    boolean isLegalByPhone(String phone); //根据手机号码查看用户是否合法

    User getUserByPhone(String phone);  //根据手机号码查看用户是否合法
}
