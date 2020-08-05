package com.fh.sqh.mapper;

import com.fh.sqh.model.po.User;

public interface userMapper {
    User queryUserByIphone(String iphone);
}
