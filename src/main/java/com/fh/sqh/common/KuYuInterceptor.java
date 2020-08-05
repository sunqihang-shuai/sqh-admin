package com.fh.sqh.common;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KuYuInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        //设置response
        // 允许跨域访问的域名：若有端口需写全（协议+域名+端口），若没有端口末尾不用加'/'
        //response.setHeader("Access-Control-Allow-Origin", "http://www.domain1.com");
        //获取请求的域名
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);


        //当客户端修改了头部信息  一共发送两个请求  第一个是预请求  第二个是真正的请求  获取他的请求名称
        String method = request.getMethod();
        if(method.equalsIgnoreCase("options")){
            //允许修改头信息  添加一个name属性
            response.setHeader("Access-Control-Allow-Headers","token");
            return false;
        }
        String token = request.getHeader("token");
        return true;
    }

}
