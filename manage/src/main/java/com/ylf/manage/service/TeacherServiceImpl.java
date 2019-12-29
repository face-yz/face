package com.ylf.manage.service;

import com.ylf.manage.daoAPI.TeacherMapper;
import com.ylf.manage.entity.Teacher;
import com.ylf.manage.serviceAPI.TeacherService;
import com.ylf.manage.util.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: leifeng.ye
 * @date: 2019-12-26
 * @desc:
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper dao;


    @Override
    public int insert(Teacher teacher) {
        teacher.settId(Encoder.encoder(teacher.gettId()));
        teacher.setPassword(Encoder.encoder(teacher.getPassword()));
        if (teacher.getPhone() != null) {
            teacher.setPhone(Encoder.encoder(teacher.getPhone()));
        }
        return dao.insert(teacher);
    }

    @Override
    public Teacher getTeacher(String tId) {
        Teacher teacher = dao.selectByPrimaryKey(Encoder.encoder(tId));
        if (teacher != null) {
            teacher.settId(Encoder.decoder(teacher.gettId()));
            if (teacher.getPhone() != null) {
                teacher.setPhone(Encoder.decoder(teacher.getPhone()));
            }
        }
        return teacher;
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        teacher.settId(Encoder.encoder(teacher.gettId()));
        if (teacher.getPassword() != null) {
            teacher.setPassword(Encoder.encoder(teacher.getPassword()));
        }
        if (teacher.getPhone() != null) {
            teacher.setPhone(Encoder.encoder(teacher.getPhone()));
        }
        return dao.updateTeacher(teacher);
    }
}
