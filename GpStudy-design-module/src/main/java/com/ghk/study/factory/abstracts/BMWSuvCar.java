package com.ghk.study.factory.abstracts;

public class BMWSuvCar extends SuvAbstractCarBaseFunction {
    public void drive() {
        super.drive("BMWSuvCar");
    }

    @Override
    public void crossdrive() {
        System.out.println("BMW suv cross");
    }
}
