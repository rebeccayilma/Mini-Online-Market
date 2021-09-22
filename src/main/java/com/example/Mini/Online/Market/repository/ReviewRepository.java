package com.example.Mini.Online.Market.repository;

import com.example.Mini.Online.Market.domain.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
//    List<Review> findAllByProduct(Optional<Product> product);
    public List<Review> findAll();
}
