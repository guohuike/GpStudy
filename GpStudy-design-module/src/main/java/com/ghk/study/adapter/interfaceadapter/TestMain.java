package com.ghk.study.adapter.interfaceadapter;

/**
 * @Title: TestMain
 * @Package: com.ghk.study.adapter.interfaceadapter
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/4/27 11:10
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class TestMain {
    public static void main(String[] args) {
        AllOil allOil = new OilAdapter(new CurdeOil());
        System.out.println(allOil.do92Gasoline());
        System.out.println(allOil.do95Gasoline());
        System.out.println(allOil.do94Gasoline());
        System.out.println(allOil.do93Gasoline());
    }
}