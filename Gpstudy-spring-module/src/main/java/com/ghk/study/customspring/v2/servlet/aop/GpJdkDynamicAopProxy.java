package com.ghk.study.customspring.v2.servlet.aop;

import com.ghk.study.customspring.v2.servlet.aop.interceptor.GpMethodInvocation;
import com.ghk.study.customspring.v2.servlet.aop.support.GpAdvisedSupport;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @Title: GpJdkDynamicAopProxy
 * @Package: com.ghk.study.customspring.v2.servlet.aop
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/23 18:14
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpJdkDynamicAopProxy implements GpAopProxy{
    private final GpAdvisedSupport advised;
    public GpJdkDynamicAopProxy(GpAdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        return getProxy(ClassUtils.getDefaultClassLoader());
    }

    /**
     * 获取代理类要实现的接口,除了Advised对象中配置的,还会加上SpringProxy, Advised(opaque=false)
     * 检查上面得到的接口中有没有定义 equals或者hashcode的接口
     * 调用Proxy.newProxyInstance创建代理对象
     */
    @Override
    public Object getProxy(@Nullable ClassLoader classLoader) {
        Class[] interfaces = advised.getTargetClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                List<Object> chain = advised.getInterceptorsAndDynamicInterceptionAdvice(method, advised.getTargetClass());
                GpMethodInvocation gpMethodInvocation = new GpMethodInvocation(proxy,advised.getTarge(),method,args,advised.getTargetClass(),chain);
                return gpMethodInvocation.proceed();
                /*Map<String, GpAdvice> gpAdviceMap = gpAdvisedSupport.getAdvices(method,gpAdvisedSupport.getTargetClass());
                GpAdvice before = gpAdviceMap.get("before");
                GpAdvice after = gpAdviceMap.get("after");
                Method beforeAspectMethod = before.getAspectMethod();
                beforeAspectMethod.invoke(before.getAspectObj());
                Object result = null;
                try{
                    result = method.invoke(gpAdvisedSupport.getTarge(), args);
                }catch (Exception e){
                    e.printStackTrace();
                    GpAdvice afterThrows = gpAdviceMap.get("afterThrows");
                    afterThrows.getAspectMethod().invoke(afterThrows.getAspectObj());
                }

                after.getAspectMethod().invoke(after.getAspectObj());
                return result;
            }*/
        }});
    }
}