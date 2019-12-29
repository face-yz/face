package com.ylf.user.remote.manage;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ylf.user.entity.AttendPlan;
import com.ylf.user.entity.Response;
import com.ylf.user.entity.Sign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author: leifeng.ye
 * @date: 2019-12-19
 * @desc:
 */
@FeignClient(name = "face-manage", fallback = ManageHystric.class)
@Service
public interface ManageRpc {
    @HystrixCommand(fallbackMethod = "selectAttendPlanLimitList")
    @RequestMapping("/api/v1/selectAttendPlanLimitList")
    Response<AttendPlan> selectAttendPlanLimitList(@RequestBody Map map);

    @HystrixCommand(fallbackMethod = "selectAttendPlanCount")
    @RequestMapping("/api/v1/selectAttendPlanCount")
    Response<Integer> selectAttendPlanCount();

    @HystrixCommand(fallbackMethod = "selectUserSign")
    @RequestMapping("/api/v1/selectUserSign")
    Response<Sign> selectUserSign(@RequestBody Sign sign);
}
