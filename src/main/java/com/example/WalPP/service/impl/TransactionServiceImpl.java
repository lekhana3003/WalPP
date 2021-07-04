package com.example.WalPP.service.impl;

import com.example.WalPP.dto.model.TransactionDTO;
import com.example.WalPP.dto.response.Response;
import com.example.WalPP.model.TransactionType;
import com.example.WalPP.model.Transactions;
import com.example.WalPP.model.Users;
import com.example.WalPP.repository.TransactionRepository;
import com.example.WalPP.repository.UserRepository;
import com.example.WalPP.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.WalPP.constants.Constants.*;
import static com.example.WalPP.dto.response.Response.*;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public Response creditTransaction(TransactionDTO transactionDTO) {

        Optional<Users> optional= Optional.ofNullable(userRepository.getByUserId(transactionDTO.getUserId()));
        if(optional.isPresent())
        {
            Transactions transaction = new Transactions();
            transaction.setUserId(transactionDTO.getUserId());
            transaction.setAmount(transactionDTO.getAmount());
            transaction.setPartyName(transactionDTO.getPartyName());
            transaction.setTransactionDetails(transactionDTO.getTransactionDetails());
            transaction.setTransactionID(transactionDTO.getTransactionID());
            transaction.setTransactionType("CREDIT");
            try {
                transactionRepository.save(transaction);
                Float updated_balance = optional.get().getBalance() + transactionDTO.getAmount();
                userRepository.updateBalance(transactionDTO.getUserId(),updated_balance );
                return Response.ValidResponse(TransactionSuccess);
            }
            catch (Exception e){
                return Exception(TransactionFailed);
            }
        }
        else{
            return Response.ValidResponse(TransactionFailed);
        }
    }

    @Override
    public Response debitTransaction(TransactionDTO transactionDTO) {
        Optional<Users> optional= Optional.ofNullable(userRepository.getByUserId(transactionDTO.getUserId()));
        if(optional.isPresent())
        {
            float amount_after=optional.get().getBalance()-transactionDTO.getAmount();
            if (amount_after>=0){
            Transactions transaction = new Transactions();
            transaction.setUserId(transactionDTO.getUserId());
            transaction.setAmount(transactionDTO.getAmount());
            transaction.setPartyName(transactionDTO.getPartyName());
            transaction.setTransactionDetails(transactionDTO.getTransactionDetails());
            transaction.setTransactionID(transactionDTO.getTransactionID());
            transaction.setTransactionType("DEBIT");
            try {
                transactionRepository.save(transaction);
                userRepository.updateBalance(transactionDTO.getUserId(), amount_after);
                return Response.ValidResponse(TransactionSuccess);
            }
            catch (Exception e){
                return Exception(TransactionFailed);
            }
        }
        else{
            return Response.LowBalance(TransactionLowBalance);
        }
        }
        else {
            return NotFound(AccountNotFound);
        }
    }

    @Override
    public Response transactionHistory(TransactionDTO transactionDTO) {
        Optional<String> optional = Optional.ofNullable(transactionDTO.getTransactionID());
        if (optional.isPresent()){
            try {
                Transactions transaction = transactionRepository.getByTransactionID(optional.get());
                if(transaction == null)
                    return NotFound(TransactionNotFound);
                return ValidResponse(transaction);
            }
            catch (Exception e)
            {
                return Exception(TransactionNotFound);
            }
        }
        Optional<Users> userOptional= Optional.ofNullable(userRepository.getByUserId(transactionDTO.getUserId()));
        if(userOptional.isPresent())
        {
            if(transactionDTO.getAftertimestamp()== null ){
                LocalDateTime aftertimestamp= LocalDateTime.of(1900,1,1,0,0,0);
                transactionDTO.setAftertimestamp(aftertimestamp);
            }
            if(transactionDTO.getBeforetimestamp()== null ){
                LocalDateTime beforetimestamp= LocalDateTime.now();
                transactionDTO.setAftertimestamp(beforetimestamp);
            }
            if(transactionDTO.getTransactionType()==null) {
                try {
                    List<Transactions> transactionsList = transactionRepository.getByUserIdTransaction(transactionDTO.getUserId(), transactionDTO.getAftertimestamp(), transactionDTO.getBeforetimestamp());
                    return ValidResponse(transactionsList);
                } catch (Exception e) {
                    return Exception(TransactionNotFound);
                }
            }
            else{
                try {
                    List<Transactions> transactionsList = transactionRepository.getByUserIdTransactionIDType(transactionDTO.getUserId(), transactionDTO.getAftertimestamp(), transactionDTO.getBeforetimestamp(),transactionDTO.getTransactionType().toString());
                    return ValidResponse(transactionsList);
                } catch (Exception e) {
                    return Exception(TransactionNotFound);
                }
            }
        }
        else {
            return NotFound(AccountNotFound);
        }
    }
}
