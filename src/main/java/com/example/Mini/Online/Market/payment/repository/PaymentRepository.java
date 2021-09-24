package com.example.Mini.Online.Market.payment.repository;

import com.example.Mini.Online.Market.payment.domain.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {
    List<Payment> findAll();
}
