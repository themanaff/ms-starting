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
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AsyncPaymentService {
    PaymentRepository paymentRepository;
    UserService userService;
    CurrencyService currencyService;
    @Async
    @SneakyThrows
    public void sendMoneyAsync(PaymentRequest request, GetUserByIdClientResponse creditorInfo,
                               GetUserByIdClientResponse debtorInfo){

        BigDecimal creditorAmount = request.getAmount();

        LocalDateTime date = LocalDateTime.now();
        String paymentTime = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        BigDecimal valueOfDebtorCurrency = currencyService.getValueOfCode(request.getDebtorCurrency(),paymentTime);
        BigDecimal valueOfCreditorCurrency = currencyService.getValueOfCode(request.getCreditorCurrency(),paymentTime);

        if(valueOfDebtorCurrency.compareTo(valueOfCreditorCurrency) < 0){
            creditorAmount = creditorAmount.divide(valueOfCreditorCurrency,2, RoundingMode.HALF_UP);
        } else if (valueOfDebtorCurrency.compareTo(valueOfCreditorCurrency) > 0) {
            creditorAmount = creditorAmount.multiply(valueOfCreditorCurrency);
        }

        userService.moneyTransferBetweenUsers(UserPaymentTransferRequest.builder()
                .amount(request.getAmount())
                .creditorCurrency(request.getCreditorCurrency())
                .debtorCurrency(request.getDebtorCurrency())
                .creditorUserId(creditorInfo.getId())
                .debtorUserId(debtorInfo.getId())
                .build());

        paymentRepository.save(PaymentsEntity.builder()
                .creditorUserCurrency(request.getCreditorCurrency())
                .debtorUserCurrency(request.getDebtorCurrency())
                .creditorUserId(creditorInfo.getId())
                .debtorUserId(debtorInfo.getId())
                .debtorUserAmount(request.getAmount())
                .creditorUserAmount(creditorAmount)
                .build());
    }

}
