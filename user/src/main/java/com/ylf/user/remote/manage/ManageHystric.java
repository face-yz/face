package com.ylf.user.remote.manage;

import com.ylf.user.entity.AttendPlan;
import com.ylf.user.entity.Response;
import com.ylf.user.entity.Sign;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @author: leifeng.ye
 * @date: 2019-12-19
 * @desc:
 */
@Component
public class ManageHystric implements ManageRpc {


    @Override
    public Response<AttendPlan> selectAttendPlanLimitList(Map map) {
        return Response.error("rpc服务器宕机");
    }

    @Override
    public Response<Integer> selectAttendPlanCount() {
        return Response.error("rpc服务器宕机");
    }

    @Override
    public Response<Sign> selectUserSign(Sign sign) {
        return Response.error("rpc服务器宕机");
    }
}
