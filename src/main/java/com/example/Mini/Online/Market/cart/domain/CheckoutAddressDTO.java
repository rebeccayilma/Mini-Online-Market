package com.example.Mini.Online.Market.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutAddressDTO {
    @NotNull
    private long billingId;

    @NotNull
    private long shippingId;
}
