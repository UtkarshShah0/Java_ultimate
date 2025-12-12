package com.bank.service;

import com.bank.entity.Account;
import com.bank.entity.User;
import com.bank.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final AccountService accountService;

    public UserService(UserRepository userRepo, AccountService accountService) {
        this.userRepo = userRepo;
        this.accountService = accountService;
    }

    @Transactional
    public User registerUser(String name, String email, String phone, double initialBalance, String password) {
        if (userRepo.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }
        if (userRepo.findByPhone(phone).isPresent()) {
            throw new IllegalArgumentException("Phone already registered");
        }
        Account acc = accountService.createAccount(initialBalance);
        User u = new User(name, email, phone, acc, password); // password stored plain
        return userRepo.save(u);
    }

    public User findByEmailOrPhone(String login) {
        return userRepo.findByEmail(login).or(() -> userRepo.findByPhone(login)).orElse(null);
    }

    public User findById(Long id) {
        return userRepo.findById(id).orElse(null);
    }


    public User authenticate(String login, String rawPassword) {
        User u = findByEmailOrPhone(login);
        if (u == null) return null;
        if (u.getPassword() != null && u.getPassword().equals(rawPassword)) return u;
        return null;
    }
}
