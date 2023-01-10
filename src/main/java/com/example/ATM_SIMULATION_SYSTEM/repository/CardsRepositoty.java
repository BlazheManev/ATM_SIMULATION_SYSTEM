package com.example.ATM_SIMULATION_SYSTEM.repository;

import com.example.ATM_SIMULATION_SYSTEM.model.Cards;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CardsRepositoty extends JpaRepository<Cards, Long> {
    @Query("SELECT u FROM Cards u WHERE u.user_id = :userId")
    List<Cards> findCardsByUser_id(@Param("userId") int userId);

    @Query("SELECT u FROM Cards u WHERE u.cardNumber = :cardNumber")
    Cards findCardsByCardNumber(@Param("cardNumber") String cardNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Cards SET money = :amount WHERE cardNumber = :cardNumber")
    void updateCardBalance(@Param("cardNumber") String cardNumber, @Param("amount") float amount);

    @Transactional
    @Modifying
    @Query("DELETE FROM Cards u WHERE u.user_id = :userId")
    void deleteCardsByUserId(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE Cards SET pin = :pin Where cardNumber = :cardNumber")
    void updateCardPin( @Param("pin") String pin,@Param("cardNumber") String cardNumber);
}
