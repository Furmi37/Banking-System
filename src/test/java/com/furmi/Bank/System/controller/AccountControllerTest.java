package com.furmi.Bank.System.controller;

import com.furmi.Bank.System.model.Account;
import com.furmi.Bank.System.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;
    private MockMvc mockMvc;
    Account account = new Account(null, "Monthy Python", "monthy@gmail.com","8933333321",1000,1234);

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }
    @Test
    void getAdminSettings() {
    }

    @Test
    void getHome() {
    }

    @Test
    void shouldGetAccountByEmail() {
        when
    }

    @Test
    void getAll() {
    }

    @Test
    void checkBalance() {
    }

    @Test
    void createAccount() {
    }

    @Test
    void withdrawMoney() {
    }

    @Test
    void depositMoney() {
    }

    @Test
    void changePin() {
    }

    @Test
    void deleteAccount() {
    }
}