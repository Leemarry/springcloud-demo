package com.example.controller;


import com.example.product.bean.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class productController {

    @Autowired
    private ProductService productService;

    // 查询所有产品信息
    @GetMapping("/product/{productId}")
    public Product getAllProduct(@PathVariable("productId") Long productId) {
       Product product =   productService.getProductById(productId);

        return product;

    }

}
