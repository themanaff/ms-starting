package com.example.msusers.service;

import com.example.msusers.dao.entity.UsersEntity;
import com.example.msusers.dao.repository.UserRepository;
import com.example.msusers.dto.request.UserPaymentTransferRequest;
import com.example.msusers.dto.response.GetUserByIdResponse;
import com.example.msusers.handler.exception.NotFoundError;
import com.example.msusers.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.msusers.dto.constant.CurrencyConstant.AZN;
import static com.example.msusers.dto.constant.StatusConstant.ACTIVE;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {
    UserRepository userRepository;
    CurrencyService currencyService;

    public GetUserByIdResponse getUserById(Long id){
        UsersEntity userEntity = fetchActiveUserById(id);
        return UserMapper.INSTANCE.toResponse(userEntity);
    }

    private UsersEntity fetchActiveUserById(Long id) {
        return userRepository.findByIdAndStatus(id, ACTIVE.getStatus()).orElseThrow(
                () -> new NotFoundError("User with id " + id + " couldn't be found","NOT_FOUND",LocalDateTime.now()));
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

    public void checkDebtorBalance(UserPaymentTransferRequest request, UsersEntity debtorUser){
        debtorUser = fetchActiveUserById(request.getDebtorUserId());
        if(debtorUser.getBalance().compareTo(request.getAmount()) < 0){
            throw new RuntimeException("Debtor has no enough money to send");
        }
    }

    @Transactional
    public void transferMoney(UserPaymentTransferRequest request){

        var debtorUser = fetchActiveUserById(request.getDebtorUserId());
        var creditorUser = fetchActiveUserById(request.getCreditorUserId());

        checkDebtorBalance(request,debtorUser);

        LocalDateTime date = LocalDateTime.now();
        String paymentTime = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        BigDecimal valueOfDebtorCurrency = currencyService.getValueOfCode(request.getDebtorCurrency(),paymentTime);
        BigDecimal valueOfCreditorCurrency = currencyService.getValueOfCode(request.getCreditorCurrency(),paymentTime);

        BigDecimal creditorNewBalance = creditorUser.getBalance();
        BigDecimal debtorNewBalance = debtorUser.getBalance();

        if(valueOfDebtorCurrency.compareTo(valueOfCreditorCurrency) < 0){
             creditorNewBalance = creditorNewBalance.add(request.getAmount().divide(valueOfCreditorCurrency,2, RoundingMode.HALF_UP));
             debtorNewBalance = debtorNewBalance.subtract(request.getAmount().divide(valueOfDebtorCurrency,2, RoundingMode.HALF_UP));
        } else if (valueOfDebtorCurrency.compareTo(valueOfCreditorCurrency) > 0) {
             creditorNewBalance = creditorNewBalance.add(request.getAmount().multiply(valueOfCreditorCurrency));
             debtorNewBalance = debtorNewBalance.subtract(request.getAmount().multiply(valueOfDebtorCurrency));
        }else {
             creditorNewBalance = creditorNewBalance.add(request.getAmount());
             debtorNewBalance = debtorNewBalance.subtract(request.getAmount());
        }

        creditorUser.setBalance(creditorNewBalance);
        debtorUser.setBalance(debtorNewBalance);
        userRepository.save(creditorUser);
        userRepository.save(debtorUser);
    }


}
