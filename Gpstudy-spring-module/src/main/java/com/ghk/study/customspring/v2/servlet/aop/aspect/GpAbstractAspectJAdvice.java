package com.ghk.study.customspring.v2.servlet.aop.aspect;

import java.lang.reflect.Method;

/**
 * @Title: AbstractAspectAdvice
 * @Package: com.ghk.study.customspring.v2.servlet.aop.aspect
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/6/1 23:34
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public abstract class GpAbstractAspectJAdvice {

    protected Object aspect;

    protected Method adiviceMethod;

    protected String throwName;

    public GpAbstractAspectJAdvice(Object aspect, Method adiviceMethod) {
        this.aspect = aspect;
        this.adiviceMethod = adiviceMethod;
    }

    protected Object invokeAdviceMethod(GpJoinPoint gpJoinPoint, Object returnVal, Throwable ex) throws Throwable{
        Class<?>[] parameterTypes = this.adiviceMethod.getParameterTypes();
        if(null == parameterTypes || parameterTypes.length == 0){
            return this.adiviceMethod.invoke(aspect);
        }else{
            Object[] args = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                if(parameterTypes[i] == GpJoinPoint.class){
                    args[i] = gpJoinPoint;
                }else if(parameterTypes[i] == Throwable.class ){
                    args[i] = ex;
                }else if(parameterTypes[i] == Object.class){
                    args[i] = returnVal;
                }
            }
            return this.adiviceMethod.invoke(aspect,args);
        }
    }
}