package com.ylf.manage.util;
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

    public static Long expire = new Date().getTime() + 1000 * 60 * 60l;        //过期时间

    public static String getToken(String uuid) {
        String id = Encoder.encoder(uuid);
        String expireTime = Encoder.encoder(expire.toString());
        String signature = Encoder.encoder((key));
        return id + "." + expireTime + "." + signature;
    }

    public static boolean isLegalToken(String token){
        if(token==null||token.equals("")){
            return false;
        }
        String[] req=token.split("\\.");
        String k=Encoder.decoder(req[2]);
        if(!k.equals(key)){
            return false;
        }
        String t=Encoder.decoder(req[1]);
        long time=Long.valueOf(t);
        if(new Date().getTime()>time){
            return false;
        }
        return true;
    }


}