package com.ylf.manage.test;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author: leifeng.ye
 * @date: 2019-12-08
 * @desc:
 */
public class UserInfo {
    public static void main(String[] args) {
        AipFace client=new AipFace("17970422","zSDokOAIfXMH06y0NhnfmUjg","NSyjgYVuq1phaWvlxL9wouGcGOVtBMsF");
        new UserInfo().getUserImageList(client);
    }
    public void getUserInfo(AipFace client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();

        String userId = "user1";String groupId = "group1";

        // 用户信息查询
        JSONObject res = client.getUser(userId, groupId, options);
        System.out.println(res.toString(2));

    }

    public void getUserImageList(AipFace client){
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();

        String userId = "user1";
        String groupId = "group1";

        // 获取用户人脸列表
        JSONObject res = client.faceGetlist(userId, groupId, options);
        System.out.println(res.toString(2));
    }
}
