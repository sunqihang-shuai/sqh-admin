package com.fh.sqh.mapper;

import com.fh.sqh.model.po.UserMessage;
import com.fh.sqh.model.vo.UserMessageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface userMessageMapper {

    List<UserMessageInfo> queryUserMessage(String iphone);

    void addUserMessage(UserMessage userMessage);

    void deleteUserMessage(Integer userId);

    List<UserMessage> queryUserMessageById(String userIphone);

    void uodateIsCheck(Integer userId);

    void updateIsCheck(@Param("iphone")String userIphone,@Param("userId") Integer userId);
}
