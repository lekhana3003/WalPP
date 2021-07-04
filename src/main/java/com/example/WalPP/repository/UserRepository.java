package com.example.WalPP.repository;

import com.example.WalPP.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//create table users( user_id int NOT NULL PRIMARY KEY AUTO_INCREMENT, intial_balance FLOAT, balance FLOAT NOT NULL);
//docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=root -u root -d -p 3306:3306 mysql:latest
///etc/init.d#
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query(value = "SELECT balance FROM users where user_id=:userId", nativeQuery = true)
    public Float getBalance(@Param("userId")Integer userId);
    @Query(value = "SELECT * FROM users where user_id=:userId", nativeQuery = true)
    public Users getByUserId(@Param("userId")Integer userId);
    @Modifying
    @Transactional
    @Query(value = "UPDATE users u set balance =?2 where u.user_id = ?1", nativeQuery = true)
    public void updateBalance(@Param("userId")Integer userId, @Param("balance")Float balance);

}
