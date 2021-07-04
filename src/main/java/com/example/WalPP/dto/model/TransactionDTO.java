package com.example.WalPP.dto.model;



import com.example.WalPP.model.TransactionType;

import java.time.LocalDateTime;

public class TransactionDTO {
    public Integer userId;
    public String transactionDetails;
    public String transactionID;
    public Float amount;
    public String partyName;
    public TransactionType transactionType;
    public LocalDateTime aftertimestamp;
    public LocalDateTime beforetimestamp;
    public LocalDateTime getAftertimestamp() {
        return aftertimestamp;
    }

    public void setAftertimestamp(LocalDateTime aftertimestamp) {
        this.aftertimestamp = aftertimestamp;
    }

    public LocalDateTime getBeforetimestamp() {
        return beforetimestamp;
    }

    public void setBeforetimestamp(LocalDateTime beforetimestamp) {
        this.beforetimestamp = beforetimestamp;
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(String transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }


}
