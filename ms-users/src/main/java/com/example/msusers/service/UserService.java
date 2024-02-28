package com.example.msusers.service;

import com.example.msusers.dao.entity.UsersEntity;
import com.example.msusers.dao.repository.UserRepository;
import com.example.msusers.dto.request.UserPaymentTransferRequest;
import com.example.msusers.dto.response.GetUserByIdResponse;
import com.example.msusers.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.example.msusers.dto.constant.CurrencyConstant.AZN;
import static com.example.msusers.dto.constant.StatusConstant.ACTIVE;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public GetUserByIdResponse getUserById(Long id){
        UsersEntity userEntity = fetchActiveUserById(id);
        return UserMapper.INSTANCE.toResponse(userEntity);
    }

    private UsersEntity fetchActiveUserById(Long id) {
        return userRepository.findByIdAndStatus(id, ACTIVE.getStatus()).orElseThrow(
                () -> new RuntimeException("User with " + " id couldn't be found"));
    }

//    @PostConstruct
    public void saveUser(){
        userRepository.save(UsersEntity.builder()
                        .name("Samir")
                        .surname("Abbasli")
                        .balance(BigDecimal.valueOf(15000))
                        .currency(AZN.name())
                        .birthDate(LocalDateTime.now().minusYears(20))
                .build());
        userRepository.save(UsersEntity.builder()
                        .name("Malik")
                        .surname("Qurbanli")
                        .balance(BigDecimal.valueOf(6700))
                        .currency(AZN.name())
                        .birthDate(LocalDateTime.now().minusYears(25))
                .build());
    }

    public void transferMoney(UserPaymentTransferRequest request){
        var creditorUser = fetchActiveUserById(request.getCreditorUserId());
        var debtorUser = fetchActiveUserById(request.getDebtorUserId());
        creditorUser.setBalance(request.getCreditorUserAmount());
        debtorUser.setBalance(request.getDebtorUserAmount());
        userRepository.save(creditorUser);
        userRepository.save(debtorUser);
    }


}
