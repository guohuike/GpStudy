package com.ghk.study.prototype;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: PrototypeInstance
 * @Package: com.ghk.study.prototype
 * @Description: 原型模式  通过原型实例指定创建对象的种类
 * 浅copy进行copy的是对象的引用,不会新生成一个新的对象.
 * @author: huike.guo
 * @date: 2021/4/26 20:31
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class PrototypeInstance implements Cloneable,Serializable{
    private String name;
    private int age;
    private List<String> list;

    @Override
    public PrototypeInstance clone(){

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos);
            objectOutputStream.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(bais);
            return (PrototypeInstance)objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        PrototypeInstance prototypeInstance = new PrototypeInstance();
        prototypeInstance.setAge(1);
        prototypeInstance.setName("2");
        prototypeInstance.setList(new ArrayList<>());

        PrototypeInstance clone = prototypeInstance.clone();
        System.out.println(clone.getList() == prototypeInstance.getList());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}