package com.ylf.manage.daoAPI;

import com.ylf.manage.entity.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherMapper {
    int deleteByPrimaryKey(String tId);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String tId);

    int updateTeacher(Teacher record);

    int updateByPrimaryKey(Teacher record);
}