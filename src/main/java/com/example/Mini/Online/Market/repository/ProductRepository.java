package com.example.Mini.Online.Market.repository;

import miniOnlineMarket.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    public void delete(long id);
//    public Product update(long id);
}
