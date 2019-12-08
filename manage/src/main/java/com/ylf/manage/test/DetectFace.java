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
public class DetectFace {

    public static void main(String[] args) {
        AipFace client=new AipFace("17970422","zSDokOAIfXMH06y0NhnfmUjg","NSyjgYVuq1phaWvlxL9wouGcGOVtBMsF");
        new DetectFace().detect(client);
    }


    public void detect(AipFace client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age");
        options.put("max_face_num", "2");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "LOW");

        try{
            File file=new File("/home/ylf/Documents/face/3.jpg");
            String bs=RegisterFace.fileToBase64(file);
            String image = "取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
            String imageType = "BASE64";
            // 人脸检测
            JSONObject res = client.detect(bs, imageType, options);
            System.out.println(res.toString(2));
        }catch (Exception e){

        }




    }
}
