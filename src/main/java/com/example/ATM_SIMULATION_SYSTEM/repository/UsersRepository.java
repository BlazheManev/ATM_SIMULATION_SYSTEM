package com.example.ATM_SIMULATION_SYSTEM.repository;

import com.example.ATM_SIMULATION_SYSTEM.model.RegisteredUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsersRepository  extends JpaRepository<RegisteredUsers,Long> {

    @Query ("SELECT S FROM RegisteredUsers S WHERE S.email=?1")
    RegisteredUsers findRegisteredUsersByEmail(String email);

    @Query("SELECT u FROM RegisteredUsers u WHERE u.email = :email AND u.password = :password")
    RegisteredUsers findRegisteredUsersByEmailAndPassword(@Param("email") String email, @Param("password") String password);
   }
