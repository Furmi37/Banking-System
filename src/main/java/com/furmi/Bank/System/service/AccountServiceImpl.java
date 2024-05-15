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
        log.info("Fetaching {} account", username);
        return accountRepository.findAccountByMyUserUsername(username);
    }

    @Override
    public List<Account> getAccounts() {
        log.info("Fetching all accounts");
        return accountRepository.findAll();
    }
}
