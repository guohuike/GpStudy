package com.ghk.study.factory.abstracts;

public abstract class JiaoAbstractCarBaseFunction implements CarBaseFunction{
    @Override
    public void drive(String carName) {
        System.out.println(carName+"drive");
    }

    public abstract void mannedDrive();
}
