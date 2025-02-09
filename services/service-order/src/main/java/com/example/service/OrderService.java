package com.example.service;


import com.example.order.bean.Order;

public interface OrderService {
    public Order createOrder(Long productId ,Long UserId);
}



