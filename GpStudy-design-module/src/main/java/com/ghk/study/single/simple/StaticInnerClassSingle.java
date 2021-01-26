package com.ghk.study.single.simple;

/**
 * @author guohuike
 * @Description 静态内部类单例
 * @date 2021/1/24
 */
public class StaticInnerClassSingle {
    private StaticInnerClassSingle(){
        System.out.println("实例化了外部类");
    }

    public static class StaticInner{
        private static StaticInnerClassSingle staticInnerClassSingle;
        static{
            System.out.println("执行了内部类的静态代码块!");
        }
        public static StaticInnerClassSingle getStaticInnerClass(){
            if(null == StaticInner.staticInnerClassSingle){
                staticInnerClassSingle = new StaticInnerClassSingle();
            }
            return staticInnerClassSingle;
        }
    }

    public static StaticInnerClassSingle getStaticInnerClassSingle(){
        return StaticInnerClassSingle.StaticInner.getStaticInnerClass();
    }
}
