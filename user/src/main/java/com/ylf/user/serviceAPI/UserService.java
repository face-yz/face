package com.ylf.user.serviceAPI;

import com.ylf.user.entity.User;

import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2019-12-10
 * @desc:
 */
public interface UserService {
    int addUser(User user);
    User getUser(String uId);
    List getUserList();
    boolean isLegal(User user);
    int updateUser(User user);
}
