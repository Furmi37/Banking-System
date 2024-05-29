package com.furmi.Bank.System.service;

import com.furmi.Bank.System.model.Account;
import com.furmi.Bank.System.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountService accountService;

    @Test
    void shouldCreateAccountWhenCallCreateAccount() {
        //given
        Account account = new Account(1,"Monthy Python", "monthy@gmail.com", "867349934", 1000.0, 2345);
        when(accountRepository.save(account)).thenReturn(account);
        //when
        Account save = accountRepository.save(account);
        //then
        assertEquals(account,save);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void shouldGetAccountWhenCallGetAccount() {
        //given
        String email= "monthy@gmail.com";
        Account account = new Account(1,"Monthy Python", email, "867349934", 1000.0, 2345);
        when(accountRepository.findAccountByEmail(email)).thenReturn(account);
        //when
        Account account1 = accountService.getAccount(email);
        //then
        assertEquals(account,account1);
        verify(accountRepository, times(1)).findAccountByEmail(email);

    }
    @Test
    void shouldNotGetAnyAccountsWhenCallGetAccounts(){
        //given
        //when
        List<Account> accounts = accountService.getAccounts();
        //then
        assertEquals(0,accounts.size());
    }

    @Test
    void shouldGetTwoAccountsWhenCallGetAccounts() {
        //given
        Account account1 = new Account(1,"Monthy Python", "monthy@gmail.com", "867349934", 1000.0, 2345);
        Account account2 = new Account(2,"Susan Wright", "susan@gmail.com", "546345654364", 2000.0, 1234);
        List<Account> accounts = List.of(account1,account2);
        when(accountRepository.findAll()).thenReturn(accounts);
        //when
        List<Account> accountsGet = accountService.getAccounts();
        //then
        assertEquals(accounts,accountsGet);
        verify(accountRepository,times(1)).findAll();
    }

    @Test
    void shouldFindAccountByIdWhenCallFindById() {
        //given
        long id = 1;
        Account account = new Account(id,"Monthy Python", "monthy@gmail.com", "867349934", 1000.0, 2345);
        when(accountRepository.findAccountById(id)).thenReturn(account);
        //when
        Account accountById = accountService.findAccountById(id);
        //then
        assertEquals(account,accountById);
        verify(accountRepository,times(1)).findAccountById(id);
    }

    @Test
    void shouldDeleteAccountWhenCallDeleteAccount() {
        //given
        String email= "monthy@gmail.com";
        Account account = new Account(1,"Monthy Python", email, "867349934", 1000.0, 2345);
        when(accountRepository.findAccountByEmail(email)).thenReturn(account);
        //when
        accountService.deleteAccount(email);
        //then
        verify(accountRepository,times(1)).delete(account);
    }
}