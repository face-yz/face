package com.ylf.manage.controller;

import com.ylf.manage.entity.Response;
import com.ylf.manage.entity.User;
import com.ylf.manage.remote.user.UserRpc;
import com.ylf.manage.util.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author: leifeng.ye
 * @date: 2019-12-11
 * @desc:
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserRpc userRpc;

    @RequestMapping("/userLogin")
    @CrossOrigin
    public Response userLogin(@RequestBody User user) {
        return userRpc.login(user);
    }

    @RequestMapping("/getUserInfo")
    @CrossOrigin
    public Response getUserInfo(HttpServletRequest httpServletRequest) {
        String token=httpServletRequest.getHeader("Authorization");
        String uId=Encoder.decoder(token.split("\\.")[0]);
        User u=new User();
        u.setuId(uId);
        User userInfo=userRpc.getUser(u);
        ArrayList list=new ArrayList();
        list.add(userInfo);
        return Response.success(list,"查询成功");
    }

    @RequestMapping("/userLoginByPhone")
    @CrossOrigin
    public Response userLoginByPhone(@RequestBody Map map) throws Exception{
        return userRpc.loginByPhone(map);
    }

    @RequestMapping("/userAuthByPhoneCode")
    @CrossOrigin
    public Response userAuthByPhoneCode(@RequestBody Map map)throws Exception{
        return userRpc.authByPhoneCode(map);
    }

    @RequestMapping("/updateUser")
    @CrossOrigin
    public Response updateUser(@RequestBody User user) {
        return userRpc.updateUser(user);
    }

    @RequestMapping("/addUser")
    @CrossOrigin
    public Response addUser(@RequestBody User user) {
        return userRpc.addUser(user);
    }

    @RequestMapping("/selectUserList")
    @CrossOrigin
    public Response selectList() {
        return userRpc.selectUserList();
    }

    @RequestMapping("/updatePhone")
    @CrossOrigin
    public Response updatePhone(@RequestBody User user)throws Exception{
        return userRpc.updatePhone(user);
    }

    @RequestMapping("/authUpdatePhone")
    @CrossOrigin
    public Response authUpdatePhone(@RequestBody Map map)throws Exception{
        return userRpc.authUpdatePhone(map);
    }

}
