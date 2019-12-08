package com.ylf.manage.test;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

/**
 * @author: leifeng.ye
 * @date: 2019-12-08
 * @desc:
 */
public class SearchFace {
    public static void main(String[] args) {
        AipFace client=new AipFace("17970422","zSDokOAIfXMH06y0NhnfmUjg","NSyjgYVuq1phaWvlxL9wouGcGOVtBMsF");
        new SearchFace().getInfo(client);
    }
    public void getInfo(AipFace client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("max_face_num", "1");
        options.put("match_threshold", "70");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("max_user_num", "1");


        try{
            File file=new File("/home/ylf/Documents/face/2_.jpg");
            String bs=RegisterFace.fileToBase64(file);
            String image = "取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
            String imageType = "BASE64";
            String groupIdList = "group1";
            // 人脸搜索
            JSONObject res = client.search(bs, imageType, groupIdList, options);
            System.out.println(res.toString(2));
        }catch (Exception e){

        }

    }

}
