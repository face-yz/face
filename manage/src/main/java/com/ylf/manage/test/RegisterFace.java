package com.ylf.manage.test;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author: leifeng.ye
 * @date: 2019-12-08
 * @desc:
 */
public class RegisterFace {

    public static String fileToBase64(File file){
        String str="";
        try{
            InputStream in=new FileInputStream(file);
            byte[] bts=new byte[in.available()];
            in.read(bts);
            BASE64Encoder bs=new BASE64Encoder();
            str=bs.encode(bts);

        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

    public static void main(String[] args) {
        AipFace client=new AipFace("17970422","zSDokOAIfXMH06y0NhnfmUjg","NSyjgYVuq1phaWvlxL9wouGcGOVtBMsF");
        new RegisterFace().addFace(client);
    }

    public void addFace(AipFace client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_info", "user's info");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("action_type", "REPLACE");

        try{
            File file=new File("/home/ylf/Documents/face/2.jpg");
            String bs=fileToBase64(file);
            String image = "取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
            String imageType = "BASE64";
            String groupId = "group1";
            String userId = "user2";

            // 人脸注册
            JSONObject res = client.addUser(bs, imageType, groupId, userId, options);
            System.out.println(res.toString(2));
        }catch (Exception e){

        }

    }
}
