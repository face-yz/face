package com.ylf.user.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author: leifeng.ye
 * @date: 2019-12-19
 * @desc:
 */
public class ImageDate {
    private MultipartFile img;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date now;


    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }
}
