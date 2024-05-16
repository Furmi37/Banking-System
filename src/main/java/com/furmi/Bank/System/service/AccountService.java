package com.furmi.Bank.System.service;

import com.furmi.Bank.System.model.Account;
import com.furmi.Bank.System.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AccountService {

    AccountRepository accountRepository;

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccount(String email) {
        Account account = accountRepository.findAccountByEmail(email);
        if (account != null) {
            return account;
        } else {
            throw new RuntimeException("This account doesnt have a owner yet");
        }
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Account findAccountById(Long id) {
        return accountRepository.findAccountById(id);
    }

    public void deleteAccount(String email) {
        Account account = getAccount(email);
        accountRepository.delete(account);
    }
}
