package com.ylf.manage.controller;

import com.ylf.manage.entity.*;
import com.ylf.manage.serviceAPI.AttendPlanSituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author: leifeng.ye
 * @date: 2020-01-01
 * @desc:
 */
@RestController
@RequestMapping("/api/v1")
public class AttendPlanSituationController {

    @Autowired
    private AttendPlanSituationService service;

    @RequestMapping("/UsersAttendPlanSituation")
    @CrossOrigin
    public Response UsersAttendPlanSituation(@RequestBody ReqSign sign){
        ArrayList<UsersClazzSituaton> list=(ArrayList<UsersClazzSituaton>) service.usersAttendPlanSituation(sign);
        return Response.success(list,"查询成功");
    }

    @RequestMapping("/selectClazzCurrentPercentage")
    @CrossOrigin
    public Response selectClazzCurrentPercentage(@RequestBody ReqSign sign){
        ArrayList<ClazzPercentage> list=(ArrayList<ClazzPercentage>) service.selectClazzCurrentPercentage(sign);
        return Response.success(list,"查询成功");
    }
}
