package com.ghk.study.delegate;

/**
 * @Title: EmployeeB
 * @Package: com.ghk.study.delegate
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/4/30 17:59
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class EmployeeB implements EmployeeAction{
    private String goodAt = "技术";
    @Override
    public void doSomething(String something) {
        System.out.println("EmployeeB擅长"+goodAt+"正在做"+something);
    }
}