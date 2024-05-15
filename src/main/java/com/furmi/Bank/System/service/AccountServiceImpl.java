package com.furmi.Bank.System.service;

import com.furmi.Bank.System.model.Account;
import com.furmi.Bank.System.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService{

    AccountRepository accountRepository;
    @Override
    public Account saveAccount(Account account) {
        log.info("Saving {} new account to database", account.getMyUser().getFullName());
        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(String username) {
        log.info("Fetching {} account", username);
        return accountRepository.findAccountByMyUserUsername(username);
    }

    @Override
    public List<Account> getAccounts() {
        log.info("Fetching all accounts");
        return accountRepository.findAll();
    }

    public Account deposit (String username, double amount){
        Account account = getAccount(username);
        if (account == null){
            throw new RuntimeException("Account not found");
        }
        account.setBalance(account.getBalance() + amount);
        log.info("You deposited {}, your balance is {}", amount, account.getBalance());
        return accountRepository.save(account);
    }

    public Account withdraw (String username, double amount){
        Account account = getAccount(username);
        if (account == null){
            throw new RuntimeException("Account not found");
        }
        if (account.getBalance() < amount){
            throw new RuntimeException("You dont have enough money to withdraw");
        }
        account.setBalance(account.getBalance() - amount);
        log.info("You withdraw {}, your balance is {}", amount, account.getBalance());
        return saveAccount(account);
    }

    public Account changePin (String username, int pin){
        Account account = getAccount(username);
        account.setPin(pin);
        return saveAccount(account);
    }

}
