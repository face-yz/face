package com.ylf.user.entity;


import lombok.Data;

/**
 * @author: leifeng.ye
 * @date: 2019-12-10
 * @desc:
 */
@Data
public class User {
    private String uId;
    private String userName;
    private String password;
    private String phone;
}
