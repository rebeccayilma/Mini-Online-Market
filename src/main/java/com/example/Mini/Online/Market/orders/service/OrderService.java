package com.example.Mini.Online.Market.orders.service;

import com.example.Mini.Online.Market.orders.domain.Order;

import java.util.*;

public interface OrderService {
    Optional<Order> getOne(long id);
    List<Order> findAll();
    Order save(Order order);
}
