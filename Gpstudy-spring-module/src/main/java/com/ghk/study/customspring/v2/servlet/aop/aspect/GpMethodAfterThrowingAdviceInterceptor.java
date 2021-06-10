package com.ghk.study.customspring.v2.servlet.aop.aspect;

import com.ghk.study.customspring.v2.servlet.aop.interceptor.GpMethodInterceptor;
import com.ghk.study.customspring.v2.servlet.aop.interceptor.GpMethodInvocation;

import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 * @Title: GpMethodAfterThrowingInterceptor
 * @Package: com.ghk.study.customspring.v2.servlet.aop.aspect
 * @Description: 抛异常拦截
 * @author: huike.guo
 * @date: 2021/6/1 11:30
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpMethodAfterThrowingAdviceInterceptor extends GpAbstractAspectJAdvice implements GpMethodInterceptor {
    private GpJoinPoint gpJoinPoint;
    private String throwName;
    public GpMethodAfterThrowingAdviceInterceptor(Object aspect, Method adviceMethod) {
        super(aspect,adviceMethod);
    }

    @Override
    public Object invoke(GpMethodInvocation mi) throws Throwable {
        try {
            return mi.proceed();
        }catch (Throwable ex) {
            afterThrowing(mi, ex);
            throw ex;
        }
    }
    private void afterThrowing(GpJoinPoint gpJoinPoint,Throwable ex) throws Throwable{
        invokeAdviceMethod(gpJoinPoint,null,ex);
    }

    public void setThrowName(String aspectAfterThrowName) {
        this.throwName = aspectAfterThrowName;
    }
}