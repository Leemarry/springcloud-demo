package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient //核心注解  开启服务发现功能
@SpringBootApplication //核心注解  开启Spring Boot的自动配置功能
public class ProductMainApplication {
    public static void main(String[] args) {
        // This is where the application starts
        SpringApplication.run(ProductMainApplication.class, args);
    }
}
