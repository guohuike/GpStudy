package com.ghk.study.adapter.classadapter;

/**
 * @Title: TestMain
 * @Package: com.ghk.study.adapter.classadapter
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/4/27 10:57
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class TestMain {
    public static void main(String[] args) throws Exception{
        Gasoline Gasoline = OilAdapter.class.newInstance();
        Gasoline.doGasoline();
    }
}