package com.ylf.manage.daoAPI;

import com.ylf.manage.entity.Acount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcountMapper {
    int deleteByPrimaryKey(String username);

    int insert(Acount record);

    int insertSelective(Acount record);

    Acount selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(Acount record);

    int updateByPrimaryKey(Acount record);

    List<Acount> selectList();
}