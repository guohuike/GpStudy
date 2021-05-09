package com.ghk.study.adapter.objectadapter;

/**
 * @Title: HotelAdapter
 * @Package: com.ghk.study.adapter.classadapter
 * @Description: 用组合的方式来最适配解决了不符合最少知道原则
 * @author: huike.guo
 * @date: 2021/4/27 10:51
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class OilAdapter implements Gasoline {
    private CurdeOil curdeOil;

    public OilAdapter(CurdeOil curdeOil) {
        this.curdeOil = curdeOil;
    }

    @Override
    public String doGasoline() {
        return "汽"+curdeOil.doCurdeOil();
    }
}