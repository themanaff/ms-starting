package com.example.msusers.controller;

import com.example.msusers.dto.request.UserPaymentTransferRequest;
import com.example.msusers.dto.response.GetUserByIdResponse;
import com.example.msusers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("{id}")
    public GetUserByIdResponse getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @PostMapping("/money-transfer")
    public void transferMoney(@RequestBody UserPaymentTransferRequest request){
        userService.transferMoney(request);
    }
}
