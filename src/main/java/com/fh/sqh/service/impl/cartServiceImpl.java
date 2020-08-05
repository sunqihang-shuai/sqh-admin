package com.fh.sqh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fh.sqh.mapper.cartMapper;
import com.fh.sqh.model.po.Goods;
import com.fh.sqh.model.po.User;
import com.fh.sqh.model.vo.GoodsCart;
import com.fh.sqh.service.cartService;
import com.fh.sqh.utils.RediaUse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class cartServiceImpl implements cartService {

    @Resource
    private HttpServletRequest request;

    @Resource
    private cartMapper cartMapper;

    @Override
    public Integer addGoodsCart(Integer goodsId, Integer count) {
        User login_user = (User) request.getAttribute("login_user");
        String iphone = login_user.getUserIphone();
        String goodsInfo = RediaUse.hget("SQH_cart_"+iphone,goodsId+"");

        if(count>0){
            Goods goods =cartMapper.queryGoodsByGoodsId(goodsId);
            Integer goodsAllCount = goods.getGoodsStoryCount();
            if(count>goodsAllCount){
                return goodsAllCount-count;
            }
        }

        //通过id查商品
        if(StringUtils.isEmpty(goodsInfo)){
            GoodsCart goodsCart = cartMapper.queryGoodsById(goodsId);
            goodsCart.setGoodsIsCheck(true);
            goodsCart.setGoodsCount(count);
            //计算小计  bigde
            BigDecimal money=goodsCart.getGoodsPrice().multiply(new BigDecimal(count));
            goodsCart.setGoodsMoney(money);
            String productCartString = JSONObject.toJSONString(goodsCart);
            //将数据放入redis
            RediaUse.hset("SQH_cart_"+iphone,goodsId+"",productCartString);
        }else{
            Goods goods =cartMapper.queryGoodsByGoodsId(goodsId);
            GoodsCart goodsCart = JSONObject.parseObject(goodsInfo, GoodsCart.class);

            Integer redisCount = goodsCart.getGoodsCount();
            Integer goodsCountAll = goods.getGoodsStoryCount();

            if(count==goodsCountAll){
                Integer downCount = goodsCountAll-redisCount;
                goodsCart.setGoodsCount(downCount+redisCount);
            }else{
                goodsCart.setGoodsCount(redisCount+count);
            }

            if(goodsCart.getGoodsCount()>goods.getGoodsStoryCount()){
                return goods.getGoodsStoryCount()-goodsCart.getGoodsStoryCount();
            }
            //计算小计  bigde
            BigDecimal money=goodsCart.getGoodsPrice().multiply(new BigDecimal(goodsCart.getGoodsCount()));
            goodsCart.setGoodsMoney(money);
            String productCartString = JSONObject.toJSONString(goodsCart);
            //将数据放入redis
            RediaUse.hset("SQH_cart_"+iphone,goodsId+"",productCartString);
        }
        long hlen = RediaUse.hlen("SQH_cart_" + iphone);
        return (int) hlen;
    }

    @Override
    public List queryGoodsCart() {
        User login_user = (User) request.getAttribute("login_user");
        String iphone = login_user.getUserIphone();

        List hvals = RediaUse.hvals("SQH_cart_" + iphone);
        return hvals;
    }

    @Override
    public void updateGoodsCartIsChecked(String checkedIds) {
        User login_user = (User) request.getAttribute("login_user");
        String iphone = login_user.getUserIphone();
        List<String> goodsCart = RediaUse.hvals("SQH_cart_" + iphone);

        for (int i = 0; i <goodsCart.size(); i++) {
            String goodsStr = goodsCart.get(i);
            GoodsCart goodsCart1 = JSONObject.parseObject(goodsStr, GoodsCart.class);
            Integer goodsId = goodsCart1.getGoodsId();
            if((","+checkedIds).contains(","+goodsId+",")!=true){
                goodsCart1.setGoodsIsCheck(false);
                RediaUse.hset("SQH_cart_" + iphone,goodsId+"",JSONObject.toJSONString(goodsCart1));
            }else {
                goodsCart1.setGoodsIsCheck(true);
                RediaUse.hset("SQH_cart_" + iphone,goodsId+"",JSONObject.toJSONString(goodsCart1));
            }
        }
    }

    @Override
    public void deleteGoodsCartAll(List<Integer> list) {
        User login_user = (User) request.getAttribute("login_user");
        String iphone = login_user.getUserIphone();

        for (int i = 0; i <list.size(); i++) {
            Integer integer = list.get(i);
            RediaUse.hdel("SQH_cart_" + iphone,integer+"");
        }
    }

    @Override
    public void deleteGoodsCartByGoodsId(Integer goodsId) {
        User login_user = (User) request.getAttribute("login_user");
        String iphone = login_user.getUserIphone();
        RediaUse.hdel("SQH_cart_" + iphone,goodsId+"");
    }


}
