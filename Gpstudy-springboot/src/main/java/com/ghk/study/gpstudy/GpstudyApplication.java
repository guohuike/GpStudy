package com.ghk.study.gpstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GpstudyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception{
        new SpringApplication(GpstudyApplication.class).run();
        /*ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        List<String> strings = SpringFactoriesLoader.loadFactoryNames(Bootstrapper.class, contextClassLoader);
        strings.forEach(e-> System.out.println(e));*/
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GpstudyApplication.class);
    }

}
