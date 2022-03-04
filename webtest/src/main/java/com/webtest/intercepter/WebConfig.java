package com.webtest.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/","/index","/books/*","/books","/borrow","/alter","/comments","/tocomments")
                .excludePathPatterns("/toLogin","/*/*.js","/*/*.css","/*/*.jpg","/*/*.png","/*/*/*.css","/*/*/*.js");
    }
}
