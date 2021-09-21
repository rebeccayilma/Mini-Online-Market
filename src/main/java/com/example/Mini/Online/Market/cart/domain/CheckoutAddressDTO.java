package com.example.Mini.Online.Market.cart.domain;

import com.example.Mini.Online.Market.mockfactory.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutAddressDTO {
    private long billingId;
    private long shippingId;
}
