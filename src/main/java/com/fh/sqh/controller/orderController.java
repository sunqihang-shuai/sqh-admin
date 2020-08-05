package com.fh.sqh.controller;

import com.fh.sqh.common.CountException;
import com.fh.sqh.common.JsonData;
import com.fh.sqh.model.po.Order;
import com.fh.sqh.service.orderService;
import com.fh.sqh.utils.RediaUse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("orderController")
public class orderController {

    @Resource
    private orderService orderService;

    @RequestMapping("createOrder")
    @ResponseBody
    public JsonData createOrder(Integer payType, Integer userMessageId, String flag) throws CountException {
        boolean exists = RediaUse.exists(flag);
        if(exists==true){
            return JsonData.getJsonSuccess("信息正在处理  请稍等");
        }else{
            RediaUse.set(flag,"",10);
        }
        Map map = orderService.createOrder(payType,userMessageId);
        return JsonData.getJsonSuccess(map);
    }

    @RequestMapping("createORCode")
    public JsonData createORCode(Integer orderId) throws Exception {
        Map<String, String> orCode = orderService.createORCode(orderId);
        return JsonData.getJsonSuccess(orCode);
    }

    @RequestMapping("initCodeStatus")
    public JsonData initCodeStatus(Integer orderId) throws Exception {
        Map<String, String> stringStringMap = orderService.initCodeStatus(orderId);
        return JsonData.getJsonSuccess(stringStringMap);
    }

    @RequestMapping("queryMyOrder")
    public JsonData queryMyOrder(){
        List<Order> list = orderService.queryMyOrder();
        return JsonData.getJsonSuccess(list);
    }


}
