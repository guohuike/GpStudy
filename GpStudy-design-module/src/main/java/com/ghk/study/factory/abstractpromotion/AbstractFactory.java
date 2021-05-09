package com.ghk.study.factory.abstractpromotion;

/**
 * @Title: AbstractFactory
 * @Package: com.ghk.study.factory.promotion
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/4/29 15:13
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public abstract class AbstractFactory implements ActivityFactory{
   @Override
   public  ActivityMethod getActivity(Class<? extends ActivityMethod> clazz){
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
   }
}