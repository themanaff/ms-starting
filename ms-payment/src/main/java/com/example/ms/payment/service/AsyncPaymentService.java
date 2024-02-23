package com.example.ms.payment.service;

import com.example.ms.payment.dao.entity.PaymentsEntity;
import com.example.ms.payment.dao.repository.PaymentRepository;
import com.example.ms.payment.dto.client.request.UserPaymentTransferRequest;
import com.example.ms.payment.dto.client.response.GetUserByIdClientResponse;
import com.example.ms.payment.dto.request.PaymentRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AsyncPaymentService {
    PaymentRepository paymentRepository;
    UserService userService;

    @Async
    @SneakyThrows
    public void sendMoneyAsync(PaymentRequest request, GetUserByIdClientResponse creditorInfo,
                               GetUserByIdClientResponse debtorInfo){
        BigDecimal creditorNewBalance = creditorInfo.getBalance().add(request.getAmount());
        BigDecimal debtorNewBalance = debtorInfo.getBalance().subtract(request.getAmount());

        Thread.sleep(15000);

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
                .creditorUserAmount(request.getAmount())
                .build());
    }

}
