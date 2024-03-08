package com.example.mscurrency.controller;

import com.example.mscurrency.service.CurrencyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("currency")
public class CurrencyController {
    CurrencyService currencyService;

    @GetMapping("/{date}")
    public String getCurrencyData(@PathVariable String date){
        return currencyService.getCurrencyData(date);
    }
}
