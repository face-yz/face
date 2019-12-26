package com.ylf.user.controller;

import com.ylf.user.entity.ImageDate;
import com.ylf.user.entity.Response;
import com.ylf.user.entity.Sign;
import com.ylf.user.entity.User;
import com.ylf.user.serviceAPI.ManageService;
import com.ylf.user.serviceAPI.UserService;
import com.ylf.user.util.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author: leifeng.ye
 * @date: 2019-12-19
 * @desc:
 */
@RequestMapping("/api/v2")
@RestController
public class ManageController {

    @Autowired
    private ManageService service;

    @Autowired
    private UserService userService;


    @RequestMapping("/selectUserSignAttendPlanList")
    public Response selectUserSignAttendPlanList(ImageDate imageDate){
        if(service.faceIsLegal(imageDate.getImg())){
            ArrayList<Sign> list=(ArrayList<Sign>) service.getUserAttendPlanList(imageDate);
            if(list!=null&&list.size()>0){
                User user=userService.getUser(list.get(0).getuId());
                for(Sign a:list){
                    a.setuId(Encoder.decoder(a.getuId()));
                }
                return Response.success(list, user.getUsername());
            }
            else{
                return Response.success(list,"您今日暂无考勤计划");
            }
        }
        else{
            return Response.error("照片不合法");
        }

    }
}
