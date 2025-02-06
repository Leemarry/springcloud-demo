package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient //核心注解  开启服务发现
@SpringBootApplication
public class OrderMainApplication {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SpringApplication.run(OrderMainApplication.class, args);
    }
}
