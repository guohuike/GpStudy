package com.ghk.study.factory.abstractpromotion;

/**
 * @Title: ActivityFactoryImpl
 * @Package: com.ghk.study.factory.promotion
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/4/29 15:14
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class ActivityFactoryImpl extends AbstractFactory{

    public ActivityMethod getPromotionActivity(){
       return super.getActivity(PromotionActivity.class);
    }

    public ActivityMethod getCouponActivity(){
        return super.getActivity(CouponActivity.class);
    }
}