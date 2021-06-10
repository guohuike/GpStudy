package com.ghk.study.customspring.v2.servlet.aop.support;

import com.ghk.study.customspring.v2.servlet.aop.aspect.GpAdvice;
import com.ghk.study.customspring.v2.servlet.aop.aspect.GpMethodAfterReturningAdviceInterceptor;
import com.ghk.study.customspring.v2.servlet.aop.aspect.GpMethodAfterThrowingAdviceInterceptor;
import com.ghk.study.customspring.v2.servlet.aop.aspect.GpMethodBeforeAdviceInterceptor;
import com.ghk.study.customspring.v2.servlet.aop.config.GpAopConfig;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: GpAdvisedSupport
 * @Package: com.ghk.study.customspring.v2.servlet.aop.support
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/23 17:58
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpAdvisedSupport {
    private GpAopConfig aopConfig;

    private Class targetClass;

    private Object targe;
    
    private Pattern pointCutClassPatten;

    //private Map<Method,Map<String,GpAdvice>> methodCache = new HashMap<>();
    private Map<Method, List<Object>> methodCache = new HashMap<>();

    public GpAdvisedSupport(GpAopConfig aopConfig) {
        this.aopConfig = aopConfig;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    public Object getTarge() {
        return targe;
    }

    public void setTarge(Object targe) {
        this.targe = targe;
    }

    public boolean pointCutMatcher() {
        parse();
        return pointCutClassPatten.matcher(this.targetClass.getName()).matches();
    }

    private void parse() {
        //权限修饰符 返回值 全类名 方法名 形参全类名
        //public .* com.ghk.study.customspring.v1.servlet.service.*Service..*(.*)
        String pointCut = this.aopConfig.getPointCut();
        String pointCutRegex = pointCut.replaceAll("\\.", "\\\\.")
                .replaceAll("\\\\.\\*", ".*")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\)", "\\\\)");
        //截取掉括号
        int i = pointCutRegex.lastIndexOf("\\(");
        pointCutRegex = pointCutRegex.substring(0,(pointCutRegex.lastIndexOf("\\(")-4));
        //截取掉public .*
        pointCutRegex = pointCutRegex.substring(pointCutRegex.lastIndexOf(" ")+1);
        pointCutClassPatten = Pattern.compile(pointCutRegex);
        String aspectClass = this.aopConfig.getAspectClass();
        Map<String,Method> aspectMethod = new HashMap<>();
        try{
            //切面类
            Class<?> aspectClazz = Class.forName(aspectClass);
            for (Method method : aspectClazz.getMethods()) {
                aspectMethod.put(method.getName(),method);
            }
            for (Method method : this.targetClass.getMethods()) {
                String name = method.getName();
                if(name.contains("throws")){
                    name = name.substring(0,name.lastIndexOf("throws")).trim();
                }
                Matcher matcher = pointCutClassPatten.matcher(targetClass.getName());
                if(matcher.matches()){
                    //Map<String, GpAdvice> advices = new HashMap<>();
                    List<Object> advices =  new LinkedList<>();
                    if(null != this.aopConfig.getAspectBefore() && !"".equals(this.aopConfig.getAspectBefore())){
                        //advices.put("before",new GpAdvice(aspectClazz.newInstance(),aspectMethod.get(this.aopConfig.getAspectBefore())));
                        advices.add(new GpMethodBeforeAdviceInterceptor(aspectClazz.newInstance(),aspectMethod.get(this.aopConfig.getAspectBefore())));
                    }
                    if(null != this.aopConfig.getAspectAfter() && !"".equals(this.aopConfig.getAspectAfter())){
                        //advices.put("after",new GpAdvice(aspectClazz.newInstance(),aspectMethod.get(this.aopConfig.getAspectAfter())));
                        advices.add(new GpMethodAfterReturningAdviceInterceptor(aspectClazz.newInstance(),aspectMethod.get(this.aopConfig.getAspectAfter())));
                    }
                    if(null != this.aopConfig.getAspectAfterThrowName() && !"".equals(this.aopConfig.getAspectAfterThrowName())){
                        //GpAdvice gpAdvice = new GpAdvice(aspectClazz.newInstance(), aspectMethod.get(this.aopConfig.getAspectAfterThrow()));
                        //gpAdvice.setThrowName(this.aopConfig.getAspectAfterThrowName());
                        //advices.put("afterThrows",gpAdvice);
                        GpMethodAfterThrowingAdviceInterceptor gpMethodAfterThrowingAdviceInterceptor = new GpMethodAfterThrowingAdviceInterceptor(aspectClazz.newInstance(), aspectMethod.get(this.aopConfig.getAspectAfter()));
                        gpMethodAfterThrowingAdviceInterceptor.setThrowName(this.aopConfig.getAspectAfterThrowName());
                        advices.add(gpMethodAfterThrowingAdviceInterceptor);
                    }
                    methodCache.put(method,advices);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Map<Method,List<Object>> getMethodCache() {
        return methodCache;
    }

    public void setMethodCache(Map<Method, List<Object>> methodCache) {
        this.methodCache = methodCache;
    }

    /*public Map<String, GpAdvice> getAdvices(Method method, Class targetClass) throws Exception{
        Map<String, GpAdvice> stringGpAdviceMap = methodCache.get(method);
        if(null == stringGpAdviceMap){
            Method method1 = targetClass.getMethod(method.getName(), method.getParameterTypes());
            stringGpAdviceMap = methodCache.get(method1);
            methodCache.put(method1,stringGpAdviceMap);
        }
        return stringGpAdviceMap;
    }*/

    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class targetClass) {
        List<Object> gpMethodInterceptors = methodCache.get(method);
        if(null == gpMethodInterceptors){
            Method method1 = null;
            try {
                method1 = targetClass.getMethod(method.getName(), method.getParameterTypes());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            gpMethodInterceptors = methodCache.get(method1);
            methodCache.put(method1,gpMethodInterceptors);
        }
        return gpMethodInterceptors;

    }
}