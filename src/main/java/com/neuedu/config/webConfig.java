package com.neuedu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//相当于一个配置文件，注册拦截器
public class webConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index","/login","/asserts/**","/error", "templates/favicon.ico");
    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/file/**").addResourceLocations("E:\\KCY\\大学\\实训\\springbootmvc\\src\\main\\resources\\static\\img");
//    }
}
