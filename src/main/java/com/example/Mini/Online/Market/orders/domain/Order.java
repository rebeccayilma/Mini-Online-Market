package com.example.Mini.Online.Market.orders.domain;

import com.example.Mini.Online.Market.cart.domain.CartLine;
import com.example.Mini.Online.Market.mockfactory.Address;
import com.example.Mini.Online.Market.mockfactory.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double amount;

    private OrderStatus orderStatus;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_line_items", referencedColumnName = "id")
    private List<OrderLine> orderLine;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private Address shippingAddress;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (OrderLine cline : orderLine) {
            totalPrice = totalPrice + (cline.getProduct().getPrice() * cline.getQuantity());
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order: " +
                "Total Amount: " + amount +
                "\nOrder Status: " + orderStatus +
                "\nBuyer Details: " + user +
                "\nItems: " + orderLine +
                "\nBillingAddress: " + billingAddress +
                "\nShippingAddress: " + shippingAddress +
                "Created At=" + created_at;
    }
}
