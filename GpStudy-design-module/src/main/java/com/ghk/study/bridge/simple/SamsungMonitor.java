package com.ghk.study.bridge.simple;

/**
 * @Title: SamsungMonitor
 * @Package: com.ghk.study.bridge.simple
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/4/28 17:55
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class SamsungMonitor implements Monitor{
    @Override
    public String display() {
        return "Samsung Monitor display";
    }
}