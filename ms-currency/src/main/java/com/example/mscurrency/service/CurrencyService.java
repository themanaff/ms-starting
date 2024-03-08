package com.example.mscurrency.service;

import com.example.mscurrency.client.CurrencyClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CurrencyService {
    CurrencyClient currencyClient;

    public String getCurrencyData(String date){
        return currencyClient.getCurrencyData(date);
    }


}
