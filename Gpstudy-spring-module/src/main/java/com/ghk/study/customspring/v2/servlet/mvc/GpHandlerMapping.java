package com.ghk.study.customspring.v2.servlet.mvc;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @Title: GpHandlerMapper
 * @Package: com.ghk.study.customspring.v2.servlet.mvc
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/20 10:45
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpHandlerMapping {
    private Pattern pattern;

    private Method method;

    private Object controller;

    public GpHandlerMapping(Pattern pattern, Method method, Object controller) {
        this.pattern = pattern;
        this.method = method;
        this.controller = controller;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Method getMethod() {
        return method;
    }

    public Object getController() {
        return controller;
    }
}