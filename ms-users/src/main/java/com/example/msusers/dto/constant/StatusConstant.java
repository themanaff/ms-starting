package com.example.msusers.dto.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public enum StatusConstant {
    ACTIVE(1),
    DE_ACTIVE(0);

    Integer status;
}
