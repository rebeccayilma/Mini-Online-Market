package com.example.Mini.Online.Market.mockfactory.controller;

import com.example.Mini.Online.Market.mockfactory.Product;
import com.example.Mini.Online.Market.mockfactory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @PostMapping
    public void saveProduct(@RequestBody Product product) {
        productService.save(product);
    }
}
