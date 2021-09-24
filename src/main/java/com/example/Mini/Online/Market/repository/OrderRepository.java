package com.example.Mini.Online.Market.repository;

import com.example.Mini.Online.Market.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();

    List<Order> findAllByUserId(long id);
}
