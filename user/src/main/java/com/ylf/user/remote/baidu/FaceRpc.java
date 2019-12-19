package com.ylf.user.remote.baidu;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.util.HashMap;

/**
 * @author: leifeng.ye
 * @date: 2019-12-10
 * @desc: 百度人脸sdk调用
 */
@Component
public class FaceRpc {
    public static void main(String[] args) {
        new FaceRpc().deleteGroup(new FaceRpc().getClient(),"test_1575734400000_39600000_1_3");
    }


    AipFace client=null;

    public AipFace getClient(){
        if(client==null){
            client=new AipFace("17970422","zSDokOAIfXMH06y0NhnfmUjg","NSyjgYVuq1phaWvlxL9wouGcGOVtBMsF");
            return client;
        }
        else {
            return client;
        }
    }

    public static String fileToBase64(MultipartFile file){
        String str="";
        try{
            BASE64Encoder bs=new BASE64Encoder();
            str=bs.encode(file.getBytes());

        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

    public JSONObject addFace(AipFace client, String user_info, MultipartFile imgFile, String groupId, String userId) {          //添加人脸
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_info", user_info);
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("action_type", "REPLACE");
        try{
            String bs=fileToBase64(imgFile);
            String imageType = "BASE64";
            // 人脸注册
            JSONObject res = client.addUser(bs, imageType, groupId, userId, options);
            return res;
        }catch (Exception e){
               e.printStackTrace();
               return null;
        }

    }

    public JSONObject update(AipFace client,String user_info, MultipartFile imgFile, String groupId, String userId) { //更新人脸
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_info", user_info);
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("action_type", "REPLACE");

        try{
            String bs=fileToBase64(imgFile);
            String image = "取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
            String imageType = "BASE64";
            // 人脸更新
            JSONObject res = client.updateUser(bs, imageType, groupId, userId, options);
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject delete(AipFace client,String userId,String groupId,String faceToken) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        // 人脸删除
        JSONObject res = client.faceDelete(userId, groupId, faceToken, options);
        return res;
    }


    public JSONObject detect(AipFace client,MultipartFile imgFile) {  //检测人脸
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age");
        options.put("max_face_num", "10");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "LOW");

        try{
            String bs= fileToBase64(imgFile);
            String imageType = "BASE64";
            // 人脸检测
            JSONObject res = client.detect(bs, imageType, options);
            return res;
        }catch (Exception e){
             e.printStackTrace();
             return null;
        }
    }

    public JSONObject search(AipFace client,MultipartFile imgFile,String groupIdList) {  //搜索
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("max_face_num", "1");
        options.put("match_threshold", "70");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("max_user_num", "1");
        try{
            String bs=fileToBase64(imgFile);
            String imageType = "BASE64";
            // 人脸搜索
            JSONObject res = client.search(bs, imageType, groupIdList, options);
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public JSONObject createGroup(AipFace client,String groupId) {  //创建用户组
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        // 创建用户组
        JSONObject res = client.groupAdd(groupId, options);
        return res;
    }

    public JSONObject deleteGroup(AipFace client,String groupId) {  //删除用户组
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        // 删除用户组
        JSONObject res = client.groupDelete(groupId, options);
        return res;
    }

    public JSONObject getUserList(AipFace client,String groupId,String length) { //返回group用户列表
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("start", "0");
        options.put("length",length);
        // 获取用户列表
        JSONObject res = client.getGroupUsers(groupId, options);
        return res;
    }

    public JSONObject getUserInfo(AipFace client, String userId,String groupId) {  //查询用户信息
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        // 用户信息查询
        JSONObject res = client.getUser(userId, groupId, options);
        return res;
    }


    public JSONObject getUserImageList(AipFace client,String userId,String groupId){   //获取用户人脸列表
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        // 获取用户人脸列表
        JSONObject res = client.faceGetlist(userId, groupId, options);
        return res;
    }
}
