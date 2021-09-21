package com.example.Mini.Online.Market.mockfactory.service;

import com.example.Mini.Online.Market.mockfactory.Product;
import com.example.Mini.Online.Market.mockfactory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Optional<Product> getOne(long id) {
        return productRepository.findById(id);
    }
}
