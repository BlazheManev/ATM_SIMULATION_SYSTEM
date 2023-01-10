package com.example.ATM_SIMULATION_SYSTEM.repository;

import com.example.ATM_SIMULATION_SYSTEM.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    @Query("SELECT u FROM Transaction u where u.user_id = :userId")
    List<Transaction> findTransactionByUser_id(@Param("userId")int userId);
    @Query("SELECT u FROM Transaction u where u.card_Number = :cardNumber")
    List<Transaction> findTransactionByCard_Number(@Param("cardNumber")String cardNumber);

}
