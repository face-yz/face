package com.ylf.manage.test;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author: leifeng.ye
 * @date: 2019-12-08
 * @desc:
 */
public class GroupFace {
    public static void main(String[] args) {
        AipFace client=new AipFace("17970422","zSDokOAIfXMH06y0NhnfmUjg","NSyjgYVuq1phaWvlxL9wouGcGOVtBMsF");
        new GroupFace().CreateGroup(client);
    }

    public void CreateGroup(AipFace client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();

        String groupId = "group2";

        // 创建用户组
        JSONObject res = client.groupAdd(groupId, options);
        System.out.println(res.toString(2));

    }

    public void deleteGroup(AipFace client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();

        String groupId = "group2";

        // 删除用户组
        JSONObject res = client.groupDelete(groupId, options);
        System.out.println(res.toString(2));

    }
}
