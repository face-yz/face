package com.ylf.manage.entity;

import lombok.Data;

/**
 * @author: leifeng.ye
 * @date: 2020-01-01
 * @desc:
 */
@Data
public class UsersClazzSituaton {
    private String uId;
    private String username;
    private String groupname;
    private String clazzname;
    private Integer total;
    private Integer normal;
    private Integer leave;
    private Integer late;
    private Integer abnormal;
    private String percentage;
}
