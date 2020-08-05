package com.fh.sqh.mapper;

import com.fh.sqh.model.po.Goods;
import com.fh.sqh.model.vo.GoodsCart;


public interface cartMapper {
    Goods queryGoodsByGoodsId(Integer goodsId);

    GoodsCart queryGoodsById(Integer goodsId);

}
