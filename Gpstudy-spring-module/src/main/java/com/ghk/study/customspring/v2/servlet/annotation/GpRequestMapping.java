package com.ghk.study.customspring.v2.servlet.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Title: GpController
 * @Package: com.ghk.study.customspring.annotation
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/10 17:27
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface GpRequestMapping {
    String value() default "";
}