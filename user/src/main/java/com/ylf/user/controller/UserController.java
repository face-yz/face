package com.ylf.user.controller;

import com.ylf.user.entity.Response;
import com.ylf.user.entity.User;
import com.ylf.user.serviceAPI.UserService;
import com.ylf.user.util.Encoder;
import com.ylf.user.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
        User readyUser=service.getUser(Encoder.encoder(user.getuId()));
        if(readyUser!=null){
            return Response.error("该学生已添加");
        }
        user.setuId(Encoder.encoder(user.getuId()));
        user.setPassword(Encoder.encoder(user.getPassword()));
        int state=service.addUser(user);
        if(state<1){
            return Response.error("添加学生失败");
        }
        else{
            return Response.success(null,"添加学生成功");
        }
    }

    @RequestMapping("/login")
    public Response login(@RequestBody User user){
        boolean f=service.isLegal(user);
        if(f){
            ArrayList<String> list=new ArrayList<>();
            list.add(Token.getToken(user.getuId()));
            return Response.success(list,"登录成功");
        }
        else {
            return Response.error("登录失败");
        }
    }

    @RequestMapping("/updateUser")
    public Response update(@RequestBody User user){
        user.setuId(Encoder.encoder(user.getuId()));
        user.setPhone(Encoder.encoder(user.getPhone()));
        int f=service.updateUser(user);
        if(f<1){
            return Response.error("更新失败");
        }
        else {
            return Response.success(null,"更新成功");
        }
    }

    @RequestMapping("/getUserInfo")
    public User getUserInfo(@RequestBody User user){
        User u=service.getUser(Encoder.encoder(user.getuId()));
        return u;
    }
}
