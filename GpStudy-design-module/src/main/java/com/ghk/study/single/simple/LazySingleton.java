package com.ghk.study.single.simple;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author guohuike
 * @Description 懒汉式单例
 * @date 2021/1/22
 */
public class LazySingleton {
    public LazySingleton(){}
    private static LazySingleton lazySingleton;

    private static final Object lock = new Object();

    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static LazySingleton getLazySingleton(){
        if(null == lazySingleton){
            reentrantLock.lock();
            try{
                if(null == lazySingleton){
                    lazySingleton = new LazySingleton();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }

        }
        return lazySingleton;
    }
}
