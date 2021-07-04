package com.example.WalPP.repository;


import com.example.WalPP.model.Transactions;
import com.example.WalPP.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Integer> {
    @Query(value = "SELECT balance FROM users where user_id=:userId", nativeQuery = true)
    public Float getBalance(@Param("userId")Integer userId);
    @Query(value = "SELECT * FROM users where user_id=:userId", nativeQuery = true)
    public Users getByUserId(@Param("userId")Integer userId);

    @Query(value = "SELECT * FROM transactions WHERE timestamp >= :aftertimestamp AND timestamp <  :beforetimestamp AND user_id=:userId", nativeQuery = true)
    public List<Transactions> getByUserIdTransaction(@Param("userId")Integer userId, @Param("aftertimestamp") LocalDateTime afterTimestamp, @Param("beforetimestamp") LocalDateTime beforeTimestamp);

    @Query(value = "SELECT * FROM transactions WHERE timestamp >= :aftertimestamp AND timestamp <  :beforetimestamp AND user_id=:userId and transaction_type=:transactionType", nativeQuery = true)
    public List<Transactions> getByUserIdTransactionIDType(@Param("userId")Integer userId, @Param("aftertimestamp") LocalDateTime afterTimestamp, @Param("beforetimestamp") LocalDateTime beforeTimestamp, @Param("transactionType") String transactionType );

    @Query(value = "SELECT * FROM transactions WHERE transactionID=:transactionID", nativeQuery = true)
    public Transactions getByTransactionID(@Param("transactionID")String transactionID);
}

