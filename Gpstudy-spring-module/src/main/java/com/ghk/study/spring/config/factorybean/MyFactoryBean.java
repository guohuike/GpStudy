package com.ghk.study.spring.config.factorybean;

import com.ghk.study.spring.service.MyService;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Title: MyImport3
 * @Package: com.ghk.study.spring.config.imports
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/9 17:26
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class MyFactoryBean implements FactoryBean<MyService> {
    @Override
    public MyService getObject() throws Exception {
        return new MyService();
    }

    @Override
    public Class<?> getObjectType() {
        return MyService.class;
    }
}