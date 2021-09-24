package com.example.Mini.Online.Market.repository;

import com.example.Mini.Online.Market.domain.Product;
import com.example.Mini.Online.Market.domain.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
//    public void delete(long id);
//    public Product update(long id);
     List<Product> findAll();
     @Query("select p.review from Product p where p.id = :id")
     List<Review> findALLReviews(long id);
}
