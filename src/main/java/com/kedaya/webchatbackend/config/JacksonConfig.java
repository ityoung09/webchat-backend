package com.kedaya.webchatbackend.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.config
 * @Project：webchat-backend
 * @name：JacksonConfig
 * @Date：2025-08-10 15:44
 * @Filename：JacksonConfig
 */
@Configuration
public class JacksonConfig {

    @Bean
    public com.fasterxml.jackson.databind.Module longToStringModule() {
        SimpleModule module = new SimpleModule();
        // Long -> String
        module.addSerializer(Long.class, ToStringSerializer.instance);
        module.addSerializer(Long.TYPE, ToStringSerializer.instance); // long 基本类型
        return module;
    }

}
