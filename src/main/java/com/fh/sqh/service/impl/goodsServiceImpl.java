package com.fh.sqh.service.impl;

import com.fh.sqh.mapper.GoodsMapper;
import com.fh.sqh.model.po.Goods;
import com.fh.sqh.model.vo.GoodsVoHot;
import com.fh.sqh.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class goodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsVoHot> queryGoodsHot() {
        return goodsMapper.queryGoodsHot();
    }

    @Override
    public Goods queryGoodsById(Integer goodsId) {
        return goodsMapper.queryGoodsById(goodsId);
    }
}
