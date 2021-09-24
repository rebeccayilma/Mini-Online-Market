package com.example.Mini.Online.Market.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessPaymentDTO {
    private String transactionCode;
    private String addressCity;
    private String addressCountry;
    private String addressState;
    private String addressZip;
    private String email;
    private String type;
}
