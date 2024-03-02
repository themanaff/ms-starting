package com.example.msusers.handler;

import com.example.msusers.dto.response.ErrorResponse;
import com.example.msusers.handler.exception.NotFoundError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundError.class)
    public ErrorResponse handle(NotFoundError error) {
        log.error("ActionLog.handle.error: {}", error);
        return ErrorResponse.builder()
                .message(error.getMessage())
                .status(error.getStatus())
                .occurredAt(error.getOccurredAt())
                .build();
    }

}
