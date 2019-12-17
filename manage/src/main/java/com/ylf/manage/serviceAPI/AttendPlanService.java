package com.ylf.manage.serviceAPI;

import com.ylf.manage.entity.AttendPlan;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2019-12-12
 * @desc:
 */
public interface AttendPlanService {
    int addPlan(AttendPlan plan);   //添加打卡计划
    List<AttendPlan> getAttendPlanList(String groupName);  //查询一个group下的所有计划
    boolean hasConflict(AttendPlan plan);            //判断计划是否有冲突
    boolean faceIsLegal(MultipartFile img);         //人脸是否合法
    void addDefaultSign(String u_id,Date start, Date end,String clazzName,String groupName);     //添加学生默认打卡记录
    List selectList();    //返回所有考勤计划记录
}
