package com.fh.sqh.service;

import com.fh.sqh.model.po.User;

public interface userService {
    User queryUserByIphone(String iphone);
}
