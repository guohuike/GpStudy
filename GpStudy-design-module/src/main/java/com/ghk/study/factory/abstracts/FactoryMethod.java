package com.ghk.study.factory.abstracts;

public interface FactoryMethod {
    SuvAbstractCarBaseFunction createSuvCar(Class<? extends CarBaseFunction> clazz);

    JiaoAbstractCarBaseFunction createSJiaoCar(Class<? extends CarBaseFunction> clazz);
}
