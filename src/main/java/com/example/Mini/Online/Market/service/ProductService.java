package com.example.Mini.Online.Market.service;

import miniOnlineMarket.domain.Product;
import miniOnlineMarket.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public Optional<Product> findById(Long id);
    public List<Product> findAll();
    public Product addProduct(Product product);
    public Product update(Product product);
    public void deleteById(long id);
//    public List<Review> getProductReview(long id);
//    public List<Order> getProductOder(long id);

}
