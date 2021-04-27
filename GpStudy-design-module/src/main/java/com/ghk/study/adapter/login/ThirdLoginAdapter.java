package com.ghk.study.adapter.login;

import com.ghk.study.adapter.login.adapter.*;

/**
 * @Title: ThirdLoginAdapter
 * @Package: com.ghk.study.adapter.login
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/4/27 14:54
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class ThirdLoginAdapter implements ThirdLogin{

    @Override
    public boolean loginQQ(String qq) {
        return process(qq, LoginQQAdapter.class);
    }

    @Override
    public boolean loginWechat(String weChat) {
        return process(weChat, LoginWechatAdapter.class);
    }

    @Override
    public boolean loginPhoneNum(String phone) {
        return process(phone, LoginPhoneAdapter.class);
    }

    public boolean process(String openId,Class<? extends LoginBase> clazz){
        try{
            LoginBase loginBase = clazz.newInstance();
            if(loginBase.support(loginBase)){
                loginBase.login(openId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}