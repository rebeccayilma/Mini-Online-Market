package com.example.Mini.Online.Market.service;

import com.example.Mini.Online.Market.domain.Payment;
import java.util.*;

public interface PaymentService {
    Payment save(Payment payment);
    List<Payment> findAll();
    Optional<Payment> getOne(long id);
}
