package com.example.ms.payment.cron;

import com.example.ms.payment.service.PaymentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE,makeFinal = true)
public class MoneyTransferCron {
    PaymentService paymentService;
    @Scheduled(fixedDelayString = "PT10S")//every 10seconds
    public void updateAnyData() {
        paymentService.sendPaymentToAzericard();
    }
}
