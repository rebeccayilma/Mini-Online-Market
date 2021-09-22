package com.example.Mini.Online.Market.orders.controller;

import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.orders.domain.Order;
import com.example.Mini.Online.Market.orders.domain.OrderUpdateDTO;
import com.example.Mini.Online.Market.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("")
    public ResponseEntity<?> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<?> getOrderHistory() {
        User user = mockUser();
        List<Order> ordersHistory = orderService.getOrderHistory(user);
        return new ResponseEntity<>(ordersHistory, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable long orderId) {
        Optional<Order> order = orderService.getOne(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/{orderId}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable long orderId, @RequestBody OrderUpdateDTO orderDTO) {
        Order order = orderService.updateOrderStatus(orderId, orderDTO.getOrderStatus());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    public User mockUser() {
        return new User(1L, "Johnstone", "Ananda", "johnolwamba@gmail.com");
    }
}
