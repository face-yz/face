package com.ylf.manage.entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: leifeng.ye
 * @date: 2019-12-12
 * @desc:
 */
public class FaceImage {
    private String uId;
    private String userName;
    private String groupName;
    private MultipartFile img;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
