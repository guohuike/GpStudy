package com.ghk.study.spring.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * @Title: LifeEntity
 * @Package: com.ghk.study.spring.entity
 * @Description: spring监控bean的生命周期,共四种方式,三种常用方式
 * 1.@Bean
 * 2.实现接口InitializingBean初始化和DisposableBean销毁
 * 3.注解方式@PostConstruct和@PreDestroy
 * 这三种方式先后顺序为注3,2,1
 * 4.实现BeanPostProcessor,这是最为强大的方式,可以监控到所有扫描到的类生命周期,最先执行初始化,最后执行销毁
 * @author: huike.guo
 * @date: 2021/5/10 13:40
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
@Component
public class LifeEntity implements InitializingBean, DisposableBean {
    public LifeEntity() {
        System.out.println("LifeEntity被调用构造了!");
    }

    @PostConstruct
    public void before(){
        System.out.println("LifeEntity----------before");
    }

    @PreDestroy
    public void after(){
        System.out.println("LifeEntity----------after");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("LifeEntity----------destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("LifeEntity----------afterPropertiesSet");
    }

    public void before2(){
        System.out.println("LifeEntity----------before2");
    }

    public void after2(){
        System.out.println("LifeEntity----------after2");
    }

    @Value("${port}")
    private int port;

    @Value("127.0.0.1")
    private String ip;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "LifeEntity{" +
                "port=" + port +
                ", ip='" + ip + '\'' +
                '}';
    }
}