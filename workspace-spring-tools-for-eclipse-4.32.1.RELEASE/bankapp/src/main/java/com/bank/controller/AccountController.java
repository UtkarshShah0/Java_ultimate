package com.bank.controller;

import com.bank.dto.AccountRegisterDto;
import com.bank.dto.TransactionDto;
import com.bank.entity.Account;
import com.bank.repository.TransactionRepository;
import com.bank.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final TransactionRepository txRepo;

    public AccountController(AccountService accountService, TransactionRepository txRepo) {
        this.accountService = accountService;
        this.txRepo = txRepo;
    }

    // Register form
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerDto", new AccountRegisterDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerDto") AccountRegisterDto dto,
                           BindingResult br) {
        if (br.hasErrors()) {
            return "register";
        }
        Account acc = new Account(dto.getAccountNumber(), dto.getAccountHolderName(), dto.getBalance());
        accountService.register(acc);
        return "redirect:/accounts/dashboard?acc=" + dto.getAccountNumber();
    }

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard(@RequestParam(name = "acc") Integer accNumber, Model model) {
        model.addAttribute("balance", accountService.balance(accNumber));
        model.addAttribute("accountNumber", accNumber);
        model.addAttribute("transactions", txRepo.findByAccount_AccountNumberOrderByTimestampDesc(accNumber));
        return "dashboard";
    }

    // Deposit
    @GetMapping("/deposit")
    public String depositForm(@RequestParam Integer acc, Model model) {
        TransactionDto dto = new TransactionDto();
        dto.setAccountNumber(acc);
        model.addAttribute("txDto", dto);
        return "deposit";
    }

    @PostMapping("/deposit")
    public String deposit(@Valid @ModelAttribute("txDto") TransactionDto dto, BindingResult br) {
        if (br.hasErrors()) return "deposit";
        accountService.deposit(dto.getAccountNumber(), dto.getAmount());
        return "redirect:/accounts/dashboard?acc=" + dto.getAccountNumber();
    }

    // Withdraw
    @GetMapping("/withdraw")
    public String withdrawForm(@RequestParam Integer acc, Model model) {
        TransactionDto dto = new TransactionDto();
        dto.setAccountNumber(acc);
        model.addAttribute("txDto", dto);
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdraw(@Valid @ModelAttribute("txDto") TransactionDto dto, BindingResult br) {
        if (br.hasErrors()) return "withdraw";
        accountService.withdraw(dto.getAccountNumber(), dto.getAmount());
        return "redirect:/accounts/dashboard?acc=" + dto.getAccountNumber();
    }
}
