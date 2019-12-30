package com.ylf.manage.serviceAPI;

import com.ylf.manage.entity.ImageDate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2019-12-19
 * @desc:
 */
public interface ManageService {
    String getBaiduGroupList(int k);      //返回百度API group列表

    List getUserAttendPlanList(ImageDate img);    //返回用户当天的考勤计划

    boolean faceIsLegal(MultipartFile img);      //照片是否合法
}
