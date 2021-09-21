package com.example.Mini.Online.Market.email;

import com.example.Mini.Online.Market.orders.domain.Order;
import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Async
    public void orderPlacementEmail(Order order) throws SparkPostException {
        String API_KEY = "82f6578a134744f2bfe1308a8332b1957272106e";
        Client client = new Client(API_KEY);

        client.sendMessage(
                "johnolwamba@wrostdevelopers.com",
                order.getUser().getEmail(),
                "Thank You For Your Order!.",
                "We have received your order and it is being processed",
                "<div>" +
                        "<p>Order confirmation # " + order.getId() + "</p>" +
                        "<p>Details:</p>" +
                        "<p>Total Amount: " + order.getAmount() + "</p>" +
                        "<p>Order Status: " + order.getOrderStatus() + "</p>" +
                        "<p>Ordered At: " + order.getCreated_at() + "</p>" +
                        "<p>Buyer: " + order.getUser().getFirstName() + " " + order.getUser().getLastName() + "</p>" +
                        "<p>Shipping Address: " + order.getShippingAddress().getStreet() + "," +
                        ", " + order.getShippingAddress().getCity() + ", " +
                        order.getShippingAddress().getState() + "</p>" +
                        "</div>");

    }
}
