package com.example.WalPP.controller;
import com.example.WalPP.config.LocalDateTimeSerializer;
import com.example.WalPP.config.LocalDateTimeDeserializer;
import com.example.WalPP.dto.model.TransactionDTO;
import com.example.WalPP.dto.request.*;
import com.example.WalPP.model.TransactionType;
import com.example.WalPP.service.impl.TransactionServiceImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Controller
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionService;
    Gson gson;

    @PostConstruct
    private void postConstruct() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        //gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        gson = gsonBuilder.setPrettyPrinting().create();
    }
    @PostMapping(path="/credit")
    public @ResponseBody
    String credit (@RequestBody CreditTransactionRequest creditTransactionRequest) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setUserId(creditTransactionRequest.getUserId());
        transactionDTO.setAmount(creditTransactionRequest.getAmount());
        transactionDTO.setPartyName(creditTransactionRequest.getPartyName());
        transactionDTO.setTransactionDetails(creditTransactionRequest.getTransactionDetails());
        transactionDTO.setTransactionID(creditTransactionRequest.getTransactionID());
        transactionDTO.setTransactionType(TransactionType.CREDIT);
        Gson gson = new Gson();
        return gson.toJson(transactionService.creditTransaction(transactionDTO));
    }
    @PostMapping(path="/debit")
    public @ResponseBody
    String debit (@RequestBody DebitTransactionRequest debitTransactionRequest) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setUserId(debitTransactionRequest.getUserId());
        transactionDTO.setAmount(debitTransactionRequest.getAmount());
        transactionDTO.setPartyName(debitTransactionRequest.getPartyName());
        transactionDTO.setTransactionDetails(debitTransactionRequest.getTransactionDetails());
        transactionDTO.setTransactionID(debitTransactionRequest.getTransactionID());
        transactionDTO.setTransactionType(TransactionType.DEBIT);
        //Gson gson = new Gson();
        return gson.toJson(transactionService.debitTransaction(transactionDTO));
    }
    @PostMapping(path="/getTransactions")
    public @ResponseBody
    String transactions (@RequestBody TransactionHistoryRequest transactionHistoryRequest) {

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAftertimestamp(transactionHistoryRequest.getAftertimestamp());
        transactionDTO.setBeforetimestamp(transactionHistoryRequest.getBeforetimestamp());
        transactionDTO.setTransactionID(transactionHistoryRequest.getTransactionID());
        transactionDTO.setUserId(transactionHistoryRequest.getUserId());
        if ("CREDIT".equals(transactionHistoryRequest.getTransactionType()))
            transactionDTO.setTransactionType(TransactionType.CREDIT);
        else if("DEBIT".equals(transactionHistoryRequest.getTransactionType()))
            transactionDTO.setTransactionType(TransactionType.DEBIT);
        return gson.toJson(transactionService.transactionHistory(transactionDTO));
    }
}