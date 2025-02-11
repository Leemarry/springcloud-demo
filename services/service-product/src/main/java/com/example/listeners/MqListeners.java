package com.example.listeners;

import com.example.product.bean.Product;
import com.example.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqListeners {

    @Autowired
    private ProductService productService;

    //交换机类型为direct的队列  只有指定routingKey的消费者可以收到消息
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "my.direct.producQueue1", durable = "true"),
            exchange = @Exchange(name = "my.direct.producExchange1", type = ExchangeTypes.TOPIC),
            key = "product.create"
    ))
    public void listenDirectQueue1(Long productId) {
        Product product =   productService.getProductById(productId);
        System.out.println("消费者1 收到了 direct1.queue1 的消息：【" + productId +"】");
    }




}
