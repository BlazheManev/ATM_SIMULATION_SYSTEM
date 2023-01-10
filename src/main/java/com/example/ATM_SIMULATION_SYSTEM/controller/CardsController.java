package com.example.ATM_SIMULATION_SYSTEM.controller;

import com.example.ATM_SIMULATION_SYSTEM.config.AESConfig;
import com.example.ATM_SIMULATION_SYSTEM.model.Cards;
import com.example.ATM_SIMULATION_SYSTEM.model.RegisteredUsers;
import com.example.ATM_SIMULATION_SYSTEM.repository.CardsRepositoty;
import com.example.ATM_SIMULATION_SYSTEM.repository.UsersRepository;
import com.example.ATM_SIMULATION_SYSTEM.service.CardsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CardsController {
    private final CardsService cardsService;
    private final UsersRepository usersRepository;
    private final CardsRepositoty cardsRepositoty;

    @Autowired
    public CardsController(CardsService cardsService,
                           UsersRepository usersRepository,
                           CardsRepositoty cardsRepositoty) {
        this.cardsService = cardsService;
        this.usersRepository = usersRepository;
        this.cardsRepositoty = cardsRepositoty;
    }

    @GetMapping(value = "/cards")
    public String getAllCards(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        Object u = session.getAttribute("user");
        RegisteredUsers user = (RegisteredUsers) u;
        List<Cards> cardsList = cardsService.getAllCardsOfUser(Math.toIntExact(user.getId()));
        AESConfig config = AESConfig.getInstance();
        for (Cards c : cardsList) {
            try {
                c.setCardNumber(config.decrypt(c.getCardNumber()));

            } catch (Exception e) {
            }
        }
        model.addAttribute("cardsList", cardsList);
        return "cards";
    }

    @GetMapping("/AddCard")
    public String Card(@RequestParam("UserId") Long id, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }
        return "AddCard";
    }

    @PostMapping("/AddCard")
    public String addCard(@RequestParam("cardNumber") String cardNumber,
                          @RequestParam("pin") String pin,
                          @RequestParam("UserId") Long UserId, Model model, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }
        AESConfig config = AESConfig.getInstance();
        List<Cards> cardsList = cardsService.getAllCards();
        for (Cards card : cardsList) {

            try {
                if (config.decrypt(card.getCardNumber()).equals(cardNumber)) {
                    return "/card-error";
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {
            cardsService.addCard(new Cards(Math.toIntExact(UserId), config.encrypt(cardNumber), 0F, config.encrypt(pin)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "AddCard";
    }

    @GetMapping("/Change")
    public String ChangeCard(@RequestParam("userId") Long id, HttpSession session, Model model) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }
        List<Cards> cardsList = cardsService.getAllCardsOfUser(Math.toIntExact(id));
        AESConfig config = AESConfig.getInstance();

        for (Cards c : cardsList) {
            try {
                c.setCardNumber(config.decrypt(c.getCardNumber()));

            } catch (Exception e) {
            }

        }
        model.addAttribute("cardsList", cardsList);
        return "Change";
    }

    @PostMapping("/changed")
    public String ChangeCardPost(@RequestParam("CardNumber") String cardNumber, @RequestParam("oldPin") String oldPin, @RequestParam("newPin") String newPin, HttpSession session, Model model) throws Exception {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }
        AESConfig config = AESConfig.getInstance();
        Cards cards= null;
            cards = cardsService.getoneCard(config.encrypt(cardNumber));
        System.out.println(cards);
            if (config.decrypt(cards.getPin()).equals(oldPin)) {
                cardsService.updateCardPin( config.encrypt(newPin),config.encrypt(cardNumber));
                System.out.println(config.encrypt(cardNumber));
                return "redirect:/Users";
            }else {
                return "ChangeError";
            }
    }
    @GetMapping("/DeleteCard")
    public String deleteUser(@RequestParam("userId")Long id){
        cardsService.deleteCard(id);
        return "redirect:/Users";
    }
}




