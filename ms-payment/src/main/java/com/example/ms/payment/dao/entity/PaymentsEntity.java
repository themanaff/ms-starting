package com.example.ms.payment.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "payment")
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long debtorUserId;
    Long creditorUserId;

    BigDecimal debtorUserAmount;
    BigDecimal creditorUserAmount;

    String debtorUserCurrency;
    String creditorUserCurrency;

    Integer status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @PrePersist
    public void assignDefaultValues(){
        createdAt = LocalDateTime.now();
        status = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentsEntity that = (PaymentsEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
