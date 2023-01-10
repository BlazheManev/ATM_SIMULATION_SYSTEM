package com.example.ATM_SIMULATION_SYSTEM.service;

import com.example.ATM_SIMULATION_SYSTEM.model.RegisteredUsers;
import com.example.ATM_SIMULATION_SYSTEM.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<RegisteredUsers> getUsers() {
        return usersRepository.findAll();
    }

    public void addNewUser(RegisteredUsers user) {
        RegisteredUsers userByEmail = usersRepository.findRegisteredUsersByEmail(user.getEmail());
        if (userByEmail!=null) {
            throw new IllegalStateException("Email taken");
        }
        usersRepository.save(user);
    }

    public void deleteUser(Long id) {
        boolean exist=   usersRepository.existsById(id);
        if (!exist){
            throw new IllegalStateException("user with id "+ id + " not exist");
        }
        usersRepository.deleteById(id);
    }

    public RegisteredUsers login(String email,String password){
        System.out.println(email + " " + password);
        return usersRepository.findRegisteredUsersByEmailAndPassword(email,password);
    }


}
