package com.ghk.study.gpstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GpstudyApplication {

    public static void main(String[] args) throws Exception{
        new SpringApplication(GpstudyApplication.class).run();
        /*ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        List<String> strings = SpringFactoriesLoader.loadFactoryNames(Bootstrapper.class, contextClassLoader);
        strings.forEach(e-> System.out.println(e));*/
    }

}
