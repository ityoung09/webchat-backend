package com.kedaya.webchatbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.kedaya.webchatbackend.repository")
@SpringBootApplication
public class WebchatBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebchatBackendApplication.class, args);
    }

}
