package com.example.Mini.Online.Market.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String transactionCode;
    private String type;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", transactionCode='" + transactionCode + '\'' +
                ", type='" + type + '\'' +
                ", order=" + order +
                ", created_at=" + created_at +
                '}';
    }
}
