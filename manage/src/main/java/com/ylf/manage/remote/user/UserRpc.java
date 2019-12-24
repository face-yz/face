package com.ylf.manage.remote.user;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ylf.manage.entity.Leave;
import com.ylf.manage.entity.Response;
import com.ylf.manage.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author: leifeng.ye
 * @date: 2019-12-11
 * @desc:
 */
@FeignClient(name = "face-user",fallback = UserHystric.class)
@Service
public interface UserRpc {
    @RequestMapping("/api/v2/login")
    @HystrixCommand(fallbackMethod = "login")
    Response login(@RequestBody User user);

    @RequestMapping("/api/v2/updateUser")
    @HystrixCommand(fallbackMethod = "updateUser")
    Response updateUser(@RequestBody User user);

    @RequestMapping("/api/v2/getUserInfo")
    @HystrixCommand(fallbackMethod = "login")
    User getUser(@RequestBody User user);


    @RequestMapping("/api/v2/addUser")
    @HystrixCommand(fallbackMethod = "addUser")
    Response addUser(@RequestBody User user);

    @RequestMapping("/api/v2/selectUserList")
    @HystrixCommand(fallbackMethod = "selectUserList")
    Response selectUserList();

    @RequestMapping("/api/v2/askLeave")
    @HystrixCommand(fallbackMethod = "askLeave")
    Response askLeave(@RequestBody Leave leave);

    @RequestMapping("api/v2/selectUserLeave")
    @HystrixCommand(fallbackMethod = "selectUserLeave")
    Response selectUserLeave(@RequestBody Map map);

    @RequestMapping("/api/v2/selectLeaveList")
    @HystrixCommand(fallbackMethod = "selectLeaveList")
    Response selectLeaveList();

    @RequestMapping("api/v2/updateNoArgee")
    @HystrixCommand(fallbackMethod = "updateNoArgee")
    Response updateNoArgee(@RequestBody Leave leave);

    @RequestMapping("/api/v2/updateArgee")
    @HystrixCommand(fallbackMethod = "updateArgee")
    Response updateArgee(@RequestBody Leave leave);

}
