package com.example.ms.payment.dto.client.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserPaymentTransferRequest {
    Long debtorUserId;
    Long creditorUserId;

    BigDecimal debtorUserAmount;
    BigDecimal creditorUserAmount;

}
