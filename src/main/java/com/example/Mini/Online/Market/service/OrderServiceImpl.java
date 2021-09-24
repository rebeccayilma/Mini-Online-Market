package com.example.Mini.Online.Market.service;

import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.email.EmailService;
import com.example.Mini.Online.Market.domain.Order;
import com.example.Mini.Online.Market.domain.OrderStatus;
import com.example.Mini.Online.Market.repository.OrderRepository;
import com.example.Mini.Online.Market.util.exeptionhandler.EntityNotFoundException;
import com.sparkpost.exception.SparkPostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    final static Integer POINTS_AWARDED = 10;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserPointService userPointService;

    @Autowired
    EmailService emailService;

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
            if (isOrderStatusValid(orderStatus, order.get())) {
                order.get().setOrderStatus(OrderStatus.values()[orderStatus]);
                if (isOrderDelivered(orderStatus)) {
                    userPointService.incrementPoints(order.get().getUser(), POINTS_AWARDED);
                }
                try {
                    emailService.orderStatusUpdate(order.get(), (OrderStatus.values()[orderStatus]).name());
                } catch (SparkPostException exception) {
                    System.out.println(exception);
                }

                return orderRepository.save(order.get());
            } else {
                throw new EntityNotFoundException("Order status not found. Try again");
            }
        } else {
            throw new EntityNotFoundException("Order not found. Try again");
        }
    }

    public Boolean isOrderDelivered(int orderStatus) {
        return orderStatus == 3;
    }

    public Boolean isOrderStatusValid(int orderStatus, Order order) {
        int ordersLength = OrderStatus.values().length;
        if(orderStatus == 4 && order.getOrderStatus() != OrderStatus.PLACED){
            return false;
        } else if (orderStatus <= ordersLength - 1){
            return true;
        } else {
            return false;
        }
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
