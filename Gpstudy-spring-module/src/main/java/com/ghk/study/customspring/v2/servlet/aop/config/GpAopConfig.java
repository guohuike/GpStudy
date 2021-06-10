package com.ghk.study.customspring.v2.servlet.aop.config;

/**
 * @Title: GpAopConfig
 * @Package: com.ghk.study.customspring.v2.servlet.aop.config
 * @Description: AopConfig
 * @author: huike.guo
 * @date: 2021/5/23 17:57
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpAopConfig {
    //切点
    private String pointCut;
    //切面类
    private String aspectClass;
    //before
    private String aspectBefore;
    //after
    private String aspectAfter;
    //异常后
    private String aspectAfterThrow;
    //异常名字
    private String aspectAfterThrowName;

    public String getAspectClass() {
        return aspectClass;
    }

    public void setAspectClass(String aspectClass) {
        this.aspectClass = aspectClass;
    }

    public String getAspectBefore() {
        return aspectBefore;
    }

    public void setAspectBefore(String aspectBefore) {
        this.aspectBefore = aspectBefore;
    }

    public String getAspectAfter() {
        return aspectAfter;
    }

    public void setAspectAfter(String aspectAfter) {
        this.aspectAfter = aspectAfter;
    }

    public String getAspectAfterThrow() {
        return aspectAfterThrow;
    }

    public void setAspectAfterThrow(String aspectAfterThrow) {
        this.aspectAfterThrow = aspectAfterThrow;
    }

    public String getAspectAfterThrowName() {
        return aspectAfterThrowName;
    }

    public void setAspectAfterThrowName(String aspectAfterThrowName) {
        this.aspectAfterThrowName = aspectAfterThrowName;
    }

    public String getPointCut() {
        return pointCut;
    }

    public void setPointCut(String pointCut) {
        this.pointCut = pointCut;
    }
}