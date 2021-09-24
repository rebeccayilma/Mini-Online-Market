package com.example.Mini.Online.Market.payment.controller;

import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.orders.domain.Order;
import com.example.Mini.Online.Market.payment.domain.Payment;
import com.example.Mini.Online.Market.payment.service.PaymentService;
import com.example.Mini.Online.Market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = {"http://localhost:3000"})
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity<?> getAllPayments(Authentication authentication) {
        Optional<User> userOptional = userService.getAuthenticatedUser(authentication);
        if (userOptional.isPresent()) {
            List<Payment> payments = paymentService.findAll();
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not authorized", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<?> getPaymentById(@PathVariable long paymentId, Authentication authentication) {
        Optional<User> userOptional = userService.getAuthenticatedUser(authentication);
        if (userOptional.isPresent()) {
            Optional<Payment> payment = paymentService.getOne(paymentId);
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not authorized", HttpStatus.UNAUTHORIZED);
        }
    }
}
