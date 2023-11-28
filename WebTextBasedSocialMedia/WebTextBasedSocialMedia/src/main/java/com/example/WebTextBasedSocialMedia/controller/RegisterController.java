package com.example.WebTextBasedSocialMedia.controller;

import com.example.WebTextBasedSocialMedia.entity.Account;
import com.example.WebTextBasedSocialMedia.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegisterController {
    private final AccountService accountService;

    public RegisterController(AccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping("/register")
    public String getRegisterForm(Model model) {

        Account account = new Account();
        System.out.println("Before saving account: " + account);
        model.addAttribute("account", account);
        return "register";
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody Account account) {
        accountService.save(account);
        String body = "Everything is ok";
        return ResponseEntity.ok(body);
    }
}
