package com.example.service.impl;

import com.example.product.bean.Product;
import com.example.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {


    @Override
    public Product getProductById(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setName("Product " + id);
        product.setNum(100);
        return product;
    }
}
