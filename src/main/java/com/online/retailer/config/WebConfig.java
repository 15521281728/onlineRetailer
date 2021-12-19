package com.online.retailer.config;

import com.online.retailer.interception.VerifyTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private VerifyTokenInterceptor verifyTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(verifyTokenInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/css/**")
            .excludePathPatterns("/js/**")
            .excludePathPatterns("/images/**")
            .excludePathPatterns("/pxcp/**")
            .excludePathPatterns("/favicon.ico");
    }
}
