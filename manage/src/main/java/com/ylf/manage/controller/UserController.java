package com.ylf.manage.controller;

import com.ylf.manage.entity.Response;
import com.ylf.manage.entity.User;
import com.ylf.manage.remote.user.UserRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: leifeng.ye
 * @date: 2019-12-11
 * @desc:
 */
@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserRpc userRpc;

    @RequestMapping("/userLogin")
    public Response userLogin(@RequestBody User user){
        return userRpc.login(user);
    }

    @RequestMapping("updateUser")
    public Response updateUser(@RequestBody User user){
        return userRpc.updateUser(user);
    }
}
