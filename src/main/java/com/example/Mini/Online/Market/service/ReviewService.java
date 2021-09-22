package com.example.Mini.Online.Market.service;

import miniOnlineMarket.domain.Product;
import miniOnlineMarket.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    public List<Review> getAllReview();
    public void addReview(Review review);
    public Optional<Review> findById(long id);
    public void update(Review review);
    public void deleteById(long id);
}
