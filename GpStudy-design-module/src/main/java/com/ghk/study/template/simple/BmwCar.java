package com.ghk.study.template.simple;

/**
 * @Title: BmwCar
 * @Package: com.ghk.study.template.simple
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/2 18:47
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class BmwCar extends AbstractCarRun{
    @Override
    protected void startUpCar(String carName) {
        System.out.println(carName+"无需踩离合start_up");
    }
}