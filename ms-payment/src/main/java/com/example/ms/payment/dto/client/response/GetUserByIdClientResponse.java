package com.example.ms.payment.dto.client.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetUserByIdClientResponse {
    private Long id;

    private String name;
    private String surname;
    private BigDecimal balance;
    private String currency;
}
