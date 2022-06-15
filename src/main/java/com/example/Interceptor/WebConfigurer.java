package com.example.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
//    @Autowired
//    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/html/**");
//                .addPathPatterns("/**")
//                .excludePathPatterns("/bootstrap-4.6.1-dist/**","/css/**","image/**","/js/**","/","/login.html");
//                .excludePathPatterns("/static/**","/","/login.html");
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/**");
//                .excludePathPatterns("/static/**")
//                .excludePathPatterns("/");
    }
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

}
