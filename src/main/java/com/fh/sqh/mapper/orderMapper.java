package com.fh.sqh.mapper;

import com.fh.sqh.model.po.Order;

import java.util.List;

public interface orderMapper {
    void addOrder(Order order);

    Order queryOrderById(Integer orderId);

    void updateOrder(Order order);

    List<Order> queryMyOrder(Integer userId);
}
