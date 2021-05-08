package com.ghk.study.chain;

/**
 * @Title: AbstractHandler
 * @Package: com.ghk.study.chain
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/7 15:50
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public abstract class AbstractHandler<T> implements Handler{
    public AbstractHandler<T> next;

    public AbstractHandler<T> setNext(AbstractHandler<T> handler){
        this.next = handler;
        return next;
    }

    public static class Builder<T>{
        public AbstractHandler<T> head;
        public AbstractHandler<T> tail;

        /***
         * @author: huike.guo
         * @description:
         * 方法备注,第一次add的时候,将头和尾都指向同一个对象,
         * 第二次添加的时候头不用动,将tail更新为当前对象,同时将tail的next指向为当前对象,
         * 第三次雷同第二次添加过程.
         * 不断替换tail,然后更新tail的next.这样就能形成单向链条结构
         * @date: 2021/5/7 16:53
         * @param
         * @return
         */
        public Builder<T> addHandler(AbstractHandler<T> handler){
            if(head == null){
                this.head = this.tail = handler;
                return this;
            }
            this.tail.setNext(handler);
            this.tail = handler;
            return this;
        }

        public AbstractHandler<T> builder(){
            return head;
        }

    }
}