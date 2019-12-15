package com.ylf.manage.controller;

import com.ylf.manage.entity.AttendPlan;
import com.ylf.manage.entity.FaceImage;
import com.ylf.manage.entity.Response;
import com.ylf.manage.entity.User;
import com.ylf.manage.remote.baidu.FaceRpc;
import com.ylf.manage.remote.user.UserRpc;
import com.ylf.manage.serviceAPI.AttendPlanService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

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

    @Autowired
    private UserRpc userRpc;

    @RequestMapping("/addPlan")
    @CrossOrigin
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


    @RequestMapping("/addPlan/addPlanUser")
    @CrossOrigin
    public Response addPlanUser(FaceImage faceImage){
        boolean f=service.faceIsLegal(faceImage.getImg());
        if(f){
            User u=new User();
            u.setuId(faceImage.getuId());
            User res=userRpc.getUser(u);
            faceRpc.addFace(faceRpc.getClient(),res.getUsername(),faceImage.getImg(),faceImage.getGroupName(),faceImage.getuId());
            return Response.success(null,"添加成功");
        }
        else {
            return Response.error("照片不符合规定");
        }

    }
}
