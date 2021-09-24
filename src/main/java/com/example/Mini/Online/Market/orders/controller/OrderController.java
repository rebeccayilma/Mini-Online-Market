package com.example.Mini.Online.Market.orders.controller;

import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.orders.domain.Order;
import com.example.Mini.Online.Market.orders.domain.OrderUpdateDTO;
import com.example.Mini.Online.Market.orders.service.OrderService;
import com.example.Mini.Online.Market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = {"http://localhost:3000"})
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllOrders(Authentication authentication) {
        Optional<User> userOptional = userService.getAuthenticatedUser(authentication);
        if (userOptional.isPresent()) {
            List<Order> orders = orderService.getAllOrders();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not authorized", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/history")
    public ResponseEntity<?> getOrderHistory(Authentication authentication) {
        Optional<User> userOptional = userService.getAuthenticatedUser(authentication);
        if (userOptional.isPresent()) {
            List<Order> ordersHistory = orderService.getOrderHistory(userOptional.get());
            return new ResponseEntity<>(ordersHistory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not authorized", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable long orderId, Authentication authentication) {
        Optional<User> userOptional = userService.getAuthenticatedUser(authentication);
        if (userOptional.isPresent()) {
            Optional<Order> order = orderService.getOne(orderId);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not authorized", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/{orderId}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable long orderId,
                                               @RequestBody OrderUpdateDTO orderDTO,
                                               Authentication authentication) {

        Optional<User> userOptional = userService.getAuthenticatedUser(authentication);
        if (userOptional.isPresent()) {
            Order order = orderService.updateOrderStatus(orderId, orderDTO.getOrderStatus());
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not authorized", HttpStatus.UNAUTHORIZED);
        }
    }
}