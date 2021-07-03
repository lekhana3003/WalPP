package com.example.WalPP.controller;
import com.example.WalPP.dto.model.UserDTO;
import com.example.WalPP.dto.request.BalanceRequest;
import com.example.WalPP.dto.request.CreateAccountRequest;
import com.example.WalPP.dto.response.Response;
import com.example.WalPP.service.impl.UserServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

@Autowired
UserServiceImpl userService;
    @PostMapping(path="/createWalletAccount")
    public @ResponseBody
    String addNewUser (@RequestBody CreateAccountRequest createAccountRequest) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(createAccountRequest.getUserId());
        userDTO.setBalance(createAccountRequest.getIntialBalance());
        userDTO.setIntialBalance(createAccountRequest.getIntialBalance());
        Gson gson = new Gson();
        return gson.toJson(userService.createAccount(userDTO));
    }
    @PostMapping(path="/getBalance")
    public @ResponseBody
    String getBalance (@RequestBody BalanceRequest balanceRequest) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(balanceRequest.getUserId());
        Gson gson = new Gson();
        return gson.toJson(userService.getBalance(userDTO));
    }
}