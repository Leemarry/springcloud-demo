package com.example.controller;


import com.example.order.bean.Order;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope //  刷新配置
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Value("${order.timeout}")
    private int timeout;

    @GetMapping("/timeout")
    public String timeout() {
        return "timeout: " + timeout;
    }

    @GetMapping("/createorder")
    public Order createOrder() {
        return orderService.createOrder();
    }


}
