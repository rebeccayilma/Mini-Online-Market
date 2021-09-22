package com.example.Mini.Online.Market.controller;

import com.example.Mini.Online.Market.domain.Review;
import com.example.Mini.Online.Market.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping
    public List<Review> getReviews(){
        return reviewService.getAllReview();
    }

    @PostMapping
    public void addReview(@RequestBody Review review) {
        reviewService.addReview(review);

    }

    @GetMapping("/{id}")
    public Optional<Review> getPostById(@PathVariable long id){
        return reviewService.findById(id);
    }

    @PutMapping
    public void updateProduct(@RequestBody Review review) {
         reviewService.update(review);
    }

    @DeleteMapping
    public void deleteReviewById(@PathVariable long id) {
        reviewService.deleteById(id);

    }

}
