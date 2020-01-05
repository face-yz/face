package com.ylf.user.controller;

import com.alibaba.fastjson.JSONObject;
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
import java.util.HashMap;
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
    public Response addUser(@RequestBody User user) {
        User readyUser = service.getUser(Encoder.encoder(user.getuId()));
        if (readyUser != null) {
            return Response.error("该学生已添加");
        }
        user.setuId(Encoder.encoder(user.getuId()));
        user.setPassword(Encoder.encoder(user.getPassword()));
        if(user.getPhone()!=null){
            user.setPhone(Encoder.encoder(user.getPhone()));
        }
        int state = service.addUser(user);
        if (state < 1) {
            return Response.error("添加学生失败");
        } else {
            return Response.success(null, "添加学生成功");
        }
    }

    @RequestMapping("/login")
    public Response login(@RequestBody User user) {
        boolean f = service.isLegal(user);
        if (f) {
            User u = service.getUser(Encoder.encoder(user.getuId()));
            ArrayList list = new ArrayList<>();
            JSONObject ticket = new JSONObject();
            ticket.put("token", Token.getToken(user.getuId()));
            list.add(ticket);
            list.add(u);
            return Response.success(list, "登录成功");
        } else {
            return Response.error("密码错误");
        }
    }

    @RequestMapping("/updateUser")
    public Response update(@RequestBody User user) {
        user.setuId(Encoder.encoder(user.getuId()));
        if (user.getPassword() != null) {
            user.setPassword(Encoder.encoder(user.getPassword()));
        }
        if (user.getPhone() != null) {
            user.setPhone(Encoder.encoder(user.getPhone()));
        }
        int f = service.updateUser(user);
        if (f < 1) {
            return Response.error("更新失败");
        } else {
            return Response.success(null, "更新成功");
        }
    }

    @RequestMapping("/getUserInfo")
    public User getUserInfo(@RequestBody User user) {
        User u = service.getUser(Encoder.encoder(user.getuId()));
        return u;
    }

    @RequestMapping("/selectUserList")
    public Response selectList() {
        ArrayList<User> list = (ArrayList<User>) service.getUserList();
        return Response.success(list, "返回所有学生信息");
    }

}
