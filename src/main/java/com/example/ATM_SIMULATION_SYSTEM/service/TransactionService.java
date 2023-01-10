package com.example.ATM_SIMULATION_SYSTEM.service;

import com.example.ATM_SIMULATION_SYSTEM.model.Transaction;
import com.example.ATM_SIMULATION_SYSTEM.repository.CardsRepositoty;
import com.example.ATM_SIMULATION_SYSTEM.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
private  final TransactionRepository transactionRepository;
    private final CardsRepositoty cardsRepositoty;

    public TransactionService(TransactionRepository transactionRepository,
                              CardsRepositoty cardsRepositoty) {
        this.transactionRepository = transactionRepository;
        this.cardsRepositoty = cardsRepositoty;
    }


    public List<Transaction> getTransactionByTheUser(int user_id){
       return   transactionRepository.findTransactionByUser_id(user_id);
    }
    public void addTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }
    public List<Transaction> getTransactionByCard(String cardNumber){
        return transactionRepository.findTransactionByCard_Number(cardNumber);
    }
}
