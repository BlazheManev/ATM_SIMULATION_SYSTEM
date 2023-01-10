package com.example.ATM_SIMULATION_SYSTEM.controller;

import com.example.ATM_SIMULATION_SYSTEM.model.RegisteredUsers;
import com.example.ATM_SIMULATION_SYSTEM.repository.UsersRepository;
import com.example.ATM_SIMULATION_SYSTEM.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@Controller
public class LoginController {
    private final UsersService UsersService;
    private final UsersRepository usersRepository;

    public LoginController(com.example.ATM_SIMULATION_SYSTEM.service.UsersService usersService,
                           UsersRepository usersRepository) {
        UsersService = usersService;
        this.usersRepository = usersRepository;
    }


    @GetMapping(value = "/login")
    public String loginPage(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/cards";
        }else if (session.getAttribute("admin") != null) {
            System.out.println("sadadssad");
            return "redirect:/register";
        }else {
            return "login";
        }
    }
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpSession session) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("admin");

        return "redirect:/login";

    }



    @PostMapping(value = "/homeUser")
    public String homeUsersPage( @RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        RegisteredUsers userByEmail = usersRepository.findRegisteredUsersByEmail(email);
        boolean isPasswordValid = passwordEncoder.matches(password, userByEmail.getPassword());
        if (Objects.equals(userByEmail.getEmail(), "Admin@admin.com" )&& isPasswordValid){
            model.addAttribute("admin", userByEmail);
            session.setAttribute("admin", userByEmail);
            return "redirect:/register";        }

        if (isPasswordValid) {
            model.addAttribute("user", userByEmail);
            session.setAttribute("user", userByEmail);
            return "homeUser";
        }
        return "login";
    }
    @GetMapping(value ="/homeUser")
    public String homePage(HttpSession session){
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "homeUser";
    }


}

