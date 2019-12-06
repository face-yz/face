package com.ylf.manage.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Base64Utils;

/**
 * @author: leifeng.ye
 * @date: 2019-12-06
 * @desc:
 */
public class Encoder {

    public static String encoder(String str){
        String s="";
        try{
            s=new String(Base64Utils.encode(str.getBytes()),"utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }

    public static String  decoder(String str){
        String s="";
        try{
            s=new String(Base64Utils.decode(str.getBytes()),"utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }


}
