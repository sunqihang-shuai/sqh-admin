package com.fh.sqh.service.impl;

import com.fh.sqh.mapper.userMapper;
import com.fh.sqh.model.po.User;
import com.fh.sqh.service.userService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class userServiceImpl implements userService {

    @Resource
    private userMapper userMapper;

    @Override
    public User queryUserByIphone(String iphone) {
        return userMapper.queryUserByIphone(iphone);
    }
}
