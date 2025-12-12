package com.bank.service;

import com.bank.entity.Account;
import com.bank.entity.Transaction;
import com.bank.entity.TransactionType;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AccountService {

    private final AccountRepository accountRepo;
    private final TransactionRepository txRepo;

    public AccountService(AccountRepository accountRepo, TransactionRepository txRepo) {
        this.accountRepo = accountRepo;
        this.txRepo = txRepo;
    }

    public Account register(Account account) {
        if (accountRepo.existsByAccountNumber(account.getAccountNumber())) {
            throw new IllegalArgumentException("Account number already exists");
        }
        if (account.getBalance() < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        return accountRepo.save(account);
    }

    @Transactional
    public double deposit(Integer accountNumber, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive");
        Account acc = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        acc.setBalance(acc.getBalance() + amount);
        accountRepo.save(acc);

        Transaction tx = new Transaction();
        tx.setAccount(acc);
        tx.setAmount(amount);
        tx.setType(TransactionType.DEPOSIT);
        tx.setTimestamp(LocalDateTime.now());
        tx.setDetails("Deposit via web");
        txRepo.save(tx);

        return acc.getBalance();
    }

    public double withdraw(Integer accountNumber, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive");
        Account acc = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        if (acc.getBalance() < amount) throw new IllegalArgumentException("Insufficient balance");
        acc.setBalance(acc.getBalance() - amount);
        accountRepo.save(acc);

        Transaction tx = new Transaction();
        tx.setAccount(acc);
        tx.setAmount(amount);
        tx.setType(TransactionType.WITHDRAWAL);
        tx.setTimestamp(LocalDateTime.now());
        tx.setDetails("Withdrawal via web");
        txRepo.save(tx);

        return acc.getBalance();
    }

    public double balance(Integer accountNumber) {
        Account acc = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        return acc.getBalance();
    }
}
