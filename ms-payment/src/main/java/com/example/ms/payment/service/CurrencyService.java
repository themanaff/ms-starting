package com.example.ms.payment.service;



import com.example.ms.payment.client.CurrencyClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CurrencyService {
    CurrencyClient currencyClient;
    public BigDecimal getValueOfCode(String code,String date){
        return currencyClient.getValueOfCode(code,date);
    }
}
