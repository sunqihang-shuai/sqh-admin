package com.fh.sqh.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.fh.sqh.common.CountException;
import com.fh.sqh.mapper.GoodsMapper;
import com.fh.sqh.mapper.orderGoodsMapper;
import com.fh.sqh.mapper.orderMapper;
import com.fh.sqh.model.po.*;
import com.fh.sqh.model.vo.GoodsCart;
import com.fh.sqh.service.GoodsService;
import com.fh.sqh.service.orderService;
import com.fh.sqh.utils.RediaUse;
import com.github.wxpay.sdk.FeiConfig;
import com.github.wxpay.sdk.WXPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class orderServiceImpl implements orderService {

    @Resource
    private orderMapper orderMapper;

    @Autowired
    private GoodsService goodsService;

    @Resource
    private HttpServletRequest request;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private orderGoodsMapper orderGoodsMapper;

    @Override
    public Map createOrder(Integer payType, Integer userMessageId) throws CountException {
        //获取它的返回数据
        Map map = new HashMap();
        //new一个订单详情的实体类
        List<OrderGoods> list = new ArrayList<>();
        //新建一个实体类
        Order order = new Order();
        //维护订单表的数据
        order.setCreateDate(new Date());
        order.setUserId(userMessageId);
        order.setOrderPayType(payType);
        order.setPayStatus(PayStatusEnum.PAY_STATUS_INIT.getStatus());
        //设置购买商品的个数
        Integer goodsCount = 0;
        //设置总价钱
        BigDecimal totalMoney = new BigDecimal(0);
        //获取用户的当前登录信息
        User login_user = (User) request.getAttribute("login_user");
        Integer userId = login_user.getUserId();
        String iphone = login_user.getUserIphone();
        order.setLoginUserId(userId);
        //获取存在redis里的购物车数据
        List<String> hvals = RediaUse.hvals("SQH_cart_" + iphone);
        for (int i = 0; i <hvals.size() ; i++) {
            GoodsCart goodsCart = JSONObject.parseObject(hvals.get(i), GoodsCart.class);
            if(goodsCart.isGoodsIsCheck()==true){
                //通过购物车里面的ID来查看商品的库存
                Goods goods = goodsService.queryGoodsById(goodsCart.getGoodsId());
                //判断  如果数据库里面的库存大于等于购物车里面的数量 说明 库存够用
                if(goods.getGoodsStoryCount()>=goodsCart.getGoodsCount()){
                    //如果他的商品库存够用 购买的商品进行加一
                    goodsCount++;
                    //总钱数 是  购物车里面的总钱数进行增加
                    totalMoney=totalMoney.add(goodsCart.getGoodsMoney());
                    //维护订单详情表
                    OrderGoods orderGoods = new OrderGoods();
                    orderGoods.setCount(goodsCart.getGoodsCount());
                    orderGoods.setGoodsId(goodsCart.getGoodsId());
                    list.add(orderGoods);

                    //在商品表里面的库存减少
                    //解决超卖问题
                    int i1 = goodsMapper.updateGoodsStoryCount(goods.getGoodsId(), goodsCart.getGoodsCount());
                    if(i1==0){
                        throw new CountException("商品为"+goodsCart.getGoodsName()+"的商品，库存不足 现在只有"+goods.getGoodsStoryCount());
                    }
                }else{
                    throw new CountException("商品为"+goodsCart.getGoodsName()+"的商品，库存不足 现在只有"+goods.getGoodsStoryCount());
                }
            }
        }
        order.setOrderTotalMoney(totalMoney);
        order.setOrderCount(goodsCount);
        orderMapper.addOrder(order);

        orderGoodsMapper.batchAddOrderGoods(list,order.getOrderId());

        //for循环删除redis中已经提交的数据
        for (int i = 0; i <hvals.size() ; i++) {
            GoodsCart goodsCart = JSONObject.parseObject(hvals.get(i), GoodsCart.class);
            if(goodsCart.isGoodsIsCheck()==true){
                RediaUse.hdel("SQH_cart_" + iphone,goodsCart.getGoodsId()+"");
            }
        }

        map.put("orderId",order.getOrderId());
        map.put("totalMoney",totalMoney);
        return map;
    }

    @Override
    public Map<String, String> createORCode(Integer orderId) throws Exception {
        Map rs = new HashMap();
        String s = RediaUse.get("order_cord_" + orderId);
        if(StringUtils.isEmpty(s)!=true){
            rs.put("code",200);
            rs.put("code_url",s);
            return rs;
        }
        Order order = orderMapper.queryOrderById(orderId);
        BigDecimal orderTotalMoney = order.getOrderTotalMoney();
        //配置配置信息
        FeiConfig config = new FeiConfig();
        //得到微信支付对象
        WXPay wxpay = new WXPay(config);
        //设置请求参数
        Map<String, String> data = new HashMap<String, String>();
        //对订单信息描述
        data.put("body", "飞狐电商666-订单支付");
        //String payId = System.currentTimeMillis()+"";
        //设置订单号 （保证唯一 ）
        data.put("out_trade_no",orderId+"weixin1_order_");
        //设置币种
        data.put("fee_type", "CNY");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        Date d=new Date();
        String dateStr = sdf.format(new Date(d.getTime() + 120000000));
        //设置二维码的失效时间
        data.put("time_expire", dateStr);
        //设置订单金额   单位分
        /*BigDecimal amount = new BigDecimal(String.valueOf(orderTotalMoney));//单位元
        amount = amount.multiply(new BigDecimal(100));// 单位分
        amount = amount.setScale(0, RoundingMode.DOWN);//取整
        String payAmt = String.valueOf(amount);*/
        data.put("total_fee","1");
        data.put("notify_url", "http://www.example.com/wxpay/notify");
        //设置支付方式
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
        // 统一下单
        Map<String, String> resp = wxpay.unifiedOrder(data);
        if("SUCCESS".equalsIgnoreCase(resp.get("return_code"))&&"SUCCESS".equalsIgnoreCase(resp.get("result_code"))){
            rs.put("code",200);
            rs.put("code_url",resp.get("code_url"));

            order.setPayStatus(PayStatusEnum.PAY_STATUS_WITE.getStatus());
            orderMapper.updateOrder(order);

            RediaUse.set("order_cord_"+orderId,"code_url",30*60);
        }else{
            rs.put("code",500);
            rs.put("info",resp.get("return_msg"));
        }
        return rs;
    }

    @Override
    public Map<String, String> initCodeStatus(Integer orderId) throws Exception {
        Map map = new HashMap();
        FeiConfig config = new FeiConfig();
        WXPay wxpay = new WXPay(config);
        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no",orderId+"weixin1_order_");
        // 查询支付状态
        Map<String, String> resp = wxpay.orderQuery(data);
        if("SUCCESS".equalsIgnoreCase(resp.get("return_code"))&&"SUCCESS".equalsIgnoreCase(resp.get("result_code"))) {
            if ("SUCCESS".equalsIgnoreCase(resp.get("trade_state"))) {
                Order order = new Order();
                order.setOrderId(orderId);
                order.setPayStatus(PayStatusEnum.PAY_STATUS_SUCCESS.getStatus());
                orderMapper.updateOrder(order);

                map.put("code", 1);
                return map;
            } else if ("NOTPAY".equalsIgnoreCase(resp.get("trade_state"))) {
                map.put("code", 3);
                return map;
            } else if ("USERPAYING".equalsIgnoreCase(resp.get("trade_state"))) {
                map.put("code", 2);
                return map;
            }
        }
        map.put("code",0);
        return map;
    }

    @Override
    public List<Order> queryMyOrder() {
        User login_user = (User) request.getAttribute("login_user");
        Integer userId = login_user.getUserId();
        List<Order> list = orderMapper.queryMyOrder(userId);
        return list;
    }
}
