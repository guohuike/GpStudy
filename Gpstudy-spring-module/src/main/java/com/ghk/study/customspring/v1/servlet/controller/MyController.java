package com.ghk.study.customspring.v1.servlet.controller;

import com.ghk.study.customspring.v1.servlet.service.ServiceInterfacesss;
import com.ghk.study.customspring.v2.servlet.annotation.GpAutowired;
import com.ghk.study.customspring.v2.servlet.annotation.GpController;
import com.ghk.study.customspring.v2.servlet.annotation.GpRequestMapping;
import com.ghk.study.customspring.v2.servlet.annotation.GpRequestParam;
import com.ghk.study.customspring.v1.servlet.service.MyService;
import com.ghk.study.customspring.v2.servlet.mvc.GpModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: MyController
 * @Package: com.ghk.study.customspring.v1.servlet.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/11 13:28
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
@GpController
@GpRequestMapping("/custom")
public class MyController {
    @GpAutowired
    private ServiceInterfacesss myService;


    @GpRequestMapping("/testMvc")
    public GpModelAndView testMvc(HttpServletRequest httpServletRequest, @GpRequestParam("age")Integer age, HttpServletResponse httpServletResponse, @GpRequestParam("name")String name){
        System.out.println(myService.getMyServiceStr());
        Map<String, Object> model = new HashMap<>();
        model.put("age",age);
        model.put("name",name);
        GpModelAndView gpModelAndView = new GpModelAndView("0",model);
        return gpModelAndView;
    }

    @GpRequestMapping("/testMvcThrows")
    public GpModelAndView testMvcThrows(HttpServletRequest httpServletRequest, @GpRequestParam("age")Integer age, HttpServletResponse httpServletResponse, @GpRequestParam("name")String name){
        myService.getMyServiceThrows();
        Map<String, Object> model = new HashMap<>();
        model.put("age",age);
        model.put("name",name);
        GpModelAndView gpModelAndView = new GpModelAndView("0",model);
        return gpModelAndView;
    }


}