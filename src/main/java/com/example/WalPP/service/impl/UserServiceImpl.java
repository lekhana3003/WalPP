package com.example.WalPP.service.impl;

import com.example.WalPP.dto.model.UserDTO;
import com.example.WalPP.dto.response.BalanceResponse;
import com.example.WalPP.dto.response.Response;
import com.example.WalPP.model.Users;
import com.example.WalPP.repository.UserRepository;
import com.example.WalPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.WalPP.constants.Constants.*;
import static com.example.WalPP.dto.response.Response.DuplicateEntity;
import static com.example.WalPP.dto.response.Response.ValidResponse;
import static com.example.WalPP.dto.response.Response.Exception;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Response createAccount(UserDTO userDTO) {
        Users user = new Users();
        user.setUser_id(userDTO.getUserId());
        user.setIntialBalance(userDTO.getIntialBalance());
        user.setBalance(userDTO.getBalance());
        Optional<Users> optional= Optional.ofNullable(userRepository.getByUserId(userDTO.getUserId()));
        if(optional.isPresent())
        {
            return DuplicateEntity(AccountAlreadyExists);
        }
        try {
            userRepository.save(user);
            return ValidResponse(AccountCreatedtText);
        } catch (Exception e){
            return Exception(AccountCreatedError);
        }
    }

    @Override
    public Response getBalance(UserDTO userDTO) {
        BalanceResponse balanceResponse= new BalanceResponse();

        try {
            balanceResponse.setBalance(userRepository.getBalance(userDTO.getUserId()));
            return ValidResponse(balanceResponse);
        }
        catch (Exception e)
        {
            return Exception(BalanceError);
        }
    }
}
