package com.example.ms.payment.cron;

import com.example.ms.payment.service.PaymentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE,makeFinal = true)
public class MoneyTransferCron {
    PaymentService paymentService;
    @Scheduled(fixedDelayString = "PT10S")//every 10seconds
    @SchedulerLock(name = "update-any-info-in-something",
            lockAtLeastFor = "PT15S",
            lockAtMostFor = "PT30M"
    )
    public void updateAnyData() {
        paymentService.sendPaymentToAzericard();
    }

//    @Scheduled(cron = "${scheduler.cron.expired-something}")
//    public void updateAnyData() {
//        paymentService.sendPaymentToAzericard();
//    }
}
