package com.example.Mini.Online.Market.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private String price;

//    private Seller seller;
//
//    private List<Order> orderlist;
//
//    private List<Review> review;
}
