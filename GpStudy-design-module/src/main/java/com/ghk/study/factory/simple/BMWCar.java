package com.ghk.study.factory.simple;

public class BMWCar implements CarBaseFunction{
    @Override
    public void drive() {
        System.out.println("BMW run");
    }
}
