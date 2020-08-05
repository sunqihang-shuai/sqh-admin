package com.fh.sqh.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fh.sqh.mapper.userMessageMapper;
import com.fh.sqh.model.po.Area;
import com.fh.sqh.model.po.User;
import com.fh.sqh.model.po.UserMessage;
import com.fh.sqh.model.vo.GoodsCart;
import com.fh.sqh.model.vo.UserMessageInfo;
import com.fh.sqh.service.userMessageService;
import com.fh.sqh.utils.RediaUse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class userMessageServiceImpl implements userMessageService {

    @Resource
    private userMessageMapper userMessageMapper;

    @Resource
    private HttpServletRequest request;

    @Override
    public List<UserMessageInfo> queryUserMessage() {
        User login_user = (User) request.getAttribute("login_user");
        String iphone = login_user.getUserIphone();
        List<UserMessageInfo> list = userMessageMapper.queryUserMessage(iphone);
        String area_shop_sqh = RediaUse.get("area_shop_sqh");
        List<Area> areas = JSONArray.parseArray(area_shop_sqh, Area.class);

        String areaIds = "";
        String areaName = "";
        for (int i = 0; i <list.size(); i++) {
            UserMessageInfo userMessageInfo = list.get(i);
            areaIds+=userMessageInfo.getAreaIds();
            for (int j = 0; j <areas.size(); j++) {
                Area area = areas.get(j);
                if(areaIds.contains(","+area.getId()+",")==true){
                    areaName+=area.getName()+",";
                }
            }
            userMessageInfo.setAllArea(areaName+userMessageInfo.getDetailArea());
            areaIds = "";
            areaName = "";
        }
        return list;
    }

    @Override
    public void addUserMessage(UserMessage userMessage) {
        User login_user = (User) request.getAttribute("login_user");
        String iphone = login_user.getUserIphone();

        userMessage.setUserIsCheck(2);
        userMessage.setCreateDate(new Date());
        userMessage.setSiteId(iphone);

        userMessageMapper.addUserMessage(userMessage);
    }

    @Override
    public void deleteUserMessage(Integer userId) {
        userMessageMapper.deleteUserMessage(userId);
    }

    @Override
    public void uodateIsCheck(Integer userId) {
        User login_user = (User) request.getAttribute("login_user");
        List<UserMessage> userMessages = userMessageMapper.queryUserMessageById(login_user.getUserIphone());
        for (int i = 0; i <userMessages.size() ; i++) {
            UserMessage userMessage = userMessages.get(i);
            if(userMessage.getUserId()== userId){
                userMessageMapper.uodateIsCheck(userId);//1
            }else{
                userMessageMapper.updateIsCheck(login_user.getUserIphone(),userId);//2
            }
        }
    }

    @Override
    public List<GoodsCart> queryGoodsCart() {
        User login_user = (User) request.getAttribute("login_user");
        String iphone = login_user.getUserIphone();
        List<String> hvals = RediaUse.hvals("SQH_cart_" + iphone);
        List<GoodsCart> list=new ArrayList<>();
        for (int i = 0; i <hvals.size(); i++) {
            String s = hvals.get(i);
            GoodsCart goodsCart = JSONObject.parseObject(s, GoodsCart.class);
            if(goodsCart.isGoodsIsCheck()==true){
                list.add(goodsCart);
            }
        }
        return list;
    }
}
