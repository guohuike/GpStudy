package com.ghk.study.combination;

/**
 * @Title: AbstractTree
 * @Package: com.ghk.study.combination
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/4/30 11:23
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public abstract class AbstractTree {
    protected String treeName;

    protected AbstractTree(String treeName) {
        this.treeName = treeName;
    }

    protected abstract void showTree();

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }
}