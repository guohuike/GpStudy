package com.ghk.study.customspring.v2.servlet.aop;

import com.ghk.study.customspring.v2.servlet.aop.support.GpAdvisedSupport;
import org.springframework.aop.framework.AopConfigException;

/**
 * @Title: GpAopProxyFactory
 * @Package: com.ghk.study.customspring.v2.servlet.aop
 * @Description: Aop代理类接口行为层
 * @author: huike.guo
 * @date: 2021/5/23 18:09
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public interface GpAopProxyFactory {

    GpAopProxy createAopProxy(GpAdvisedSupport config) throws AopConfigException;
}
