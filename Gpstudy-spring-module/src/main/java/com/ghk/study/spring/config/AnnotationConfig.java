package com.ghk.study.spring.config;

import com.ghk.study.spring.entity.SpringBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author guohuike
 * @Description TODO
 * @date 2021/2/23
 */
@ComponentScan("com.ghk.study.spring.entity")
@Configuration
public class AnnotationConfig {
    @Bean
    public SpringBean getSpringBean(){
        return new SpringBean();
    }
}
