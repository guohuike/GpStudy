package com.ghk.study.adapter.interfaceadapter;

import com.ghk.study.adapter.interfaceadapter.CurdeOil;

/**
 * @Title: OilAdapter
 * @Package: com.ghk.study.adapter.interfaceadapter
 * @Description: 接口适配
 * @author: huike.guo
 * @date: 2021/4/27 11:08
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class OilAdapter implements AllOil{
    private CurdeOil curdeOil;

    public OilAdapter(CurdeOil curdeOil) {
        this.curdeOil = curdeOil;
    }

    @Override
    public String do92Gasoline() {
        return "92汽"+curdeOil.doCurdeOil();
    }

    @Override
    public String do95Gasoline() {
        return "95汽"+curdeOil.doCurdeOil();
    }

    @Override
    public String do94Gasoline() {
        return "94汽"+curdeOil.doCurdeOil();
    }

    @Override
    public String do93Gasoline() {
        return"93汽"+curdeOil.doCurdeOil();
    }
}