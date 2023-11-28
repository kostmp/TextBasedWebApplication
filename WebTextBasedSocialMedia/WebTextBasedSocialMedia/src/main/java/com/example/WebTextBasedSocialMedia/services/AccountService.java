package com.example.WebTextBasedSocialMedia.services;

import com.example.WebTextBasedSocialMedia.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.WebTextBasedSocialMedia.repository.AccountRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public AccountService(AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }
    public void save(Account account) {
        System.out.println("Before saving account: " + account);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        System.out.println("After saving account");

    }
    public Optional<Account> findByEmailandPassword(String email, String password) {
        return accountRepository.findByEmailAndPassword(email, password);
    }
}