package com.ghk.study.flyweight;

import java.util.concurrent.CountDownLatch;

/**
 * @Title: TestMain
 * @Package: com.ghk.study.flyweight
 * @Description: 享元模式注重对象的复用,不注重创建
 * @author: huike.guo
 * @date: 2021/4/29 17:39
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class TestMain {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(20);
        for(int i = 0; i< 20; i++){
            new Thread(()->{
                ShareCard car = ShareCardPool.getCar();
                if(null == car){
                    System.out.println("汽车池中已无汽车,请稍后重试!");
                    countDownLatch.countDown();
                    return;
                }
                try{
                    car.driver("BMW "+Thread.currentThread().getName()+"号租用");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    ShareCardPool.agentInto(car);
                }
                countDownLatch.countDown();
            }).start();
        }
        try{
            countDownLatch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("=======汽车全部用完!");


    }
}