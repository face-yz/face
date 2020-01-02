package com.ylf.manage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author: leifeng.ye
 * @date: 2020-01-02
 * @desc:
 */
@Component
public class TokenAuthConfig  implements WebMvcConfigurer {

    @Autowired
    private TokenAuth tokenAuth;

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有的请求
        registry.addInterceptor(tokenAuth).addPathPatterns("/**").excludePathPatterns("/api/v1/login", "/api/v1/userLogin").excludePathPatterns("/static/**");
    }
}
