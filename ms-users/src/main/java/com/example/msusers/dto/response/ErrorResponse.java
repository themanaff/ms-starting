package com.example.msusers.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE)
public class ErrorResponse {
    String message;
    String status;
    LocalDateTime occurredAt;

}
