package com.ghk.study.spring.run;

import com.ghk.study.spring.config.AnnotationConfig;
import com.ghk.study.spring.entity.SpringBean;
import com.ghk.study.spring.entity.SpringBeanAnno;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.*;

/**
 * @author guohuike
 * @Description TODO
 * @date 2021/2/23
 */
public class AnnoRun {
    public static void main(String[] args) throws Exception{
        /*ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        System.out.println(applicationContext.getBean(SpringBean.class));
        System.out.println(applicationContext.getBean(SpringBeanAnno.class));*/
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> urls = classLoader != null ? classLoader.getResources("META-INF/spring.factories") : ClassLoader.getSystemResources("META-INF/spring.factories");
        LinkedMultiValueMap result = new LinkedMultiValueMap();

        while(urls.hasMoreElements()) {
            URL url = (URL)urls.nextElement();
            UrlResource resource = new UrlResource(url);
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            Iterator var6 = properties.entrySet().iterator();

            while(var6.hasNext()) {
                Map.Entry<?, ?> entry = (Map.Entry)var6.next();
                List<String> factoryClassNames = Arrays.asList(StringUtils.commaDelimitedListToStringArray((String)entry.getValue()));
                result.addAll((String)entry.getKey(), factoryClassNames);
            }
        }
    }
}
