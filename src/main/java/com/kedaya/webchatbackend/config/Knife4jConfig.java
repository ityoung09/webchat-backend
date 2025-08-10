package com.kedaya.webchatbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Knife4j API文档配置类
 * 配置OpenAPI文档信息和JWT认证
 *
 * @author wei.chen
 */
@Configuration
public class Knife4jConfig {

    /**
     * 配置OpenAPI文档信息
     *
     * @return OpenAPI配置对象
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("WebChat API 文档")
                        .version("1.0.0")
                        .description("WebChat 聊天应用后端接口文档")
                        .contact(new Contact()
                                .name("开发团队")
                                .email("weic8247@gmail.com")
                                .url("https://kedaya.us")));
    }
}