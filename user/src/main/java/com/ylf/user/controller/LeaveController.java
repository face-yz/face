package com.ylf.user.controller;

import com.ylf.user.entity.Leave;
import com.ylf.user.entity.Response;
import com.ylf.user.serviceAPI.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author: leifeng.ye
 * @date: 2019-12-23
 * @desc:
 */
@RestController
@RequestMapping("api/v2")
public class LeaveController {

    @Autowired
    private LeaveService service;

    @RequestMapping("/askLeave")
    @CrossOrigin
    public Response askLeave(@RequestBody Leave leave){
        int n=service.askLeave(leave);
        if(n>0){
            return Response.success(null,"请假成功,等待老师审批");
        }
        else{
            return Response.error("请假失败");
        }
    }

    @RequestMapping("/selectUserLeave")
    @CrossOrigin
    public Response selectUserLeave(@RequestBody Map map){
        String uId=(String)map.get("uId");
        ArrayList<Leave> list=(ArrayList<Leave>) service.selectUserLeave(uId);
        return Response.success(list,"查询成功");
    }

    @RequestMapping("/selectLeaveList")
    @CrossOrigin
    public Response selectLeaveList(){
        ArrayList<Leave> list=(ArrayList<Leave>) service.selectList();
        return Response.success(list,"查询成功");
    }

    @RequestMapping("/updateNoArgee")
    @CrossOrigin
    public Response updateNoArgee(@RequestBody Leave leave){
        int n=service.updateNoArgee(leave);
        if(n>0){
            return Response.success(null,"审批成功");
        }
        else{
            return Response.error("审批失败");
        }
    }

    @RequestMapping("/updateArgee")
    @CrossOrigin
    public Response updateArgee(@RequestBody Leave leave){
        int n=service.updateArgee(leave);
        if(n>0){
            return Response.success(null,"审批成功");
        }
        else{
            return Response.error("审批失败");
        }
    }
}
