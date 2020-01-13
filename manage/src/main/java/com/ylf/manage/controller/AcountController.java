package com.ylf.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.ylf.manage.config.FaceRedisTemplate;
import com.ylf.manage.entity.Acount;
import com.ylf.manage.entity.Response;
import com.ylf.manage.serviceAPI.AcountService;
import com.ylf.manage.serviceAPI.ManageService;
import com.ylf.manage.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: leifeng.ye
 * @date: 2019-12-04
 * @desc:
 */
@RestController
@RequestMapping("/api/v1")
public class AcountController {

    @Autowired
    public AcountService service;

    @Autowired
    public MessageController messageController;

    @Autowired
    public FaceRedisTemplate factory;


    @Autowired
    public RedisTemplate redisTemplate;

    @RequestMapping("/login")
    @CrossOrigin
    public Response login(@RequestBody Acount acount) {
        if (acount.getUsername() == null) {
            return Response.error("必要参数为空");
        }
        boolean f = service.isLegal(acount);
        if (f) {
            String uuid = Token.getToken(acount.getUsername());
            JSONObject ticket = new JSONObject();
            ticket.put("token", uuid);
            ArrayList<JSONObject> list = new ArrayList<>();
            list.add(ticket);
            return Response.success(list, "登录成功");
        } else {
            return Response.error("密码错误");
        }

    }

    @RequestMapping("/loginByPhone")
    @CrossOrigin
    public Response loginByPhone(@RequestBody Map m)throws Exception{
        String phone=(String) m.get("phone");
        if (phone == null) {
            return Response.error("必要参数为空");
        }
        boolean f = service.isLegalByPhone(phone);
        if(f){
            HashMap map=new HashMap();
            map.put("phone",phone);
            messageController.sendCode(map);
            return Response.success(null,"发送成功");
        }
        else {
            return Response.error("没有此手机号码绑定的用户");
        }

    }

    @RequestMapping("/authByPhoneCode")
    @CrossOrigin
    public Response authByPhoneCode(@RequestBody Map map)throws Exception{
        String phone=(String) map.get("phone");
        String userCode=(String) map.get("code");
        RedisTemplate redisTemplate=factory.getRedisTemplate();
        String realCode=(String) redisTemplate.opsForValue().get(phone);
        if(realCode==null){
            return Response.error("验证码已失效");
        }
        else{
            if(realCode.equals(userCode)){
                String token = Token.getToken(phone);
                JSONObject ticket = new JSONObject();
                ticket.put("token", token);
                ArrayList<JSONObject> list = new ArrayList<>();
                list.add(ticket);
                redisTemplate.delete(phone);
                return Response.success(list, "登录成功");
            }
            else{
                return Response.error("验证码不正确");
            }
        }
    }
}
