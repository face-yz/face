package com.ylf.manage.test;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author: leifeng.ye
 * @date: 2019-12-08
 * @desc:
 */
public class DeleteFace {
    public static void main(String[] args) {
        AipFace client=new AipFace("17970422","zSDokOAIfXMH06y0NhnfmUjg","NSyjgYVuq1phaWvlxL9wouGcGOVtBMsF");
        new DeleteFace().delete(client);
    }
    public void delete(AipFace client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        String userId = "user1";
        String groupId = "group1";
        String faceToken = "7e3f97e1d8c3e06ff580e0ca80046ebb";

        // 人脸删除
        JSONObject res = client.faceDelete(userId, groupId, faceToken, options);
        System.out.println(res.toString(2));

    }
}
