package com.ghk.study.decorator;

/**
 * @Title: EggBatterCakeDecorator
 * @Package: com.ghk.study.decorator
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/7 17:57
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class EggBatterCakeDecorator extends BatterCakeDecorator{

    public EggBatterCakeDecorator(AbstractBatterCake abstractBatterCake) {
        super(abstractBatterCake);
    }

    @Override
    public String getBatterCakeName() {
        return super.getBatterCakeName()+"加1个鸡蛋";
    }

    @Override
    public int getBatterCakePrice() {
        return super.getBatterCakePrice()+1;
    }
}