package com.ghk.study.customspring.v2.servlet.beans;

/**
 * @Title: BeanWrapper
 * @Package: com.ghk.study.customspring.v2.servlet.beans
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/17 14:18
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpBeanWrapper {
    /**真正的实例化对象*/
    private Object wrapperedInstance;

    /**实例化对象的全类名*/
    private Class<?> wrapperedClass;

    public GpBeanWrapper(Object wrapperedInstance) {
        this.wrapperedInstance = wrapperedInstance;
        this.wrapperedClass = wrapperedInstance.getClass();
    }

    public Object getWrapperedInstance() {
        return wrapperedInstance;
    }

    public Class<?> getWrapperedClass() {
        return wrapperedClass;
    }

}