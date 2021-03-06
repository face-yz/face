package com.ylf.manage.controller;

import com.ylf.manage.entity.*;
import com.ylf.manage.entity.BasePage.ReqPage;
import com.ylf.manage.remote.baidu.FaceRpc;
import com.ylf.manage.remote.user.UserRpc;
import com.ylf.manage.serviceAPI.AttendPlanService;
import com.ylf.manage.serviceAPI.SignService;
import com.ylf.manage.util.Encoder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;


/**
 * @author: leifeng.ye
 * @date: 2019-12-12
 * @desc:
 */
@RestController
@RequestMapping("/api/v1")
public class AttendPlanController {

    @Autowired
    private AttendPlanService service;

    @Autowired
    private SignService signService;

    @Autowired
    private FaceRpc faceRpc;

    @RequestMapping("/addPlan")
    @CrossOrigin
    public Response addPlan(@RequestBody AttendPlan plan) {
        if (service.hasConflict(plan) == true) {
            return Response.error("此计划和已有计划有冲突");
        } else {
            String days = "";
            for (int i = 0; i < plan.getWeekdays().length; i++) {
                if (i == plan.getWeekdays().length - 1) {
                    days += plan.getWeekdays()[i];
                } else {
                    days += plan.getWeekdays()[i] + "_";
                }
            }
            plan.setDays(days);
            String faceGroupName = plan.getGroupname() + "_" + plan.getStarttime().getTime() + "_" + plan.getMarktime().getTime() + "_" + days;
            faceRpc.createGroup(faceRpc.getClient(), faceGroupName);
            service.addPlan(plan);
            return Response.success(null, "添加计划成功");
        }
    }


    @RequestMapping("/addPlan/addPlanUser")
    @CrossOrigin
    public Response addPlanUser(FaceImage faceImage) {
        AttendPlan attendPlan=new AttendPlan();
        attendPlan.setGroupname(faceImage.getGroupName());
        attendPlan.setStarttime(faceImage.getStart());
        attendPlan.setEndtime(faceImage.getEnd());
        attendPlan.setMarktime(faceImage.getMarktime());
        attendPlan.setDays(faceImage.getDays());
        attendPlan.setClazzname(faceImage.getClazzname());
        String[] t=faceImage.getDays().split("_");
        Integer[] nums=new Integer[t.length];
        for(int i=0;i<t.length;i++){
            nums[i]=Integer.valueOf(t[i]);
        }
        attendPlan.setWeekdays(nums);
        boolean flag=service.isAddUserHasConflict(faceImage.getuId(),attendPlan);
        if(flag){
            return Response.error("此学生已在本时间段有其他课程");
        }
        boolean f = service.faceIsLegal(faceImage.getImg());
        if (f) {
            String bdGroupName = faceImage.getGroupName() + "_" + faceImage.getStart().getTime() + "_" + faceImage.getMarktime().getTime() + "_" + faceImage.getDays();
            JSONObject res = faceRpc.getUserInfo(faceRpc.getClient(), faceImage.getuId(), bdGroupName);
            ReqSign sign=new ReqSign();
            sign.setuId(faceImage.getuId());
            sign.setClazzname(faceImage.getClazzname());
            sign.setGroupname(faceImage.getGroupName());
            sign.setDays(faceImage.getDays());
            sign.setMarktime(faceImage.getMarktime());
            sign.setStarttime(faceImage.getStart());
            System.out.println(res.getInt("error_code"));
            if (res.getInt("error_code") != 0&&signService.selectExistUserSign(sign).size()==0) {
                faceRpc.addFace(faceRpc.getClient(), faceImage.getUserName(), faceImage.getImg(), bdGroupName, faceImage.getuId());
                String[] days = faceImage.getDays().split("_");
                Integer[] l = new Integer[days.length];
                for (int i = 0; i < days.length; i++) {
                    l[i] = Integer.valueOf(days[i]);
                }
                new Thread(() -> {
                    service.addDefaultSign(Encoder.encoder(faceImage.getuId()), faceImage.getStart(), faceImage.getEnd(), faceImage.getClazzname(), faceImage.getGroupName(), faceImage.getMarktime(), l, faceImage.getDays());
                }).start();
                return Response.success(null, "学生录入成功");
            } else {
                return Response.success(null, "此学生已录入当前考勤计划");
            }

        } else {
            return Response.error("照片不符合规定");
        }

    }

    @RequestMapping("/selectAttendPlanList")
    @CrossOrigin
    public Response selectList() {
        ArrayList<AttendPlan> list = (ArrayList<AttendPlan>) service.selectList();
        return Response.success(list, "返回所有考勤计划记录");
    }

    @RequestMapping("/selectAttendPlanCount")
    @CrossOrigin
    public Response selectCount() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(service.selectCount());
        return Response.success(list, "查询考勤计划数目成功");
    }

    @RequestMapping("/selectAttendPlanLimitList")
    @CrossOrigin
    public Response selectAttendPlanLimitList(@RequestBody Map map) {
        ReqPage page = new ReqPage();
        page.setPageNo((Integer) map.get("k"));
        ArrayList<AttendPlan> list = (ArrayList<AttendPlan>) service.selectLimitList(page);
        return Response.success(list, "分页查询成功");
    }

}
