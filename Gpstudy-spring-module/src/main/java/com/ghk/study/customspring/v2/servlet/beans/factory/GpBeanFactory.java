package com.ghk.study.customspring.v2.servlet.beans.factory;

/**
 * @Title: GpBeanFactory
 * @Package: com.ghk.study.customspring.v2.servlet.beans.factory
 * @Description: spring的顶层父类beanFactory
 * @author: huike.guo
 * @date: 2021/5/14 11:32
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public interface GpBeanFactory {

    Object getBean(String var1);

    <T> T getBean(Class<T> var1);

}
