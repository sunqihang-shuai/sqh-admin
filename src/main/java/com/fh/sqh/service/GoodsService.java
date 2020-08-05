package com.fh.sqh.service;

import com.fh.sqh.model.po.Goods;
import com.fh.sqh.model.vo.GoodsVoHot;

import java.util.List;

public interface GoodsService {
    List<GoodsVoHot> queryGoodsHot();

    Goods queryGoodsById(Integer goodsId);
}
