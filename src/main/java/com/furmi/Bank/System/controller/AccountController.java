package com.furmi.Bank.System.controller;

import com.furmi.Bank.System.model.Account;
import com.furmi.Bank.System.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@Slf4j
@AllArgsConstructor
public class AccountController {
    AccountService accountService;

    @GetMapping("/{email}")
    public Account getById (@PathVariable String username){
        log.info("Getting {} account", username);
        return accountService.getAccount(username);
    }

    @GetMapping("/all")
    public List<Account> getAll (){
        return accountService.getAccounts();
    }

    @GetMapping("/balance")
    public Double checkBalance (@RequestParam String email){
        log.info("Used check balance function");
        Account account = accountService.getAccount(email);
        return account.getBalance();
    }
    @PostMapping("/create")
    public Account createAccount (@RequestBody Account account){
        log.info("Created account");
        return accountService.saveAccount(account);
    }

    @PutMapping("/withdraw")
    public Account withdrawMoney (@RequestParam String email, double amount){
        log.info("Used withdraw withdraw");
        Account account = accountService.getAccount(email);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        if (account.getBalance() < amount) {
            throw new RuntimeException("You dont have enough money to withdraw");
        }
        account.setBalance(account.getBalance() - amount);
        return accountService.saveAccount(account);
    }

    @PutMapping("/deposit")
    public Account depositMoney (@RequestParam String email, double amount){
        log.info("Used deposit function");
        Account account = accountService.getAccount(email);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        account.setBalance(account.getBalance() + amount);
        return accountService.saveAccount(account);
    }

    @PutMapping("/pin")
    public Account changePin (@RequestParam String email, int newPin){
        Account account = accountService.getAccount(email);
        account.setPin(newPin);
        log.info("Pin to {} account has been changed", account.getAccountOwner());
        return accountService.saveAccount(account);
    }
}
