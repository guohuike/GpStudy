package com.ghk.study.spring.run;

import com.ghk.study.spring.entity.SpringBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author guohuike
 * @Description TODO
 * @date 2021/2/23
 */
public class FileXmlRun {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:application-context.xml");
        System.out.println(applicationContext.getBean(SpringBean.class));

    }
}
