package com.ghk.study.customspring.v2.servlet.aop.interceptor;

import com.ghk.study.customspring.v2.servlet.aop.aspect.GpJoinPoint;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: GpMethodInvocation
 * @Package: com.ghk.study.customspring.v2.servlet.aop.interceptor
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/31 23:15
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpMethodInvocation implements GpJoinPoint {

    protected  final Object proxy;

    protected final Object target;

    protected final Method method;

    protected final Object[] arguments;

    protected final Class<?> targetClass;

    protected final List<?> interceptorsAndDynamicMethodMatchers;

    private int currentInterceptorIndex = -1;

    private Map<String,Object> userAttribute = new HashMap<>();



    public GpMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, Class targetClass, List<Object> interceptorsAndDynamicMethodMatchers) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.targetClass = targetClass;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }

    public Object proceed() throws Throwable{
        Object invoke;
        //如果Interceptor执行完了，则执行joinPoint
        if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1) {
            return this.method.invoke(target,arguments);
        }
        Object interceptorOrInterceptionAdvice =
                this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);
        if (interceptorOrInterceptionAdvice instanceof GpMethodInterceptor) {
            GpMethodInterceptor methodInterceptor = (GpMethodInterceptor)interceptorOrInterceptionAdvice;
            invoke = methodInterceptor.invoke(this);
            return invoke;
        }else{
            return proceed();
        }
    }

    public Object getProxy() {
        return proxy;
    }

    public Object getTarget() {
        return target;
    }

    @Override
    public Object getThis() {
        return null;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public void setUserAttribute(String key, Object value) {
        userAttribute.put(key,value);
    }

    @Override
    public Object getUserAttribute(String key) {
        return userAttribute.get(key);
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public List<?> getInterceptorsAndDynamicMethodMatchers() {
        return interceptorsAndDynamicMethodMatchers;
    }

    public int getCurrentInterceptorIndex() {
        return currentInterceptorIndex;
    }

    public void setCurrentInterceptorIndex(int currentInterceptorIndex) {
        this.currentInterceptorIndex = currentInterceptorIndex;
    }
}