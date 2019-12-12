package com.ylf.manage.controller;

import com.ylf.manage.entity.AttendPlan;
import com.ylf.manage.entity.Response;
import com.ylf.manage.remote.baidu.FaceRpc;
import com.ylf.manage.serviceAPI.AttendPlanService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: leifeng.ye
 * @date: 2019-12-12
 * @desc:
 */
@RestController
@RequestMapping("/api/v1")
public class AttendPlanController {

    @Autowired
    private AttendPlanService service;

    @Autowired
    private FaceRpc faceRpc;

    @RequestMapping("/addPlan")
    public Response addPlan(@RequestBody AttendPlan plan){

        if(service.hasConflict(plan)==true){
            return Response.error("此计划和已有计划有冲突");
        }
        else{
            String faceGroupName=plan.getGroupname()+"_"+plan.getStarttime().getTime()+"_"+plan.getMarktime().getTime();
            faceRpc.createGroup(faceRpc.getClient(),faceGroupName);
            service.addPlan(plan);
            return Response.success(null,"添加计划成功");
        }

    }
}
