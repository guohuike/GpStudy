package com.ghk.study.customspring.v2.servlet.mvc;

import com.ghk.study.customspring.v2.servlet.annotation.GpRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: GpHandlerAdapter
 * @Package: com.ghk.study.customspring.v2.servlet.mvc
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/20 10:51
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpHandlerAdapter {
    public GpModelAndView handler(HttpServletRequest req,HttpServletResponse resp,GpHandlerMapping handlerMapping) {
        GpModelAndView gpModelAndView = null;
        Method method = handlerMapping.getMethod();
        Map<String, Integer> indexParamMap = new HashMap<>();
        //处理参数
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                if(annotation instanceof GpRequestParam){
                    if(!"".equals(((GpRequestParam) annotation).value())){
                        indexParamMap.put(((GpRequestParam) annotation).value().trim(),i);
                    }
                }
            }
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] paramObj = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            if(parameterTypes[i] == HttpServletRequest.class || parameterTypes[i] == HttpServletResponse.class){
                indexParamMap.put(parameterTypes[i].getName(),i);
            }
        }
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> paramEntity : parameterMap.entrySet()) {
            String key = paramEntity.getKey();
            if(!indexParamMap.containsKey(key))continue;
            Integer index = indexParamMap.get(key);
            String value = Arrays.toString(paramEntity.getValue()).replaceAll("\\[|\\]", "").replace("\\s", "");
            paramObj[index] = caseValueBaseType(value,parameterTypes[index]);
        }
        if(indexParamMap.containsKey(HttpServletRequest.class.getName())){
            Integer index = indexParamMap.get(HttpServletRequest.class.getName());
            paramObj[index] = req;
        }
        if(indexParamMap.containsKey(HttpServletResponse.class.getName())){
            Integer index = indexParamMap.get(HttpServletResponse.class.getName());
            paramObj[index] = resp;
        }
        try {
            Object invoke = method.invoke(handlerMapping.getController(), paramObj);
            Class<?> returnType = method.getReturnType();
            if(null != invoke && returnType == GpModelAndView.class){
                gpModelAndView = (GpModelAndView)invoke;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return gpModelAndView;
    }

    private Object caseValueBaseType(String value,Class<?>parameterType) {
        //转换数据类型
        if(String.class == parameterType){
            return value;
        }else if(Integer.class == parameterType){
            return Integer.valueOf(value);
        }else if(Float.class == parameterType){
            return Float.valueOf(value);
        }else{
            if(null != value){
                return value;
            }
        }
        return null;
    }
}