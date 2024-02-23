package com.example.ms.payment.client;

import com.example.ms.payment.dto.client.request.UserPaymentTransferRequest;
import com.example.ms.payment.dto.client.response.GetUserByIdClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-users",url = "${urls.ms-users}")
public interface UserClient {
    @GetMapping("{id}")
    GetUserByIdClientResponse getUserById(@PathVariable Long id);

    @PutMapping("/money-transfer")
    void transferMoney(@RequestBody UserPaymentTransferRequest request);



}
