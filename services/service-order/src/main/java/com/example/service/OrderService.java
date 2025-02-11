package com.example.service;


import com.example.order.bean.Order;

public interface OrderService {
    public Order createOrder(Long productId ,Long UserId);


    public Order createOrder2(Long productId ,Long UserId);
}



