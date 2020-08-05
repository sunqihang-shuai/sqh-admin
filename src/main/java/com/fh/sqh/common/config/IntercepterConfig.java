package com.fh.sqh.common.config;

import com.fh.sqh.common.KuYuInterceptor;
import com.fh.sqh.common.intercepter.LoginIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //注册TestInterceptor拦截器
        InterceptorRegistration registration11 = registry.addInterceptor(new KuYuInterceptor());
        //   /** 是拦截所有的路径
        registration11.addPathPatterns("/**");


        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new LoginIntercepter());
        //   /** 是拦截所有的路径
        /*registration.addPathPatterns("/**");*/
        registration.addPathPatterns("/cartController/**");                      //所有路径都被拦截
        registration.addPathPatterns("/userMessageController/**");
        registration.addPathPatterns("/orderController/**");
        registration.addPathPatterns("/myOrderController/**");



    }

}
