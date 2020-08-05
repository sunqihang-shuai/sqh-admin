package com.fh.sqh.mapper;

import com.fh.sqh.model.po.Goods;
import com.fh.sqh.model.vo.GoodsVoHot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    List<GoodsVoHot> queryGoodsHot();

    Goods queryGoodsById(Integer goodsId);

    int updateGoodsStoryCount(@Param("goodsId") Integer goodsId,@Param("goodsCount") Integer goodsCount);
}
