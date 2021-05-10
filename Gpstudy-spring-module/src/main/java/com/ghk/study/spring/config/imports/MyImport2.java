package com.ghk.study.spring.config.imports;

import com.ghk.study.spring.run.FileXmlRun;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Title: MyImport2
 * @Package: com.ghk.study.spring.config.imports
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/9 17:12
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class MyImport2 implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        Boolean a = beanDefinitionRegistry.containsBeanDefinition("springBean2");
        Boolean b = beanDefinitionRegistry.containsBeanDefinition("springBean1");
        if(a || b){
            BeanDefinition beanDefinition = new RootBeanDefinition(FileXmlRun.class);
            beanDefinitionRegistry.registerBeanDefinition("fileXmlRun",beanDefinition);
        }
    }
}