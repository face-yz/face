package com.ylf.manage.remote.user;

import com.ylf.manage.entity.Response;
import com.ylf.manage.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author: leifeng.ye
 * @date: 2019-12-11
 * @desc:
 */
@Component
public class UserHystric implements UserRpc{

    @Override
    public Response login(User user) {
        return Response.error("Rpc服务器宕机");
    }

    @Override
    public Response updateUser(User user) {
        return Response.error("Rpc服务器宕机");
    }
}
