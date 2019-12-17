package com.ylf.manage.service;

import com.ylf.manage.daoAPI.AttendPlanMapper;
import com.ylf.manage.daoAPI.SignMapper;
import com.ylf.manage.entity.AttendPlan;
import com.ylf.manage.entity.Sign;
import com.ylf.manage.remote.baidu.FaceRpc;
import com.ylf.manage.serviceAPI.AttendPlanService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.Date;
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
    private SignMapper signDao;

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
            System.out.println(p.getStarttime().toLocaleString());
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
            if(plan.getStarttime().getTime()<=p.getStarttime().getTime()&&plan.getEndtime().getTime()>=p.getEndtime().getTime()){
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

    @Override
    public void addDefaultSign(String u_id,Date start, Date end,String clazzName,String groupName) {
        Long s=start.getTime();
        Long e=end.getTime();
        Long oneDay=1000*60*60*24l;
         while(s<=e){
             Date time=new Date(s);
             if(time.getDay()==6||time.getDay()==0){
                 s+=oneDay;
             }
             else{
                 Sign sign=new Sign();
                 sign.setuId(u_id);
                 sign.setClazzname(clazzName);
                 sign.setGroupname(groupName);
                 sign.setState(0);
                 sign.setSigndate(time);
                 signDao.insert(sign);
                 s+=oneDay;
             }

         }
    }

    @Override
    public List selectList() {
        return dao.selectList();
    }

}
