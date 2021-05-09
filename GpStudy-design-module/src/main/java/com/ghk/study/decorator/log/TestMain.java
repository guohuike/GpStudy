package com.ghk.study.decorator.log;

import org.slf4j.Logger;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Title: TestMain
 * @Package: com.ghk.study.decorator.log
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/8 10:35
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class TestMain {
    public static void main(String[] args) {
        CustomLogger customLogger = Loggerfactory.getCustomLogger(TestMain.class);
        customLogger.info("执行成功!");
        customLogger.error("空异常",new RuntimeException("kong zhi zhen"));
        try{
            //装饰器
            InputStream inputStream = new FileInputStream("");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedInputStream.read();

        }catch (Exception e){

        }
    }
}