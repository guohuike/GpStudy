package com.ghk.study.factory.simple;

public class SimpleCarFactory {
    public static CarBaseFunction getCar(Class<? extends CarBaseFunction> clazz){
        try{
            return clazz.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
