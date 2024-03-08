package com.example.mscurrency.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cbar-az",url = "${urls.cbar-az}")
public interface CurrencyClient {
    @GetMapping("/{date}.xml")
    String getCurrencyData(@PathVariable String date);

}
