package com.ghk.study.combination;

/**
 * @Title: TestMain
 * @Package: com.ghk.study.combination
 * @Description: 组合模式  相对来说  关系依赖更强.   对客户端来不论组合还是单个对象对都用相同的接口进行表示
 * @author: huike.guo
 * @date: 2021/4/30 15:30
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class TestMain {
    public static void main(String[] args) {
        BaseTree baseTree = new BaseTree("我是二级级目录001!");
        BaseTree twoBaseTree = new BaseTree("我是二级级目录002!");
        BaseTree baseTreeThree = new BaseTree("我是二级级目录003!");
        BaseTree twoBaseTreeFour = new BaseTree("我是二级级目录004!");
        BaseTree baseTreeFive = new BaseTree("我是二级级目录005!");
        BaseTree twoBaseTreeSix = new BaseTree("我是二级级目录006!");
        CatalogueTree catalogueTree003 = new CatalogueTree("我是一级目录003",3);
        catalogueTree003.addTree(baseTreeFive);
        catalogueTree003.addTree(twoBaseTreeSix);
        CatalogueTree catalogueTree001 = new CatalogueTree("我是一级目录002",2);
        catalogueTree001.addTree(baseTreeThree);
        catalogueTree001.addTree(twoBaseTreeFour);
        CatalogueTree catalogueTree002 = new CatalogueTree("我是一级目录001",1);
        catalogueTree002.addTree(baseTree);
        catalogueTree002.addTree(twoBaseTree);
        catalogueTree002.addTree(catalogueTree001);
        catalogueTree001.addTree(catalogueTree003);
        catalogueTree002.showTree();
    }
}