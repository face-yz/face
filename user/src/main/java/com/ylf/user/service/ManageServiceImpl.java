package com.ylf.user.service;

import com.ylf.user.entity.AttendPlan;
import com.ylf.user.entity.ImageDate;
import com.ylf.user.entity.Response;
import com.ylf.user.entity.Sign;
import com.ylf.user.remote.baidu.FaceRpc;
import com.ylf.user.remote.manage.ManageRpc;
import com.ylf.user.serviceAPI.ManageService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: leifeng.ye
 * @date: 2019-12-19
 * @desc:
 */
@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private ManageRpc manageRpc;

    @Autowired
    private FaceRpc faceRpc;

    @Override
    public String getBaiduGroupList(int k) {
        Map map=new HashMap();
        map.put("k",k);
        Response<AttendPlan> res=manageRpc.selectAttendPlanLimitList(map);
        ArrayList<AttendPlan> list=( ArrayList<AttendPlan>)res.getData();
        String str="";
        for(int i=0;i<list.size();i++){
            if(i==list.size()-1){
                str=str+list.get(i).getGroupname()+"_"+list.get(i).getStarttime().getTime()+"_"+list.get(i).getMarktime().getTime()+"_"+list.get(i).getDays();
            }
            else{
                str=str+list.get(i).getGroupname()+"_"+list.get(i).getStarttime().getTime()+"_"+list.get(i).getMarktime().getTime()+"_"+list.get(i).getDays()+",";
            }
        }
        return str;
    }

    @Override
    public List getUserAttendPlanList(ImageDate img) {
        int n=manageRpc.selectAttendPlanCount().getData().get(0)/10;
        int i=0;
        while (i<=n){
            String groupList=getBaiduGroupList(i);
            JSONObject res=faceRpc.search(faceRpc.getClient(),img.getImg(),groupList);
            if(res.getInt("error_code")==0){
                  JSONObject result=res.getJSONObject("result");
                  JSONArray users=result.getJSONArray("user_list");
                  String id= users.getJSONObject(0).getString("user_id");
                  Sign sign=new Sign();
                  sign.setuId(id);
                  sign.setSigndate(img.getNow());
                  ArrayList<Sign> list=(ArrayList<Sign>) manageRpc.selectUserSign(sign).getData();
                  return  list;
            }
            else{
                i++;
            }
        }
        return null;
    }
}
