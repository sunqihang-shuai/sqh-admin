package com.fh.sqh.service;

import com.fh.sqh.model.po.UserMessage;
import com.fh.sqh.model.vo.GoodsCart;
import com.fh.sqh.model.vo.UserMessageInfo;

import java.util.List;

public interface userMessageService {
    List<UserMessageInfo> queryUserMessage();

    void addUserMessage(UserMessage userMessage);

    void deleteUserMessage(Integer userId);

    void uodateIsCheck(Integer userId);

    List<GoodsCart> queryGoodsCart();
}
