package com.example.Mini.Online.Market.cart.service;

import com.example.Mini.Online.Market.cart.domain.ShoppingCart;
import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.orders.domain.Order;

import java.util.Optional;

public interface ShoppingCartService {
    public ShoppingCart addToCart(Long productId, int quantity, User user);

    public ShoppingCart removeFromCart(Long productId, User user);

    public Order checkoutCart(Long cartId, User user);

    public Optional<ShoppingCart> getCart(User user);

    public ShoppingCart addAddressToCart(User user, long billingId, long shippingId);
}
