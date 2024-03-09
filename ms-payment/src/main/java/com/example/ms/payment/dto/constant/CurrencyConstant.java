package com.example.ms.payment.dto.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public enum CurrencyConstant {
    AZN("AZN"),USD("USD"),EUR("EUR");
    String value;
}
