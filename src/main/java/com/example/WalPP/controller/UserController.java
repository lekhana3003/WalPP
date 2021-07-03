package com.example.WalPP.controller;

import com.example.WalPP.model.Users;
import com.example.WalPP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository repo;

    @PostMapping(path="/createWalletAccount") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser (@RequestParam Integer user_id
            , @RequestParam Float intial_balance
            , @RequestParam Float balance) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Users users = new Users();
        users.setUser_id(user_id);
        users.setIntial_balance(intial_balance);
        users.setBalance(balance);
        repo.save(users);
        return "Saved";
    }
}