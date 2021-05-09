package com.ghk.study.adapter.login.adapter;

/**
 * @Title: LoginPhoneAdapter
 * @Package: com.ghk.study.adapter.login.adapter
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/4/27 15:19
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class LoginPhoneAdapter extends AbstractLogin{
    @Override
    public boolean login(String openId) {
        //自己逻辑
        return super.baseLogin(openId,null);
    }

    @Override
    public boolean support(Object obj) {
        return obj instanceof LoginPhoneAdapter;
    }
}