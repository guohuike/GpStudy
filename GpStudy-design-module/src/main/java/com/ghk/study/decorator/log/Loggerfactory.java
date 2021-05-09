package com.ghk.study.decorator.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: Loggerfactory
 * @Package: com.ghk.study.decorator.log
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/8 11:23
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class Loggerfactory {
    public static CustomLogger getCustomLogger(Class<?> clazz){
        Logger logger = LoggerFactory.getLogger(clazz);
        return new CustomLogger(logger);
    }
}