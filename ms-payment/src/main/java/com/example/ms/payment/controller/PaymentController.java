package com.example.ms.payment.controller;

import com.example.ms.payment.dto.request.PaymentRequest;
import com.example.ms.payment.service.PaymentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PaymentController {
    PaymentService paymentService;

    @PostMapping("transfer-money")
    public void sendMoney(@RequestBody PaymentRequest request){
        paymentService.sendMoney(request);
    }



}
