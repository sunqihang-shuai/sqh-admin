package com.fh.sqh.controller;


import com.fh.sqh.common.JsonData;
import com.fh.sqh.model.po.User;
import com.fh.sqh.service.userService;
import com.fh.sqh.utils.JWT;
import com.fh.sqh.utils.RediaUse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("userController")
@RestController
public class userController {

    @Resource
    private userService userService;

    @RequestMapping("sendMessage")
    public JsonData sendMessage(String iphone){
        //String s = MessageUtils.sendMsg(iphone);
        String s = "1111";
        RediaUse.set(iphone+"_sqh",s,60*5);
        return JsonData.getJsonSuccess("短信发送成功");
    }

    @RequestMapping("login")
    public JsonData login(String iphone,String code){
        Map map = new HashMap();
        String redisCode = RediaUse.get(iphone + "_sqh");
        if(redisCode!=null && redisCode.equals(code)){
            User user = userService.queryUserByIphone(iphone);
            if(user==null){
                map.put("status",500);
                map.put("message","用户不存在 或者 验证码错误");
            }else {
                String sign = JWT.sign(user, 1000 * 6 * 60 * 24);
                RediaUse.set(iphone+"_token_sqh",sign,60*30);

                String s = Base64.getEncoder().encodeToString((iphone + "," + sign).getBytes());
                map.put("status",200);
                map.put("token",s);
                map.put("message","登陆成功");
            }
        }else{
            map.put("status",500);
            map.put("message","用户不存在 或者 验证码错误");
        }
        return JsonData.getJsonSuccess(map);
    }
}
