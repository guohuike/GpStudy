package com.ghk.study.customspring.v2.servlet.aop.aspect;

import java.lang.reflect.Method;

/**
 * @Title: GpJoinPoint
 * @Package: com.ghk.study.customspring.v2.servlet.aop.aspect
 * @Description: 连接点
 * @author: huike.guo
 * @date: 2021/6/1 23:13
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public interface GpJoinPoint {

    Object getThis();

    Method getMethod();

    Object[] getArguments();

    void setUserAttribute(String key,Object value);

    Object getUserAttribute(String key);
}