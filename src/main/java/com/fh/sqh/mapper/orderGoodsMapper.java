package com.fh.sqh.mapper;

import com.fh.sqh.model.po.OrderGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface orderGoodsMapper {
    void batchAddOrderGoods(@Param("list")List<OrderGoods> list,@Param("orderId") Integer orderId);
}
