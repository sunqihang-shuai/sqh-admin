package com.fh.sqh.service;

import com.fh.sqh.common.CountException;
import com.fh.sqh.model.po.Order;

import java.util.List;
import java.util.Map;

public interface orderService {
    Map createOrder(Integer payType, Integer userMessageId) throws CountException;

    Map<String, String> createORCode(Integer orderId) throws Exception;

    Map<String, String> initCodeStatus(Integer orderId) throws Exception;

    List<Order> queryMyOrder();
}
