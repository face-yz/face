package com.ylf.manage.controller;

import com.ylf.manage.entity.*;
import com.ylf.manage.remote.user.UserRpc;
import com.ylf.manage.serviceAPI.SignService;
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
}
