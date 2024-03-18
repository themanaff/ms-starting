package com.example.ms.payment.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.ByteBuffer;

@UtilityClass
public class ObjectMapperUtil {

    // decode bytecode into DTO
    public <T> T readValue(ObjectMapper mapper, ByteBuffer byteBuffer, Class<T> clazz) {
        byte[] responseArray = byteBuffer.array();

// Decode the byte array into a human-readable String
        String jsonResponse = new String(responseArray);

        try {
            return mapper.readValue(jsonResponse, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}