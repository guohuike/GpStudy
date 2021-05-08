package com.ghk.study.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: PayStrategyService
 * @Package: com.ghk.study.strategy
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/7 14:31
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class PayStrategyService {
    public static final String ALI_PAY = "Ali_Pay";
    public static final String WECHAT_PAY = "WeChat_Pay";
    public static final String UNION_PAY = "Union_Pay";
    private static final Map<String,PayWay> PAY_MAP = new HashMap<>();
    static{
        PAY_MAP.put(ALI_PAY,new AliPay());
        PAY_MAP.put(UNION_PAY,new UnionPay());
        PAY_MAP.put(WECHAT_PAY,new WechatPay());
    }

    public void doPay(String payKey){
        PayWay payWay = PAY_MAP.get(payKey);
        if(null != payWay){
            payWay.pay();
        }else{
            System.out.println(payKey+"无法支付!");
        }
    }

}