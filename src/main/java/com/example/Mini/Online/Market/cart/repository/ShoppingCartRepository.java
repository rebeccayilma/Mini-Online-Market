package com.example.Mini.Online.Market.cart.repository;

import com.example.Mini.Online.Market.cart.domain.ShoppingCart;
import com.example.Mini.Online.Market.mockfactory.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
    public Optional<ShoppingCart> findShoppingCartByUser(User user);
}
