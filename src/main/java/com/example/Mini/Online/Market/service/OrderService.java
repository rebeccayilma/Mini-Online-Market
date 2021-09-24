package com.example.Mini.Online.Market.service;

import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> getOne(long id);

    List<Order> findAll();

    Order save(Order order);

    public Order updateOrderStatus(long orderId, int orderStatus);

    List<Order> getOrderHistory(User user);

    List<Order> getAllOrders();
}
