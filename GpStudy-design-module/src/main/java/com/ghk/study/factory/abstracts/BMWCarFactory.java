package com.ghk.study.factory.abstracts;

public class BMWCarFactory implements FactoryMethod{

    @Override
    public SuvAbstractCarBaseFunction createSuvCar(Class<? extends CarBaseFunction> clazz) {
        try{
            return (SuvAbstractCarBaseFunction)clazz.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JiaoAbstractCarBaseFunction createSJiaoCar(Class<? extends CarBaseFunction> clazz) {
        try{
            return (JiaoAbstractCarBaseFunction)clazz.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
