package com.example.Mini.Online.Market.orders.service;

import com.example.Mini.Online.Market.orders.domain.Order;

import java.util.Optional;

public interface OrderService {
public Iterable<Long> fetchOrders();
public Optional<Order> getOrder(Long id);
public void createOrder(Order order);
public Optional<Order> updateOrder(Order order);
public void deleteOrder(Long id);
}
