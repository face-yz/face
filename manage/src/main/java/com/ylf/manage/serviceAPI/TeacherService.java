package com.ylf.manage.serviceAPI;

import com.ylf.manage.entity.Teacher;

/**
 * @author: leifeng.ye
 * @date: 2019-12-26
 * @desc:
 */
public interface TeacherService {
    int insert(Teacher teacher);  //添加老师信息

    Teacher getTeacher(String tId); //获取老师信息

    int updateTeacher(Teacher teacher); //更新老师信息
}
