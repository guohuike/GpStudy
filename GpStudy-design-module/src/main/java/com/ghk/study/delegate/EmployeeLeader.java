package com.ghk.study.delegate;

/**
 * @Title: EmployeeLeader
 * @Package: com.ghk.study.delegate
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/4/30 18:00
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class EmployeeLeader implements EmployeeAction{
    @Override
    public void doSomething(String something) {
        if("编程".equals(something)){
            new EmployeeB().doSomething(something);
        }else if("卖产品".equals(something)){
            new EmployeeA().doSomething(something);
        }
    }
}