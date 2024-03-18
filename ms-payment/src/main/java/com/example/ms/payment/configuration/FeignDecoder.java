package com.example.ms.payment.configuration;

import com.example.ms.payment.dto.handler.exception.NotFoundError;
import com.example.ms.payment.dto.response.ErrorResponse;
import com.example.ms.payment.util.ObjectMapperUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.nio.ByteBuffer;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class FeignDecoder implements ErrorDecoder {
    private final ObjectMapper mapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();

        FeignException exception = feign.FeignException.errorStatus(methodKey, response);
        Optional<ByteBuffer> responseBodyOptional = exception.responseBody();

        if (status == HttpStatus.NOT_FOUND.value()) {
            if (responseBodyOptional.isPresent()) {
                ByteBuffer responseBody = responseBodyOptional.get();
                ErrorResponse readValue = ObjectMapperUtil
                        .readValue(mapper, responseBody, ErrorResponse.class);

                return new NotFoundError(
                        readValue.getMessage(), readValue.getStatus(), readValue.getOccurredAt()
                );
            }
        }
        throw new RuntimeException();
    }
}