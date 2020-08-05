package com.fh.sqh.controller;

import com.fh.sqh.common.JsonData;
import com.fh.sqh.model.vo.GoodsCart;
import com.fh.sqh.service.cartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("cartController")
@RestController
public class cartController {

    @Resource
    private cartService cartService;

    @RequestMapping("addCart")
    public JsonData addCart(Integer goodsId,Integer count){
        Integer goodsCount = cartService.addGoodsCart(goodsId,count);
        return JsonData.getJsonSuccess(goodsCount);
    }

    @RequestMapping("queryGoodsCart")
    public JsonData queryGoodsCart(){
        List list = cartService.queryGoodsCart();
        return JsonData.getJsonSuccess(list);
    }

    @RequestMapping("updateGoodsCartIsChecked")
    public JsonData updateGoodsCartIsChecked(String checkedIds){
        cartService.updateGoodsCartIsChecked(checkedIds);
        return JsonData.getJsonSuccess("修改成功");
    }

    @RequestMapping("deleteGoodsCartAll")
    public JsonData deleteGoodsCartAll(@RequestParam("ids[]") List<Integer> list){
        cartService.deleteGoodsCartAll(list);
        return JsonData.getJsonSuccess("删除成功");
    }

    @RequestMapping("deleteGoodsCartByGoodsId")
    public JsonData deleteGoodsCartByGoodsId(Integer goodsId){
        cartService.deleteGoodsCartByGoodsId(goodsId);
        return JsonData.getJsonSuccess("删除成功");
    }


}
