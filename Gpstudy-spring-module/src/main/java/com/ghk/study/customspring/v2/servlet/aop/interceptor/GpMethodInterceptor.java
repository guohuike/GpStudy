package com.ghk.study.customspring.v2.servlet.aop.interceptor;

/**
 * @Title: GpMethodInterceptor
 * @Package: com.ghk.study.customspring.v2.servlet.aop.interceptor
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/31 23:14
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public interface GpMethodInterceptor {
    Object invoke(GpMethodInvocation gpMethodInvocation)throws Throwable;
}
