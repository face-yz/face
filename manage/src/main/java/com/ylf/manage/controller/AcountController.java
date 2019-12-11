package com.ylf.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.ylf.manage.entity.Acount;
import com.ylf.manage.entity.Response;
import com.ylf.manage.serviceAPI.AcountService;
import com.ylf.manage.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

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
    public Response login(@RequestBody Acount acount){
        if(acount.getUsername()==null){
            return Response.error("登录失败");
        }
        boolean f=service.isLegal(acount);
        if(f){
            String uuid= Token.getToken(acount.getUsername());
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
