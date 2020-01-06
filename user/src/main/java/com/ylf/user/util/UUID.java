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
        File file = new File("/dev/urandom");
        String res = "";
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            long random = raf.readLong();
            long abs = Math.abs(random);
            String id = java.util.UUID.randomUUID().toString();
            id = id.replaceAll("-", "");
            res = id + abs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static int getCode() {
        return (int) ((Math.random() * 9 + 1) * 100000);
    }

}
