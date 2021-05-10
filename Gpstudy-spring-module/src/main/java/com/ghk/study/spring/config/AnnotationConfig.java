package com.ghk.study.spring.config;

import com.ghk.study.spring.config.condition.OneConditional;
import com.ghk.study.spring.config.condition.TwoConditional;
import com.ghk.study.spring.config.imports.MyImport;
import com.ghk.study.spring.config.imports.MyImport2;
import com.ghk.study.spring.config.factorybean.MyFactoryBean;
import com.ghk.study.spring.controller.MyController;
import com.ghk.study.spring.entity.LifeEntity;
import com.ghk.study.spring.entity.SpringBean;
import org.springframework.context.annotation.*;

/**
 * @author guohuike
 * @Description TODO
 * @date 2021/2/23
 */
@ComponentScan(value = "com.ghk.study.spring.entity",
    //只加载有注解的类
    includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION)}
    //加载指定的实体
    //includeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = {SpringBean.class})},
    //自定义加载规则
    //includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM,value = MyFilterType.class)},
)
@Configuration
@Import(value = {MyController.class, MyImport.class, MyImport2.class})
@PropertySource(value = "classpath:application.properties")
public class AnnotationConfig {
    /***
     * 1.最后取类名首字母小写
     * 2.其次取方法名
     * 3.优先取bean注解里面的name属性
     */
    //@Bean("springBean1")
    //@Conditional(value = OneConditional.class)
    public SpringBean getSpringBean1(){
        return new SpringBean();
    }

    //@Bean("springBean2")
    //@Conditional(value = TwoConditional.class)
    public SpringBean getSpringBean2(){
        return new SpringBean();
    }

    //@Bean
    public MyFactoryBean getMyFactoryBean(){
        return new MyFactoryBean();
    }
    @Bean(initMethod = "before2",destroyMethod = "after2")
    public LifeEntity lifeEntity(){
        return new LifeEntity();
    }
}
