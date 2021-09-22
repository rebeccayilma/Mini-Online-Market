package com.example.Mini.Online.Market.service;

import com.example.Mini.Online.Market.domain.Review;
import com.example.Mini.Online.Market.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReview() {
        return reviewRepository.findAll();
    }

    @Override
    public void addReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public Optional<Review> findById(long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public void update(Review review) {
        Review r = reviewRepository.findById(review.getId()).orElse(null);
        r.setContent(review.getContent());
        r.setStatus(review.getStatus());
        reviewRepository.save(r);

    }

    @Override
    public void deleteById(long id) {
        reviewRepository.deleteById(id);
    }
}
