package com.furmi.Bank.System.service;

import com.furmi.Bank.System.model.Account;
import com.furmi.Bank.System.repository.AccountRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface AccountService {

    Account saveAccount(Account account);
    Account getAccount (String username);
    List<Account> getAccounts();


}
