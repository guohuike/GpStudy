package com.ghk.study.customspring.v2.servlet.beans.support;

import com.ghk.study.customspring.v2.servlet.beans.config.GpBeanDefinition;
import org.springframework.beans.factory.BeanDefinitionStoreException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Title: GpBeanDefinitionReader
 * @Package: com.ghk.study.customspring.v2.servlet.support
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/17 11:12
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpBeanDefinitionReader {
    private Properties properties;

    private List<String> registerBeanClasses = new ArrayList<>();


    /***
     * @author: huike.guo
     * @description: 将配置文件封装成beanDefinition 在工厂中的名字  和全类名
     * @date: 2021/5/17 21:51
     * @param
     * @return List<GpBeanDefinition>
     */
    public List<GpBeanDefinition> loadBeanDefinitions() throws BeanDefinitionStoreException{
        List<GpBeanDefinition> resultList = new ArrayList<>();
        for (String registerBeanClass : registerBeanClasses) {
            try{
                Class<?> beanClass = Class.forName(registerBeanClass);
                if(beanClass.isInterface() || beanClass.isEnum())continue;
                resultList.add(doCreateBeanDefinition(toLowCase(beanClass.getSimpleName()),beanClass.getName()));
                Class<?>[] interfaces = beanClass.getInterfaces();
                if(interfaces.length > 0){
                    for (Class<?> anInterface : interfaces) {
                        resultList.add(doCreateBeanDefinition((anInterface.getSimpleName()),beanClass.getName()));
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return resultList;
    }

    private GpBeanDefinition doCreateBeanDefinition(String simpleName, String name) {
        GpBeanDefinition gpBeanDefinition = new GpBeanDefinition();
        //全类名
        gpBeanDefinition.setBeanClassName(name);
        //在IOC容器中的名字
        gpBeanDefinition.setFactoryBeanName(simpleName);
        return gpBeanDefinition;
    }

    public GpBeanDefinitionReader(String...configrationPath) {
        //加载配置文件
        loadConfigProperties(configrationPath);
        //根据配置文件扫描包路径
        doSacnner(properties.getProperty("scanner.package"));
    }
    /***
     * @author: huike.guo
     * @description: 扫描指定的包路径下的文件
     * @date: 2021/5/17 21:54
     * @param scannerPackage
     * @return void
     */
    private void doSacnner(String scannerPackage) {
        URL resource = this.getClass().getClassLoader().getResource("/" + scannerPackage.replace(".", "/"));
        File file = new File(resource.getFile());
        File[] files = file.listFiles();
        for (File file1 : files) {
            if(file1.isDirectory()){
                doSacnner(scannerPackage+"."+file1.getName());
            }else{
                if(!file1.getName().endsWith(".class"))continue;
                //包名加类名
                String className = scannerPackage+"."+file1.getName().replace(".class","");
                registerBeanClasses.add(className);
            }
        }
    }
    /***
     * @author: huike.guo
     * @description: 加载配置文件
     * @date: 2021/5/17 21:54
     * @param configrationPath
     * @return void
     */
    private void loadConfigProperties(String...configrationPath){
        properties = new Properties();
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(configrationPath[0]);
        try {
            properties.load(resourceAsStream);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != resourceAsStream){
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String toLowCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0]+=32;
        return new String(chars);
    }

    public Properties getConfig(){
        return properties;
    }
}