package com.ghk.study.factory.method;

public class CarFactory {
    public static FactoryMethod getFactory(Class<? extends FactoryMethod> clazz){
        try{
            return clazz.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
