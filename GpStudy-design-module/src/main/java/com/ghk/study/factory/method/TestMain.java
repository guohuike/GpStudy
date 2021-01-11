package com.ghk.study.factory.method;

/**
 * 工厂方法 针对的主要是产品工厂的工厂进行细致的划分  细化到具体的工厂
 */
public class TestMain {
    public static void main(String[] args) {
        FactoryMethod benzfactory = CarFactory.getFactory(BenzCarFactory.class);
        CarBaseFunction benzcar = benzfactory.createCar();
        benzcar.drive();
        FactoryMethod bMWfactory = CarFactory.getFactory(BMWCarFactory.class);
        CarBaseFunction bMWcar = bMWfactory.createCar();
        bMWcar.drive();
    }
}
