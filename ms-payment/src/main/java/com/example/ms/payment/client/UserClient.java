package com.example.ms.payment.client;

import com.example.ms.payment.configuration.FeignDecoder;
import com.example.ms.payment.dto.client.request.UserPaymentTransferRequest;
import com.example.ms.payment.dto.client.response.GetUserByIdClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-users",url = "${urls.ms-users}",configuration = {FeignDecoder.class})
public interface UserClient {
    @GetMapping("{id}")
    GetUserByIdClientResponse getUserById(@PathVariable Long id);

    @PostMapping("money-transfer")
    void transferMoney(@RequestBody UserPaymentTransferRequest request);

}
