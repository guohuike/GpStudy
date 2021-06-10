package com.ghk.study.customspring.v1.servlet.service;

import com.ghk.study.customspring.v2.servlet.annotation.GpAutowired;
import com.ghk.study.customspring.v2.servlet.annotation.GpService;

/**
 * @Title: MyService
 * @Package: com.ghk.study.customspring.v1.servlet.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/11 13:29
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
@GpService
public class MyService implements ServiceInterfacesss {
    //@GpAutowired
    private MyService2 myService2;

    public String getMyServiceStr(){
        System.out.println("yichang");
        return "myService";
    }

    @Override
    public void getMyServiceThrows() {
        System.out.println("throws doing exception!");
        throw new RuntimeException("自定义异常!");
    }
}