package com.example.Mini.Online.Market.mockfactory.repository;

import com.example.Mini.Online.Market.mockfactory.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    public List<Product> findAll();
}
