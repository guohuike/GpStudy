package com.ghk.study.build;

/**
 * @Title: IbuilderImpl
 * @Package: com.ghk.study.build
 * @Description: 建造者模式,将创建对象和表示进行分离,通过同样的创建过程,创建不同的表示.
 * 对象创建需要很多步骤,但是却不确定步骤先后顺序.可以构建建造者模式,让用户自己去选择步骤.实现灵活定制
 * @author: huike.guo
 * @date: 2021/4/26 21:03
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class IbuilderImpl implements IBuilder{

    @Override
    public Object builder() {
        return new BuilderProduct();
    }

    public static void main(String[] args) {
        IBuilder iBuilder = new IbuilderImpl();
        Object builder = iBuilder.builder();
        System.out.println(builder);
    }
}