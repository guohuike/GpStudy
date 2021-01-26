package com.ghk.study.single.simple;

/**
 * @author guohuike
 * @Description 饿汉式单例
 * @date 2021/1/22
 */
public class HungrySimple {
    private HungrySimple(){}
    private static final HungrySimple HUNGRY_SIMPLE = new HungrySimple();

    public static HungrySimple GetTimpleTestSimple(){
        return HUNGRY_SIMPLE;
    }
}
