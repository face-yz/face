package com.ylf.manage.controller;

import com.ylf.manage.config.FaceRedisTemplate;
import com.ylf.manage.entity.Response;
import com.ylf.manage.util.MessageService;
import com.ylf.manage.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author: leifeng.ye
 * @date: 2020-01-02
 * @desc:
 */
@RestController
@RequestMapping("/api/v1")
public class MessageController {

    @Autowired
    private FaceRedisTemplate factory;

    @RequestMapping("/sendCode")
    @CrossOrigin
    public Response sendCode(@RequestBody Map map)throws Exception{
        long expire=1000*60*10l;
        RedisTemplate redisTemplate=factory.getRedisTemplate();
        String phone=(String) map.get("phone");
        String code= String.valueOf(UUID.getCode());
        redisTemplate.opsForValue().set(phone,code);
        redisTemplate.expire(phone,expire, TimeUnit.MILLISECONDS);
        MessageService.sendCode(code,phone);
        return Response.success(null,"验证码发送成功");
    }

    @RequestMapping("/getCode")
    @CrossOrigin
    public Response getCode(@RequestBody Map map){
        RedisTemplate redisTemplate=factory.getRedisTemplate();
        String phone=(String) map.get("phone");
        String code=(String) redisTemplate.opsForValue().get(phone)==null?"":(String) redisTemplate.opsForValue().get(phone);
        ArrayList list=new ArrayList();
        list.add(code);
        return Response.success(list,"查询成功");
    }
}
