package com.example.mscurrency.controller;

import com.example.mscurrency.service.CurrencyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("currencies")
public class CurrencyController {
    CurrencyService currencyService;

    @GetMapping("/{date}")
    public String getCurrencyData(@PathVariable String date){
        return currencyService.getCurrencyData(date);
    }

    @GetMapping()
    public BigDecimal getValueOfCode(@RequestParam String code,@RequestParam String date){
        return currencyService.getValueFromXml(code,date);
    }
}
