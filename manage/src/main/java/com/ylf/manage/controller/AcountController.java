package com.ylf.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.ylf.manage.entity.Acount;
import com.ylf.manage.entity.Response;
import com.ylf.manage.serviceAPI.AcountService;
import com.ylf.manage.util.Encryption;
import com.ylf.manage.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
    public RedisTemplate redisTemplate;

    @RequestMapping("/login")
    @CrossOrigin
    public Response login(@RequestBody Acount acount, HttpServletResponse response){
        boolean f=service.isLegal(acount);
        if(f){
            String uuid= UUID.getUUID();
            redisTemplate.opsForValue().set(Encryption.toEncryption(acount.getUsername()),uuid);
            redisTemplate.expire(Encryption.toEncryption(acount.getUsername()),60*60, TimeUnit.SECONDS);
            JSONObject ticket=new JSONObject();
            ticket.put("token",uuid);
            ArrayList<JSONObject> list=new ArrayList<>();
            list.add(ticket);
            return Response.success(list,"登录成功");
        }
        else{
            return Response.error("登录失败");
        }

    }
}
