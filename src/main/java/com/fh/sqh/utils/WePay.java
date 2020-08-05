package com.fh.sqh.utils;

import com.github.wxpay.sdk.FeiConfig;
import com.github.wxpay.sdk.WXPay;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WePay {

    public static Map<String, String> createORCode(Integer orderId, BigDecimal totalPrice) throws Exception {
        //通过orderId查表

        // 微信支付  natvie   商户生成二维码
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
        BigDecimal amount = new BigDecimal(String.valueOf(totalPrice));//单位元
        amount = amount.multiply(new BigDecimal(100));// 单位分
        amount = amount.setScale(0, RoundingMode.DOWN);//取整
        String payAmt = String.valueOf(amount);
        data.put("total_fee",payAmt);
        data.put("notify_url", "http://www.example.com/wxpay/notify");
        //设置支付方式
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
        // 统一下单
        Map<String, String> resp = wxpay.unifiedOrder(data);
       return resp;
    }

    public static Map<String,String> selectCodeStatus(Integer orderId) throws Exception {
        FeiConfig config = new FeiConfig();
        WXPay wxpay = new WXPay(config);
        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no",orderId+"weixin1_order_");
        // 查询支付状态
        Map<String, String> resp = wxpay.orderQuery(data);
        return resp;
    }


}
