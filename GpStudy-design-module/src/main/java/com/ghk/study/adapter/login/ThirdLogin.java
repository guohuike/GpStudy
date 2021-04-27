package com.ghk.study.adapter.login;

/**
 * @Title: ThirdLogin
 * @Package: com.ghk.study.adapter.login
 * @Description: 第三方登入
 * @author: huike.guo
 * @date: 2021/4/27 14:52
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public interface ThirdLogin {

    boolean loginQQ(String qq);

    boolean loginWechat(String qq);

    boolean loginPhoneNum(String qq);

}
