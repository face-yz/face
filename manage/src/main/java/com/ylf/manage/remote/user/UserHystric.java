package com.ylf.manage.remote.user;

import com.ylf.manage.entity.ImageDate;
import com.ylf.manage.entity.Leave;
import com.ylf.manage.entity.Response;
import com.ylf.manage.entity.User;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: leifeng.ye
 * @date: 2019-12-11
 * @desc:
 */
@Component
public class UserHystric implements UserRpc {

    @Override
    public Response login(User user) {
        return Response.error("Rpc服务器宕机");
    }

    @Override
    public Response updateUser(User user) {
        return Response.error("Rpc服务器宕机");
    }

    @Override
    public User getUser(User user) {
        return null;
    }

    @Override
    public Response addUser(User user) {
        return Response.error("Rpc服务器宕机");
    }

    @Override
    public Response selectUserList() {
        return Response.error("Rpc服务器宕机");
    }

    @Override
    public Response askLeave(Leave leave) {
        return Response.error("Rpc服务器宕机");
    }

    @Override
    public Response selectUserLeave(Map map) {
        return Response.error("Rpc服务器宕机");
    }

    @Override
    public Response selectLeaveList() {
        return Response.error("Rpc服务器宕机");
    }

    @Override
    public Response updateNoArgee(Leave leave) {
        return Response.error("Rpc服务器宕机");
    }

    @Override
    public Response updateArgee(Leave leave) {
        return Response.error("Rpc服务器宕机");
    }

    @Override
    public Response selectUserSignAttendPlanList(ImageDate imageDate) {
        return Response.error("Rpc服务器宕机");
    }


}
