package com.ghk.study.factory.method;

public class BenzCarFactory implements FactoryMethod{
    @Override
    public CarBaseFunction createCar() {
        return new BenZCar();
    }
}
