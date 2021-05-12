package com.ghk.study.customspring.v1.servlet.service;

import com.ghk.study.customspring.annotation.GpService;

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
public class MyService {
    public String getMyServiceStr(){
        return "myService";
    }
}