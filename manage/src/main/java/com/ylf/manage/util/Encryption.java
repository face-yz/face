package com.ylf.manage.util;

import org.springframework.util.DigestUtils;

/**
 * @author: leifeng.ye
 * @date: 2019-12-04
 * @desc: 加密
 */
public class Encryption {
    public static String toEncryption(String str) {
        String res = DigestUtils.md5DigestAsHex(str.getBytes());
        return res;
    }
}