package com.example.ms.payment.dao.repository;

import com.example.ms.payment.dao.entity.PaymentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentsEntity,Long> {
    Optional<List<PaymentsEntity>> findAllByStatus(int status);
}
