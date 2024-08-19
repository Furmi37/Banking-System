package com.furmi.Bank.System.controller;

import com.furmi.Bank.System.model.Account;
import com.furmi.Bank.System.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;
    private MockMvc mockMvc;
    Account account = new Account(null, "Monthy Python", "monthy@gmail.com","8933333321",1000,1234);
    Account account1 = new Account(null, "Barrack Obama", "barrack@gmail.com","79822333321",4000,4321);


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
    void shouldGetAccountByEmail() throws Exception {

        when(accountService.getAccount("monthy@gmail.com")).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/account/monthy@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.accountOwner").value("Monthy Python"))
                .andExpect(jsonPath("$.email").value("monthy@gmail.com"))
                .andExpect(jsonPath("$.accountNumber").value("8933333321"))
                .andExpect(jsonPath("$.balance").value(1000))
                .andExpect(jsonPath("$.pin").value(1234))
                .andReturn();


    }

    @Test
    void getAll() throws Exception {
        List<Account> list = List.of(account,account1);
        when(accountService.getAccounts()).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/account/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].accountOwner").value("Monthy Python"))
                .andExpect(jsonPath("$[0].email").value("monthy@gmail.com"))
                .andExpect(jsonPath("$[0].accountNumber").value("8933333321"))
                .andExpect(jsonPath("$[0].balance").value(1000))
                .andExpect(jsonPath("$[0].pin").value(1234))

                .andExpect(jsonPath("$[1].accountOwner").value("Barrack Obama"))
                .andExpect(jsonPath("$[1].email").value("barrack@gmail.com"))
                .andExpect(jsonPath("$[1].accountNumber").value("79822333321"))
                .andExpect(jsonPath("$[1].balance").value(4000))
                .andExpect(jsonPath("$[1].pin").value(4321))
                .andReturn();
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