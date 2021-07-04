package com.example.WalPP.dto.request;

import java.time.LocalDateTime;

public class TransactionHistoryRequest {
    public Integer userId;
    public String transactionID;
    private LocalDateTime aftertimestamp;
    private LocalDateTime beforetimestamp;
    private String transactionType;

    public LocalDateTime getAftertimestamp() {
        return aftertimestamp;
    }

    public LocalDateTime getBeforetimestamp() {
        return beforetimestamp;
    }


    public Integer getUserId() {
        return userId;
    }

    public String getTransactionID() {
        return transactionID;
    }



    public String getTransactionType() {
        return transactionType;
    }

}
