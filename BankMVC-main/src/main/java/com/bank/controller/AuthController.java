package com.bank.controller;

import com.bank.dto.LoginDto;
import com.bank.dto.UserRegisterDto;
import com.bank.entity.User;
import com.bank.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    public AuthController(UserService userService) { this.userService = userService; }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("registerDto", new UserRegisterDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerDto") UserRegisterDto dto,
                           BindingResult br, Model model, HttpSession session,
                           RedirectAttributes redirectAttrs) {
        if (br.hasErrors()) return "register";
        try {
            User user = userService.registerUser(dto.getName(), dto.getEmail(), dto.getPhone(), dto.getInitialBalance(), dto.getPassword());
            session.setAttribute("userId", user.getId());
            redirectAttrs.addFlashAttribute("success", "Registration successful. Welcome, " + user.getName() + "!");
            return "redirect:/app/dashboard";
        } catch (IllegalArgumentException ex) {
            br.reject("register", ex.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDto") LoginDto dto, BindingResult br,
                        HttpSession session, RedirectAttributes redirectAttrs) {
        if (br.hasErrors()) return "login";
        User user = userService.authenticate(dto.getLogin(), dto.getPassword());
        if (user == null) {
            br.rejectValue("password", "invalid", "Invalid credentials");
            return "login";
        }
        session.setAttribute("userId", user.getId());
        redirectAttrs.addFlashAttribute("success", "Login successful. Welcome back!");
        return "redirect:/app/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();

        // Delete JSESSIONID cookie
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0); // deletes cookie
        response.addCookie(cookie);

        return "redirect:/auth/login";
    }
}
