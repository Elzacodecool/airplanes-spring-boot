package com.codecool.spring.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class ProducerServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    ProducerServiceInterceptor producerServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(producerServiceInterceptor);
    }
}
