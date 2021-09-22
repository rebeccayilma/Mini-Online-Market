package com.example.Mini.Online.Market.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private String city;
    private String state;

    @Override
    public String toString() {
        return "Address: " +
                "\nStreet: " + street + '\'' +
                "\nCity='" + city + '\'' +
                "\nState='" + state + '\'';
    }
}
