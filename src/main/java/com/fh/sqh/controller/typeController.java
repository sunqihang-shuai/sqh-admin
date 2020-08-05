package com.fh.sqh.controller;

import com.alibaba.fastjson.JSONObject;
import com.fh.sqh.common.JsonData;
import com.fh.sqh.model.po.Area;
import com.fh.sqh.model.po.Type;
import com.fh.sqh.service.typeService;
import com.fh.sqh.utils.RediaUse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("typeController")
@RestController
public class typeController {

    @Resource
    private typeService typeService;

    @RequestMapping("queryType")
    public JsonData queryType(){
        String shop_type_sqh = RediaUse.get("shop_type_sqh");
        if(shop_type_sqh==null){
            List<Type> list = typeService.queryType();
            String s = JSONObject.toJSONString(list);
            RediaUse.set("shop_type_sqh",s);
        }
        return JsonData.getJsonSuccess(shop_type_sqh);
    }

}
