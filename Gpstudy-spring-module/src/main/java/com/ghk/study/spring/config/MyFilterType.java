package com.ghk.study.spring.config;


import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @Title: MyFilterType
 * @Package: com.ghk.study.spring.config
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/9 16:11
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class MyFilterType implements TypeFilter {
    /**
     * @param metadataReader 当前扫描到的类的信息
     * @param metadataReaderFactory 获取当前上下文类的信息
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取到扫描到的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取到扫描到的类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        System.out.println("====="+classMetadata.getClassName()+"=====");
        //得到资源信息
        Resource resource = metadataReader.getResource();
        if(classMetadata.getClassName().contains("Anno")){
            return true;
        }
        return false;
    }
}