package com.example.ms.payment.service;

import com.example.ms.payment.dao.entity.PaymentsEntity;
import com.example.ms.payment.dao.repository.PaymentRepository;
import com.example.ms.payment.dto.client.request.NotificationRequest;
import com.example.ms.payment.dto.client.response.GetUserByIdClientResponse;
import com.example.ms.payment.dto.constant.StatusConstant;
import com.example.ms.payment.dto.request.PaymentRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.ms.payment.dto.constant.StatusConstant.ACTIVE;
import static com.example.ms.payment.dto.constant.StatusConstant.DE_ACTIVE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentService {
    PaymentRepository paymentRepository;
    UserService userService;
    AsyncPaymentService asyncPaymentService;
    NotificationService notificationService;

    public void sendMoney(PaymentRequest request) {
        var creditorInfo = userService.getUserById(request.getCreditorUserId());
        var debtorInfo = userService.getUserByIdForDeptor(request.getDebtorUserId());

        checkDebtorAmount(request, debtorInfo);

        asyncPaymentService.sendMoneyAsync(request, creditorInfo, debtorInfo);
    }

    public void sendPaymentToAzericard() {
        List<PaymentsEntity> paymentsEntities = fetchAllDeactivePayments();
        paymentsEntities.forEach(payment -> {
            payment.setStatus(ACTIVE.getStatus());
            paymentRepository.save(payment);
            notificationService.sendNotification(NotificationRequest.builder()
                            .message("Odenisiniz ugurlu olmusdur ")
                            .userId(payment.getDebtorUserId())
                    .build());
            notificationService.sendNotification(NotificationRequest.builder()
                            .message("Balansiniz artirilmisdir")
                            .userId(payment.getCreditorUserId())
                    .build());
        });
    }

    public void sendMoneyFromDebtorToCreditor(){

    }

    private List<PaymentsEntity> fetchAllDeactivePayments() {
        return paymentRepository.findAllByStatus(DE_ACTIVE.getStatus())
                .orElseGet(ArrayList::new);
    }

    private void checkDebtorAmount(PaymentRequest request, GetUserByIdClientResponse debtorInfo) {
        if (debtorInfo.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient amount");
        }
    }
}
