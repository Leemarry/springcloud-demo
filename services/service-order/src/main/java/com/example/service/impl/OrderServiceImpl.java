package com.example.service.impl;

import com.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import com.example.order.bean.Order;
import com.example.product.bean.Product;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl  implements OrderService {
    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public Order createOrder(Long productId ,Long UserId) {
        Order order = new Order();
        order.setId(1111111111111111111L);
        order.setAddress("123 Main St");
        order.setNickname("John 100.00");
        Product product = getProductFromRemoteWithLoadBalanceAnnotation(1234567890L);
        List<Product> products = new ArrayList<>();
        products.add(product);
        order.setProducts(products);
        return order;
    }

    // 使用DiscoveryClient实现服务发现
    private Product getProductFromRemoteWithDiscoveryClient(Long id) {
        // TODO: implement this method
        List<ServiceInstance> instances =  discoveryClient.getInstances("service-product");
        if (instances.size() > 0) {
                ServiceInstance serviceInstance = instances.get(0);
               String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/" + id;
            log.info("Calling product service at " + url);
            // 发送请求获取产品信息
            return restTemplate.getForObject(url, Product.class);
        }
        return null;
    }

    //
    private Product getProductFromRemoteWithDiscoveryClient2(Long id) {
        // TODO: implement this method
        List<ServiceInstance> instances =  discoveryClient.getInstances("service-product");
        if (instances.size() > 0) {
            ServiceInstance serviceInstance = loadBalancerClient.choose("service-product");
            String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/" + id;
            log.info("Calling product service at " + url);
            // 发送请求获取产品信息
            return restTemplate.getForObject(url, Product.class);
        }
        return null;
    }

     // 使用注解方式实现负载均衡
    private Product getProductFromRemoteWithLoadBalanceAnnotation(Long id) {
        // 给远程发送请求； service-product 会被动态替换成实际的服务名
        Product product = restTemplate.getForObject("http://service-product/product/" + id, Product.class);
        return product;
    }



    @Override
    public Order createOrder2(Long productId ,Long UserId) {
        Order order = new Order();
        order.setId(1111111111111111111L);
        order.setAddress("123 Main St");
        order.setNickname("John 100.00");
        // 发送消息到 RabbitMQ
        String exchangeName = "my.direct.producExchange1";
       try {
           rabbitTemplate.convertAndSend(exchangeName, "product.create", productId);
       }catch (Exception e){
           e.printStackTrace();
       }
        List<Product> products = new ArrayList<>();
        order.setProducts(products);
        return order;
    }



}
