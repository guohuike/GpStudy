package com.ghk.study.customspring.v2.servlet.aop.aspect;

/**
 * @Title: LogAspect
 * @Package: com.ghk.study.customspring.v2.servlet.aop.aspect
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/26 22:24
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class LogAspect {
    public void before(GpJoinPoint gpJoinPoint){
        System.out.println("before doing!");
        gpJoinPoint.setUserAttribute("start_time",System.currentTimeMillis());
    }

    public void after(GpJoinPoint gpJoinPoint){
        Long start_time = (Long)gpJoinPoint.getUserAttribute("start_time");
        Long last = System.currentTimeMillis() - start_time;
        System.out.println("after doing,耗时:"+last);
    }

    public void afterThrows(GpJoinPoint gpJoinPoint,Throwable ex){
        System.out.println("afterThrows:"+ex.getMessage());
    }
}