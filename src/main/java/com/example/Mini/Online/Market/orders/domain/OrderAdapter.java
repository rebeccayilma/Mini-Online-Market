package com.example.Mini.Online.Market.orders.domain;

import com.example.Mini.Online.Market.cart.domain.ShoppingCart;
import com.example.Mini.Online.Market.domain.Product;

import java.sql.Date;
import java.util.ArrayList;

public class OrderAdapter {
    public static Order parseCartToOrder(ShoppingCart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setAmount(cart.getTotalPrice());
        order.setOrderStatus(OrderStatus.PLACED);
        order.setCreated_at(new Date(System.currentTimeMillis()));
        order.setShippingAddress(cart.getShippingAddress());
        order.setBillingAddress(cart.getBillingAddress());

        ArrayList<OrderLine> newLines = new ArrayList<>();
        for (int i = 0; i < cart.getCartLine().size(); i++) {

            OrderLine orderLine = new OrderLine();
            orderLine.setQuantity(cart.getCartLine().get(i).getQuantity());
            Product product = new Product();
            product.setQuantity(cart.getCartLine().get(i).getProduct().getQuantity());
            product.setPrice(cart.getCartLine().get(i).getProduct().getPrice());
            product.setQuantity(cart.getCartLine().get(i).getProduct().getQuantity());
            orderLine.setProduct(product);

            newLines.add(orderLine);
        }

        order.setOrderLine(newLines);
        return order;
    }
}
