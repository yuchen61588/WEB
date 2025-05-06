package org.example.log_in2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**") // 访问所有东西都跨域
                .allowedOrigins("Http://localhost:8081","null")
                //.allowedHeaders("*")
                .allowedMethods("POST","GET","PUT","OPTIONS","DELETE") //
                .maxAge(3600) // 最大响应时间
                .allowCredentials(true); // 是否携带信息
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "D:/WebFile/File/img/");
    }

}
