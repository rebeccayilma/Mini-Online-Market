package com.example.Mini.Online.Market.service;

import miniOnlineMarket.domain.Product;
import miniOnlineMarket.domain.Review;
import miniOnlineMarket.repository.ProductRepository;
import miniOnlineMarket.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        Product p = productRepository.findById(product.getId()).orElse(null);
        p.setName(product.getName());
        p.setDescription(product.getDescription());
        p.setPrice(product.getPrice());
        return productRepository.save(p);
    }

    @Override
    public void deleteById(long id) {
         productRepository.deleteById(id);
    }

//    @Override
//    public List<Review> getProductReview(long id) {
//        Optional<Product> product = productRepository.findById(id);
//        return reviewRepository.findAllByProduct(product);
//    }
}
