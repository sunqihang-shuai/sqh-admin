package com.fh.sqh.controller;

import com.alibaba.fastjson.JSONObject;
import com.fh.sqh.common.JsonData;
import com.fh.sqh.model.po.Area;
import com.fh.sqh.service.areaService;
import com.fh.sqh.utils.RediaUse;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("areaController")
@RestController
@Api(description="地区controller")
public class areaController {

    @Autowired
    private areaService areaService;

    @ApiOperation("地区接口")
    @RequestMapping("queryArea")
    @ApiImplicitParams({@ApiImplicitParam(name="name",dataType = "java.lang.String",value ="名字",required =true)})
    public JsonData queryArea(String name){
        String area_shop_sqh = RediaUse.get("area_shop_sqh");
        if(area_shop_sqh==null){
            List<Area> list = areaService.queryArea();
            String s = JSONObject.toJSONString(list);
            RediaUse.set("area_shop_sqh",s);
        }
        return JsonData.getJsonSuccess(area_shop_sqh);
    }

}
