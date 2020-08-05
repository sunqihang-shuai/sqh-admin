package com.fh.sqh.controller;

import com.fh.sqh.common.JsonData;
import com.fh.sqh.model.po.UserMessage;
import com.fh.sqh.model.vo.GoodsCart;
import com.fh.sqh.model.vo.UserMessageInfo;
import com.fh.sqh.service.userMessageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("userMessageController")
public class userMessageController {

    @Resource
    private userMessageService userMessageService;

    @RequestMapping("queryUserMessage")
    public JsonData queryUserMessage(){
        List<UserMessageInfo> list = userMessageService.queryUserMessage();
        return JsonData.getJsonSuccess(list);
    }

    @RequestMapping("addUserMessage")
    public JsonData addUserMessage(UserMessage userMessage){
        userMessageService.addUserMessage(userMessage);
        return JsonData.getJsonSuccess("新增成功");
    }

    @RequestMapping("deleteUserMessage")
    public JsonData deleteUserMessage(Integer userId){
        userMessageService.deleteUserMessage(userId);
        return JsonData.getJsonSuccess("删除成功");
    }

    @RequestMapping("uodateIsCheck")
    public JsonData uodateIsCheck(Integer userId){
        userMessageService.uodateIsCheck(userId);
        return JsonData.getJsonSuccess("修改成功");
    }

    @RequestMapping("queryGoodsCart")
    public JsonData queryGoodsCart(){
        List<GoodsCart> list = userMessageService.queryGoodsCart();
        return JsonData.getJsonSuccess(list);
    }

}
