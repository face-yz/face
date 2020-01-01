package com.ylf.manage.service;

import com.google.common.annotations.VisibleForTesting;
import com.ylf.manage.daoAPI.SignMapper;
import com.ylf.manage.entity.*;
import com.ylf.manage.remote.user.UserRpc;
import com.ylf.manage.serviceAPI.AttendPlanSituationService;
import com.ylf.manage.util.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2020-01-01
 * @desc:
 */
@Service
public class AttendPlanSituationServiceImpl implements AttendPlanSituationService {

    @Autowired
    private UserRpc userRpc;

    @Autowired
    private SignMapper dao;

    @Override
    public List usersAttendPlanSituation(ReqSign sign) {
        ArrayList<ResSign> signList=(ArrayList<ResSign>) dao.selectAttendPlanAllSignList(sign);
        ArrayList<UsersClazzSituaton> list=new ArrayList<>();
        ArrayList<ResSign> userList=(ArrayList<ResSign>) selectDistinctUserCount(sign);
        for(ResSign c:userList){
            User t=new User();
            t.setuId(Encoder.decoder(c.getuId()));
            c.setUsername(userRpc.getUser(t).getUsername());
        }
        for(int i=0;i<userList.size();i++){
            int normal=0,late=0,leave=0,abnormal=0;
            int total=signList.size()/userList.size();
            UsersClazzSituaton a=new UsersClazzSituaton();
            a.setGroupname(signList.get(0).getGroupname());
            a.setClazzname(signList.get(0).getClazzname());
            a.setUId(Encoder.decoder(userList.get(i).getuId()));
            a.setUsername(userList.get(i).getUsername());
            for(int j=0;j<signList.size();j++){
                if(userList.get(i).getuId().equals(signList.get(j).getuId())){
                    if(signList.get(j).getState()==0){
                        abnormal++;
                    }
                    if(signList.get(j).getState()==1){
                        normal++;
                    }
                    if(signList.get(j).getState()==2){
                        leave++;
                    }
                    if(signList.get(j).getState()==3){
                        late++;
                    }
                }
            }
            a.setAbnormal(abnormal);
            a.setLate(late);
            a.setNormal(normal);
            a.setLeave(leave);
            a.setTotal(total);
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(2);
            String percentage = numberFormat.format((float)(late+normal)/(float)total*100)+"%";
            a.setPercentage(percentage);
            list.add(a);
        }
        return list;
    }

    @Override
    public List selectDistinctUserCount(ReqSign sign) {
        ArrayList<ResSign> list=(ArrayList<ResSign>)dao.selectDistinctUserCount(sign);
        return list;
    }

    @Override
    public List selectClazzCurrentPercentage(ReqSign sign) {
        ArrayList<ResSign> list=(ArrayList<ResSign>) dao.selectCurrentPercentage(sign);
        int total=list.size();
        int normal=0,late=0,leave=0,abnormal=0;
        ArrayList<ClazzPercentage> res=new ArrayList<>();
        ClazzPercentage c=new ClazzPercentage();
        if(list.size()>0) {
            c.setTotal(list.size());
            c.setGroupname(list.get(0).getGroupname());
            c.setClazzname(list.get(0).getClazzname());
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getState() == 0) {
                    abnormal++;
                }
                if (list.get(i).getState() == 1) {
                    normal++;
                }
                if (list.get(i).getState() == 2) {
                    leave++;
                }
                if (list.get(i).getState() == 3) {
                    late++;
                }
            }
            c.setNormal(normal);
            c.setLate(late);
            c.setLeave(leave);
            c.setAbnormal(abnormal);
            res.add(c);
        }
        return res;
    }

}
