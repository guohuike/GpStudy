package com.ghk.study.strategy;

/**
 * @Title: WechatPay
 * @Package: com.ghk.study.strategy
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/7 14:29
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class WechatPay extends AbstractPayWay{
    private String payName = "WeChat_Pay";

    @Override
    public void pay(){
        super.pay(payName);
    }
}