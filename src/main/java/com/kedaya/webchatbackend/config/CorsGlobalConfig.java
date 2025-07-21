package com.kedaya.webchatbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.config
 * @Project：webchat-backend
 * @name：CorsGlobalConfig
 * @Date：2025-07-19 14:14
 * @Filename：CorsGlobalConfig
 */
@Configuration
public class CorsGlobalConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 匹配所有路径
                .allowedOriginPatterns("*") // 允许所有来源（也可以指定具体域名）
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的方法
                .allowedHeaders("*") // 允许所有请求头
                .allowCredentials(true) // 允许携带cookie
                .maxAge(3600); // 预检请求有效期（秒）
    }
}
