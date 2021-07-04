package com.example.WalPP.service;

import com.example.WalPP.dto.model.TransactionDTO;
import com.example.WalPP.dto.response.Response;

public interface TransactionService {
    Response creditTransaction(TransactionDTO transactionDTO);
    Response debitTransaction(TransactionDTO transactionDTO);
    Response transactionHistory(TransactionDTO transactionDTO);
}
