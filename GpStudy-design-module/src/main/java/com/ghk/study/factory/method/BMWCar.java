package com.ghk.study.factory.method;

import com.ghk.study.factory.method.CarBaseFunction;

public class BMWCar implements CarBaseFunction {
    @Override
    public void drive() {
        System.out.println("BMW run");
    }
}
