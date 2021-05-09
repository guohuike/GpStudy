package com.ghk.study.template.simple;

/**
 * @Title: BYDCar
 * @Package: com.ghk.study.template.simple
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/2 18:49
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class BYDCar extends AbstractCarRun{
    @Override
    protected void startUpCar(String carName) {
        System.out.println(carName+"需要踩离合start_up");
    }
}