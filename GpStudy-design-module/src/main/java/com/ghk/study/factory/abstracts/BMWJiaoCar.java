package com.ghk.study.factory.abstracts;


public class BMWJiaoCar extends JiaoAbstractCarBaseFunction {
    public void drive() {
        super.drive("BMWJiaoCar");
    }

    @Override
    public void mannedDrive() {
        System.out.println("BMW Jiao manned");
    }
}
