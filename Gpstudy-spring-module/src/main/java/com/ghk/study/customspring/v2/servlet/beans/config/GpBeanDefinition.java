package com.ghk.study.customspring.v2.servlet.beans.config;

/**
 * @Title: GpBeanDefinition
 * @Package: com.ghk.study.customspring.v2.servlet.beans.config
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/17 11:19
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpBeanDefinition {
    /**IOC beanName*/
    private String factoryBeanName;
    /**bean的全类名*/
    private String beanClassName;

    public boolean isLazyInit(){
        return false;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }
}