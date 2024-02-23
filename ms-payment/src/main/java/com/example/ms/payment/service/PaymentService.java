package com.example.ms.payment.service;

import com.example.ms.payment.dao.entity.PaymentsEntity;
import com.example.ms.payment.dao.repository.PaymentRepository;
import com.example.ms.payment.dto.client.request.UserPaymentTransferRequest;
import com.example.ms.payment.dto.request.PaymentRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PaymentService {
    PaymentRepository paymentRepository;
    UserService userService;

    public void sendMoney(PaymentRequest request){
        var creditorInfo = userService.getUserById(request.getCreditorUserId());
        var debtorInfo = userService.getUserByIdForDeptor(request.getDebtorUserId());

        if (debtorInfo.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient amount");
        }
        BigDecimal creditorNewBalance = creditorInfo.getBalance().add(request.getAmount());
        BigDecimal debtorNewBalance = debtorInfo.getBalance().subtract(request.getAmount());

        userService.moneyTransferBetweenUsers(UserPaymentTransferRequest.builder()
                        .creditorUserAmount(creditorNewBalance)
                        .debtorUserAmount(debtorNewBalance)
                        .creditorUserId(creditorInfo.getId())
                        .debtorUserId(debtorInfo.getId())
                .build());

        paymentRepository.save(PaymentsEntity.builder()
                        .creditorUserCurrency(creditorInfo.getCurrency())
                        .debtorUserCurrency(debtorInfo.getCurrency())
                        .creditorUserId(creditorInfo.getId())
                        .debtorUserId(debtorInfo.getId())
                        .debtorUserAmount(request.getAmount())
                        .
                .build())
    }
}
