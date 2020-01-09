package com.ylf.user.util;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: leifeng.ye
 * @date: 2019-12-06
 * @desc:
 */
@Component
public class Token {

    public static String key = "5ff32454ebff4ad2b461089af3192aed557541952771215843";  //秘钥

    public static String getToken(String uid) {
        Long expire = new Date().getTime() + 1000 * 60 * 60l;        //过期时间
        String id = Encoder.encoder(uid);
        String expireTime = Encoder.encoder(expire.toString());
        String signature = Encoder.encoder((key));
        return id + "." + expireTime + "." + signature;
    }


}