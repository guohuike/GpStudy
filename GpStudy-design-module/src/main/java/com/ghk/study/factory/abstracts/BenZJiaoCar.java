package com.ghk.study.factory.abstracts;

public class BenZJiaoCar extends JiaoAbstractCarBaseFunction {
    public void drive() {
        super.drive("BenzJiaoCar");
    }

    @Override
    public void mannedDrive() {
        System.out.println("Benz jiao manned");
    }
}
