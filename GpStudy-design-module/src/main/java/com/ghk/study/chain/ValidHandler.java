package com.ghk.study.chain;

import org.springframework.util.StringUtils;

/**
 * @Title: LoginHandler
 * @Package: com.ghk.study.chain
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/7 15:52
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class ValidHandler extends AbstractHandler{
    @Override
    public void doHandler(String name) {
        if(StringUtils.isEmpty(name)){
            System.out.println("用户名信息不能为空!");
            return;
        }else{
            System.out.println("用户名校验信息通过!");
            next.doHandler(name);
        }
    }
}