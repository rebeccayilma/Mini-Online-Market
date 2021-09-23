package com.example.Mini.Online.Market.orders.service;

import com.example.Mini.Online.Market.orders.domain.Order;
import com.example.Mini.Online.Market.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;
    @Override
    public Iterable<Long> fetchOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void createOrder(Order order) {

    }

    @Override
    public Optional<Order> updateOrder(Order order) {
        return Optional.empty();
    }

    @Override
    public void deleteOrder(Long id) {

    }
}
