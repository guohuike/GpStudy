package com.ghk.study.single;

import com.ghk.study.single.simple.HungrySimple;
import com.ghk.study.single.simple.LazySingleton;
import com.ghk.study.single.simple.StaticInnerClassSingle;

import java.util.concurrent.CountDownLatch;

/**
 * @author guohuike
 * @Description TODO
 * @date 2021/1/22
 */
public class TestMain {
    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for(int i = 0;i<20;i++){
            new Thread() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                        StaticInnerClassSingle  staticInnerClassSingle = getStaticInnerClassSingle();
                        System.out.println(staticInnerClassSingle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }
        Thread.sleep(2000);
        countDownLatch.countDown();

    }

    /**
     * 饿汉式单例
     * @return HungrySimple
     */
    private static HungrySimple getSingle(){
        HungrySimple hungrySimple = null;
        hungrySimple = HungrySimple.GetTimpleTestSimple();
        return hungrySimple;
    }

    /**
     * 懒汉式单例
     * @return LazySingleton
     */
    private static LazySingleton getLazySingle(){
        LazySingleton lazySingleton = null;
        lazySingleton = LazySingleton.getLazySingleton();
        return lazySingleton;
    }

    /**
     * 静态内部类
     * @return StaticInnerClassSingle
     */
    private static StaticInnerClassSingle getStaticInnerClassSingle(){
        StaticInnerClassSingle staticInnerClassSingle = null;
        staticInnerClassSingle = StaticInnerClassSingle.getStaticInnerClassSingle();
        return staticInnerClassSingle;
    }
}
