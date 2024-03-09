package com.example.ms.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "ms-currencies",url = "${urls.ms-currencies}")
public interface CurrencyClient {
    @GetMapping
    BigDecimal getValueOfCode(@RequestParam String code, @RequestParam String date);
}
