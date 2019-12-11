package com.ylf.manage.remote.user;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ylf.manage.entity.Response;
import com.ylf.manage.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
