package com.example.Mini.Online.Market.mockfactory.service;

import com.example.Mini.Online.Market.mockfactory.Product;

import java.util.*;

public interface ProductService {
    public List<Product> getAll();
    public void save(Product product);
    public Optional<Product> getOne(long id);
}
