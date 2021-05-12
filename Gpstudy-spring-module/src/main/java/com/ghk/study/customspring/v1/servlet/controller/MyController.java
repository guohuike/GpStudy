package com.ghk.study.customspring.v1.servlet.controller;

import com.ghk.study.customspring.annotation.GpAutowired;
import com.ghk.study.customspring.annotation.GpController;
import com.ghk.study.customspring.annotation.GpRequestMapping;
import com.ghk.study.customspring.annotation.GpRequestParam;
import com.ghk.study.customspring.v1.servlet.service.MyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    private MyService myService;


    @GpRequestMapping("/testMvc")
    public String testMvc(HttpServletRequest httpServletRequest,@GpRequestParam("age")String age,HttpServletResponse httpServletResponse,@GpRequestParam("name")String name){
        System.out.println(myService.getMyServiceStr());
        try {
            httpServletResponse.getWriter().write("parameter is:"+name+"age:"+age);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }


}