package com.ylf.manage.controller;

import com.ylf.manage.entity.Response;
import com.ylf.manage.entity.Teacher;
import com.ylf.manage.serviceAPI.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: leifeng.ye
 * @date: 2019-12-26
 * @desc:
 */
@RestController
@RequestMapping("/api/v1")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @RequestMapping("/addTeacher")
    @CrossOrigin
    public Response addTeacher(@RequestBody Teacher teacher) {
        Teacher t = service.getTeacher(teacher.gettId());
        if (t != null) {
            return Response.error("此老师已添加");
        } else {
            int n = service.insert(teacher);
            if (n > 0) {
                return Response.success(null, "添加成功");
            } else {
                return Response.error("添加失败");
            }
        }
    }

    @RequestMapping("/updateTeacher")
    @CrossOrigin
    public Response updateTeacher(@RequestBody Teacher teacher) {
        int n = service.updateTeacher(teacher);
        if (n > 0) {
            return Response.success(null, "更新成功");
        } else {
            return Response.error("更新失败");
        }
    }
}
