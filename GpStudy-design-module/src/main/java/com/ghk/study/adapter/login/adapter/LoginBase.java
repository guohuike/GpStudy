package com.ghk.study.adapter.login.adapter;

/**
 * @Title: LoginBase
 * @Package: com.ghk.study.adapter.login.adapter
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/4/27 14:56
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public interface LoginBase {
    boolean login(String openId);

    boolean support(Object obj);
}