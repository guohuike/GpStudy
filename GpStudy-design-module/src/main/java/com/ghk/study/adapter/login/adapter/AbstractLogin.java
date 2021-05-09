package com.ghk.study.adapter.login.adapter;

import com.ghk.study.adapter.login.PasswordLogin;

/**
 * @Title: AbstractLogin
 * @Package: com.ghk.study.adapter.login.adapter
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/4/27 14:58
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public abstract class AbstractLogin extends PasswordLogin implements LoginBase {

    public boolean baseLogin(String userName,String password){
        super.register(userName,password);
      return super.login(userName,password);
    }
}