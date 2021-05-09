package com.ghk.study.flyweight.string;

/**
 * @Title: TestStr
 * @Package: com.ghk.study.flyweight.string
 * @Description: 如果是常量会在编译阶段进行拼接指向同一个对象,如果是变量的话,就会在运行是生成新对象
 *
 * @author: huike.guo
 * @date: 2021/4/29 17:54
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class TestStr {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "hello";
        String s3 = "he"+"llo";
        String s4 = "he" + new String("llo");
        String s5 = "he";
        String s6 = "llo";
        String s7 = s5+s6;
        //true
        System.out.println(s1 == s2);
        //true
        System.out.println(s1 == s3);
        //false  首先在常量池中放入变量he,然后分配内存new出新对象,然后进行拼接,在来一个新对象
        System.out.println(s1 == s4);
        //false 用S5对象的连接+S6对象的连接,新生成对象S7
        System.out.println(s1 == s7);
    }
}