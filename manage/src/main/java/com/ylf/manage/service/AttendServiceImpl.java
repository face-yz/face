package com.ylf.manage.service;

import com.ylf.manage.daoAPI.AttendPlanMapper;
import com.ylf.manage.daoAPI.SignMapper;
import com.ylf.manage.entity.AttendPlan;
import com.ylf.manage.entity.BasePage.ReqPage;
import com.ylf.manage.entity.ReqSign;
import com.ylf.manage.entity.ResSign;
import com.ylf.manage.remote.baidu.FaceRpc;
import com.ylf.manage.serviceAPI.AttendPlanService;
import com.ylf.manage.util.Encoder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

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
    public boolean hasConflict(AttendPlan plan) {
        Long standard = 2 * 60 * 60 * 1000 + 30 * 60 * 1000l;   //上课时间2小时+休息时间30分钟
        ArrayList<AttendPlan> list = (ArrayList<AttendPlan>) getAttendPlanList(plan.getGroupname());
        for (AttendPlan p : list) {
            String[] days = p.getDays().split("_");
            ArrayList<Integer> l = new ArrayList<>();
            for (String d : days) {
                l.add(Integer.valueOf(d));
            }
            boolean f = weekhasConflict(l, plan.getWeekdays());
            if (plan.getStarttime().getTime() >= p.getStarttime().getTime() && plan.getStarttime().getTime() <= p.getEndtime().getTime()) {
                if (f) {
                    if (Math.abs(p.getMarktime().getTime() - plan.getMarktime().getTime()) < standard) {
                        return true;
                    }
                }
            }
            if (plan.getEndtime().getTime() >= p.getStarttime().getTime() && plan.getEndtime().getTime() <= p.getEndtime().getTime()) {
                if (f) {
                    if (Math.abs(p.getMarktime().getTime() - plan.getMarktime().getTime()) < standard) {
                        return true;
                    }
                }
            }
            if (plan.getStarttime().getTime() <= p.getStarttime().getTime() && plan.getEndtime().getTime() >= p.getEndtime().getTime()) {
                if (f) {
                    if (Math.abs(p.getMarktime().getTime() - plan.getMarktime().getTime()) < standard) {
                        return true;
                    }
                }

            }

        }
        return false;
    }

    @Override
    public boolean faceIsLegal(MultipartFile img) {
        JSONObject msg = faceRpc.detect(faceRpc.getClient(), img);
        if (msg.getInt("error_code") == 222202 || msg.getJSONObject("result").getInt("face_num") > 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void addDefaultSign(String u_id, Date start, Date end, String clazzName, String groupName, Date markTime, Integer[] weekdays, String days) {
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer a : weekdays) {
            list.add(a);
        }
        Long s = start.getTime();
        Long e = end.getTime();
        Long oneDay = 1000 * 60 * 60 * 24l;
        while (s <= e) {
            Date time = new Date(s);
            if (time.getDay() == 6 || time.getDay() == 0 || !list.contains(time.getDay())) {
                s += oneDay;
            } else {
                ReqSign sign = new ReqSign();
                sign.setuId(u_id);
                sign.setClazzname(clazzName);
                sign.setGroupname(groupName);
                sign.setState(0);
                sign.setSigndate(time);
                sign.setMarktime(markTime);
                sign.setStarttime(start);
                sign.setDays(days);
                signDao.insert(sign);
                s += oneDay;
            }

        }
    }

    @Override
    public List selectList() {
        return dao.selectList();
    }

    @Override
    public boolean weekhasConflict(ArrayList<Integer> l, Integer[] plan) {
        boolean f = false;
        for (int i = 0; i < plan.length; i++) {
            if (l.contains(plan[i])) {
                f = true;
                break;
            }
        }
        return f;
    }

    @Override
    public int selectCount() {
        return dao.selectCount();
    }

    @Override
    public List selectLimitList(ReqPage page) {
        page.setPageSize(10);
        page.setPageNo(page.getPageNo() * 10);
        return dao.selectLimitList(page);
    }

    @Override
    public boolean isAddUserHasConflict(String uId,AttendPlan plan) {
        Long standard = 2 * 60 * 60 * 1000 + 30 * 60 * 1000l;   //上课时间2小时+休息时间30分钟
        ArrayList<ResSign> list=(ArrayList<ResSign>) signDao.selectUserAttendPlan(Encoder.encoder("04163035"));
        ArrayList<AttendPlan> plans=new ArrayList<>();
        for(ResSign a:list){
            AttendPlan t=selectAttendPlan(a);
            plans.add(t);
        }
        for (AttendPlan p : plans) {
            String[] days = p.getDays().split("_");
            ArrayList<Integer> l = new ArrayList<>();
            for (String d : days) {
                l.add(Integer.valueOf(d));
            }
            boolean f = weekhasConflict(l, plan.getWeekdays());
            if (plan.getStarttime().getTime() >= p.getStarttime().getTime() && plan.getStarttime().getTime() <= p.getEndtime().getTime()) {
                if (f) {
                    if (Math.abs(p.getMarktime().getTime() - plan.getMarktime().getTime()) < standard) {
                        return true;
                    }
                }
            }
            if (plan.getEndtime().getTime() >= p.getStarttime().getTime() && plan.getEndtime().getTime() <= p.getEndtime().getTime()) {
                if (f) {
                    if (Math.abs(p.getMarktime().getTime() - plan.getMarktime().getTime()) < standard) {
                        return true;
                    }
                }
            }
            if (plan.getStarttime().getTime() <= p.getStarttime().getTime() && plan.getEndtime().getTime() >= p.getEndtime().getTime()) {
                if (f) {
                    if (Math.abs(p.getMarktime().getTime() - plan.getMarktime().getTime()) < standard) {
                        return true;
                    }
                }

            }

        }
        return false;
    }

    @Override
    public AttendPlan selectAttendPlan(ResSign sign) {
        return dao.selectAttendPlan(sign);
    }

}
