package com.ylf.manage.controller;
import com.ylf.manage.entity.Response;
import com.ylf.manage.entity.Sign;
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

    @RequestMapping("/selectUserSign")
    @CrossOrigin
    public Response selectUserSign(@RequestBody Sign sign){
        ArrayList list=(ArrayList<Sign>) service.selectUserSign(sign);
        return Response.success(list,"查询成功");
    }

    @RequestMapping("/selectUserAttendPlan")
    @CrossOrigin
    public Response selectUserAttendPlan(@RequestBody Map map){
        String uId=(String) map.get("uId");
        ArrayList list=(ArrayList<Sign>)service.selectUserAttendPlan(uId);
        return Response.success(list,"查询成功");
    }

    @RequestMapping("/selectUserSignList")
    @CrossOrigin
    public Response selectUserSignList(@RequestBody Sign sign){
        ArrayList list=(ArrayList<Sign>) service.selectUserSignList(sign);
        return Response.success(list,"查询成功");
    }
}
