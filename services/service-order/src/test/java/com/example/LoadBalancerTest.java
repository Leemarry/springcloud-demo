package com.example;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@SpringBootTest
public class LoadBalancerTest {


    @Autowired
    LoadBalancerClient loadBalancerClient;

    // 随机
    @Test
    public void test1() {
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        String url = "http://" + choose.getHost() + ":" + choose.getPort() ;
        System.out.println(url);
    }

    // 轮询
    @Test
    public void test() {
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        String url = "http://" + choose.getHost() + ":" + choose.getPort() ;
        System.out.println(url);
        System.out.println(loadBalancerClient.choose("service-product").getUri());
        System.out.println(loadBalancerClient.choose("service-product").getUri());
        System.out.println(loadBalancerClient.choose("service-product").getUri());
    }


}
