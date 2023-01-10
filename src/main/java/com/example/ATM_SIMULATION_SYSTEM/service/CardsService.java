package com.example.ATM_SIMULATION_SYSTEM.service;

import com.example.ATM_SIMULATION_SYSTEM.model.Cards;
import com.example.ATM_SIMULATION_SYSTEM.repository.CardsRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardsService {
    private final CardsRepositoty cardsRepositoty;
    @Autowired
    public CardsService(CardsRepositoty cardsRepositoty) {
        this.cardsRepositoty = cardsRepositoty;
    }
    public List<Cards> getAllCardsOfUser(int userId){
        List<Cards> cards=cardsRepositoty.findCardsByUser_id(userId);
         return cards;
    }
    public Cards getoneCard(String cardNumber){
        return cardsRepositoty.findCardsByCardNumber(cardNumber);
    }
    public void withDrawOrDeposit(String cardNumber, float amount) {
        cardsRepositoty.updateCardBalance(cardNumber, amount);
    }
    public void addCard(Cards card){
        cardsRepositoty.save(card);
    }
    public List<Cards> getAllCards(){
        return cardsRepositoty.findAll();
    }
    public void deleteCardsByUserId(Long userId){
         cardsRepositoty.deleteCardsByUserId(userId);
    }

    public void updateCardPin(String pin,String cardNumber){
        cardsRepositoty.updateCardPin(pin,cardNumber);
    }
    public void deleteCard(Long id){
        cardsRepositoty.deleteById(id);
    }

}
