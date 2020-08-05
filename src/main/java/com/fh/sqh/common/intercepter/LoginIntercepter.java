package com.fh.sqh.common.intercepter;

import com.fh.sqh.common.RedisUse;
import com.fh.sqh.common.NoLoginException;
import com.fh.sqh.model.po.User;

import com.fh.sqh.utils.JWT;
import com.fh.sqh.utils.RediaUse;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

public class LoginIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        //查看头部信息是否完整
        if("null".equals(token)){
            throw new NoLoginException("头部信息不完整");
        }

        //在登录的时候进行了base64加密  现在要进行解密  得到的是字节数组
        byte[] decode = Base64.getDecoder().decode(token);
        //将字节数组转换成字符串
        String signToken = new String(decode);
        //判断字符串是否被篡改
        String[] split = signToken.split(",");
        if(split.length!=2){
            throw new NoLoginException("token格式不正确");
        }

        String iphone = split[0];
        String sign1 = split[1];
        //在给密钥进行解密
        User unsign = JWT.unsign(sign1, User.class);

        if(unsign==null){
            throw new NoLoginException("您还没有进行登录");
        }

        if(unsign!=null){
            //通过key从redis中取出tocken进行比较
            String sign = RedisUse.get(iphone+"_token_sqh");
            if(!sign1.equals(sign)){//如果不相等  tocken过期重新登陆
                throw new NoLoginException("您的身份已过期  请重新登录");
            }

            RediaUse.set("token_"+unsign.getUserIphone(),sign1,60*30);
            request.setAttribute("login_user",unsign);
        }
        return true;
    }
}
