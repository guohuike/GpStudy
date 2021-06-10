package com.ghk.study.customspring.v2.servlet.core;

import com.ghk.study.customspring.v2.servlet.annotation.GpAutowired;
import com.ghk.study.customspring.v2.servlet.annotation.GpController;
import com.ghk.study.customspring.v2.servlet.annotation.GpService;
import com.ghk.study.customspring.v2.servlet.aop.GpAopProxy;
import com.ghk.study.customspring.v2.servlet.aop.GpDefaultAopProxyFactory;
import com.ghk.study.customspring.v2.servlet.aop.config.GpAopConfig;
import com.ghk.study.customspring.v2.servlet.aop.support.GpAdvisedSupport;
import com.ghk.study.customspring.v2.servlet.beans.GpBeanWrapper;
import com.ghk.study.customspring.v2.servlet.beans.config.GpBeanDefinition;
import com.ghk.study.customspring.v2.servlet.beans.factory.GpBeanFactory;
import com.ghk.study.customspring.v2.servlet.beans.support.GpBeanDefinitionReader;
import com.ghk.study.customspring.v2.servlet.beans.support.GpDefaultListableBeanFactory;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Title: GpApplicationContext
 * @Package: com.ghk.study.customspring.v2.servlet.core
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/14 11:34
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpApplicationContext implements GpBeanFactory {
    public GpDefaultListableBeanFactory gpDefaultListableBeanFactory = new GpDefaultListableBeanFactory();

    public GpBeanDefinitionReader gpBeanDefinitionReader;

    //相互依赖标识
    private Set<String> singletonCurrentlyInCreation = new HashSet<>();

    //一级缓存 保存成熟的bean(完成互相依赖的bean)
    private Map<String,Object> singletonObjects = new HashMap<>();

    //二级缓存 保存早期的bean
    private Map<String,Object> earlySingletonObjects = new HashMap<>();

    //三级缓存 终极缓存
    public Map<String, GpBeanWrapper> factoryBeanInstanceCache = new HashMap<>();

    //原生对象缓存
    public Map<String,Object> factoryBeanObjectCache = new HashMap<>();

    //创建Aop代理类工厂
    private GpDefaultAopProxyFactory defaultAopProxyFactory = new GpDefaultAopProxyFactory();

    public GpApplicationContext(String...configrationPath) {
        //加载配置文件
        gpBeanDefinitionReader = new GpBeanDefinitionReader(configrationPath[0]);
        //解析配置文件 将解析信息封装成beanDefination对象
        List<GpBeanDefinition> gpBeanDefinitions = gpBeanDefinitionReader.loadBeanDefinitions();
        //将配置信息进行实例化  并缓存
        gpDefaultListableBeanFactory.doRegisterBeanDefinition(gpBeanDefinitions);
        //加载非延时bean
        doLoadBeanInstance();
    }
    /***
     * @author: huike.guo
     * @description: 初始化不是懒加载的bean
     * @date: 2021/5/17 22:00
     * @param []
     * @return void
     */
    private void doLoadBeanInstance() {
        Set<Map.Entry<String, GpBeanDefinition>> entries = gpDefaultListableBeanFactory.beanDefinitionMap.entrySet();
        for (Map.Entry<String, GpBeanDefinition> entry : entries) {
            if(entry.getValue().isLazyInit()) continue;
            getBean(entry.getKey());
        }
    }

    @Override
    public Object getBean(String beanName) {
        //1.得到beanDefinition
        GpBeanDefinition gpBeanDefinition = this.gpDefaultListableBeanFactory.beanDefinitionMap.get(beanName);
        //得到单例的bean
        Object obj = getSingleton(beanName,gpBeanDefinition);
        if(null != obj) return  obj;
        //添加循环依赖标识 标识当前bean正在创建中
        if(!singletonCurrentlyInCreation.contains(beanName)){
            singletonCurrentlyInCreation.add(beanName);
        }
        //2.根据beanDefinition进行实例化
        Object objInstance = instantiateBean(beanName,gpBeanDefinition);
        //放入一级缓存
        singletonObjects.put(beanName,objInstance);
        //3.将实例化的对象封装成beanWrapper
        GpBeanWrapper beanWrapper = new GpBeanWrapper(objInstance);
        //4.将beanwrapper进行依赖注入
        populateBean(beanName,gpBeanDefinition,beanWrapper);
        //5.将beanWrapper保存至IOC
        factoryBeanInstanceCache.put(beanName, beanWrapper);
        return beanWrapper.getWrapperedInstance();
    }

    private GpAdvisedSupport instantiateAopConfig(GpBeanDefinition gpBeanDefinition) {
        GpAopConfig gpAopConfig = new GpAopConfig();
        gpAopConfig.setPointCut(gpBeanDefinitionReader.getConfig().getProperty("point.cut"));
        gpAopConfig.setAspectClass(gpBeanDefinitionReader.getConfig().getProperty("aspect.class"));
        gpAopConfig.setAspectBefore(gpBeanDefinitionReader.getConfig().getProperty("aspect.before"));
        gpAopConfig.setAspectAfter(gpBeanDefinitionReader.getConfig().getProperty("aspect.after"));
        gpAopConfig.setAspectAfterThrow(gpBeanDefinitionReader.getConfig().getProperty("aspect.after.throw"));
        gpAopConfig.setAspectAfterThrowName(gpBeanDefinitionReader.getConfig().getProperty("aspect.after.throw.name"));
        return new GpAdvisedSupport(gpAopConfig);
    }

    private Object getSingleton(String beanName, GpBeanDefinition gpBeanDefinition) {
        //一级缓存
        Object obj = singletonObjects.get(beanName);
        if(obj == null && singletonCurrentlyInCreation.contains(beanName)){
            //二级缓存
            obj = earlySingletonObjects.get(beanName);
            if(null == obj){
                //三级缓存
                obj = factoryBeanObjectCache.get(beanName);
                if(null == obj){
                    //实例化bean 并放入三级缓存
                    obj = instantiateBean(beanName, gpBeanDefinition);
                }
                //放入二级缓存
                earlySingletonObjects.put(beanName,obj);
            }
        }
        return obj;
    }

    /***
     * @author: huike.guo
     * @description: 进行依赖注入操作
     * @date: 2021/5/17 21:58
     * @param [beanName, gpBeanDefinition, beanWrapper]
     * @return void
     */
    private void populateBean(String beanName, GpBeanDefinition gpBeanDefinition, GpBeanWrapper beanWrapper) {
        Class<?> wrapperedClass = beanWrapper.getWrapperedClass();
        if(!(wrapperedClass.isAnnotationPresent(GpController.class) || wrapperedClass.isAnnotationPresent(GpService.class))) return;
        Object wrapperedInstance = beanWrapper.getWrapperedInstance();
        Field[] declaredFields = wrapperedClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if(declaredField.isAnnotationPresent(GpAutowired.class)){
                String autowireBeanName = declaredField.getName();
                GpAutowired annotation = declaredField.getAnnotation(GpAutowired.class);
                if(!"".equals(annotation.value().trim())){
                    autowireBeanName = annotation.value().trim();
                }
                //if(!factoryBeanInstanceCache.containsKey(autowireBeanName)) continue;
                try {
                    //declaredField.set(wrapperedInstance,factoryBeanInstanceCache.get(autowireBeanName).getWrapperedInstance());
                    declaredField.set(wrapperedInstance,getBean(autowireBeanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /***
     * @author: huike.guo
     * @description: 通过反射实例化bean
     * @date: 2021/5/17 21:59
     * @param beanName, gpBeanDefinition
     * @return java.lang.Object
     */
    private Object instantiateBean(String beanName, GpBeanDefinition gpBeanDefinition) {
        Object instance = null;
        try{
            Class<?> aClass = Class.forName(gpBeanDefinition.getBeanClassName());
            instance = aClass.newInstance();
            //进行判断是否需要AOP动态代理
            GpAdvisedSupport advisedSupport = instantiateAopConfig(gpBeanDefinition);
            advisedSupport.setTarge(instance);
            advisedSupport.setTargetClass(instance.getClass());
            //判断是否符合切面规则
            if(advisedSupport.pointCutMatcher()){
                //得到创建AOP工厂的代理类
                GpAopProxy aopProxy = defaultAopProxyFactory.createAopProxy(advisedSupport);
                //创建代理类
                instance = aopProxy.getProxy();
            }
            //如果是代理对象 就会触发Aop
            factoryBeanObjectCache.put(beanName,instance);
            factoryBeanObjectCache.put(aClass.getName(),instance);
        }catch (Exception e){
            e.printStackTrace();
        }
        return instance;
    }

    @Override
    public <T> T getBean(Class<T> var1) {
        return (T)getBean(var1.getName());
    }

    public int getBeanDefinitionCount() {
        return factoryBeanInstanceCache.keySet().size();
    }

    public String[] getBeanDefinitionNames() {
        return factoryBeanInstanceCache.keySet().toArray(new String[0]);
    }

    public Properties getConfig() {
        return gpBeanDefinitionReader.getConfig();
    }
}