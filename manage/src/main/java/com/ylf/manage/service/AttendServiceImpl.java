package com.ylf.manage.service;

import com.ylf.manage.daoAPI.AttendPlanMapper;
import com.ylf.manage.entity.AttendPlan;
import com.ylf.manage.serviceAPI.AttendPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
            if (p.getEndtime().getTime()-plan.getStarttime().getTime()>=0) {
                if (Math.abs(p.getMarktime().getTime() - plan.getMarktime().getTime()) < standard) {
                        return true;
                }
            }
        }
        return false;
    }
}
