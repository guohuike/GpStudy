package com.ghk.study.template.simple;

/**
 * @Title: AbstractCarRun
 * @Package: com.ghk.study.template.simple
 * @Description: 模板方法模式
 * @author: huike.guo
 * @date: 2021/5/2 18:42
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public abstract class AbstractCarRun {
    protected void openCarDoor(String carName){
        System.out.println(carName+"打开车门");
    }

    protected abstract void startUpCar(String carName);

    protected void driverCar(String carName){
        System.out.println(carName+"踩油门");
    }

    protected void closeCarDoor(String carName){
        System.out.println(carName+"关上车门");
    }


    public void driverCarProcess(String carName){
        openCarDoor(carName);
        startUpCar(carName);
        driverCar(carName);
        closeCarDoor(carName);
    }


}