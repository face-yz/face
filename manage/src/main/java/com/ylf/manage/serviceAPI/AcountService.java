package com.ylf.manage.serviceAPI;

import com.ylf.manage.entity.Acount;

import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2019-12-04
 * @desc:
 */
public interface AcountService {
    List<Acount> selectList();                //返回账户list

    boolean isLegal(Acount acount);       //是否为合法用户
}
