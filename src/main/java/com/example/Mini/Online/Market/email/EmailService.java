package com.example.Mini.Online.Market.email;

import com.example.Mini.Online.Market.domain.Order;
import com.example.Mini.Online.Market.domain.Payment;
import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final String API_KEY = "82f6578a134744f2bfe1308a8332b1957272106e";
    Client client = new Client(API_KEY);

    @Async
    public void orderPlacementEmail(Payment payment) throws SparkPostException {
        client.sendMessage(
                "johnolwamba@wrostdevelopers.com",
                payment.getOrder().getUser().getEmail(),
                "Thank You For Your Order!.",
                "We have received your order and it is being processed",
                "<div>" +
                        "<p>Order confirmation # " + payment.getOrder().getId() + "</p>" +
                        "<p>Details:</p>" +
                        "<p>Total Amount: " + payment.getOrder().getAmount() + "</p>" +
                        "<p>Order Status: " + payment.getOrder().getOrderStatus() + "</p>" +
                        "<p>Ordered At: " + payment.getOrder().getCreated_at() + "</p>" +
                        "<p>Buyer: " + payment.getOrder().getUser().getName()+"</p>" +
                        "<p>Shipping Address: " + payment.getOrder().getShippingAddress().getStreet() + "," +
                        ", " + payment.getOrder().getShippingAddress().getCity() + ", " +
                        payment.getOrder().getShippingAddress().getState() + "</p>" +
                        "</br>"+
                        "<p>Payment Details</p>"+
                        "<p>Transaction Code: " + payment.getTransactionCode() +"</p>"+
                        "<p>Transaction Type: " + payment.getType()+"</p>"+
                        "</div>");

    }

    @Async
    public void orderStatusUpdate(Order order, String orderStatus) throws SparkPostException {
        client.sendMessage(
                "johnolwamba@wrostdevelopers.com",
                order.getUser().getEmail(),
                "Your order status update.",
                "There is an new status for order # " + order.getId(),
                "<div>" +
                        "<p>There is an new status for order # " + order.getId() + "</p>"+
                        "<p>Your order status is now: <b>" + orderStatus + "</b></p>" +
                        "</div>");

    }
}
