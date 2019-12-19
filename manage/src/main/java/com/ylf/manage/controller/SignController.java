package com.ylf.manage.controller;

import com.ylf.manage.entity.AttendPlan;
import com.ylf.manage.entity.Response;
import com.ylf.manage.entity.Sign;
import com.ylf.manage.serviceAPI.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author: leifeng.ye
 * @date: 2019-12-19
 * @desc:
 */
@RequestMapping("/api/v1")
@RestController
public class SignController {

    @Autowired
    private SignService service;

    @RequestMapping("/selectUserAttendPlan")
    public Response selectUserAttendPlan(@RequestBody Sign sign){
        ArrayList<AttendPlan> list=(ArrayList<AttendPlan>) service.selectUserAttendPlan(sign);
        return Response.success(list,"查询成功");
    }
}
