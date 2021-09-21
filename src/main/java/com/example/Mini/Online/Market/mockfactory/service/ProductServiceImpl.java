package com.example.Mini.Online.Market.mockfactory.service;

import com.example.Mini.Online.Market.mockfactory.Product;
import com.example.Mini.Online.Market.mockfactory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public boolean isEnoughInStock(long id, int quantity) {
        Optional<Product> product = productRepository.findById(id);
        return product.filter(value -> value.getQuantity() >= quantity).isPresent();
    }
}
