package com.ghk.study.customspring.v2.servlet.aop;

import com.ghk.study.customspring.v2.servlet.aop.support.GpAdvisedSupport;
import org.springframework.aop.framework.AopConfigException;

/**
 * @Title: GpDefaultAopProxyFactory
 * @Package: com.ghk.study.customspring.v2.servlet.aop
 * @Description: 得到aop代理类的工厂
 * @author: huike.guo
 * @date: 2021/5/23 18:08
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpDefaultAopProxyFactory implements GpAopProxyFactory{

    @Override
    public GpAopProxy createAopProxy(GpAdvisedSupport config) throws AopConfigException {
        Class targetClass = config.getTargetClass();
        if(targetClass.getInterfaces().length > 0){
            return new GpJdkDynamicAopProxy(config);
        }
        return null;//new GpCglibAopProxy(config);
    }
}
