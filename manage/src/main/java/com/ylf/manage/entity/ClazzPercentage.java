package com.ylf.manage.entity;

import lombok.Data;

/**
 * @author: leifeng.ye
 * @date: 2019-12-30
 * @desc:
 */
@Data
public class ClazzPercentage {
    private String groupname;
    private String clazzname;
    private Integer total;
    private Integer normal;
    private Integer late;
    private Integer leave;
    private Integer abnormal;
}
