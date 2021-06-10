package com.ghk.study.customspring.v2.servlet.aop.aspect;

import java.lang.reflect.Method;

/**
 * @Title: GpAdvice
 * @Package: com.ghk.study.customspring.v2.servlet.aop.aspect
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/26 22:57
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpAdvice {
    private Object aspectObj;

    private Method aspectMethod;

    private String throwName;

    public GpAdvice(Object aspectObj, Method aspectMethod) {
        this.aspectObj = aspectObj;
        this.aspectMethod = aspectMethod;
    }

    public Object getAspectObj() {
        return aspectObj;
    }

    public void setAspectObj(Object aspectObj) {
        this.aspectObj = aspectObj;
    }

    public Method getAspectMethod() {
        return aspectMethod;
    }

    public void setAspectMethod(Method aspectMethod) {
        this.aspectMethod = aspectMethod;
    }

    public String getThrowName() {
        return throwName;
    }

    public void setThrowName(String throwName) {
        this.throwName = throwName;
    }
}