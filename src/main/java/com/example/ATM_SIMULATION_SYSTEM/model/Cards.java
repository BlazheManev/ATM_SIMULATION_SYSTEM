package com.example.ATM_SIMULATION_SYSTEM.model;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.time.LocalDate;
@Entity
@Table
public class Cards {
    @Id
    @SequenceGenerator(
            name="users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_sequence"
    )
    private Long id;
    private int user_id;
    private String cardNumber;
    private LocalDate date_created;
    private Float money;
    private String pin;

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Cards(int user_id, String cardNumber,Float money,String pin) {
        this.user_id = user_id;
        this.cardNumber = cardNumber;
        this.money=money;
        this.date_created = LocalDate.now();
        this.pin=pin;
    }

    public Cards() {

    }
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getDate_created() {
        return date_created;
    }

    public void setDate_created(LocalDate date_created) {
        this.date_created = date_created;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", cardNumber=" + cardNumber +
                ", date_created=" + date_created +
                '}';
    }
}
