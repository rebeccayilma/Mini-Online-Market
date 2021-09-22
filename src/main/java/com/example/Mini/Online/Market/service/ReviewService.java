package com.example.Mini.Online.Market.service;


import com.example.Mini.Online.Market.domain.Review;

import java.util.*;

public interface ReviewService {

    public List<Review> getAllReview();
    public void addReview(Review review);
    public Optional<Review> findById(long id);
    public void update(Review review);
    public void deleteById(long id);
}
