package com.ylf.manage.service;

import com.ylf.manage.daoAPI.AttendPlanMapper;
import com.ylf.manage.entity.AttendPlan;
import com.ylf.manage.entity.Response;
import com.ylf.manage.remote.baidu.FaceRpc;
import com.ylf.manage.serviceAPI.AttendPlanService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2019-12-12
 * @desc:
 */
@Service
public class AttendServiceImpl implements AttendPlanService {

    @Autowired
    private AttendPlanMapper dao;

    @Autowired
    private FaceRpc faceRpc;

    @Override
    public int addPlan(AttendPlan plan) {
        return dao.insert(plan);
    }

    @Override
    public List<AttendPlan> getAttendPlanList(String groupName) {
        return dao.selectByPrimaryKey(groupName);
    }

    @Override
    public boolean hasConflict(AttendPlan plan){
        Long standard=2*60*60*1000+30*60*1000l;   //上课时间2小时+休息时间30分钟
        ArrayList<AttendPlan> list=(ArrayList<AttendPlan>) getAttendPlanList(plan.getGroupname());
        for(AttendPlan p:list) {
            if (plan.getStarttime().getTime()>=p.getStarttime().getTime()&&plan.getStarttime().getTime()<=p.getEndtime().getTime()) {
                if (Math.abs(p.getMarktime().getTime() - plan.getMarktime().getTime()) < standard) {
                        return true;
                }
            }
            if(plan.getEndtime().getTime()>=p.getStarttime().getTime()&&plan.getEndtime().getTime()<=p.getEndtime().getTime()){
                if (Math.abs(p.getMarktime().getTime() - plan.getMarktime().getTime()) < standard) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean faceIsLegal(MultipartFile img) {
        JSONObject msg=faceRpc.detect(faceRpc.getClient(),img);
        if(msg.getInt("error_code")==222202||msg.getJSONObject("result").getInt("face_num")>1){
            return false;
        }
        else {
            return true;
        }
    }
}
