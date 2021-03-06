package com.ghk.study.decorator;

/**
 * @Title: BatterCakeDecorator
 * @Package: com.ghk.study.decorator
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/7 17:56
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public abstract class BatterCakeDecorator extends AbstractBatterCake{
    private AbstractBatterCake abstractBatterCake;

    public BatterCakeDecorator(AbstractBatterCake baseBatterCake) {
        this.abstractBatterCake = baseBatterCake;
    }

    @Override
    public String getBatterCakeName() {
        return abstractBatterCake.getBatterCakeName();
    }

    @Override
    public int getBatterCakePrice() {
        return abstractBatterCake.getBatterCakePrice();
    }


}