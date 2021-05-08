package com.ghk.study.delegate;

/**
 * @Title: TestMain
 * @Package: com.ghk.study.delegate
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/2 17:05
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class TestMain {
    public static void main(String[] args) {
        Boss boss = new Boss();
        boss.doDispatch("卖产品");
        boss.doDispatch("编程");
    }
}