package com.example.Mini.Online.Market.orders.repository;

import com.example.Mini.Online.Market.orders.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
