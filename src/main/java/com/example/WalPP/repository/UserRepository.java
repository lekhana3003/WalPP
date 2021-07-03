package com.example.WalPP.repository;

import com.example.WalPP.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query(value = "SELECT balance FROM users where user_id=:userId", nativeQuery = true)
    public Float getBalance(@Param("userId")Integer userId);
    @Query(value = "SELECT * FROM users where user_id=:userId", nativeQuery = true)
    public Users getByUserId(@Param("userId")Integer userId);

}
