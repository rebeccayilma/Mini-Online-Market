package com.example.Mini.Online.Market.cart.service;

import com.example.Mini.Online.Market.cart.domain.ShoppingCart;
import com.example.Mini.Online.Market.mockfactory.User;

import java.util.Optional;

public interface ShoppingCartService {
    public ShoppingCart addToCart(Long productId, int quantity, User user);

    public ShoppingCart removeFromCart(Long productId, User user);

    public Optional<ShoppingCart> checkoutCart(Long cartId, User user);

    public Optional<ShoppingCart> getCart(User user);
}
