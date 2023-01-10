package com.example.ATM_SIMULATION_SYSTEM.model;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table
public class Transaction {
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
    private String card_Number;
    private TypeOfTransaction typeOfTransaction;
    private Float  amount;
    private LocalDate date_created;

    public Transaction(int user_id, String card_Number, TypeOfTransaction typeOfTransaction, Float amount) {
        this.user_id = user_id;
        this.card_Number = card_Number;
        this.typeOfTransaction = typeOfTransaction;
        this.amount = amount;
        this.date_created=LocalDate.now();
    }

    public Transaction() {

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

    public String getCard_Number() {
        return card_Number;
    }

    public void setCard_Number(String card_Number) {
        this.card_Number = card_Number;
    }

    public TypeOfTransaction getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public void setTypeOfTransaction(TypeOfTransaction typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public LocalDate getDate_created() {
        return date_created;
    }

    public void setDate_created(LocalDate date_created) {
        this.date_created = date_created;
    }
}
