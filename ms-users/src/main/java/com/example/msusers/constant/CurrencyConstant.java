package com.example.msusers.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public enum CurrencyConstant {
    AZN(1D),USD(1.7),EUR(1.8);
    Double value;
}
