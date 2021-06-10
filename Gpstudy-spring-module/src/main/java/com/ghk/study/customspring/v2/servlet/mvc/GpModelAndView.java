package com.ghk.study.customspring.v2.servlet.mvc;

import java.util.Map;

/**
 * @Title: GpModelAndView
 * @Package: com.ghk.study.customspring.v2.servlet.mvc
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/20 11:44
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpModelAndView {
    private String viewName;

    private Map<String,?> model;

    public GpModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public GpModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }
}