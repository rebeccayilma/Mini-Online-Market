package com.example.Mini.Online.Market.repository;

import com.example.Mini.Online.Market.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
//    public void delete(long id);
//    public Product update(long id);
    public List<Product> findAll();
}
