package com.example.Mini.Online.Market.service;

import com.example.Mini.Online.Market.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public Optional<Product> findById(Long id);
    public List<Product> findAll();
    public Product addProduct(Product product);
    public Product update(Product product);
    public void deleteById(long id);
    public boolean isEnoughInStock(long id, int quantity);
//    public List<Review> getProductReview(long id);
//    public List<Order> getProductOder(long id);

    /*
     public List<Product> getAll();
    public void save(Product product);
    public Optional<Product> getOne(long id);
    public boolean isEnoughInStock(long id, int quantity);
     */
}
