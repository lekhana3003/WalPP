package com.example.WalPP.service;

import com.example.WalPP.dto.model.UserDTO;
import com.example.WalPP.dto.response.Response;

public interface UserService {
     Response createAccount(UserDTO userDTO);
     Response getBalance(UserDTO userDTO);
}
