package com.example.ms.payment.service;

import com.example.ms.payment.client.UserClient;
import com.example.ms.payment.dto.client.request.UserPaymentTransferRequest;
import com.example.ms.payment.dto.client.response.GetUserByIdClientResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {
    UserClient userClient;

    public GetUserByIdClientResponse getUserByIdForDeptor(Long id) {
        GetUserByIdClientResponse userById = userClient.getUserById(id);
        if (userById.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            return userById;
        }
        throw new RuntimeException("User balance is insufficient");
    }

    public GetUserByIdClientResponse getUserById(Long id) {
        GetUserByIdClientResponse userById = userClient.getUserById(id);
        return userById;
    }

    public void moneyTransferBetweenUsers(UserPaymentTransferRequest request){
        userClient.transferMoney(request);
    }
}
