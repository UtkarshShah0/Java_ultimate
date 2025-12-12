package com.bank.controller;

import com.bank.dto.TransactionDto;
import com.bank.dto.TransferDto;
import com.bank.entity.Account;
import com.bank.entity.User;
import com.bank.service.AccountService;
import com.bank.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/app")
public class AppController {

    private final UserService userService;
    private final AccountService accountService;

    public AppController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }


    private User requireUser(HttpSession session) {
        Object uid = session.getAttribute("userId");
        String sid = session.getId();
        System.out.println("DEBUG: sessionId=" + sid + " session.userId=" + uid);
        if (uid == null) return null;
        try {
            Long id = (Long) uid;
            User u = userService.findById(id);
            System.out.println("DEBUG: loaded user=" + (u != null ? u.getEmail() + " id=" + u.getId() : "null"));
            return u;
        } catch (ClassCastException cce) {
            System.out.println("DEBUG: ClassCastException reading userId from session: " + cce.getMessage());
            return null;
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        User u = requireUser(session);
        if (u == null) return "redirect:/auth/login";
        Account acc = u.getAccount();
        model.addAttribute("user", u);
        model.addAttribute("account", acc);
        return "dashboard";
    }

    @GetMapping("/deposit")
    public String showDeposit(Model model, HttpSession session) {
        User u = requireUser(session);
        if (u == null) return "redirect:/auth/login";
        TransactionDto dto = new TransactionDto();
        if (u.getAccount() != null) dto.setAccountNumber(u.getAccount().getAccountNumber());
        model.addAttribute("txDto", dto);
        return "deposit";
    }

    @PostMapping("/deposit")
    public String deposit(@Valid @ModelAttribute("txDto") TransactionDto dto, BindingResult br, HttpSession session, Model model) {
        User u = requireUser(session);
        if (u == null) return "redirect:/auth/login";

        // debug info
        System.out.println("DEBUG deposit called: session.userId=" + session.getAttribute("userId") +
                " dto.accountNumber=" + dto.getAccountNumber() + " amount=" + dto.getAmount());

        if (br.hasErrors()) {
            // keep dto in model so user sees errors
            model.addAttribute("txDto", dto);
            return "deposit";
        }

        // double-check account belongs to logged-in user (safety)
        if (u.getAccount() == null || !u.getAccount().getAccountNumber().equals(dto.getAccountNumber())) {
            // user tried to act on a different account - redirect + message
            model.addAttribute("errorMessage", "Invalid account for logged-in user");
            return "error";
        }

        accountService.deposit(dto.getAccountNumber(), dto.getAmount());
        return "redirect:/app/dashboard";
    }

    @GetMapping("/withdraw")
    public String showWithdraw(Model model, HttpSession session) {
        User u = requireUser(session);
        if (u == null) return "redirect:/auth/login";
        TransactionDto dto = new TransactionDto();
        if (u.getAccount() != null) dto.setAccountNumber(u.getAccount().getAccountNumber());
        model.addAttribute("txDto", dto);
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdraw(@Valid @ModelAttribute("txDto") TransactionDto dto, BindingResult br, HttpSession session, Model model) {
        User u = requireUser(session);
        if (u == null) return "redirect:/auth/login";

        System.out.println("DEBUG withdraw called: session.userId=" + session.getAttribute("userId") +
                " dto.accountNumber=" + dto.getAccountNumber() + " amount=" + dto.getAmount());

        if (br.hasErrors()) {
            model.addAttribute("txDto", dto);
            return "withdraw";
        }

        if (u.getAccount() == null || !u.getAccount().getAccountNumber().equals(dto.getAccountNumber())) {
            model.addAttribute("errorMessage", "Invalid account for logged-in user");
            return "error";
        }

        accountService.withdraw(dto.getAccountNumber(), dto.getAmount());
        return "redirect:/app/dashboard";
    }

    @GetMapping("/transfer")
    public String showTransfer(Model model, HttpSession session) {
        User u = requireUser(session);
        if (u == null) return "redirect:/auth/login";
        TransferDto dto = new TransferDto();
        if (u.getAccount() != null) dto.setFromAccountNumber(u.getAccount().getAccountNumber());
        model.addAttribute("transferDto", dto);
        return "transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@Valid @ModelAttribute("transferDto") TransferDto dto, BindingResult br, HttpSession session, Model model) {
        User u = requireUser(session);
        if (u == null) return "redirect:/auth/login";

        System.out.println("DEBUG transfer called: session.userId=" + session.getAttribute("userId") +
                " from=" + dto.getFromAccountNumber() + " to=" + dto.getToAccountNumber() + " amount=" + dto.getAmount());

        if (br.hasErrors()) {
            model.addAttribute("transferDto", dto);
            return "transfer";
        }

        if (u.getAccount() == null || !u.getAccount().getAccountNumber().equals(dto.getFromAccountNumber())) {
            model.addAttribute("errorMessage", "Invalid source account for logged-in user");
            return "error";
        }

        accountService.transfer(dto.getFromAccountNumber(), dto.getToAccountNumber(), dto.getAmount());
        return "redirect:/app/dashboard";
    }
}
