package com.ghk.study.combination;

/**
 * @Title: BaseTree
 * @Package: com.ghk.study.combination
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/4/30 11:26
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class BaseTree extends AbstractTree{

    protected BaseTree(String treeName) {
        super(treeName);
    }

    @Override
    protected void showTree() {
        System.out.println(super.getTreeName());
    }
}