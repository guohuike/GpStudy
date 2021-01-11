package com.ghk.study.factory.method;

public class BMWCarFactory implements FactoryMethod{
    @Override
    public CarBaseFunction createCar() {
        return new BMWCar();
    }
}
