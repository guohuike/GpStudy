package com.ghk.study.flyweight;

/**
 * @Title: ShareCard
 * @Package: com.ghk.study.flyweight
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/4/29 17:23
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class ShareCard implements Car{
    private Integer usedStatus = 1;

    @Override
    public void driver(String carName) {
        System.out.println(carName+"正在行驶!");
    }

    public Integer getUsedStatus() {
        return usedStatus;
    }

    public void setUsedStatus(Integer usedStatus) {
        this.usedStatus = usedStatus;
    }
}