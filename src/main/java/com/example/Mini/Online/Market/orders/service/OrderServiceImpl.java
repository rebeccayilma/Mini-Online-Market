package com.example.Mini.Online.Market.orders.service;

import com.example.Mini.Online.Market.mockfactory.User;
import com.example.Mini.Online.Market.orders.domain.Order;
import com.example.Mini.Online.Market.orders.domain.OrderStatus;
import com.example.Mini.Online.Market.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Optional<Order> getOne(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrderStatus(long orderId, int orderStatus) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            if (isOrderStatusValid(orderStatus)) {
                order.get().setOrderStatus(OrderStatus.values()[orderStatus]);
                if (isOrderDelivered(orderStatus)) {
                    //handle order delivered => award points
                } else {
                    //send order in progress email
                }
                return orderRepository.save(order.get());
            } else {
                throw new NoSuchElementException("Order status not found. Try again");
            }
        } else {
            throw new NoSuchElementException("Order not found. Try again");
        }
    }

    public Boolean isOrderDelivered(int orderStatus) {
        int ordersLength = OrderStatus.values().length;
        return orderStatus == ordersLength - 1;
    }

    public Boolean isOrderStatusValid(int orderStatus) {
        int ordersLength = OrderStatus.values().length;
        return orderStatus <= ordersLength - 1;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderRepository.findAllByUserId(user.getId());
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
