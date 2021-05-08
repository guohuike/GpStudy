package com.ghk.study.chain;

/**
 * @Title: LoginHandler
 * @Package: com.ghk.study.chain
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/7 15:56
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class LoginHandler extends AbstractHandler{
    @Override
    public void doHandler(String name) {
        if("tom".equals(name)){
            System.out.println("账号密码验证正确!");
            next.doHandler(name);
        }else{
            System.out.println("账号密码输入有误,请重新输入!");
        }
    }
}