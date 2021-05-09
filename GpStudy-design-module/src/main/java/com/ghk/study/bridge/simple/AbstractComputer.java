package com.ghk.study.bridge.simple;

/**
 * @Title: AbstractComputer
 * @Package: com.ghk.study.bridge.simple
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/4/28 17:57
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public abstract class AbstractComputer implements Computer{

    protected Cpu cpu;

    protected Monitor monitor;


    @Override
    public void calculation(String computerName) {
        System.out.println(computerName+"通过"+cpu.calculation()+"在"+monitor.display());
    }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }
}