package com.example.Mini.Online.Market.cart.domain;

import com.example.Mini.Online.Market.mockfactory.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    int quantity;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
