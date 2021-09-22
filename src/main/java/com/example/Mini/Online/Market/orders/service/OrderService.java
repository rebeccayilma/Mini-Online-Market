package com.example.Mini.Online.Market.orders.service;

import com.example.Mini.Online.Market.mockfactory.User;
import com.example.Mini.Online.Market.orders.domain.Order;
import com.example.Mini.Online.Market.orders.domain.OrderStatus;

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
