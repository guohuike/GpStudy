package com.ghk.study.customspring.v2.servlet.beans.support;

import com.ghk.study.customspring.v2.servlet.beans.config.GpBeanDefinition;
import com.ghk.study.customspring.v2.servlet.beans.factory.GpBeanFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: DefaultListableBeanFactory
 * @Package: com.ghk.study.customspring.v2.servlet.support
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/14 11:33
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpDefaultListableBeanFactory implements GpBeanFactory {
    public Map<String,GpBeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public Object getBean(String var1) {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> var1) {
        return (T)getBean(var1.getName());
    }

    /***
     * @author: huike.guo
     * @description: 将对应的beanName和beanDefinition进行对应,以便后续实例化使用
     * @date: 2021/5/17 21:55
     * @param
     * @return
     */
    public void doRegisterBeanDefinition(List<GpBeanDefinition> gpBeanDefinitionList) throws RuntimeException{
        for (GpBeanDefinition gpBeanDefinition : gpBeanDefinitionList) {
            if(this.beanDefinitionMap.containsKey(gpBeanDefinition.getFactoryBeanName())){
                throw new RuntimeException(gpBeanDefinition.getFactoryBeanName()+" is already exist!!!");
            }
            this.beanDefinitionMap.put(gpBeanDefinition.getFactoryBeanName(),gpBeanDefinition);
        }
    }
}