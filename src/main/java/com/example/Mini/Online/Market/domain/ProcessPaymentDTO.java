package com.example.Mini.Online.Market.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessPaymentDTO {
    @NotEmpty
    private String transactionCode;
    @NotEmpty
    private String addressCity;
    private String addressCountry;
    private String addressState;
    private String addressZip;

    @NotEmpty
    private String email;
    @NotEmpty

    private String type;
}
