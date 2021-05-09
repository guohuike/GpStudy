package com.ghk.study.decorator.log;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;

/**
 * @Title: CustomLogger
 * @Package: com.ghk.study.decorator.log
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/8 10:31
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class CustomLogger extends LoggerDecorator{

    public CustomLogger(Logger logger) {
        super(logger);
    }

    @Override
    public void info(String s) {
        JSONObject jsonObject = newJsonObject();
        jsonObject.put("message",s);
        super.info(jsonObject.toJSONString());
    }

    @Override
    public void error(String s, Throwable throwable) {
        JSONObject jsonObject = newJsonObject();
        jsonObject.put("message",s);
        jsonObject.put("stackTrace",throwable.getMessage()+throwable.getStackTrace());
        super.error(jsonObject.toJSONString(),throwable);
    }

    public JSONObject newJsonObject(){
        return new JSONObject();
    }
}