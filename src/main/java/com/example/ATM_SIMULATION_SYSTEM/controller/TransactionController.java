package com.example.ATM_SIMULATION_SYSTEM.controller;

import com.example.ATM_SIMULATION_SYSTEM.config.AESConfig;
import com.example.ATM_SIMULATION_SYSTEM.model.Cards;
import com.example.ATM_SIMULATION_SYSTEM.model.RegisteredUsers;
import com.example.ATM_SIMULATION_SYSTEM.model.Transaction;
import com.example.ATM_SIMULATION_SYSTEM.model.TypeOfTransaction;
import com.example.ATM_SIMULATION_SYSTEM.service.CardsService;
import com.example.ATM_SIMULATION_SYSTEM.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class TransactionController {

    private final TransactionService transactionService;
    private final CardsService cardsService;

    @Autowired
    public TransactionController(TransactionService transactionService, CardsService cardsService) {
        this.transactionService = transactionService;
        this.cardsService = cardsService;
    }

    @GetMapping("/transactions")
    public String getTransaction(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        Object u = session.getAttribute("user");
        RegisteredUsers user = (RegisteredUsers) u;
        List<Transaction> transactionLists = transactionService.getTransactionByTheUser(Math.toIntExact(user.getId()));
        model.addAttribute("transactionLists", transactionLists);
        return "transactions";
    }

    @GetMapping(value = "/transactionByCard")
    public String getCard(Model model, HttpSession session, @RequestParam("cardNumber") Long cardId) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        Object u = session.getAttribute("user");
        List<Transaction> transactionLists = transactionService.getTransactionByCard(String.valueOf(cardId));
        model.addAttribute("transactionLists", transactionLists);
        return "transactionByCard";
    }

    @GetMapping("/withdraw")
    public String WithDraw(@RequestParam("cardNumber") Long cardId, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String handleTransaction(@RequestParam("cardNumber") Long cardId, @RequestParam("amount") float amount, @RequestParam("transactionType") String transactionType, Model model, HttpSession session) throws Exception {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        AESConfig config= AESConfig.getInstance();
        Object u = session.getAttribute("user");
        RegisteredUsers user = (RegisteredUsers) u;
        if (Objects.equals(transactionType, "deposit")) {
            Cards cards = cardsService.getoneCard(config.encrypt(String.valueOf(cardId)));
            float money = cards.getMoney() + amount;
            cardsService.withDrawOrDeposit(config.encrypt(String.valueOf(cardId)), money);
            Transaction transaction = new Transaction(Math.toIntExact(user.getId()), cardId.toString(), TypeOfTransaction.DEPOSIT, amount);
            transactionService.addTransaction(transaction);
            return "transaction-confirmation";
        } else if (Objects.equals(transactionType, "withdraw")) {
            Cards cards = cardsService.getoneCard(config.encrypt(String.valueOf(cardId)));
            float money = cards.getMoney() - amount;
            if (money> -400F) {
                cardsService.withDrawOrDeposit(config.encrypt(String.valueOf(cardId)), money);
                Transaction transaction = new Transaction(Math.toIntExact(user.getId()), cardId.toString(), TypeOfTransaction.WITHDRAWAL, amount);
                transactionService.addTransaction(transaction);
                return "transaction-confirmation";
            }else{
                return "transaction-error";
            }
        }
        return "cards";
    }


}
