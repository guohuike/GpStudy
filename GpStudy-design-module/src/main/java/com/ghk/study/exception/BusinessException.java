package com.ghk.study.exception;

/**
 * @Title: BusinessException
 * @Package: com.leyou.apollo.data.transfer.exception
 * @Description: 业务异常
 * @author: huike.guo
 * @date: 2021/4/13 10:48
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class BusinessException extends AbstractException {


    public BusinessException(int code, String message) {
        super(code, message);
    }
}