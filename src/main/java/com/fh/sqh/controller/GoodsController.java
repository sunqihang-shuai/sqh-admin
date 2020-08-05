package com.fh.sqh.controller;


import com.alibaba.fastjson.JSONObject;
import com.fh.sqh.common.JsonData;
import com.fh.sqh.model.po.Goods;
import com.fh.sqh.model.vo.GoodsVoHot;
import com.fh.sqh.service.GoodsService;
import com.fh.sqh.utils.RediaUse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("goodsController")
@RestController
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @RequestMapping("queryGoodsHot")
    public JsonData queryGoodsHot(){
        String goodsCart_hot_sqh = RediaUse.get("goodsCart_Hot_sqh");
        if(goodsCart_hot_sqh==null){
            List<GoodsVoHot> goodsHot = goodsService.queryGoodsHot();
            String s = JSONObject.toJSONString(goodsHot);
            RediaUse.set("goodsCart_Hot_sqh",s);
        }
        return JsonData.getJsonSuccess(goodsCart_hot_sqh);
    }

    @RequestMapping("queryGoodsById")
    public JsonData queryGoodsById(Integer goodsId){
        Goods goods = goodsService.queryGoodsById(goodsId);
        return JsonData.getJsonSuccess(goods);
    }
}
