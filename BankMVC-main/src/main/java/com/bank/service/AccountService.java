package com.bank.service;

import com.bank.entity.Account;
import com.bank.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class AccountService {

    private final AccountRepository accountRepo;
    private final Random rnd = new SecureRandom();

    public AccountService(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    
    public synchronized Long generateUniqueAccountNumber() {
        for (int i = 0; i < 10; i++) {
            long candidate = 1000000000L + Math.abs(rnd.nextLong()) % 9000000000L;
            if (!accountRepo.existsByAccountNumber(candidate)) {
                return candidate;
            }
        }
        long base = System.currentTimeMillis() % 9000000000L + 1000000000L;
        while (accountRepo.existsByAccountNumber(base)) { base++; }
        return base;
    }

    @Transactional
    public Account createAccount(double initialBalance) {
        Long accNo = generateUniqueAccountNumber();
        Account acc = new Account(accNo, initialBalance);
        return accountRepo.save(acc);
    }

    @Transactional
    public double deposit(Long accountNumber, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive");
        Account acc = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        acc.setBalance(acc.getBalance() + amount);
        accountRepo.save(acc);
        return acc.getBalance();
    }

    @Transactional
    public double withdraw(Long accountNumber, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive");
        Account acc = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        if (acc.getBalance() < amount) throw new IllegalArgumentException("Insufficient balance");
        acc.setBalance(acc.getBalance() - amount);
        accountRepo.save(acc);
        return acc.getBalance();
    }

    @Transactional
    public void transfer(Long fromAccNo, Long toAccNo, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Transfer amount must be positive");
        if (fromAccNo.equals(toAccNo)) throw new IllegalArgumentException("Cannot transfer to same account");

        Account from = accountRepo.findByAccountNumber(fromAccNo)
                .orElseThrow(() -> new IllegalArgumentException("Source account not found"));
        Account to = accountRepo.findByAccountNumber(toAccNo)
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found"));
        if (from.getBalance() < amount) throw new IllegalArgumentException("Insufficient balance");
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        accountRepo.save(from);
        accountRepo.save(to);
    }

    public Account findByAccountNumber(Long accountNumber) {
        return accountRepo.findByAccountNumber(accountNumber).orElse(null);
    }
}
