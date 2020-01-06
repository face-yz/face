//package com.ylf.manage.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.ArrayList;
//
//
///**
// * @author: leifeng.ye
// * @date: 2020-01-02
// * @desc:
// */
//@Component
//public class TokenAuthConfig  implements WebMvcConfigurer {
//
//    @Autowired
//    private TokenAuth tokenAuth;
//
//    //注册拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //拦截所有的请求
//        ArrayList<String> list=new ArrayList<>();
//        list.add("/api/v1/login");
//        list.add("/api/v1/userLogin");
//        list.add("/api/v1/loginByPhone");
//        list.add("/api/vi/sendCode");
//        list.add("/api/v1/getCode");
//        list.add("/api/v1/authByPhoneCode");
//        list.add("/static/**");
//        registry.addInterceptor(tokenAuth).addPathPatterns("/**").excludePathPatterns(list);
//    }
//}
