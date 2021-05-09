package com.ghk.study.decorator;

/**
 * @Title: TestMain
 * @Package: com.ghk.study.decorator
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/7 18:00
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class TestMain {
    public static void main(String[] args) {
        AbstractBatterCake abstractBatterCake = new BaseBatterCake();
        System.out.println(abstractBatterCake.getBatterCakeName()+",价格:"+abstractBatterCake.getBatterCakePrice());

        abstractBatterCake = new EggBatterCakeDecorator(abstractBatterCake);
        System.out.println(abstractBatterCake.getBatterCakeName()+",价格:"+abstractBatterCake.getBatterCakePrice());

        abstractBatterCake = new EggBatterCakeDecorator(abstractBatterCake);
        System.out.println(abstractBatterCake.getBatterCakeName()+",价格:"+abstractBatterCake.getBatterCakePrice());

        abstractBatterCake  = new SausageBatterCakeDecorator(abstractBatterCake);
        System.out.println(abstractBatterCake.getBatterCakeName()+",价格:"+abstractBatterCake.getBatterCakePrice());
    }
}