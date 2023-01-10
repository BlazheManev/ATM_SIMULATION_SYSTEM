package com.example.ATM_SIMULATION_SYSTEM.controller;

import com.example.ATM_SIMULATION_SYSTEM.model.Cards;
import com.example.ATM_SIMULATION_SYSTEM.model.RegisteredUsers;
import com.example.ATM_SIMULATION_SYSTEM.service.CardsService;
import com.example.ATM_SIMULATION_SYSTEM.service.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@Controller
public class UserController {
    private final UsersService usersService;
    private final CardsService cardsService;
    @Autowired
    public UserController(UsersService usersService, CardsService cardsService) {
        this.usersService = usersService;
        this.cardsService = cardsService;
    }
    @GetMapping(value = "/register")
    public String registerPage(HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }
        return "register";
    }
    @PostMapping("/Users")
    public String getUsers(Model model, @RequestParam("name")String name, @RequestParam("surname") String surname, @RequestParam("dob") LocalDate dob, @RequestParam("email") String email, @RequestParam("password") String password, HttpSession session){
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        RegisteredUsers registeredUsers= new RegisteredUsers(name,surname,dob,email,passwordEncoder.encode(password));
        usersService.addNewUser(registeredUsers);
        List<RegisteredUsers> listUsers=usersService.getUsers();
        listUsers.removeIf(r -> r.getName().equals("Admin"));
        model.addAttribute("listUsers", listUsers);

        return "Users" ;
    }
    @GetMapping("/Users")
    public String getUser(Model model,  HttpSession session){
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }
        List<RegisteredUsers> listUsers=usersService.getUsers();
        listUsers.removeIf(r -> r.getName().equals("Admin"));
        model.addAttribute("listUsers", listUsers);
        return "Users" ;
    }
    @GetMapping("/DeleteUser")
    public String deleteUser(@RequestParam("userId")Long id){
        usersService.deleteUser(id);
        cardsService.deleteCardsByUserId(id);

        return "redirect:/Users";
    }





}
