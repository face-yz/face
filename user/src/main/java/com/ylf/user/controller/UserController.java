package com.ylf.user.controller;

import com.ylf.user.entity.Response;
import com.ylf.user.entity.User;
import com.ylf.user.serviceAPI.UserService;
import com.ylf.user.util.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: leifeng.ye
 * @date: 2019-12-10
 * @desc:
 */
@RestController
@RequestMapping("/api/v2")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/addUser")
    public Response addUser(@RequestBody User user){
        user.setuId(Encryption.toEncryption(user.getuId()));
        user.setPassword(Encryption.toEncryption(user.getPassword()));
        int state=service.addUser(user);
        if(state<1){
            return Response.error("添加学生失败");
        }
        else{
            return Response.success(null,"添加学生成功");
        }
    }
}
