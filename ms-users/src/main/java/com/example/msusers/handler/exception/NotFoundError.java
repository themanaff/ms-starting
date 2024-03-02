package com.example.msusers.handler.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class NotFoundError extends RuntimeException{
    String status;
    LocalDateTime occurredAt;

    public NotFoundError(String message, String status, LocalDateTime occurredAt) {
        super(message);
        this.status = status;
        this.occurredAt = occurredAt;
    }
}
