package com.ylf.manage.service;

import com.ylf.manage.controller.AttendPlanController;
import com.ylf.manage.controller.SignController;
import com.ylf.manage.entity.*;
import com.ylf.manage.remote.baidu.FaceRpc;
import com.ylf.manage.serviceAPI.AttendPlanService;
import com.ylf.manage.serviceAPI.ManageService;
import com.ylf.manage.serviceAPI.SignService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author: leifeng.ye
 * @date: 2019-12-19
 * @desc:
 */
@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private AttendPlanController manageRpc;

    @Autowired
    private SignController signController;

    @Autowired
    private FaceRpc faceRpc;

    @Override
    public String getBaiduGroupList(int k) {
        Map map = new HashMap();
        map.put("k", k);
        Response<AttendPlan> res = manageRpc.selectAttendPlanLimitList(map);
        ArrayList<AttendPlan> list = (ArrayList<AttendPlan>) res.getData();
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                str = str + list.get(i).getGroupname() + "_" + list.get(i).getStarttime().getTime() + "_" + list.get(i).getMarktime().getTime() + "_" + list.get(i).getDays();
            } else {
                str = str + list.get(i).getGroupname() + "_" + list.get(i).getStarttime().getTime() + "_" + list.get(i).getMarktime().getTime() + "_" + list.get(i).getDays() + ",";
            }
        }
        return str;
    }

    @Override
    public List getUserAttendPlanList(ImageDate img) {   //匹配度需要大于80
        int n = (Integer) manageRpc.selectCount().getData().get(0)/10;
        int i = 0;
        while (i <= n) {
            String groupList = getBaiduGroupList(i);
            JSONObject res = faceRpc.search(faceRpc.getClient(), img.getImg(), groupList);
            if (res.getInt("error_code") == 0 && res.getJSONObject("result").getJSONArray("user_list").getJSONObject(0).getDouble("score") > 80f) {
                JSONObject result = res.getJSONObject("result");
                JSONArray users = result.getJSONArray("user_list");
                String id = users.getJSONObject(0).getString("user_id");
                ReqSign sign = new ReqSign();
                sign.setuId(id);
                sign.setSigndate(img.getNow());
                ArrayList<ResSign> list = (ArrayList<ResSign>) signController.selectUserSign(sign).getData();
                ArrayList<ResSign> r=new ArrayList<>();
                for (ResSign a : list) {
                    if (a.getState() == 0) {
                        if (img.getNowtime().getTime() - a.getMarktime().getTime() < -1000 * 60 * 30l) {
                            a.setFlag(-1);
                            r.add(a);
                        } else if (img.getNowtime().getTime() - a.getMarktime().getTime() >= -1000 * 60 * 30l && img.getNowtime().getTime() <= a.getMarktime().getTime()) {
                            a.setFlag(1);
                            r.add(a);
                        } else if (img.getNowtime().getTime() - a.getMarktime().getTime() > 0 && img.getNowtime().getTime() - a.getMarktime().getTime() <= 1000 * 60 * 60 * 2l) {
                            a.setFlag(3);
                            r.add(a);
                        } else {
                            a.setFlag(-4);
                        }
                    }
                }
                ArrayList<ResSign> l=new ArrayList<>();
                long u=Long.MAX_VALUE;
                for(int m=0;m<r.size();m++){
                    if(Math.abs(r.get(m).getMarktime().getTime()-img.getNowtime().getTime())<u){
                        l.add(0,r.get(m));
                    }
                }
                return l;
            } else {
                i++;
            }
        }
        return null;
    }

    @Override
    public boolean faceIsLegal(MultipartFile img) {
        JSONObject msg = faceRpc.detect(faceRpc.getClient(), img);
        if (msg.getInt("error_code") == 222202) {
            return false;
        } else {
            return true;
        }
    }
}
