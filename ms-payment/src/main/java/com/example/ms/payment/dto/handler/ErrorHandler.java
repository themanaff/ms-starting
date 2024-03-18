package com.example.ms.payment.dto.handler;

import com.example.ms.payment.dto.handler.exception.NotFoundError;
import com.example.ms.payment.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundError.class)
    public com.example.ms.payment.dto.response.ErrorResponse handle(NotFoundError error) {
        log.error("ActionLog.handle.error: {}", error);
        return com.example.ms.payment.dto.response.ErrorResponse.builder()
                .message(error.getMessage())
                .status(error.getStatus())
                .occurredAt(error.getOccurredAt())
                .build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public com.example.ms.payment.dto.response.ErrorResponse handle(RuntimeException error) {
        log.error("ActionLog.handle.error: {}", error);
        return ErrorResponse.builder()
                .message(error.getMessage())
                .occurredAt(LocalDateTime.now())
                .build();
    }

}
