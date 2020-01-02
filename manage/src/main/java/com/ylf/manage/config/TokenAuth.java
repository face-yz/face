package com.ylf.manage.config;

import com.alibaba.fastjson.JSONObject;
import com.ylf.manage.entity.Response;
import com.ylf.manage.util.Token;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author: leifeng.ye
 * @date: 2020-01-02
 * @desc:
 */
@Component
public class TokenAuth implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception{
        String token=httpServletRequest.getHeader("Authorization");
        if(!Token.isLegalToken(token)){
            Response res=new Response();
            res.setMsg("token不合法");
            res.setCode("-1");
            res.setData(null);
            String jsonRes = JSONObject.toJSONString(res);
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            PrintWriter out=httpServletResponse.getWriter();
            out.append(jsonRes);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)  {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
    }
}
