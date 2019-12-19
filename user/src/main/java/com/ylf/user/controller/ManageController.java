package com.ylf.user.controller;

import com.ylf.user.entity.ImageDate;
import com.ylf.user.entity.Response;
import com.ylf.user.entity.Sign;
import com.ylf.user.entity.User;
import com.ylf.user.remote.manage.ManageRpc;
import com.ylf.user.serviceAPI.ManageService;
import com.ylf.user.serviceAPI.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author: leifeng.ye
 * @date: 2019-12-19
 * @desc:
 */
@RequestMapping("api/v2")
@RestController
public class ManageController {

    @Autowired
    private ManageService service;

    @Autowired
    private UserService userService;


    @RequestMapping("/selectUserAttendPlanList")
    public Response test(ImageDate imageDate){
        ArrayList<Sign> list=(ArrayList<Sign>) service.getUserAttendPlanList(imageDate);
        if(list!=null&&list.size()>0){
            User user=userService.getUser(list.get(0).getuId());
            return Response.success(list,user.getUsername());
        }
        else{
            return Response.success(list,"您今日暂无考勤计划");
        }

    }
}
