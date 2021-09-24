package com.example.Mini.Online.Market.service;

import com.example.Mini.Online.Market.domain.ProcessPaymentDTO;
import com.example.Mini.Online.Market.domain.ShoppingCart;
import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.domain.Payment;

import java.util.Optional;

public interface ShoppingCartService {
    public ShoppingCart addToCart(Long productId, int quantity, User user);

    public ShoppingCart removeFromCart(Long productId, User user);

    public Payment checkoutCart(ProcessPaymentDTO processPaymentDTO, User user);

    public Optional<ShoppingCart> getCart(User user);

    public ShoppingCart addAddressToCart(User user, long billingId, long shippingId);
}
