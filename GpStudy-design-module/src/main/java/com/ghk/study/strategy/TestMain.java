package com.ghk.study.strategy;

/**
 * @Title: TestMain
 * @Package: com.ghk.study.strategy
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/7 14:36
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class TestMain {
    public static void main(String[] args) {
        PayStrategyService payStrategyService = new PayStrategyService();
        payStrategyService.doPay(PayStrategyService.ALI_PAY);
        payStrategyService.doPay("未知支付");
    }
}