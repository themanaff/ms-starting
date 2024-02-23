package com.example.ms.payment.dao.repository;

import com.example.ms.payment.dao.entity.PaymentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentsEntity,Long> {

}
