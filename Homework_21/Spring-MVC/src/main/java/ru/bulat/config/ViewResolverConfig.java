package ru.bulat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
public class ViewResolverConfig {

    @Bean
    public ViewResolver viewResolver(){
        MyViewResolver viewResolver = new MyViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates");
        viewResolver.setSuffix(".ftl");
        viewResolver.setViewClass(FreeMarkerViewResolver.class);
        return viewResolver;
    }
}
