package com.ghk.study.delegate;

/**
 * @Title: Boss
 * @Package: com.ghk.study.delegate
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/2 17:04
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class Boss {
    public void doDispatch(String something){
        EmployeeLeader employeeLeader = new EmployeeLeader();
        employeeLeader.doSomething(something);
    }
}