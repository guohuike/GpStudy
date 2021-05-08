package com.ghk.study.chain;

/**
 * @Title: TestMain
 * @Package: com.ghk.study.chain
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/7 15:59
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class TestMain {
    public static void main(String[] args) {
        AbstractHandler.Builder<Object> objectBuilder = new AbstractHandler.Builder<>();
        objectBuilder.addHandler(new ValidHandler())
                .addHandler(new LoginHandler())
                .addHandler(new AuthPowerHandler());
        objectBuilder.builder()
                .doHandler("to");
    }
}