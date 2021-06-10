package com.ghk.study.customspring.v2.servlet.aop.aspect;

import com.ghk.study.customspring.v2.servlet.aop.interceptor.GpMethodInterceptor;
import com.ghk.study.customspring.v2.servlet.aop.interceptor.GpMethodInvocation;

import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 * @Title: GpMethodBeforeAdviceInterceptor
 * @Package: com.ghk.study.customspring.v2.servlet.aop.aspect
 * @Description: 方法执行之前拦截
 * @author: huike.guo
 * @date: 2021/6/1 11:27
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpMethodBeforeAdviceInterceptor extends GpAbstractAspectJAdvice implements GpMethodInterceptor {
    private GpJoinPoint gpJoinPoint;


    public GpMethodBeforeAdviceInterceptor(Object aspect, Method adviceMethod) {
        super(aspect,adviceMethod);
    }

    @Override
    public Object invoke(GpMethodInvocation mi) throws Throwable {
        this.gpJoinPoint = mi;
        this.before(mi.getMethod(),mi.getArguments(),mi.getTarget());
        return mi.proceed();
    }

    protected void before(Method method,Object[]arguments,Object targetObj) throws Throwable{
        invokeAdviceMethod(gpJoinPoint,null,null);
    }
}