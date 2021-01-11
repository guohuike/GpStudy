package com.ghk.study.factory.abstracts;

public class BenZSuvCar extends SuvAbstractCarBaseFunction {
    public void drive() {
        super.drive("BenZSuvCar");
    }

    @Override
    public void crossdrive() {
        System.out.println("Benz suv Cross");
    }
}
