package com.ghk.study.customspring.v2.servlet.aop;

import org.springframework.lang.Nullable;

/**
 * @Title: GpAopProxy
 * @Package: com.ghk.study.customspring.v2.servlet.aop
 * @Description: aop动态代理接口
 * @author: huike.guo
 * @date: 2021/5/23 18:12
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public interface GpAopProxy {

    Object getProxy();

    Object getProxy(@Nullable ClassLoader classLoader);
}
