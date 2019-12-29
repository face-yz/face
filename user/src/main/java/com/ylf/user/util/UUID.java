package com.ylf.user.util;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @author: leifeng.ye
 * @date: 2019-12-04
 * @desc: token
 */
public class UUID {
    public static String getUUID() {
        String id = java.util.UUID.randomUUID().toString();
        id = id.replaceAll("-", "");
        return id;
    }

}
