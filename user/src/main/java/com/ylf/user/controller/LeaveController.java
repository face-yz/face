package com.ylf.user.controller;

import com.ylf.user.constantEnum.LeaveEnum;
import com.ylf.user.entity.Leave;
import com.ylf.user.entity.Response;
import com.ylf.user.serviceAPI.LeaveService;
import com.ylf.user.util.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
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
    public Response askLeave(@RequestBody Leave leave){
        boolean f=service.isLegalLeave(leave);
        if(f){
            return Response.error("请假重复");
        }
        else{
            int n=service.askLeave(leave);
            if(n>0){
                return Response.success(null,"请假成功,等待老师审批");
            }
            else{
                return Response.error("请假失败");
            }
        }
    }

    @RequestMapping("/selectUserLeave")
    public Response selectUserLeave(@RequestBody Map map){
        String uId=(String)map.get("uId");
        ArrayList list=(ArrayList<Leave>) service.selectUserLeave(Encoder.encoder(uId));
        return Response.success(list,"查询成功");
    }

    @RequestMapping("/selectLeaveList")
    public Response selectLeaveList(){
        ArrayList list=(ArrayList<Leave>) service.selectList();
        return Response.success(list,"查询成功");
    }

    @RequestMapping("/updateNoArgee")
    public Response updateNoArgee(@RequestBody Leave leave){
        service.updateNoArgee(leave);
        return Response.success(null,"审批成功");
    }

    @RequestMapping("/updateArgee")
    public Response updateArgee(@RequestBody Leave leave){
        service.updateArgee(leave);
        return Response.success(null,"审批成功");
    }
}
