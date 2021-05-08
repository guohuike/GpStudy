package com.ghk.study.decorator.log;

import org.slf4j.Logger;

/**
 * @Title: LoggerDecorator
 * @Package: com.ghk.study.decorator.log
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/8 10:22
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public abstract class LoggerDecorator extends AbstractLogger {
    protected Logger logger;

    public LoggerDecorator(Logger logger) {
        this.logger = logger;
    }


}