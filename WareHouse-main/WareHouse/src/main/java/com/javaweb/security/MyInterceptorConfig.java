package com.javaweb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {
    @Bean(name = "myAuthorizationInterceptor")
    public HandlerInterceptor myAuthorizationInterceptor() {
        return new AuthorizationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myAuthorizationInterceptor())
                .addPathPatterns("/manager/**","/profile","/") // Đặt URL mà interceptor áp dụng ở đây
                .excludePathPatterns(""); // Đặt URL mà interceptor không áp dụng ở đây
    }
}