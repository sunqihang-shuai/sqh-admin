package com.fh.sqh.service.impl;

import com.fh.sqh.mapper.typeMapper;
import com.fh.sqh.model.po.Area;
import com.fh.sqh.model.po.Type;
import com.fh.sqh.service.typeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class typeServiceImpl implements typeService {

    @Resource
    private typeMapper typeMapper;

    @Override
    public List<Type> queryType() {
        return typeMapper.queryType();
    }

}
