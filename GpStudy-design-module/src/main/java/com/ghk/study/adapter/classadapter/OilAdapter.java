package com.ghk.study.adapter.classadapter;

/**
 * @Title: HotelAdapter
 * @Package: com.ghk.study.adapter.classadapter
 * @Description: 用继承的方式来构建是适配器模式不符合最好知道原则
 * @author: huike.guo
 * @date: 2021/4/27 10:51
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class OilAdapter extends CurdeOil implements Gasoline {


    @Override
    public String doGasoline() {
        System.out.println("汽"+super.doCurdeOil());
        return "汽"+super.doCurdeOil();
    }
}