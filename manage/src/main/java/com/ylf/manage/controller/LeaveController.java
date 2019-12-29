package com.ylf.manage.controller;

import com.ylf.manage.entity.Leave;
import com.ylf.manage.entity.Response;
import com.ylf.manage.remote.user.UserRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: leifeng.ye
 * @date: 2019-12-24
 * @desc:
 */
@RestController
@RequestMapping("/api/v1")
public class LeaveController {

    @Autowired
    private UserRpc userRpc;

    @RequestMapping("/askLeave")
    @CrossOrigin
    public Response askLeave(@RequestBody Leave leave) {
        return userRpc.askLeave(leave);
    }

    @RequestMapping("/selectUserLeave")
    @CrossOrigin
    public Response selectUserLeave(@RequestBody Map map) {
        return userRpc.selectUserLeave(map);
    }


    @RequestMapping("/selectLeaveList")
    @CrossOrigin
    public Response selectLeaveList() {
        return userRpc.selectLeaveList();
    }

    @RequestMapping("/updateNoArgee")
    @CrossOrigin
    public Response updateNoArgee(@RequestBody Leave leave) {
        return userRpc.updateNoArgee(leave);
    }

    @RequestMapping("/updateArgee")
    @CrossOrigin
    public Response updateArgee(@RequestBody Leave leave) {
        return userRpc.updateArgee(leave);
    }

}
