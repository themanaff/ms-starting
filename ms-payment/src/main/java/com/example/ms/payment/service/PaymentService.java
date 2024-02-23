package com.example.ms.payment.service;

import com.example.ms.payment.dao.repository.PaymentRepository;
import com.example.ms.payment.dto.client.response.GetUserByIdClientResponse;
import com.example.ms.payment.dto.request.PaymentRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PaymentService {
    PaymentRepository paymentRepository;
    UserService userService;
    AsyncPaymentService asyncPaymentService;

    public void sendMoney(PaymentRequest request){
        var creditorInfo = userService.getUserById(request.getCreditorUserId());
        var debtorInfo = userService.getUserByIdForDeptor(request.getDebtorUserId());

        checkDebtorAmount(request,debtorInfo);

        asyncPaymentService.sendMoneyAsync(request,creditorInfo,debtorInfo);
    }
    public void checkDebtorAmount(PaymentRequest request,GetUserByIdClientResponse debtorInfo ){
        if (debtorInfo.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient amount");
        }
    }
}
