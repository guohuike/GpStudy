package com.ghk.study.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: CatalogueTree
 * @Package: com.ghk.study.combination
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/4/30 11:27
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class CatalogueTree extends AbstractTree{
    private List<AbstractTree> treeList = new ArrayList<>();

    private Integer level;

    protected CatalogueTree(String treeName,Integer level) {
        super(treeName);
        this.level = level;
    }

    @Override
    protected void showTree() {
        System.out.println(this.treeName);
        for (AbstractTree abstractTree : treeList) {
            for (int i = 0; i < this.level; i++) {
                System.out.print("   ");
            }
            for (int i = 0; i < this.level; i++) {
                if(i == 0){
                    System.out.print("+");
                }
                System.out.print("-");
            }
            abstractTree.showTree();
            System.out.println("");

        }
    }

    public void addTree(AbstractTree abstractTree){
        treeList.add(abstractTree);
    }

    public void removeTree(AbstractTree abstractTree){
        treeList.remove(abstractTree);
    }
}