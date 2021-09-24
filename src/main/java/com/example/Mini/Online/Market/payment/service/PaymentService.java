package com.example.Mini.Online.Market.payment.service;

import com.example.Mini.Online.Market.payment.domain.Payment;
import java.util.*;

public interface PaymentService {
    Payment save(Payment payment);
    List<Payment> findAll();
}
