package com.ylf.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.ylf.user.config.FaceRedisTemplate;
import com.ylf.user.entity.Response;
import com.ylf.user.entity.User;
import com.ylf.user.serviceAPI.UserService;
import com.ylf.user.util.Encoder;
import com.ylf.user.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private FaceRedisTemplate factory;

    @Autowired
    private MessageController messageController;

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
            user.setFlag("1");
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

    @RequestMapping("/loginByPhone")
    public Response loginByPhone(@RequestBody Map map)throws Exception{
        String phone=(String) map.get("phone");
        if (phone == null) {
            return Response.error("必要参数为空");
        }
        boolean f = service.isLegalByPhone(phone);
        if(f){
            HashMap m=new HashMap();
            m.put("phone",phone);
            messageController.sendCode(m);
            return Response.success(null,"发送成功");
        }
        else {
            return Response.error("没有此手机号码绑定的用户");
        }
    }

    @RequestMapping("/authByPhoneCode")
    public Response authByPhoneCode(@RequestBody Map map)throws Exception {
        String phone = (String) map.get("phone");
        String userCode = (String) map.get("code");
        RedisTemplate redisTemplate = factory.getRedisTemplate();
        String realCode = (String) redisTemplate.opsForValue().get(phone);
        if (realCode == null) {
            return Response.error("验证码已失效");
        } else {
            if (realCode.equals(userCode)) {
                RedisTemplate redisTemplate1=factory.getRedisTemplate();
                redisTemplate1.delete(phone);
                User user=service.getUserByPhone(phone);
                String token = Token.getToken(user.getuId());
                JSONObject ticket = new JSONObject();
                ticket.put("token", token);
                ArrayList list = new ArrayList<>();
                list.add(ticket);
                list.add(service.getUserByPhone(phone));
                return Response.success(list, "登录成功");
            } else {
                return Response.error("验证码不正确");
            }
        }
    }

    @RequestMapping("/updatePhone")
    public Response updatePhone(@RequestBody User user)throws Exception{
        User t=service.getUserByPhone(user.getPhone());
        if(t!=null){
            return Response.error("此手机号码已被其他账号绑定");
        }
        else{
            HashMap map=new HashMap();
            map.put("phone",user.getPhone());
            return messageController.sendCode(map);
        }
    }

    @RequestMapping("/authUpdatePhone")
    public Response authUpdatePhone(@RequestBody Map map)throws Exception{
        String phone = (String) map.get("phone");
        String userCode = (String) map.get("code");
        String uId=(String)map.get("uId");
        RedisTemplate redisTemplate = factory.getRedisTemplate();
        String realCode = (String) redisTemplate.opsForValue().get(phone);
        if (realCode == null) {
            return Response.error("验证码已失效");
        }
        else{
            if(realCode.equals(userCode)){
                RedisTemplate redisTemplate1=factory.getRedisTemplate();
                redisTemplate1.delete(phone);
                User user=new User();
                user.setuId(uId);
                user.setPhone(phone);
                update(user);
                return Response.success(null,"手机号码绑定成功");
            }
            else{
                return Response.error("验证码不正确");
            }
        }
    }

}
