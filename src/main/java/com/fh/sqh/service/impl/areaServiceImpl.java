package com.fh.sqh.service.impl;

import com.fh.sqh.mapper.areaMapper;
import com.fh.sqh.model.po.Area;
import com.fh.sqh.service.areaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class areaServiceImpl implements areaService {

    @Resource
    private areaMapper areaMapper;

    @Override
    public List<Area> queryArea() {
        return areaMapper.queryArea();
    }
}
