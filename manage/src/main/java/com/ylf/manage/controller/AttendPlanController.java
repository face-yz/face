package com.ylf.manage.controller;

import com.ylf.manage.entity.AttendPlan;
import com.ylf.manage.entity.FaceImage;
import com.ylf.manage.entity.Response;
import com.ylf.manage.entity.User;
import com.ylf.manage.remote.baidu.FaceRpc;
import com.ylf.manage.remote.user.UserRpc;
import com.ylf.manage.serviceAPI.AttendPlanService;
import com.ylf.manage.util.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;


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
    public Response addPlanUser(FaceImage faceImage, @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,  @DateTimeFormat(pattern = "yyyy-MM-dd")Date end,String clazzname){
        boolean f=service.faceIsLegal(faceImage.getImg());
        if(f){
            User u=new User();
            u.setuId(faceImage.getuId());
            User res=userRpc.getUser(u);
            faceRpc.addFace(faceRpc.getClient(),res.getUsername(),faceImage.getImg(),faceImage.getGroupName(),faceImage.getuId());
            String[] split=faceImage.getGroupName().split("_");
            new Thread(()->{
                service.addDefaultSign(Encoder.encoder(faceImage.getuId()),start,end,clazzname,split[0]);
            }).start();
            return Response.success(null,"添加成功");
        }
        else {
            return Response.error("照片不符合规定");
        }

    }

    @RequestMapping("/selectAttendPlanList")
    @CrossOrigin
    public Response selectList(){
        ArrayList<AttendPlan> list=(ArrayList<AttendPlan>) service.selectList();
        return Response.success(list,"返回所有考勤计划记录");
    }
}
