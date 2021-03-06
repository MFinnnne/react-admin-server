package com.df.config;

import com.df.interceptor.RedisUrlCountInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/3/2 22:00
 **/
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private RedisUrlCountInterceptor redisUrlCountInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(redisUrlCountInterceptor).addPathPatterns("/**");
    }
}
