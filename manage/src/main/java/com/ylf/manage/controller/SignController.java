package com.ylf.manage.controller;

import com.ylf.manage.entity.*;
import com.ylf.manage.remote.user.UserRpc;
import com.ylf.manage.serviceAPI.ManageService;
import com.ylf.manage.serviceAPI.SignService;
import com.ylf.manage.util.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Map;

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

    @Autowired
    private ManageService ManageService;

    @Autowired
    private UserRpc userRpc;

    @RequestMapping("/selectUserSign")
    @CrossOrigin
    public Response selectUserSign(@RequestBody ReqSign sign) {
        ArrayList list = (ArrayList<ReqSign>) service.selectUserSign(sign);
        return Response.success(list, "查询成功");
    }

    @RequestMapping("/selectUserAttendPlan")
    @CrossOrigin
    public Response selectUserAttendPlan(@RequestBody Map map) {
        String uId = (String) map.get("uId");
        ArrayList list = (ArrayList<ReqSign>) service.selectUserAttendPlan(uId);
        return Response.success(list, "查询成功");
    }

    @RequestMapping("/selectUserSignList")
    @CrossOrigin
    public Response selectUserSignList(@RequestBody ReqSign sign) {
        ResSignPage res = service.selectUserSignList(sign);
        ArrayList list = new ArrayList();
        list.add(res);
        return Response.success(list, "查询成功");
    }


    @RequestMapping("/updateUserSign")
    @CrossOrigin
    public Response updateUserSign(@RequestBody ReqSign sign) {
        int n = service.updateUserSign(sign);
        if (n > 0) {
            return Response.success(null, "打卡成功");
        } else {
            return Response.error("打卡失败");
        }
    }

    @RequestMapping("/selectAttendPlanSignList")
    @CrossOrigin
    public Response selectAttendPlanSignList(@RequestBody ReqSign sign){
        ArrayList<ResSign> list=(ArrayList<ResSign>) service.selectAttendPlanSignList(sign);
        return Response.success(list,"查询成功");
    }

    @RequestMapping("/currentClazzPercentage")
    @CrossOrigin
    public Response currentClazzPercentage(@RequestBody ReqSign sign){
        ClazzPercentage c=new ClazzPercentage();
        c.setTotal(service.selectAttendPlanOneSignDateCount(sign));
        c.setReal(service.selectAttendPlanOneSignDateOkCount(sign));
        ArrayList list=new ArrayList();
        list.add(c);
        return Response.success(list,"查询成功");
    }

    @RequestMapping("/selectUserSignAttendPlanList")
    @CrossOrigin
    public Response selectUserSignAttendPlanList(ImageDate imageDate) {
        if (ManageService.faceIsLegal(imageDate.getImg())) {
            ArrayList<ResSign> list = (ArrayList<ResSign>) ManageService.getUserAttendPlanList(imageDate);
            if (list != null && list.size() > 0) {
                User t=new User();
                t.setuId(Encoder.decoder(list.get(0).getuId()));
                User user = userRpc.getUser(t);
                for (ResSign a : list) {
                    a.setuId(Encoder.decoder(a.getuId()));
                    a.setUsername(user.getUsername());
                }
                return Response.success(list, "查询成功");
            } else {
                return Response.success(list, "您今日暂无考勤计划");
            }
        } else {
            return Response.error("照片不合法");
        }

    }
}
