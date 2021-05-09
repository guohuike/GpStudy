package com.ghk.study.template.simple;

import com.ghk.study.template.simple.BmwCar;

/**
 * @Title: TestMain
 * @Package: com.ghk.study.template.simple
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/2 18:51
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class TestMain {
    public static void main(String[] args) {
        BYDCar bydCar = new BYDCar();
        bydCar.driverCarProcess("BYD");
        BmwCar bmwCar = new BmwCar();
        bmwCar.driverCarProcess("BMW");
    }
}