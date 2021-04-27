package com.ghk.study.adapter.login;

/**
 * @Title: PasswordLogin
 * @Package: com.ghk.study.adapter.login
 * @Description: 账号密码登入
 * @author: huike.guo
 * @date: 2021/4/27 14:46
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class PasswordLogin {
    private static final String FIXED_PASSWORD = "EMPTY_PASSWORD";
    public boolean login(String userName,String password){
        if(null == password){
            password = FIXED_PASSWORD;
        }
        if(password.equals(FIXED_PASSWORD)){
            //生成token
            return true;
        }else{
            return false;
        }
    }

    public boolean register(String userName,String password){
        if(null == password){
            password = FIXED_PASSWORD;
        }
        //进行注册
        return true;
    }
}