package com.example.Mini.Online.Market.orders.repository;

import com.example.Mini.Online.Market.mockfactory.User;
import com.example.Mini.Online.Market.orders.domain.Order;
import com.example.Mini.Online.Market.orders.domain.OrderStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();

    List<Order> findAllByUserId(long id);
}
