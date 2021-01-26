package com.ghk.study.factory.simple;

/**
 * 简单工厂 创建产品的工厂,无细致划分产品以及产品工厂。
 */
public class TestMain {
    public static void main(String[] args) {
        /*CarBaseFunction car = SimpleCarFactory.getCar(BenZCar.class);
        car.drive();
        CarBaseFunction car2 = SimpleCarFactory.getCar(BMWCar.class);
        car2.drive();*/

        String match = "^[0-1]{1}$";
        System.out.println("0".matches(match));
    }
}
